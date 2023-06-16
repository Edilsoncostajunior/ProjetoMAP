package com.grupo4.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import com.grupo4.config.DatabaseStorage;
import com.grupo4.error.NullReadableValuesToWriteException;
import com.grupo4.models.Store;

public class StoreController {
    private List<Store> stores;

    public StoreController() {
        this.stores = DatabaseStorage.creatingStoreList();
    }

    public Boolean client_LOGIN(String email, String password) {
        return stores.stream().anyMatch(value -> value.getEmail().equals(email)
                && value.getPassword().equals(password));
    }

    public List<Store> read() {
        return stores;
    }

    public Store read_by_id(String id) {
        return stores.stream().filter(value -> value.getId().equals(id)).findFirst().get();
    }

    public void write(String name, String email, String password, String documento, String street, String house_number,
            String neighbourhood, String postal_code, String city, String state, String country) {
        if (isUniquedocumento(documento, "")) {
            String id = String.valueOf(stores.stream()
                    .mapToInt(p -> Integer.parseInt(p.getId()))
                    .max()
                    .orElse(0) + 1);
            stores.add(new Store(id, name, email, password, documento, street, house_number, neighbourhood, postal_code,
                    city, state, country));
            DatabaseStorage.writtingStoreFile(stores);

        } else {
            System.out.println("Cpf ou Cnpj corresponde a uma store existente.");
        }
    }

    public void update(Map<String, String> changes) throws NullReadableValuesToWriteException {
        int client_index = IntStream.range(0, stores.size())
                .filter(i -> stores.get(i).getId().equals(changes.get("id")))
                .findFirst()
                .orElse(-1);

        if (client_index == -1) {
            System.out.println("Erro: empresa não cadastrada!");
            return;
        }

        if (changes.get("document") == null || isUniquedocumento(changes.get("document"), changes.get("id"))) {
            stores.get(client_index).update(changes);
            DatabaseStorage.writtingStoreFile(stores);
        } else {
            System.out.println("Cpf ou Cnpj corresponde a uma store existente.");
        }
    }

    public void remove(String id) {
        stores = stores.stream().filter(value -> !value.getId().equals(id)).toList();
        DatabaseStorage.writtingStoreFile(stores);
    }

    public boolean isUniquedocumento(String store_comp, String id) {
        if (stores.size() == 0) {
            return true;
        }

        for (Store store : stores) {
            if (store_comp.equals(store.getDocument()) && !store.getId().equals(id)) {
                return false;
            }
        }
        return true;
    }
}
