package com.example.alexandramolina.quiniela.clases;

import com.example.alexandramolina.quiniela.R;

/**
 * Created by Usuario on 08/06/2018.
 */

public class Partido {

    private int idEquipo1;
    private int idEquipo2;
    private String lugar;
    private String fecha;
    private String resultado;
    private String golesequipo1;
    private String getGolesequipo2;


    public Partido(int idEquipo1, int idEquipo2) {
        this.idEquipo1 = idEquipo1;
        this.idEquipo2 = idEquipo2;
        resultado="1";
    }

    public Partido(int idEquipo1, int idEquipo2, String lugar, String fecha, String golesequipo1, String getGolesequipo2) {
        this.idEquipo1 = idEquipo1;
        this.idEquipo2 = idEquipo2;
        this.lugar = lugar;
        this.fecha = fecha;
        this.golesequipo1 = golesequipo1;
        this.getGolesequipo2 = getGolesequipo2;
    }

    public void setIdEquipo1(int idEquipo1) {
        this.idEquipo1 = idEquipo1;
    }

    public void setIdEquipo2(int idEquipo2) {
        this.idEquipo2 = idEquipo2;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getGolesequipo1() {
        return golesequipo1;
    }

    public void setGolesequipo1(String golesequipo1) {
        this.golesequipo1 = golesequipo1;
    }

    public String getGetGolesequipo2() {
        return getGolesequipo2;
    }

    public void setGetGolesequipo2(String getGolesequipo2) {
        this.getGolesequipo2 = getGolesequipo2;
    }

    public int getIdEquipo1() {
        return idEquipo1;
    }

    public int getIdEquipo2() {
        return idEquipo2;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getTotal(){
        int total1=0;
        total1 += Integer.parseInt(getGolesequipo1());
        total1 += Integer.parseInt(getGetGolesequipo2());

        return Integer.toString(total1);
    }
}

