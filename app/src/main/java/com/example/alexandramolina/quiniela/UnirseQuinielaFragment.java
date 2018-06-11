package com.example.alexandramolina.quiniela;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public class UnirseQuinielaFragment extends Fragment {


//vF4cf
    EditText codigo;
    Button unirse;
    SharedPreferences sharedPreferences;
    View v;

    public UnirseQuinielaFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_quiniela, container, false);

        unirse=v.findViewById(R.id.unirse);
        codigo= v.findViewById(R.id.codigoQuiniela);

        unirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                añadirParticipante();
                abrirPrediccion();
            }
        });

        return v;
    }

    public void añadirParticipante(){

        StringRequest registroRequest = new StringRequest(Request.Method.POST, "https://quinielaapp.herokuapp.com/v1/juegos/add", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject json = null;

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
                        id = main.getString("idJuego");
                        authentication_token = json.getString("authentication_token");
                        Log.d("PRUEBA",id);
                        sharedPreferences = v.getContext().getSharedPreferences("com.example.alexandramolina.quiniela", Context.MODE_PRIVATE);
                        sharedPreferences.edit().putString("authentication_token", authentication_token).apply();
                        sharedPreferences.edit().putString("idJuego",id).apply();


                    }
                    else {
                        Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
                        Log.d("ERROR",message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                sharedPreferences = v.getContext().getSharedPreferences("com.example.alexandramolina.quiniela", Context.MODE_PRIVATE);
                String user_id = sharedPreferences.getString("id", "");
                String authentication_token = sharedPreferences.getString("authentication_token", "");

                params.put("id",user_id);
                params.put("authentication_token",authentication_token);
                params.put("codigo",codigo.getText().toString());


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(registroRequest);

    }
    public void abrirPrediccion(){
        Intent intent;
        intent = new Intent(getActivity(),PrediccionActivity.class);
        sharedPreferences = v.getContext().getSharedPreferences("com.example.alexandramolina.quiniela", Context.MODE_PRIVATE);
        String idJuego = sharedPreferences.getString("idJuego", "");

        intent.putExtra("idJuego", idJuego);
        startActivity(intent);
    }




}
