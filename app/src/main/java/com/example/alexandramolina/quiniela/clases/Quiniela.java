package com.example.alexandramolina.quiniela.clases;

public class Quiniela {

    private String nombre,monto,codigo,participantes,id;

    public Quiniela(String nombre, String monto, String codigo, String participantes,String id) {
        this.nombre = nombre;
        this.monto = monto;
        this.codigo = codigo;
        this.participantes = participantes;
        this.id = id;
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

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getParticipantes() {
        return participantes;
    }

    public void setParticipantes(String participantes) {
        this.participantes = participantes;
    }
}
