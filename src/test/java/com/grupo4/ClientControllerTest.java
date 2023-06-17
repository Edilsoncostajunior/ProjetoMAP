package com.grupo4;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.grupo4.controllers.ClientController;
import com.grupo4.models.Client;

public class ClientControllerTest {
    private ClientController clientController = ClientController.getInstance();

    @Before
    public void setUp() {
        clientController.client_POST("Caio", "12345678901", "caio@email.com", "senha111", "rua 2", "5", "33", "44",
                "CG", "PB", "BR");
    }

    @Test
    public void testClientLoginIfTrue() {
        Assertions.assertNotNull(clientController.client_LOGIN("caio@email.com", "senha111"));
    }

    @Test
    public void testClientGetAllIfNotNull() {
        List<Client> clients = clientController.client_GET_ALL();
        Assertions.assertNotNull(clients);
    }

    @Test
    public void testClientGetByIdIfNotNull() {
        Client client = clientController
                .client_GET_BY_ID(clientController.client_GET_ALL().stream().findFirst().get().getId());
        Assertions.assertNotNull(client);
    }

    @Test
    public void testClientGetByNameIfNotNull() {
        Client client = clientController.client_GET_BY_NAME("Caio");
        Assertions.assertNotNull(client);

    }

    @Test
    public void testClientGetByCpfIfNotNull() {
        Client client = clientController.client_GET_BY_CPF("12345678901");
        Assertions.assertNotNull(client);
    }

    @Test
    public void testClientPatchIfEquals() {
        Map<String, String> changes = new HashMap<>();
        String id = clientController.client_GET_ALL().stream().findFirst().get().getId();
        changes.put("id", id);
        changes.put("name", "Caio henrique");

        String result = clientController.client_PATCH(changes);
        assertEquals("Finalizado com sucesso as alterações no cliente!", result);

        Client client = clientController.client_GET_BY_ID(id);
        assertEquals("Caio henrique", client.getName());
    }

    @Test
    public void testClientPostIfEquals() {
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
    public void testClientDeleteIfEquals() {
        String result = clientController
                .client_DELETE(clientController.client_GET_ALL().stream().findFirst().get().getId());
        assertEquals("Finalizado com sucesso o cliente foi deletado com sucesso!", result);
    }
}
