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

    public Product product_GET_BY_NAME(String name) {
        return products.stream().filter(value -> value.getName().equals(name)).findFirst().get();
    }

    public String product_POST(String brand, String description, String category, String name, double price,
            int quantity) {
        String id = products.size() == 0 ? "0" : String.valueOf(products.stream()
        .mapToInt(p -> Integer.parseInt(p.getId()))
        .max()
        .orElse(0) + 1);
        products.add(new Product(id, brand, description, category, name, price, quantity));
        DatabaseStorage.writtingProductFile(products);

        return "O novo Cliente foi adicionado com sucesso!";

    }

    public String product_PATCH(Map<String, String> changes) {

        int product_index = IntStream.range(0, products.size())
                .filter(i -> products.get(i).getId().equals(changes.get("id")))
                .findFirst()
                .orElse(-1);

        if (product_index == -1) {
            return "Erro: produto não cadastrada!";
        }

        products.get(product_index).update(changes);
        DatabaseStorage.writtingProductFile(products);
        return "Finalizado com sucesso as alterações no produto!";
    }

    public String product_DELETE(String id) {
        products = products.stream().filter(value -> !value.getId().equals(id)).toList();
        DatabaseStorage.writtingProductFile(products);
        return "O produto foi deletado com sucesso!";
    }
}
