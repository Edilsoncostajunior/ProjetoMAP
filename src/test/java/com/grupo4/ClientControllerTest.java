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

import com.grupo4.controllers.ClientController;
import com.grupo4.models.Client;

public class ClientControllerTest {

    JSONObject json = new JSONObject();

    public void testClient() {

        Client client = new Client("1", "Caio", "12345678901", "caio@email.com", "senha111", "rua 2", "5", "33", "44",
                "CG", "PB", "BR");
        assertEquals("caio@email.com", client.getEmail());
    }

    private ClientController clientController;

    @Before
    public void setUp() {
        clientController = new ClientController();
    }

    @Test
    public void testClient_LOGIN() {
        assertFalse(clientController.client_LOGIN("caio@email.com", "senha111"));
    }

    @Test
    public void testClient_GET_ALL() {
        List<Client> clients = clientController.client_GET_ALL();
        Assertions.assertNotNull(clients);
    }

    @Test
    public void testClient_GET_BY_ID() {
        Client client = clientController.client_GET_BY_ID("0");
        assertEquals("Caio henrique", client.getName());
    }

    @Test
    public void testClient_GET_BY_NAME() {
        Client client = clientController.client_GET_BY_NAME("Caio henrique");
        assertEquals("asdasdsa@gmail.com", client.getEmail());
    }

    @Test
    public void testClient_GET_BY_CPF() {
        Client client = clientController.client_GET_BY_CPF("088.722.665-12");
        assertEquals("asdasdsa@gmail.com", client.getEmail());
    }

    @Test
    public void testClient_PATCH() {
        Map<String, String> changes = new HashMap<>();
        changes.put("id", "0");
        changes.put("name", "Caio henrique");

        String result = clientController.client_PATCH(changes);
        assertEquals("Finalizado com sucesso as alterações no cliente!", result);

        Client client = clientController.client_GET_BY_ID("0");
        assertEquals("Caio henrique", client.getName());
    }

    @Test
    public void testClient_POST() {
        String name = "CH";
        String cpf = "987.654.321-00";
        String email = "Ch@email.com";
        String password = "senha123";
        String street = "rua cc";
        String house_number = "69";
        String neighbourhood = "Bairro c";
        String postal_code = "58431-070";
        String city = "Cg";
        String state = "Pb";
        String country = "brasil";

        String result = clientController.client_POST(name, cpf, email, password, street, house_number, neighbourhood,
                postal_code, city, state, country);
        assertEquals("Finalizado com sucesso o novo cliente foi adicionado com sucesso!", result);

        Client client = clientController.client_GET_BY_NAME("CH");
        assertEquals("987.654.321-00", client.getDocument());
    }

    @Test
    public void testClient_DELETE() {
        String result = clientController.client_DELETE("1");
        assertEquals("Finalizado com sucesso o cliente foi deletado com sucesso!", result);

        List<Client> clients = clientController.client_GET_ALL();
        Assertions.assertNotNull(clients);
    }
}