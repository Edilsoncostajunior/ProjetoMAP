package com.grupo4.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import com.grupo4.config.DatabaseStorage;
import com.grupo4.models.Client;

public class ClientController {
    private static ClientController instance = null;

    private List<Client> clients;

    private ClientController() {
        this.clients = DatabaseStorage.creatingClientList();
    }

    public static synchronized ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }

        return instance;
    }

    // FACADE FUNCTIONS
    // - Client
    public Map<String, String> client_LOGIN(String email, String password) {
        Optional<Client> clienteOptional = clients.stream().filter(value -> value.getEmail().equals(email)
                && value.getPassword().equals(password)).findFirst();
        if (clienteOptional.isPresent()) {
            Map<String, String> loginInfo = new HashMap<>();

            loginInfo.put("id", clienteOptional.get().getId());
            loginInfo.put("name", clienteOptional.get().getName());

            return loginInfo;
        }
        return null;
    }

    public List<Client> client_GET_ALL() {
        return clients;
    }

    public Client client_GET_BY_ID(String id) {
        return clients.stream().filter(value -> value.getId().equals(id)).findFirst().get();
    }

    public Client client_GET_BY_NAME(String name) {
        return clients.stream().filter(value -> value.getName().equals(name)).findFirst().get();
    }

    public Client client_GET_BY_CPF(String cpf) {
        return clients.stream().filter(value -> value.getDocument().equals(cpf)).findFirst().get();
    }

    public String client_PATCH(Map<String, String> changes) {
        int client_index = IntStream.range(0, clients.size())
                .filter(i -> clients.get(i).getId().equals(changes.get("id")))
                .findFirst()
                .orElse(-1);
        if (client_index != -1) {
            clients.get(client_index).update(changes);
            DatabaseStorage.writtingClientFile(clients);
            return "Finalizado com sucesso as alterações no cliente!";
        }

        return "Falha na atualização do cliente";
    }

    public String client_POST(String name, String cpf, String email, String password, String street,
            String house_number, String neighbourhood, String postal_code, String city,
            String state, String country) {
        List<Client> clients = new ArrayList<>(this.clients);

        String id = clients.size() == 0 ? "0"
                : "" + (Integer.parseInt(
                        clients.stream().max((comp1, comp2) -> comp1.getId().compareTo(comp2.getId())).get().getId())
                        + 1);
        clients.add(
                new Client(id, name, cpf, email, password, street, house_number, neighbourhood, postal_code, city,
                        state, country));
        DatabaseStorage.writtingClientFile(clients);

        this.clients = clients;
        return "Finalizado com sucesso o novo cliente foi adicionado com sucesso!";
    }

    public String client_DELETE(String id) {
        this.client_GET_BY_ID(id);

        clients = clients.stream().filter(value -> !value.getId().equals(id)).toList();

        DatabaseStorage.writtingClientFile(clients);

        return "Finalizado com sucesso o cliente foi deletado com sucesso!";
    }
}
