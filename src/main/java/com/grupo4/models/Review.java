package com.grupo4.models;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class Review {
    private String id;

    private String product_name;
    private String product_id;
    private String client_name;
    private String client_id;
    private String message;
    private int grade;

    public Review(String id, String product_name, String product_id, String client_name, String client_id,
            String message, int grade) {
        this.id = id;
        this.product_name = product_name;
        this.product_id = product_id;
        this.client_name = client_name;
        this.client_id = client_id;
        this.message = message;
        this.grade = grade;
    }

    public Review(JSONObject json) {
        this.id = (String) json.get("id");
        this.product_id = (String) json.get("product_id");
        this.product_name = (String) json.get("product_name");
        this.client_id = (String) json.get("client_id");
        this.client_name = (String) json.get("client_name");
        this.message = (String) json.get("message");
        this.grade = ((Long) json.get("grade")).intValue();
    }

    public JSONObject transformToJsonObject() {
        Map<String, Object> map = new HashMap<>();

        map.put("id", this.id);
        map.put("product_id", this.product_id);
        map.put("product_name", this.product_name);
        map.put("client_id", this.client_id);
        map.put("client_name", this.client_name);
        map.put("message", this.message);
        map.put("grade", this.grade);

        return new JSONObject(map);
    }

    public void update(Map<String, String> changes) {
        if (changes.size() == 0)
            return;

        if (changes.containsKey("message"))
            this.message = changes.get("message");
        if (changes.containsKey("nota"))
            this.grade = Integer.parseInt(changes.get("nota"));
    }

    @Override
    public String toString() {
        String returnMessage = "id :" + this.id + "\n";
        returnMessage += "id do produto :" + this.product_id + "\n";
        returnMessage += "nome do produto :" + this.product_name + "\n";
        returnMessage += "id do cliente :" + this.client_id + "\n";
        returnMessage += "nome do cliente :" + this.client_name + "\n";
        returnMessage += "comentario :" + this.message + "\n";
        returnMessage += "nota :" + this.grade + "\n";
        return returnMessage;
    }

    public String getId() {
        return id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getClient_name() {
        return client_name;
    }

    public String getClient_id() {
        return client_id;
    }

    public String getMessage() {
        return message;
    }

    public int getGrade() {
        return grade;
    }
}
