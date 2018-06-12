package com.example.alexandramolina.quiniela;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alexandramolina.quiniela.adapters.PartidoAdapter;
import com.example.alexandramolina.quiniela.adapters.PosicionAdapter;
import com.example.alexandramolina.quiniela.clases.Partido;
import com.example.alexandramolina.quiniela.clases.Posicion;
import com.example.alexandramolina.quiniela.clases.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PosicionesActivity extends AppCompatActivity {

    ArrayList<Posicion> posiciones;

    PosicionAdapter adapter;
    ListView lv;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posiciones);

        lv=findViewById(R.id.listaPosicion);

        posiciones = new ArrayList<Posicion>();

        adapter= new PosicionAdapter(getApplicationContext(),R.layout.posicion_item,posiciones);
        obtenerPosiciones();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView sv=(SearchView) item.getActionView();

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    public void obtenerPosiciones(){

        StringRequest registroRequest = new StringRequest(Request.Method.POST, "https://quinielaapp.herokuapp.com/v1/juegos/actualizar", new Response.Listener<String>() {
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
                    String id,acierto,authentication_token;
                    if(status.equals("Success")){
                        JSONArray jsonArray = new JSONArray(json.getString("data"));
                        JSONArray usu = new JSONArray(json.getString("usuarios"));
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            JSONObject jsonObject = usu.getJSONArray(i).getJSONObject(0);
                            id = jsonObject.getString("nombre");
                            acierto = jsonObject1.getString("aciertos");
                            posiciones.add(new Posicion(id,acierto));
                        }
                        Collections.reverse(posiciones);

                        lv.setAdapter(adapter);

                        authentication_token = json.getString("authentication_token");
                        sharedPreferences = getApplicationContext().getSharedPreferences("com.example.alexandramolina.quiniela", Context.MODE_PRIVATE);
                        sharedPreferences.edit().putString("authentication_token", authentication_token).apply();


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

                sharedPreferences = getApplicationContext().getSharedPreferences("com.example.alexandramolina.quiniela", Context.MODE_PRIVATE);
                String user_id = sharedPreferences.getString("id", "");
                String idJuego = sharedPreferences.getString("idJuego", "");
                String authentication_token = sharedPreferences.getString("authentication_token", "");

                params.put("idUsuario",user_id);
                params.put("authentication_token",authentication_token);
                params.put("idQuiniela",idJuego);


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(registroRequest);

    }


}
