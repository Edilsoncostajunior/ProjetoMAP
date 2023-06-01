package com.grupo4.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import com.grupo4.config.DatabaseStorage;
import com.grupo4.models.Product;

public class ProductController {
    private List<Product> products;

    public ProductController(String store_id) {
        this.products = DatabaseStorage.creatingProductList(store_id);
    }

    public List<Product> read() {
        return products;
    }

    public void write(String brand, String description, String category, String name, double price,
            int quantity) {
        String id = "" + Integer.parseInt(
                products.stream().max((comp1, comp2) -> comp1.getId().compareTo(comp2.getId())).get().getId()) + 1;
        products.add(new Product(id, brand, description, category, name, price, quantity));
        DatabaseStorage.writtingProductFile(products);
    }

    public void update(Map<String, String> changes) {
        int client_index = IntStream.range(0, products.size())
                .filter(i -> products.get(i).getId().equals(changes.get("id")))
                .findFirst()
                .orElse(-1);

        if (client_index == -1) {
            System.out.println("Erro: produto não cadastrada!");
            return;
        }

        products.get(client_index).update(changes);
        DatabaseStorage.writtingProductFile(products);
    }

    public void remove(String id) {
        products = products.stream().filter(value -> !value.getId().equals(id)).toList();
        DatabaseStorage.writtingProductFile(products);
    }
}
