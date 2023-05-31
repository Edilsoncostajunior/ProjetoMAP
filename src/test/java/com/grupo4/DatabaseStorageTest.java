package com.grupo4;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import com.grupo4.config.DatabaseStorage;
import com.grupo4.controllers.ClientController;

public class DatabaseStorageTest {
    ClientController controller;

    // Map<String, String> map = Stream.of(new String[][] {
    // { "id", "0" },
    // { "name", "Joao" },
    // }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    // controller.client_POST("Lucas", "21321321321", "asfas", "null", "null",
    // "null", "null", "null", "null", "null",
    // "null");
    // controller.client_PATCH(map);
    // controller.client_DELETE("2");

    @BeforeAll
    public void setup() {
        controller = new ClientController();
    }

    @Test
    @DisplayName("Getting all clients present in the json file")
    public void gettingAllClientsFromJsonFile() {
        Assertions.assertNotNull(DatabaseStorage.creatingClientList());
    }

    @Test
    @DisplayName("Getting all products present in the json file")
    public void gettingAllProductsFromJsonFile() {
        Assertions.assertNotNull(DatabaseStorage.creatingProductList(""));
    }

    @Test
    @DisplayName("Getting all stores present in the json file")
    public void gettingAllStoresFromJsonFile() {
        Assertions.assertNotNull(DatabaseStorage.creatingStoreList());
    }
}
