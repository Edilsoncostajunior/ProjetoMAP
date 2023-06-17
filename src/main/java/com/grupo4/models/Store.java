package com.grupo4.models;

import org.json.simple.JSONObject;
import com.grupo4.models.parent_model.User;
import java.util.Map;

public class Store extends User {
    public Store(String id, String name, String email, String password, String document, String street,
            String house_number, String neighbourhood, String postal_code, String city, String state,
            String country) {
        super(id, name, document, email, password, street, house_number, neighbourhood, postal_code, city, state,
                country);
    }

    public Store(JSONObject json) {
        super(json);
    }

    @Override
    public void update(Map<String, String> changes) {
        super.update(changes);

    }

    public JSONObject transformToJsonObject() {
        Map<String, Object> map = mappingValues();

        return new JSONObject(map);
    }

    @Override
    public String toString() {
        String returnMessage = super.toString();

        return returnMessage;
    }
}
