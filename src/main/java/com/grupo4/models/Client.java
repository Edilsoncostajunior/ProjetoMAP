package com.grupo4.models;

import java.util.Map;

import org.json.simple.JSONObject;

import com.grupo4.models.parent_model.User;

public class Client extends User {
    public Client(String id, String name, String document, String email, String password, String street,
            String house_number, String neighbourhood, String postal_code, String city, String state,
            String country) {
        super(id, name, document, email, password, street, house_number, neighbourhood, postal_code, city, state,
                country);

        // Campos específicos
    }

    public Client(JSONObject json) {
        super(json);

        // Campos específicos
    }

    public JSONObject transformToJsonObject() {
        Map<String, Object> map = mappingValues();

        return new JSONObject(map);
    }

    @Override
    public void update(Map<String, String> changes) {
        super.update(changes);

    }

    @Override
    public String toString() {
        String returnMessage = super.toString();

        return returnMessage;
    }
}