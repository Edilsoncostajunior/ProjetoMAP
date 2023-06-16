package com.grupo4.controllers;

import java.util.List;
import com.grupo4.config.DatabaseStorage;
import com.grupo4.models.CartProduct;

public class HistoryController {
    private List<CartProduct> products;
    private String client_id;

    public HistoryController(String client_id) {
        this.products = DatabaseStorage.creatingHistoryList(client_id);
        this.client_id = client_id;
    }

    public List<CartProduct> product_GET_ALL() {
        return products;
    }

    public CartProduct product_GET_BY_ID(String id) {
        return products.stream().filter(value -> value.getId().equals(id)).findFirst().get();
    }

    public void product_DELETE(String id) {
        products = products.stream().filter(value -> !value.getId().equals(id)).toList();
        DatabaseStorage.writtingHistoryFile(products, client_id);
    }
}
