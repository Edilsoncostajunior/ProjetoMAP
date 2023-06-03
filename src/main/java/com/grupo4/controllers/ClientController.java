package com.grupo4.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import com.grupo4.config.DatabaseStorage;
import com.grupo4.models.Client;

public class ClientController {
    private List<Client> clients;

    public ClientController() {
        this.clients = DatabaseStorage.creatingClientList();
    }

    // FACADE FUNCTIONS
    // - Client
    public Boolean client_LOGIN(String email, String password) {
        return clients.stream().anyMatch(value -> value.getEmail().equals(email)
                && value.getPassword().equals(password));
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
        clients.get(client_index).update(changes);
        DatabaseStorage.writtingClientFile(clients);
        return "Finalizado com sucesso as alterações no cliente!";
    }

    public String client_POST(String name, String cpf, String email, String password, String street,
            String house_number, String neighbourhood, String postal_code, String city,
            String state, String country) {
        String id = clients.size() == 0 ? "0" : "" + Integer.parseInt(
                clients.stream().max((comp1, comp2) -> comp1.getId().compareTo(comp2.getId())).get().getId()) + 1;
        clients.add(
                new Client(id, name, cpf, email, password, street, house_number, neighbourhood, postal_code, city,
                        state, country));
        DatabaseStorage.writtingClientFile(clients);
        return "Finalizado com sucesso o novo cliente foi adicionado com sucesso!";
    }

    public String client_DELETE(String id) {
        clients = clients.stream().filter(value -> !value.getId().equals(id)).toList();
        DatabaseStorage.writtingClientFile(clients);

        return "Finalizado com sucesso o cliente foi deletado com sucesso!";
    }
}
