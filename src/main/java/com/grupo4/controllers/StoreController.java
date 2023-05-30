package com.grupo4.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import com.grupo4.config.DatabaseStorage;
import com.grupo4.models.Store;

public class StoreController {
    private List<Store> stores;

    public StoreController() {
        this.stores = DatabaseStorage.creatingStoreList();
    }

    public List<Store> read() {
        return stores;
    }

    public void write(Store store) {
        if (isUniquedocumento(store)) {
            stores.add(store);
            DatabaseStorage.writtingStoreFile(stores);
        } else {
            System.out.println("Cpf ou Cnpj corresponde a uma store existente.");
        }
    }

    public void update(Map<String, String> changes) {
        int client_index = IntStream.range(0, stores.size())
                .filter(i -> stores.get(i).getId().equals(changes.get("id")))
                .findFirst()
                .orElse(-1);

        if (client_index == -1) {
            System.out.println("Erro: empresa não cadastrada!");
            return;
        }

        if (isUniquedocumento(stores.get(client_index))) {
            stores.get(client_index).valuesStore(changes);
            DatabaseStorage.writtingStoreFile(stores);
        } else {
            System.out.println("Cpf ou Cnpj corresponde a uma store existente.");
        }
    }

    public void remove(String id) {
        stores = stores.stream().filter(value -> !value.getId().equals(id)).toList();
        DatabaseStorage.writtingStoreFile(stores);
    }

    public boolean isUniquedocumento(Store store_comp) {
        for (Store store : stores) {
            if (store.equals(store_comp))
                continue;
            if (store_comp.getdocumento().equals(store.getdocumento())) {
                return false;
            }
        }
        return true;
    }
}
