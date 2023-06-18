package com.grupo4.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.grupo4.config.DatabaseStorage;
import com.grupo4.models.CartProduct;

public class HistoryController {
    private static List<HistoryController> instance = null;

    private List<CartProduct> products;
    private String client_id;

    private HistoryController(String client_id) {
        this.products = DatabaseStorage.creatingHistoryList(client_id);
        this.client_id = client_id;
    }

    public static synchronized HistoryController getInstance(String client_id) {
        if (instance == null) {
            instance = new ArrayList<>();
        }

        Optional<HistoryController> hOptional = instance.stream()
                .filter(value -> value.getClient_id().equals(client_id))
                .findFirst();

        if (!hOptional.isPresent()) {
            HistoryController historyController = new HistoryController(client_id);
            instance.add(historyController);

            return historyController;
        }

        return hOptional.get();
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

    public String getClient_id() {
        return client_id;
    }

    public static void setInstance(List<HistoryController> instance) {
        HistoryController.instance = instance;
    }

    public void setProducts(List<CartProduct> products) {
        this.products = products;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    
}
