package com.grupo4.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.grupo4.models.Product;

public class Store_ProductControllerTest {

    private ProductController store_ProductController = ProductController.getInstance("6");

    @Test
    public void testProductGetAllIfNotNull() {
        store_ProductController.product_POST("Avon", "Perfume da Avon", "Perfumes", "joy fragancy", 1000, 10);

        List<Product> products = store_ProductController.product_GET_ALL();
        Assertions.assertNotNull(products);
    }

    @Test
    public void testProductGetByIdIfEquals() {
        String id = store_ProductController.product_GET_ALL().stream().findFirst().get().getId();
        Product Product = store_ProductController.product_GET_BY_ID(id);
        assertEquals(id, Product.getId());

    }

    @Test
    public void testProductPostIfEquals() {
        String brand = "Avon";
        String description = "Perfume da Avon";
        String category = "Perfumes";
        String name = "Secrets";
        double price = 1000;
        int quantity = 10;

        String resultado = store_ProductController.product_POST(brand, description, category, name, price, quantity);
        assertEquals("Produto adicionado com sucesso!", resultado);

        Product Product = store_ProductController.product_GET_BY_NAME("Secrets");
        assertEquals("Secrets", Product.getName());
    }

    @Test
    public void testProductPatchIfEquals() {
        String id = store_ProductController.product_GET_ALL().stream().findFirst().get().getId();

        Map<String, String> changes = new HashMap<>();
        changes.put("id", id);
        changes.put("name", "patch test");

        store_ProductController.product_PATCH(changes);

        Product product = store_ProductController.product_GET_BY_ID(id);
        assertEquals("patch test", product.getName());
    }

    @Test
    public void testProductDeleteIfNotNull() {
        String result = store_ProductController
                .product_DELETE(store_ProductController.product_GET_ALL().stream().findFirst().get().getId());
        assertEquals("Produto deletado com sucesso!", result);
    }
}
