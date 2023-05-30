package com.grupo4.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import com.grupo4.config.DatabaseStorage;
import com.grupo4.models.Product;

public class ProductController {
    private List<Product> stores;

    public ProductController(String store_id) {
        this.stores = DatabaseStorage.creatingProductList(store_id);
    }

    public List<Product> read() {
        return stores;
    }

    public void write(Product store) {
        stores.add(store);
        DatabaseStorage.writtingProductFile(stores);
    }

    public void update(Map<String, String> changes) {
        int client_index = IntStream.range(0, stores.size())
                .filter(i -> stores.get(i).getId().equals(changes.get("id")))
                .findFirst()
                .orElse(-1);

        if (client_index == -1) {
            System.out.println("Erro: produto não cadastrada!");
            return;
        }

        stores.get(client_index).valuesProduct(changes);
        DatabaseStorage.writtingProductFile(stores);
    }

    public void remove(String id) {
        stores = stores.stream().filter(value -> !value.getId().equals(id)).toList();
        DatabaseStorage.writtingProductFile(stores);
    }
}
