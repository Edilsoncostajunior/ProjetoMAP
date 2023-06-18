package com.grupo4.view;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.grupo4.controllers.ClientController;
import com.grupo4.models.Client;

public class Menu_clientTest {

    private Menu_client menu;
    private ClientController clientController;
    private ByteArrayOutputStream outputStream;


    @BeforeEach
    public void setup() {
        menu = Menu_client.init();
        clientController = ClientController.getInstance();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    
    }


    /*  @Test
    void testInit() {

    }*/


    @Test
    void testOption_get_all() {

       List<Client> clientControllers = clientController.client_GET_ALL();
       Assertions.assertNotNull(clientControllers);
     
       /*
        String expectedOutput = "-----------  clientes ----------\n" +
                clientController.client_GET_ALL() + "\n" +
                "Pressione enter para continuar...\n";
        InputStream inputStream = new ByteArrayInputStream("\n".getBytes());
        System.setIn(inputStream);
        //menu.option_get_all();
        String consoleOutput = outputStream.toString();

        // Verificação
        //Assertions.assertEquals(expectedOutput, consoleOutput);
        Assertions.assertNotNull(clientController.client_GET_ALL());
         */
    }

    @Test
    void testOption_delete() {

    }

    @Test
    void testOption_get_id() {

    }

    @Test
    void testOption_post() {

    }

    @Test
    void testOption_update() {

    }

    @Test
    void testRun() {

    }
}
