package com.grupo4.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import com.grupo4.config.DatabaseStorage;
import com.grupo4.models.Product;

public class ProductController {
    private List<Product> products;

    public ProductController() {
        this.products = DatabaseStorage.creatingProductList();
    }

    public List<Product> product_GET_ALL() {
        return products;
    }

    public Product product_GET_BY_ID(String id) {
        return products.stream().filter(value -> value.getId().equals(id)).findFirst().get();
    }

    public Product client_GET_BY_NAME(String name) {
        return products.stream().filter(value -> value.getName().equals(name)).findFirst().get();
    }

    public void product_POST(String brand, String description, String category, String name, double price,
            int quantity) {
        String id = String.valueOf(products.stream()
        .mapToInt(p -> Integer.parseInt(p.getId()))
        .max()
        .orElse(0) + 1);
        products.add(new Product(id, brand, description, category, name, price, quantity));
        DatabaseStorage.writtingProductFile(products);
    }

    public void product_PATCH(Map<String, String> changes) {
        int product_index = IntStream.range(0, products.size())
                .filter(i -> products.get(i).getId().equals(changes.get("id")))
                .findFirst()
                .orElse(-1);

        if (product_index == -1) {
            System.out.println("Erro: produto não cadastrada!");
            return;
        }

        products.get(product_index).update(changes);
        DatabaseStorage.writtingProductFile(products);
    }

    public void product_DELETE(String id) {
        products = products.stream().filter(value -> !value.getId().equals(id)).toList();
        DatabaseStorage.writtingProductFile(products);
    }
}
