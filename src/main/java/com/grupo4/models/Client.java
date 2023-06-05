package com.grupo4.models;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class Client {
    private String id;
    private String name;
    private String document;
    private String email;
    private String password;
    private Address address;

    public Client(String id, String name, String document, String email, String password, String street,
            String house_number, String neighbourhood, String postal_code, String city,
            String state, String country) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.email = email;
        this.password = password;
        this.address = new Address(street, house_number, neighbourhood, postal_code, city, state, country);
    }

    public Client(JSONObject json) {
        this.id = (String) json.get("id");
        this.name = (String) json.get("name");
        this.document = (String) json.get("document");
        this.email = (String) json.get("email");
        this.password = (String) json.get("password");
        this.address = new Address((JSONObject) json.get("address"));
    }

    public JSONObject transformToJsonObject() {
        Map<String, Object> map = new HashMap<>();

        map.put("id", this.id);
        map.put("name", this.name);
        map.put("document", this.document);
        map.put("email", this.email);
        map.put("password", this.password);
        map.put("address", this.address.transformToJsonObject());

        return new JSONObject(map);
    }

    public void update(Map<String, String> changes) {
        if (changes.size() == 0)
            return;

        if (changes.containsKey("name"))
            this.name = changes.get("name");
        if (changes.containsKey("document"))
            this.document = changes.get("document");
        if (changes.containsKey("email"))
            this.email = changes.get("email");
        if (changes.containsKey("password"))
            this.password = changes.get("password");

        this.address.updateAddress(changes);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        String returnMessage = "id :" + this.id + "\n";
        returnMessage += "name :" + this.name + "\n";
        returnMessage += "document :" + this.document + "\n";
        returnMessage += "email :" + this.email + "\n";
        returnMessage += "password :" + this.password + "\n";
        returnMessage += "address :" + this.address.toString() + "\n";
        return returnMessage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}