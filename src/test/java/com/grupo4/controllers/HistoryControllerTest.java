package com.grupo4.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.grupo4.models.CartProduct;
import com.grupo4.models.Product;
import com.grupo4.models.Store;

public class HistoryControllerTest {

    private HistoryController historyController;
    private List<CartProduct> products;

     @BeforeEach
     void setUp() {
        String id = "6";
        historyController = HistoryController.getInstance(id);
        products = new ArrayList<>();
        products.add(new CartProduct("6", "13", "15", "joy fragancy", "sadsadas", 2));
        products.add(new CartProduct("7", "13", "15", "joy fragancy", "sadsadas", 2));
        historyController.setProducts(products);
    }

    @Test
    void testProduct_DELETE() {
        int size = historyController.product_GET_ALL().size();
        String id = historyController.product_GET_ALL().stream().findFirst().get().getId();
        historyController.product_DELETE(id);
        List<CartProduct> products = historyController.product_GET_ALL();
        Assertions.assertTrue(products.size() < size);
        assertFalse(products.stream().anyMatch(product -> product.getId().equals(id)));
    }

    @Test
    void testProduct_GET_ALL() {
        List<CartProduct> retrievedProducts = historyController.product_GET_ALL();
        assertEquals(products, retrievedProducts);
    }

    @Test
    void testProduct_GET_BY_ID() {
        String id = historyController.product_GET_ALL().stream().findFirst().get().getId();
        CartProduct expectedProduct = historyController.product_GET_BY_ID(id);
        assertEquals(id, expectedProduct.getId());
    }
}

