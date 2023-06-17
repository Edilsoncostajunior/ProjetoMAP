package com.grupo4.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.grupo4.controllers.StoreController;
import com.grupo4.error.NullReadableValuesToWriteException;
import com.grupo4.models.Store;

public class StoreControllerTest {

    private StoreController storeController;

    @Before
    public void setUp() {
        storeController = new StoreController();
    }

    @Test
    public void readTestIfTrue() {
        List<Store> stores = storeController.read();
        Assertions.assertTrue(stores.size() >= 0);
    }

    @Test
    public void testAddNewStoreIncreasesStoreListSize() {
    Random random = new Random();

    // Gera um número de documento aleatório com 7 dígitos
    int numeroDocumento = random.nextInt(9000000) + 1000000;
    String numeroDocumentoString = Integer.toString(numeroDocumento);
    // Configuração do estado inicial
    int initialSize = storeController.read().size();

    // Utiliza o número de documento gerado no método write
    storeController.write("New Store", "newstore@example.com", "password", numeroDocumentoString, "123 Main St",
        "42", "Downtown", "12345", "Anytown", "ST", "US");
    // Verificação do resultado
    List<Store> stores = storeController.read();
    int newSize = stores.size();
    Assertions.assertEquals(initialSize + 1, newSize);
}

    @Test
    public void updateTestIfEquals() {
        Map<String, String> changes = new HashMap<>();
        String id = storeController.read().stream().findFirst().get().getId();
        changes.put("id", id);
        changes.put("name", "Caio");
        changes.put("email", "caio@example.com");
        changes.put("password", "senha445");
        changes.put("document", "10987654321");

        try {
            storeController.update(changes);
        } catch (NullReadableValuesToWriteException e) {
            e.printStackTrace();
        }

        Store stores = storeController.read_by_id(id);
        assertEquals(changes.get("name"), stores.getName());
        assertEquals(changes.get("email"), stores.getEmail());
        assertEquals(changes.get("password"), stores.getPassword());
        assertEquals(changes.get("document"), stores.getDocument());
    }

    @Test
    public void removeTestIfTrue() {
        int size = storeController.read().size();
        String id = storeController.read().stream().findFirst().get().getId();
        storeController.remove(id);
        List<Store> stores = storeController.read();
        Assertions.assertTrue(stores.size() < size);
        assertFalse(stores.stream().anyMatch(store -> store.getId().equals(id)));
    }

    @Test
    public void isUniquedocumentoTestIfTrue() {
        assertTrue(storeController.isUniquedocumento("1", ""));
    }
}
