package com.grupo4.models;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class Product {
    private String id;
    private String brand;
    private String description;
    private String category;
    private String name;
    private double price;
    private int quantity;

    public Product(String id, String brand, String description, String category, String name, double price,
            int quantity) {
        this.id = id;
        this.brand = brand;
        this.description = description;
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(JSONObject json) {
        this.id = (String) json.get("id");
        this.brand = (String) json.get("brand");
        this.description = (String) json.get("description");
        this.category = (String) json.get("category");
        this.name = (String) json.get("name");
        this.price = (double) json.get("price");
        this.quantity = ((Long) json.get("quantity")).intValue();

    }

    public JSONObject transformToJsonObject() {
        Map<String, Object> map = new HashMap<>();

        map.put("id", this.id);
        map.put("brand", this.brand);
        map.put("description", this.description);
        map.put("category", this.category);
        map.put("name", this.name);
        map.put("price", this.price);
        map.put("quantity", this.quantity);

        return new JSONObject(map);
    }

    public void update(Map<String, String> changes) {
        if (changes.size() == 0)
            return;

        if (changes.containsKey("brand"))
            this.brand = changes.get("brand");
        if (changes.containsKey("description"))
            this.description = changes.get("description");
        if (changes.containsKey("category"))
            this.category = changes.get("category");
        if (changes.containsKey("name"))
            this.name = changes.get("name");
        if (changes.containsKey("price"))
            this.price = Double.parseDouble(changes.get("price"));
        if (changes.containsKey("quantity"))
            this.quantity = Integer.parseInt(changes.get("quantity"));
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", brand=" + brand + ", description=" + description + ", category=" + category
                + ", name=" + name + ", price=" + price + ", quantity=" + quantity + "]";
    }
}
