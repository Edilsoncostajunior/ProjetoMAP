package com.grupo4.models;

import org.json.simple.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class Store {
    private String id;
    private String name;
    private String email;
    private String password;
    private String documento;
    private Address address;

    public Store(String name, String email, String password, String documento, Address address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.documento = documento;
        this.address = address;
    }

    public Store(JSONObject json) {
        this.id = (String) json.get("id");
        this.name = (String) json.get("name");
        this.email = (String) json.get("email");
        this.password = (String) json.get("password");
        this.documento = (String) json.get("documento");
        this.address = new Address((JSONObject) json.get("address"));
    }

    
    public void valuesStore(Map<String, String> changes) {
        if (changes.size() == 0)
            return;

        if (changes.containsKey("name"))
            this.name = changes.get("name");
        if (changes.containsKey("documento"))
            this.documento = changes.get("documento");
        if (changes.containsKey("email"))
            this.email = changes.get("email");
        if (changes.containsKey("password"))
            this.password = changes.get("password");

        this.address.valuesAddress(changes);
    }

    public JSONObject transformToJsonObject() {
        Map<String, Object> map = new HashMap<>();

        map.put("id", this.id);
        map.put("name", this.name);
        map.put("documento", this.documento);
        map.put("email", this.email);
        map.put("password", this.password);
        map.put("address", this.address.transformToJsonObject());

        return new JSONObject(map);
    }

    @Override
    public String toString() {
        return "Store [name=" + name + ", email=" + email + ", password=" + password + ", documento=" + documento
                + ", address=" + address + "]";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getdocumento() {
        return documento;
    }

    public void setdocumento(String documento) {
        this.documento = documento;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((documento == null) ? 0 : documento.hashCode());
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
        Store other = (Store) obj;
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
        if (documento == null) {
            if (other.documento != null)
                return false;
        } else if (!documento.equals(other.documento))
            return false;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        return true;
    }

}
