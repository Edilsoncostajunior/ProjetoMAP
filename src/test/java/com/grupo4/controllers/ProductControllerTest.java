package com.grupo4.controllers;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import com.grupo4.models.Product;

public class ProductControllerTest {

    private ProductController productController;

    JSONObject json = new JSONObject();

    @Before
    public void setUp() {
        productController = new ProductController();
        productController.product_POST( "Avon", "Perfume da Avon", "Perfumes", "joy fragancy", 1000, 10);
    }

    @Test
    public void testProductIfEquals() {
        Product Product = new Product("1","O boticario", "Perfume ", "Cosmeticos", "Secrets", 150.00, 10);
        assertEquals("1", Product.getId());
    }

    @Test
    public void testProductGetAllIfNotNull() {
        List<Product> Products = productController.product_GET_ALL();
        Assertions.assertNotNull(Products);
    }

    @Test
    public void testProductGetByIdIfEquals() {
        String id = productController.product_GET_ALL().stream().findAny().get().getId();
        Product Product = productController.product_GET_BY_ID(id);
        assertEquals(id, Product.getId());

    }

    @Test
    public void testProductPostIfEquals() {
        String brand = "Avon";
        String description = "Perfume da Avon";
        String category= "Perfumes";
        String name =  "Secrets";
        double price = 1000;
        int quantity = 10;

        String resultado = productController.product_POST(brand, description, category, name, price, quantity);
        assertEquals("O novo Cliente foi adicionado com sucesso!", resultado);

        Product Product = productController.product_GET_BY_NAME("Secrets");
        assertEquals("Secrets", Product.getName());
    }

   @Test
    public void testProductPatchIfEquals() {
        String id = productController.product_GET_ALL().stream().findFirst().get().getId();

        Map<String, String> changes = new HashMap<>();
        changes.put("id", id);
        changes.put("name", "patch test");

        productController.product_PATCH(changes);

        Product product = productController.product_GET_BY_ID(id);
        assertEquals("patch test", product.getName());
    }

    @Test
    public void testProductDeleteIfNotNull() {
        String result = productController
                .product_DELETE(productController.product_GET_ALL().stream().findFirst().get().getId());
        productController.product_DELETE(productController.product_GET_ALL().stream().findFirst().get().getId());
        assertEquals("O produto foi deletado com sucesso!", result);
    }

}
