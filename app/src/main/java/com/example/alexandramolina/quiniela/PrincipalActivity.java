package com.example.alexandramolina.quiniela;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PrincipalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ActionBar actionBar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    SharedPreferences sharedPreferences;
    NavigationView nv;
    android.support.v4.app.FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.container,new InicioFragment()).commit();

        setNavigationViewListener();
        nv=findViewById(R.id.navigation_view);

        setHeader();



        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#233a62")));

        mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setNavigationViewListener(){
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.info:
                fm.beginTransaction().replace(R.id.container,new GruposFragment()).commit();
                break;
            case R.id.usuario:
                fm.beginTransaction().replace(R.id.container, new UsuarioFragment()).commit();
                break;
            case R.id.cerrarSesion:
                sharedPreferences = getSharedPreferences("com.example.alexandramolina.quiniela", Context.MODE_PRIVATE);
                sharedPreferences.edit().putString("authentication_token", "").apply();
                sharedPreferences.edit().putString("id", "").apply();
                sharedPreferences.edit().putString("email", "").apply();
                sharedPreferences.edit().putString("name", "").apply();
                abrirMainActivity();
                break;
            case R.id.quiniela:
                fm.beginTransaction().replace(R.id.container,new InicioFragment()).commit();
                break;
            case R.id.quinielas:
                fm.beginTransaction().replace(R.id.container,new MisQuinielasFragment()).commit();
                break;


        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    public void abrirMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

    }
    private void setHeader(){
        sharedPreferences = getSharedPreferences("com.example.alexandramolina.quiniela", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        String name = sharedPreferences.getString("name", "");
        View header = nv.getHeaderView(0);
        TextView headerEmail =  header.findViewById(R.id.headerEmail);
        TextView headerName =  header.findViewById(R.id.headerName);
        headerEmail.setText(email);
        headerName.setText(name);
    }
}