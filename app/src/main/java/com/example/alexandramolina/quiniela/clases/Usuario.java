package com.example.alexandramolina.quiniela.clases;

public class Usuario {

    String id,nombrer;

    public Usuario(String id, String nombrer) {
        this.id = id;
        this.nombrer = nombrer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombrer() {
        return nombrer;
    }

    public void setNombrer(String nombrer) {
        this.nombrer = nombrer;
    }
}
