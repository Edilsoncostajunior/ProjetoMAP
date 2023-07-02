package com.grupo4.view;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.grupo4.controllers.CartController;
import com.grupo4.controllers.HistoryController;
import com.grupo4.controllers.ProductController;
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
        String clientId = "9";
        String storeId = "13";

        Map<String, String> loginInfo = new HashMap<>();
        loginInfo.put("id", clientId);
        loginInfo.put("name", "teste");
        menuLoginClient = Menu_login_client.init(loginInfo);

        historyController = HistoryController.getInstance(clientId);
        store_ProductController = ProductController.getInstance(storeId);
        cartController = CartController.getInstance(clientId, storeId);
        product1 = store_ProductController.product_GET_BY_ID("17");

        for (int i = 0; i < 10; i++) {
            cartController.PutInTheCart(product1, 1);
            cartController.buyProducts();
        }

        String expectedOutput = "Parabéns, Você ganhou:";
        Assertions.assertEquals(expectedOutput, getConsoleOutput());
        historyController.product_DELETE_ALL();
    }

    private String getConsoleOutput() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
    menuLoginClient.pontuacao();
    String output = outputStream.toString();

    String[] lines = output.split("\n");
    if (lines.length >= 2) {
        return lines[1];
    }

    return output; 
}

}
