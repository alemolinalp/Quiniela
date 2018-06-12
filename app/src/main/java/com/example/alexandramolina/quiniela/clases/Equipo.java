package com.example.alexandramolina.quiniela.clases;

public class Equipo
{
    private String id,nombre,puntos,jugados,ganados,empatados,perdidos,favor,contra,grupo;

    public Equipo(String id, String nombre, String puntos, String jugados, String ganados, String empatados, String perdidos, String favor, String contra,String grupo) {
        this.id = id;
        this.nombre = nombre;
        this.puntos = puntos;
        this.jugados = jugados;
        this.ganados = ganados;
        this.empatados = empatados;
        this.perdidos = perdidos;
        this.favor = favor;
        this.contra = contra;
        this.grupo =grupo;
    }


    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }

    public String getJugados() {
        return jugados;
    }

    public void setJugados(String jugados) {
        this.jugados = jugados;
    }

    public String getGanados() {
        return ganados;
    }

    public void setGanados(String ganados) {
        this.ganados = ganados;
    }

    public String getEmpatados() {
        return empatados;
    }

    public void setEmpatados(String empatados) {
        this.empatados = empatados;
    }

    public String getPerdidos() {
        return perdidos;
    }

    public void setPerdidos(String perdidos) {
        this.perdidos = perdidos;
    }

    public String getFavor() {
        return favor;
    }

    public void setFavor(String favor) {
        this.favor = favor;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
}
