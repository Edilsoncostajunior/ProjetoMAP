package com.grupo4.view;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.grupo4.controllers.CartController;
import com.grupo4.controllers.HistoryController;
import com.grupo4.controllers.ProductController;
import com.grupo4.models.CartProduct;
import com.grupo4.models.Product;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

public class Menu_login_clientTest {

    private Menu_login_client menuLoginClient;
    private ProductController store_ProductController;
    private HistoryController historyController;
    private CartController cartController;
    private Product product1;

    @Before
    void setUp() {

        }

    @Test
    void pontuacaoTest() {

        Map<String, String> loginInfo = new HashMap<>();
        loginInfo.put("joy@gmail.com", "senha1234");
        menuLoginClient = Menu_login_client.init(loginInfo);

        String clientId = "25";
        String storeId = "13";

        historyController = HistoryController.getInstance(clientId);
        store_ProductController = ProductController.getInstance(storeId);
        cartController = CartController.getInstance(clientId, storeId);
        product1 = store_ProductController.product_GET_BY_ID("17");


        // Create sample products
        //Product product1 = store_ProductController.product_GET_BY_NAME("Product 2");
        //Product product2 = productController.product_GET_BY_NAME("Product 2");

        // Add products to the history controller
        cartController.PutInTheCart(product1, 1);
        cartController.buyProducts();



        // Simulating user input
        ByteArrayInputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        System.setIn(inputStream);

        // Running the method
        menuLoginClient.pontuacao();

        // Verifying the expected behavior
        String expectedOutput = "Pontuação do Produto 1: 10.0\n";
        Assertions.assertEquals(expectedOutput, getConsoleOutput());
    }

    // Helper method to capture console output
    private String getConsoleOutput() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        menuLoginClient.pontuacao();
        return outputStream.toString();
    }
}
