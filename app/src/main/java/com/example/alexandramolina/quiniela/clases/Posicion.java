package com.example.alexandramolina.quiniela.clases;

public class Posicion

{

    String nombre;
    String aciertos;
    int posicion;

    public Posicion(String nombre,String aciertos) {
        this.nombre = nombre;
        this.aciertos=aciertos;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
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
