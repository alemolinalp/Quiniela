package com.example.alexandramolina.quiniela;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String usuario;
    String password;
    TextView txtUsuario, txtContrasena;
    SharedPreferences sharedPreferences;
    CallbackManager callbackManager;
    TextView txtBirthday, txtEmail;
    ProgressDialog mDialog;
    ImageView imgAvatar;
    String email;
    //String usuario, contraseña;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }


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

        callbackManager = CallbackManager.Factory.create();

        final LoginButton loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mDialog = new ProgressDialog(MainActivity.this);
                mDialog.setMessage("Retrieving data...");
                mDialog.show();

                String accesstoken = loginResult.getAccessToken().getToken();

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        mDialog.dismiss();
                        Log.d("response", response.toString());
                        getData(object);
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields","id,email,birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        if(AccessToken.getCurrentAccessToken() != null){
            //txtEmail.setText(AccessToken.getCurrentAccessToken().getUserId());
        }
    }

    private void getData(JSONObject object){
        try{
            String imagen ="https://graph.facebook.com/" + object.getString("id")+"/picture?width=250&height=250";
            URL profile_picture = new URL(imagen);

            sharedPreferences = getSharedPreferences("com.example.alexandramolina.quiniela", Context.MODE_PRIVATE);
            sharedPreferences.edit().putString("imagen", imagen).apply();

            //Picasso.with(this).load(profile_picture.toString()).into(imgAvatar);

            email=object.getString("email");
            usuario  =email.split("@")[0];
            fb();
            abrirActivityPrincipal();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    public void fb(){

        StringRequest loginRequest = new StringRequest(Request.Method.POST, "https://celytranslate.herokuapp.com/v1/sessions/fb", new Response.Listener<String>() {
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
                        name = main.getString("name");
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
                params.put("email",email);
                params.put("password","123456");
                params.put("name",usuario);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(loginRequest);

    }

    public void abrirAyudaActivity(View v){
        Intent intent = new Intent(this, AyudaActivity.class);
        startActivity(intent);
    }


}
