package com.grupo4.models.parent_model;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.grupo4.models.Address;

public class User {
    private String id;
    private String name;
    private String document;
    private String email;
    private String password;
    private Address address;

    public User(String id, String name, String document, String email, String password, String street,
            String house_number, String neighbourhood, String postal_code, String city,
            String state, String country) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.email = email;
        this.password = password;
        this.address = new Address(street, house_number, neighbourhood, postal_code, city, state, country);
    }

    public User(JSONObject json) {
        this.id = (String) json.get("id");
        this.name = (String) json.get("name");
        this.document = (String) json.get("document");
        this.email = (String) json.get("email");
        this.password = (String) json.get("password");
        this.address = new Address((JSONObject) json.get("address"));
    }

    protected Map<String, Object> mappingValues() {
        Map<String, Object> map = new HashMap<>();

        map.put("id", this.id);
        map.put("name", this.name);
        map.put("document", this.document);
        map.put("email", this.email);
        map.put("password", this.password);
        map.put("address", this.address.transformToJsonObject());

        return map;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((document == null) ? 0 : document.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (document == null) {
            if (other.document != null)
                return false;
        } else if (!document.equals(other.document))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        return true;
    }
}
