package com.example.alexandramolina.quiniela;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alexandramolina.quiniela.adapters.PartidoAdapter;
import com.example.alexandramolina.quiniela.adapters.QuinielaAdapter;
import com.example.alexandramolina.quiniela.clases.Partido;
import com.example.alexandramolina.quiniela.clases.Quiniela;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MisQuinielasFragment extends Fragment {

    ListView lv;
    SharedPreferences sharedPreferences;
    ArrayList<Quiniela> quinielas;
    QuinielaAdapter adapter;


    public MisQuinielasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_mis_quinielas, container, false);

        lv=v.findViewById(R.id.listQ);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent;
                intent = new Intent(getActivity(),QuinielaActivity.class);

                intent.putExtra("idJuego", quinielas.get(i).getId());
                intent.putExtra("nombre",quinielas.get(i).getNombre());
                intent.putExtra("codigo", quinielas.get(i).getCodigo());
                intent.putExtra("monto", quinielas.get(i).getMonto());
                intent.putExtra("participantes", quinielas.get(i).getParticipantes());

                startActivity(intent);


            }
        });
        quinielas = new ArrayList<Quiniela>();
        adapter= new QuinielaAdapter(v.getContext(),R.layout.quiniela_item,quinielas);
        obtenerQuinielas();

        return v;
    }

    public void obtenerQuinielas(){


        StringRequest registroRequest = new StringRequest(Request.Method.POST, "https://quinielaapp.herokuapp.com/v1/juegos/obtenerQuinielas", new Response.Listener<String>() {
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
                    String nom,cod,par,mon,authentication_token,id;
                    id="";
                    if(status.equals("Success")){
                        JSONArray jsonArray = new JSONArray(json.getString("data"));
                        for(int j=0; j < jsonArray.length(); j++){
                            JSONArray array = jsonArray.getJSONArray(j);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject1 = array.getJSONObject(i);
                                nom= jsonObject1.getString("nombre");
                                cod= jsonObject1.getString("codigo");
                                mon= jsonObject1.getString("monto");
                                par= jsonObject1.getString("participantes");
                                id = jsonObject1.getString("id");

                                quinielas.add(new Quiniela(nom,mon,cod,par,id));
                            }
                            lv.setAdapter(adapter);
                        }
                        authentication_token = json.getString("authentication_token");
                        sharedPreferences = getContext().getSharedPreferences("com.example.alexandramolina.quiniela", Context.MODE_PRIVATE);
                        sharedPreferences.edit().putString("authentication_token", authentication_token).apply();
                        sharedPreferences.edit().putString("idJuego",id).apply();

                    }
                    else {
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                        Log.d("ERROR",message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();

                if (null != error.networkResponse)
                {
                    Log.d("TAG" + ": ", "Error Response code: " + error.networkResponse.statusCode);
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                sharedPreferences = getContext().getSharedPreferences("com.example.alexandramolina.quiniela", Context.MODE_PRIVATE);
                String user_id = sharedPreferences.getString("id", "");
                String authentication_token = sharedPreferences.getString("authentication_token", "");

                Log.d("PRUEBA",user_id);
                Log.d("PRUEBA",authentication_token);

                params.put("id",user_id);
                params.put("authentication_token",authentication_token);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(registroRequest);

    }



}
