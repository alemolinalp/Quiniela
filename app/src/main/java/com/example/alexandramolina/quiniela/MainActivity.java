package com.example.alexandramolina.quiniela;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirActivityPrincipal(){

        Intent intent = new Intent(this, UsuarioActivity.class);
        startActivity(intent);
    }

    public void iniciarSesion(View v){
        abrirActivityPrincipal();

    }
}
