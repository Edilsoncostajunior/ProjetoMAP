package com.grupo4.models;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.grupo4.error.ConstructorRequirementsNotAttendedException;

public class Client {
    private String id;
    private String name;
    private String cpf;
    private String email;
    private String password;
    private Address address;

    public Client(Map<String, String> mapping) {
        try {
            if (mapping.size() != 12)
                throw new ConstructorRequirementsNotAttendedException("Mapeamento não contém todas as informações");
            if (mapping.containsKey("id"))
                this.id = mapping.get("id");

            this.address = new Address();
            this.valuesClient(mapping);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public Client(String id, String name, String cpf, String email, String password, String street,
            String house_number, String neighbourhood, String postal_code, String city,
            String state, String country) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.address = new Address(street, house_number, neighbourhood, postal_code, city, state, country);
    }

    public Client(JSONObject json) {
        this.id = (String) json.get("id");
        this.name = (String) json.get("name");
        this.cpf = (String) json.get("cpf");
        this.email = (String) json.get("email");
        this.password = (String) json.get("password");
        this.address = new Address((JSONObject) json.get("address"));
    }

    public JSONObject transformToJsonObject() {
        Map<String, Object> map = new HashMap<>();

        map.put("id", this.id);
        map.put("name", this.name);
        map.put("cpf", this.cpf);
        map.put("email", this.email);
        map.put("password", this.password);
        map.put("address", this.address.transformToJsonObject());

        return new JSONObject(map);
    }

    public void valuesClient(Map<String, String> changes) {
        if (changes.size() == 0)
            return;

        if (changes.containsKey("name"))
            this.name = changes.get("name");
        if (changes.containsKey("cpf"))
            this.cpf = changes.get("cpf");
        if (changes.containsKey("email"))
            this.email = changes.get("email");
        if (changes.containsKey("password"))
            this.password = changes.get("password");

        this.address.valuesAddress(changes);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
        return "Client [name=" + name + ", cpf=" + cpf + ", email=" + email + ", password=" + password + ", address="
                + address + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}