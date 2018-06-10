package com.example.alexandramolina.quiniela;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alexandramolina.quiniela.adapters.PartidoAdapter;
import com.example.alexandramolina.quiniela.clases.Partido;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PrediccionActivity extends AppCompatActivity {

    ArrayList<Partido> partidos;
    PartidoAdapter adapter;
    ListView lv;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediccion);

        lv= findViewById(R.id.lvPrediccion);
        id= getIntent().getStringExtra("idJuego");


        partidos = new ArrayList<Partido>();
        adapter= new PartidoAdapter(getApplicationContext(),R.layout.partido_item,partidos);
        obtenerPartidos();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("Prueba","holaa");
                String resultado;
                Spinner spinner;


                for (int j = 0; j < 48; i++)
                {

                    spinner =  view.findViewById(R.id.spinner);
                    resultado= spinner.getSelectedItem().toString();
                    Log.d("Prueba",resultado);
                }
            }
        });

        lv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                String resultado;
                Spinner spinner;


                for (int i = 0; i < 48; i++)
                {

                    spinner =  view.findViewById(R.id.spinner);
                    resultado= spinner.getSelectedItem().toString();
                    Log.d("Prueba",resultado);
                }

                return true;
            }
        });



    }


    public void btnGuardar(View v){


        for(Partido p:partidos){
            Log.d("PRUEBA",p.getResultado());
        }

    }

    public void obtenerPartidos(){

        StringRequest registroRequest = new StringRequest(Request.Method.GET, "https://quinielaapp.herokuapp.com/v1/partidos", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject json = null;

                String status="";
                String message="";
                try {
                    json = new JSONObject(response);
                    status=json.getString("status");
                    message=json.getString("message");
                    Log.d("RESPONSE",response);
                    int id1,id2;
                    if(status.equals("Success")){
                        JSONArray jsonArray = new JSONArray(json.getString("data"));
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            id1 = Integer.parseInt(jsonObject1.getString("equipo1"));
                            id2 = Integer.parseInt(jsonObject1.getString("equipo2"));
                            partidos.add(new Partido(id1, id2));
                        }
                        lv.setAdapter(adapter);


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

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(registroRequest);

    }


    public void guardarResultado(){

        StringRequest registroRequest = new StringRequest(Request.Method.POST, "https://quinielaapp.herokuapp.com/v1/resultados", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject json = null;

                String status="";
                String message="";
                try {
                    json = new JSONObject(response);
                    status=json.getString("status");
                    message=json.getString("message");
                    Log.d("RESPONSE",response);
                    int id1,id2;
                    if(status.equals("Success")){
                        JSONArray jsonArray = new JSONArray(json.getString("data"));
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            id1 = Integer.parseInt(jsonObject1.getString("equipo1"));
                            id2 = Integer.parseInt(jsonObject1.getString("equipo2"));
                            partidos.add(new Partido(id1, id2));
                        }
                        lv.setAdapter(adapter);


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

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(registroRequest);

    }
}
