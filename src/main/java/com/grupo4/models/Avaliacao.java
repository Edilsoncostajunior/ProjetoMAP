package com.grupo4.models;

import org.json.simple.JSONObject;

public class Avaliacao {
    private int nota;
    private String comment;

    public Avaliacao(int nota, String comment) {
        this.nota = nota;
        this.comment = comment;
    }

    public Avaliacao(JSONObject json) {
        this.nota = ((Long) json.get("nota")).intValue();
        this.comment = (String) json.get("comment");
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Avaliacao [nota=" + nota + ", comment=" + comment + "]";
    }
}
