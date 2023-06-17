package com.grupo4.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import com.grupo4.config.DatabaseStorage;
import com.grupo4.error.NullReadableValuesToWriteException;
import com.grupo4.models.Store;

public class StoreController {
    private static StoreController instance = null;

    private List<Store> stores;

    private StoreController() {
        this.stores = DatabaseStorage.creatingStoreList();
    }

    public static synchronized StoreController getInstance() {
        if (instance == null) {
            instance = new StoreController();
        }

        return instance;
    }

    public Map<String, String> store_LOGIN(String email, String password) {
        Optional<Store> clienteOptional = stores.stream().filter(value -> value.getEmail().equals(email)
                && value.getPassword().equals(password)).findFirst();
        if (clienteOptional.isPresent()) {
            Map<String, String> loginInfo = new HashMap<>();

            loginInfo.put("id", clienteOptional.get().getId());
            loginInfo.put("name", clienteOptional.get().getName());

            return loginInfo;
        }
        return null;
    }

    public List<Store> read() {
        return stores;
    }

    public Store read_by_id(String id) {
        return stores.stream().filter(value -> value.getId().equals(id)).findFirst().get();
    }

    public void write(String name, String email, String password, String documento, String street, String house_number,
            String neighbourhood, String postal_code, String city, String state, String country) {
        if (isUniquedocumentoAndEmail(documento, email, "")) {
            List<Store> stores = new ArrayList<>(this.stores);
            String id = String.valueOf(stores.stream()
                    .mapToInt(p -> Integer.parseInt(p.getId()))
                    .max()
                    .orElse(0) + 1);
            stores.add(new Store(id, name, email, password, documento, street, house_number, neighbourhood, postal_code,
                    city, state, country));
            DatabaseStorage.writtingStoreFile(stores);
            this.stores = stores;

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

        if (changes.get("document") == null
                || isUniquedocumentoAndEmail(changes.get("document"), changes.get("email"), changes.get("id"))) {
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

    public boolean isUniquedocumentoAndEmail(String store_doc_comp, String store_email_comp, String id) {
        if (stores.size() == 0) {
            return true;
        }

        for (Store store : stores) {
            if (store_doc_comp.equals(store.getDocument()) && store_email_comp.equals(store.getEmail())
                    && !store.getId().equals(id)) {
                return false;
            }
        }
        return true;
    }
}
