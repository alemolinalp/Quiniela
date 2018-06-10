package com.example.alexandramolina.quiniela;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String usuario;
    String password;
    TextView txtUsuario, txtContrasena;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("com.example.alexandramolina.quiniela", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "");
        if(!(id.equals(""))){
            abrirActivityPrincipal();
        }


        txtUsuario=findViewById(R.id.txt_usuario);
        txtContrasena=findViewById(R.id.txt_contrasena);
    }

    public void abrirActivityPrincipal(){
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }

    public void iniciarSesion(View v){

        usuario=  txtUsuario.getText().toString();
        password= txtContrasena.getText().toString();
        iniciarSesion();

    }
    public void registro(View v){

        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }
    public void iniciarSesion(){

        StringRequest loginRequest = new StringRequest(Request.Method.POST, "https://quinielaapp.herokuapp.com/v1/usuarios/login", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject json = null;
                String name="";
                String email="";
                String id="";
                String status="";
                String message="";
                String authentication_token="";
                try {
                    json = new JSONObject(response);
                    status=json.getString("status");
                    message=json.getString("message");
                    if(status.equals("Success")){
                        JSONObject main = new JSONObject(json.getString("data"));
                        name = main.getString("nombre");
                        email = main.getString("email");
                        id = main.getString("id");
                        authentication_token = main.getString("authentication_token");
                        sharedPreferences = getSharedPreferences("com.example.alexandramolina.quiniela", Context.MODE_PRIVATE);
                        sharedPreferences.edit().putString("authentication_token", authentication_token).apply();
                        sharedPreferences.edit().putString("id", id).apply();
                        sharedPreferences.edit().putString("email", email).apply();
                        sharedPreferences.edit().putString("name", name).apply();
                        abrirActivityPrincipal();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
                        Log.d("ERROR",message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email",usuario);
                params.put("contrasena",password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(loginRequest);

    }

    @Override
    public void onBackPressed() {

    }

}
