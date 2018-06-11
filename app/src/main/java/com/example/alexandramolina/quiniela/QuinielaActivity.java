package com.example.alexandramolina.quiniela;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class QuinielaActivity extends AppCompatActivity {

    TextView nombre,codigo,monto,participantes;
    String id,nom,cod,mon,par;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiniela);

        nombre=findViewById(R.id.Nombreq);
        codigo=findViewById(R.id.Codigoq);
        monto=findViewById(R.id.Montoq);
        participantes=findViewById(R.id.Participantesq);

        id= getIntent().getStringExtra("idJuego");
        nom= getIntent().getStringExtra("nombre");
        cod= getIntent().getStringExtra("codigo");
        mon= getIntent().getStringExtra("monto");
        par= getIntent().getStringExtra("participantes");

        nombre.setText(nom);
        codigo.setText("Código de invitación: "+ cod);
        monto.setText(mon + " colones");
        participantes.setText(par + " máximo de participantes");



    }

    public void tablaPosiciones(View v){
        Intent intent;
        intent = new Intent(this,PosicionesActivity.class);

        startActivity(intent);
    }
}
