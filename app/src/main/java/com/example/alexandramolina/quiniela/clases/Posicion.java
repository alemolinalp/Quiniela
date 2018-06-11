package com.example.alexandramolina.quiniela.clases;

public class Posicion

{

    String nombre;
    String aciertos;

    public Posicion(String nombre,String aciertos) {
        this.nombre = nombre;
        this.aciertos=aciertos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAciertos() {
        return aciertos;
    }

    public void setAciertos(String aciertos) {
        this.aciertos = aciertos;
    }
}
