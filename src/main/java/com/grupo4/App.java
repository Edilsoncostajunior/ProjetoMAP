package com.grupo4;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.grupo4.controllers.ClientController;

public class App {
    public static void main(String[] args) throws IOException, ParseException {
        ClientController controller = new ClientController();
        // Map<String, String> map = Stream.of(new String[][] {
        // { "id", "0" },
        // { "name", "Joao" },
        // }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

        controller.client_POST("Lucas", "21321321321", "asfas", "null", "null", "null", "null", "null", "null", "null",
                "null");
        // controller.client_PATCH(map);
        controller.client_DELETE("2");
    }
}