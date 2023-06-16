package com.grupo4.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import com.grupo4.config.DatabaseStorage;
import com.grupo4.models.CartProduct;
import com.grupo4.models.Product;
import com.grupo4.models.Store;

public class CartController {
    private List<CartProduct> products;
    private String client_id;
    private Store store;

    public CartController(String client_id, Store store) {
        this.products = DatabaseStorage.creatingCartList(client_id, store.getId());
        this.client_id = client_id;
        this.store = store;
    }

    public List<CartProduct> product_GET_ALL() {
        return products;
    }

    public CartProduct product_GET_BY_ID(String id) {
        return products.stream().filter(value -> value.getId().equals(id)).findFirst().get();
    }

    public void PutInTheCart(Product product, int quantity) {
        String id = products.size() == 0 ? "0"
                : String.valueOf(products.stream()
                        .mapToInt(p -> Integer.parseInt(p.getId()))
                        .max()
                        .orElse(0) + 1);
        products.add(new CartProduct(id, store.getId(), product.getId(), product.getName(), store.getName(), quantity));
        DatabaseStorage.writtingCartFile(products, client_id, store.getId());
    }

    public void MakeChanges(Map<String, String> changes) {
        int product_index = IntStream.range(0, products.size())
                .filter(i -> products.get(i).getId().equals(changes.get("id")))
                .findFirst()
                .orElse(-1);

        if (product_index == -1) {
            System.out.println("Erro: produto não cadastrada!");
            return;
        }

        products.get(product_index).update(changes);
        DatabaseStorage.writtingCartFile(products, client_id, store.getId());
    }

    public void TakeOfFromTheCart(String id) {
        products = products.stream().filter(value -> !value.getId().equals(id)).toList();
        DatabaseStorage.writtingCartFile(products, client_id, store.getId());
    }

    public void buyProducts() {
        List<CartProduct> history = DatabaseStorage.creatingCartList(client_id, store.getId());
        history.addAll(products);
        DatabaseStorage.writtingHistoryFile(history, client_id);

        products = new ArrayList<>();
        DatabaseStorage.writtingCartFile(products, client_id, store.getId());
    }
}
