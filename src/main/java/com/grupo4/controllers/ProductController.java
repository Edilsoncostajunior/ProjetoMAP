package com.grupo4.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import com.grupo4.config.DatabaseStorage;
import com.grupo4.models.Product;

public class ProductController {
    public static List<ProductController> instance = null;

    private List<Product> products;
    private String store_id;

    private ProductController(String store_id) {
        this.store_id = store_id;
        this.products = DatabaseStorage.creatingStoreProductList(store_id);
    }

    public static synchronized ProductController getInstance(String store_id) {
        if (instance == null) {
            instance = new ArrayList<>();
        }

        Optional<ProductController> pOptional = instance.stream().filter(value -> value.getStore_id().equals(store_id))
                .findFirst();

        if (!pOptional.isPresent()) {
            ProductController productController = new ProductController(store_id);
            instance.add(productController);

            return productController;
        }

        return pOptional.get();
    }

    public List<Product> product_GET_ALL() {
        return products;
    }

    public Product product_GET_BY_ID(String id) {
        return products.stream().filter(value -> value.getId().equals(id)).findFirst().get();
    }

    public Product product_GET_BY_NAME(String name) {
        return products.stream().filter(value -> value.getName().equals(name)).findFirst().get();
    }

    public String product_POST(String brand, String description, String category, String name, double price,
            int quantity) {
        String id = products.size() == 0 ? "0"
                : String.valueOf(products.stream()
                        .mapToInt(p -> Integer.parseInt(p.getId()))
                        .max()
                        .orElse(0) + 1);
        products.add(new Product(id, brand, description, category, name, price, quantity));
        DatabaseStorage.writtingStoreProductFile(products, store_id);

        return "Produto adicionado com sucesso!";
    }

    public void product_PATCH(Map<String, String> changes) {
        int product_index = IntStream.range(0, products.size())
                .filter(i -> products.get(i).getId().equals(changes.get("id")))
                .findFirst()
                .orElse(-1);

        if (product_index == -1) {
            System.out.println("Erro: produto não cadastrada!");
            return;
        }

        products.get(product_index).update(changes);
        DatabaseStorage.writtingStoreProductFile(products, store_id);
    }

    public String product_DELETE(String id) {
        products = products.stream().filter(value -> !value.getId().equals(id)).toList();
        DatabaseStorage.writtingStoreProductFile(products, store_id);

        return "Produto deletado com sucesso!";
    }

    public String getStore_id() {
        return store_id;
    }
}
