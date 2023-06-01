package com.grupo4.models;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class Address {
    private String street;
    private String house_number;
    private String neighbourhood;
    private String postal_code;
    private String city;
    private String state;
    private String country;

    public Address(String street, String house_number, String neighbourhood, String postal_code, String city,
            String state, String country) {
        this.street = street;
        this.house_number = house_number;
        this.neighbourhood = neighbourhood;
        this.postal_code = postal_code;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public Address(JSONObject json) {
        this.street = (String) json.get("street");
        this.house_number = (String) json.get("house_number");
        this.neighbourhood = (String) json.get("neighbourhood");
        this.postal_code = (String) json.get("postal_code");
        this.city = (String) json.get("city");
        this.state = (String) json.get("state");
        this.country = (String) json.get("country");
    }

    public JSONObject transformToJsonObject() {
        Map<String, String> map = new HashMap<>();

        map.put("street", street);
        map.put("house_number", house_number);
        map.put("neighbourhood", neighbourhood);
        map.put("postal_code", postal_code);
        map.put("city", city);
        map.put("state", state);
        map.put("country", country);

        return new JSONObject(map);
    }

    public void updateAddress(Map<String, String> changes) {
        if (changes.containsKey("street"))
            this.street = changes.get("street");
        if (changes.containsKey("house_number"))
            this.house_number = changes.get("house_number");
        if (changes.containsKey("neighbourhood"))
            this.neighbourhood = changes.get("neighbourhood");
        if (changes.containsKey("postal_code"))
            this.postal_code = changes.get("postal_code");
        if (changes.containsKey("city"))
            this.city = changes.get("city");
        if (changes.containsKey("state"))
            this.state = changes.get("state");
        if (changes.containsKey("country"))
            this.country = changes.get("country");
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse_number() {
        return house_number;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Address [street=" + street + ", house_number=" + house_number + ", neighbourhood=" + neighbourhood
                + ", postal_code=" + postal_code + ", city=" + city + ", state=" + state + "]";
    }

}