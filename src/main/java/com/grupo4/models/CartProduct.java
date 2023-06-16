package com.grupo4.models;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class CartProduct {
    private String id;
    private String store_id;
    private String product_id;

    private String product_name;
    private String store_name;
    private int quantity;

    public CartProduct(String id, String store_id, String product_id, String product_name, String store_name,
            int quantity) {
        this.id = id;
        this.store_id = store_id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.store_name = store_name;
        this.quantity = quantity;
    }

    public CartProduct(JSONObject json) {
        this.id = (String) json.get("id");
        this.store_id = (String) json.get("store_id");
        this.product_id = (String) json.get("product_id");
        this.product_name = (String) json.get("product_name");
        this.store_name = (String) json.get("store_name");
        this.quantity = ((Long) json.get("quantity")).intValue();
    }

    public JSONObject transformToJsonObject() {
        Map<String, Object> map = new HashMap<>();

        map.put("id", this.id);
        map.put("store_id", this.store_id);
        map.put("product_id", this.product_id);
        map.put("product_name", this.product_name);
        map.put("store_name", this.store_name);
        map.put("quantity", this.quantity);

        return new JSONObject(map);
    }

    public void update(Map<String, String> changes) {
        if (changes.size() == 0)
            return;

        if (changes.containsKey("store_id"))
            this.store_id = changes.get("store_id");
        if (changes.containsKey("product_id"))
            this.product_id = changes.get("product_id");
        if (changes.containsKey("product_name"))
            this.product_name = changes.get("product_name");
        if (changes.containsKey("store_name"))
            this.store_name = changes.get("store_name");
        if (changes.containsKey("quantity"))
            this.quantity = Integer.parseInt(changes.get("quantity"));
    }

    @Override
    public String toString() {
        String returnMessage = "id :" + this.id + "\n";
        returnMessage += "store_id :" + this.store_id + "\n";
        returnMessage += "store_name :" + this.store_name + "\n";
        returnMessage += "product_id :" + this.product_id + "\n";
        returnMessage += "product_name :" + this.product_name + "\n";
        returnMessage += "quantity :" + this.quantity + "\n";
        return returnMessage;
    }

    public String getId() {
        return id;
    }

    public String getStore_id() {
        return store_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getStore_name() {
        return store_name;
    }

    public int getQuantity() {
        return quantity;
    }
}
