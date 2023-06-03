package com.grupo4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.grupo4.controllers.ProductController;
import com.grupo4.models.Product;

public class ProductControllerTest {

    private ProductController productController;

    JSONObject json = new JSONObject();

    public void testProduct() {
        Product Product = new Product("1","O boticario", "Perfume ", "Cosmeticos", "Secrets", 150.00, 10);
        assertEquals("1", Product.getId());
    }


    @Before
    public void setUp() {
        productController = new ProductController();
    }


    @Test
    public void testProduct_GET_ALL() {
        List<Product> Products = productController.product_GET_ALL();
        Assertions.assertNotNull(Products);
    }

    @Test
    public void testProduct_GET_BY_ID() {
        Product Product = productController.product_GET_BY_ID("2");
        assertEquals("2", Product.getId());

    }

    @Test
    public void testProduct_PATCH() {
        Map<String, String> changes = new HashMap<>();
        changes.put("id", "0");
        changes.put("name", "patch test");

        productController.product_PATCH(changes);

        Product client = productController.product_GET_BY_ID("0");
        assertEquals("patch test", client.getName());
    }

    @Test
    public void testProduct_POST() {
        String brand = "Avon";
        String description = "Perfume da Avon";
        String category= "Perfumes";
        String name =  "joy fragancy";
        double price = 1000;
        int quantity = 10;

        productController.product_POST( brand,  description,  category,  name,  price,
        quantity);
        Product Product = productController.product_GET_BY_ID("1");
        assertEquals("joy fragancy", Product.getName());
    }

    @Test
    public void testProduct_DELETE() {
        productController.product_DELETE("1");
        List<Product> Products = productController.product_GET_ALL();
        Assertions.assertNotNull(Products);
    }

}
