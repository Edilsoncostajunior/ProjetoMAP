package com.grupo4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtensionContext.Store;

import com.grupo4.controllers.StoreController;

public class StoreControllerTest {

    private StoreController storeController;

    @Before
    public void setUp() {
        storeController = new StoreController();
    }

    @Test
    public void readTest() {
        List<com.grupo4.models.Store> stores = storeController.read();
        assertEquals(1, stores.size());
    }

    @Test
    public void writeTest() {
        storeController.write("New Store", "newstore@example.com","password","123456789","123 Main St",
        "42","Downtown","12345","Anytown","ST","US");
        List<com.grupo4.models.Store> stores = storeController.read();
        assertEquals(2, stores.size());
    }

    /*@Test
    public void updateTest() {
        Map<String, String> changes = new HashMap<>();
        changes.put("id", "1");
        changes.put("name", "Caio");
        changes.put("email", "caio@example.com");
        changes.put("password", "senha445");
        changes.put("documento", "10987654321");
        changes.put("street", "2324nn");
        changes.put("house_number", "24");
        changes.put("neighbourhood", "baiox");
        changes.put("postal_code", "54321");
        changes.put("city", "CG");
        changes.put("state", "PB");
        changes.put("country", "BR");
        storeController.update(changes);
        List<com.grupo4.models.Store> stores = storeController.read();
        assertEquals("New Name", stores.get(1).getName());
        assertEquals("newemail@example.com", stores.get(1).getEmail());
        assertEquals("newpassword", stores.get(1).getPassword());
        assertEquals("987654321", stores.get(1).getDocument());
    }*/

    @Test
    public void removeTest() {
        storeController.remove("1");
        List<com.grupo4.models.Store> stores = storeController.read();
        assertEquals(1, stores.size());
        assertFalse(stores.stream().anyMatch(store -> store.getId().equals("1")));
    }

    @Test
    public void isUniquedocumentoTest() {
        assertFalse(storeController.isUniquedocumento("12345678901"));
        assertTrue(storeController.isUniquedocumento("1"));
    }
}
