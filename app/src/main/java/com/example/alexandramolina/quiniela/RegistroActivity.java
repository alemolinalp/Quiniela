package com.example.alexandramolina.quiniela;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

    EditText txtEmail, txtUsuario, txtPassword, txtConfirmPassword;
    String usuario,password,email;
    AwesomeValidation awesomeValidation;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        txtEmail = findViewById(R.id.txtEmail);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword);

        String regexPassword = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

        awesomeValidation.addValidation(RegistroActivity.this, R.id.txt_usuario, "[a-zA-Z\\s]+",R.string.fusuario);
        awesomeValidation.addValidation(RegistroActivity.this, R.id.txtEmail, Patterns.EMAIL_ADDRESS,R.string.femail);
        awesomeValidation.addValidation(RegistroActivity.this, R.id.txtPassword,regexPassword,R.string.fpassword);
        awesomeValidation.addValidation(RegistroActivity.this, R.id.txtConfirmPassword, R.id.txtPassword,R.string.fConfirmpassword);

    }

    public void registrarse(View v) {
        usuario=txtUsuario.getText().toString();
        password=txtPassword.getText().toString();
        email= txtEmail.getText().toString();

        if(awesomeValidation.validate()){


            registro();
            abrirActivityPrincipal();
            Toast.makeText(RegistroActivity.this, "Data received Succesfully", Toast.LENGTH_SHORT);
        }
        else{
            Toast.makeText(RegistroActivity.this, "Error", Toast.LENGTH_SHORT);
        }
    }

    public void registro(){

        StringRequest registroRequest = new StringRequest(Request.Method.POST, "https://quinielaapp.herokuapp.com/v1/usuarios", new Response.Listener<String>() {
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
                    Log.d("RESPONSE",response);
                    if(status.equals("Success")){
                        JSONObject main = new JSONObject(json.getString("data"));
                        name = main.getString("name");
                        email = main.getString("email");
                        id = main.getString("id");
                        Log.d("PRUEBA",id);
                        Log.d("PRUEBA",email);
                        Log.d("PRUEBA",name);
                        authentication_token = main.getString("authentication_token");
                        sharedPreferences = getSharedPreferences("com.example.alexandramolina.quiniela", Context.MODE_PRIVATE);
                        sharedPreferences.edit().putString("authentication_token", authentication_token).apply();
                        sharedPreferences.edit().putString("id", id).apply();
                        sharedPreferences.edit().putString("email", email).apply();
                        sharedPreferences.edit().putString("name", usuario).apply();
                        abrirActivityPrincipal();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
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
                Log.d("PRUEBA",usuario);
                Log.d("PRUEBA",email);
                Log.d("PRUEBA",password);
                params.put("email",email);
                params.put("contrasena",password);
                params.put("nombre",usuario);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(registroRequest);

    }

    public void abrirActivityPrincipal(){

        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }
}
