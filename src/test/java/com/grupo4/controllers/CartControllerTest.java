package com.grupo4.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.grupo4.models.CartProduct;
import com.grupo4.models.Product;

public class CartControllerTest {
    private CartController cartController;
    private String clientId;
    private String storeId;
    private Product product;

    @BeforeEach
    public void setUp() {
        ProductController store_ProductController = ProductController.getInstance("13");
        clientId = "15";
        storeId = "13";
        cartController = CartController.getInstance(clientId, storeId);
        product = store_ProductController.product_GET_BY_ID("15");
    }

    @Test
    public void testPutInTheCart() {
        int initialSize = cartController.product_GET_ALL().size();

        cartController.PutInTheCart(product, 2);

        List<CartProduct> products = cartController.product_GET_ALL();
        Assertions.assertEquals(initialSize + 1, products.size());

        CartProduct cartProduct = products.get(products.size() - 1);
        Assertions.assertEquals(storeId, cartProduct.getStore_id());
        Assertions.assertEquals(product.getId(), cartProduct.getProduct_id());
        Assertions.assertEquals(product.getName(), cartProduct.getProduct_name());
        Assertions.assertEquals(2, cartProduct.getQuantity());
    }

    @Test
    public void testMakeChanges() {
        cartController.PutInTheCart(product, 2);
        List<CartProduct> products = cartController.product_GET_ALL();
        CartProduct cartProduct = products.get(0);
        String cartProductId = cartProduct.getId();

        Map<String, String> changes = new HashMap<>();
        changes.put("id", cartProductId);
        changes.put("quantity", "3");
        changes.put("product_name", "Updated Product");

        cartController.MakeChanges(changes);

        CartProduct updatedCartProduct = cartController.product_GET_BY_ID(cartProductId);
        Assertions.assertEquals(3, updatedCartProduct.getQuantity());
        Assertions.assertEquals("Updated Product", updatedCartProduct.getProduct_name());
    }

    @Test
    public void testTakeOfFromTheCart() {
        cartController.PutInTheCart(product, 2);
        List<CartProduct> products = cartController.product_GET_ALL();
        int initialSize = products.size();
        CartProduct cartProduct = products.get(0);
        String cartProductId = cartProduct.getId();

        cartController.TakeOfFromTheCart(cartProductId);

        List<CartProduct> updatedProducts = cartController.product_GET_ALL();
        Assertions.assertEquals(initialSize - 1, updatedProducts.size());
        Assertions.assertFalse(updatedProducts.stream().anyMatch(p -> p.getId().equals(cartProductId)));
    }

    @Test
    public void testBuyProducts() {
        cartController.PutInTheCart(product, 2);
        List<CartProduct> products = cartController.product_GET_ALL();
        Assertions.assertFalse(products.isEmpty());

        cartController.buyProducts();

        List<CartProduct> updatedProducts = cartController.product_GET_ALL();
        Assertions.assertTrue(updatedProducts.isEmpty());
    }
}
