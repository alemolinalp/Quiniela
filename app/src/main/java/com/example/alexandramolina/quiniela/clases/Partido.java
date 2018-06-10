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


    public Partido(int idEquipo1, int idEquipo2) {
        this.idEquipo1 = idEquipo1;
        this.idEquipo2 = idEquipo2;
        resultado="1";
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
}

