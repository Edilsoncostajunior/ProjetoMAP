package com.grupo4.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import com.grupo4.config.DatabaseStorage;
import com.grupo4.models.CartProduct;
import com.grupo4.models.Product;
import com.grupo4.models.Store;

public class CartController {
    private static ArrayList<CartController> instance = null;

    private List<CartProduct> products;
    private int pontos;
    private String client_id;
    private Store store;

    private CartController(String client_id, String store_id) {
        this.products = DatabaseStorage.creatingCartList(client_id, store_id);
        this.client_id = client_id;
        this.store = DatabaseStorage.creatingStoreList().stream().filter(value -> value.getId().equals(store_id))
                .findAny().get();
    }

    public static synchronized CartController getInstance(String client_id, String store_id) {
        if (instance == null) {
            instance = new ArrayList<>();
        }

        Optional<CartController> cOptional = instance.stream()
                .filter(value -> value.getClient_id().equals(client_id) && value.getStore().getId().equals(store_id))
                .findFirst();

        if (!cOptional.isPresent()) {
            CartController cartController = new CartController(client_id, store_id);
            instance.add(cartController);

            return cartController;
        }

        return cOptional.get();
    }

    public List<CartProduct> product_GET_ALL() {
        return products;
    }

    public CartProduct product_GET_BY_ID(String id) {
        return products.stream().filter(value -> value.getId().equals(id)).findFirst().get();
    }

    public void PutInTheCart(Product product, int quantity) {
        List<CartProduct> products = new ArrayList<>(this.products);

        if (product.getQuantity() > 0) {
            String id = products.size() == 0 ? "0"
                    : String.valueOf(products.stream()
                            .mapToInt(p -> Integer.parseInt(p.getId()))
                            .max()
                            .orElse(0) + 1);
            products.add(
                    new CartProduct(id, store.getId(), product.getId(), product.getName(), store.getName(), quantity));
            DatabaseStorage.writtingCartFile(products, client_id, store.getId());

            this.products = products;
        }
        else {
            System.out.println("Não é possível, pois não há estoque");
        }
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
        List<CartProduct> history = new ArrayList<>(DatabaseStorage.creatingHistoryList(client_id));
        ProductController productController = ProductController.getInstance(store.getId());
        for (CartProduct cartProduct : products) {
            String getReponse = productController.decreaseProduct(cartProduct.getProduct_id(),
                    cartProduct.getQuantity());
            if (!getReponse.equals("OK")) {
                cartProduct.setQuantity(Integer.parseInt(getReponse));
            }
            String id = history.size() == 0 ? "0"
                    : String.valueOf(products.stream()
                            .mapToInt(p -> Integer.parseInt(p.getId()))
                            .max()
                            .orElse(0) + 1);
            cartProduct.setId(id);
            history.add(cartProduct);
        }

        DatabaseStorage.writtingHistoryFile(history, client_id);

        products = new ArrayList<>();
        DatabaseStorage.writtingCartFile(products, client_id, store.getId());

        HistoryController.getInstance(client_id).setProducts(history);
    }

    public String getClient_id() {
        return client_id;
    }

    public Store getStore() {
        return store;
    }
}
