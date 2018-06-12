package com.example.alexandramolina.quiniela;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alexandramolina.quiniela.adapters.CalendarioAdapter;
import com.example.alexandramolina.quiniela.adapters.PartidoAdapter;
import com.example.alexandramolina.quiniela.clases.Partido;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarioFragment extends Fragment {

    ArrayList<Partido> partidos;
    CalendarioAdapter adapter;
    ListView lv;
    String id="";
    SharedPreferences sharedPreferences;
    RadioButton lugar,goles;


    public CalendarioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_calendario, container, false);

        lv= v.findViewById(R.id.listCalendario);
        lugar=v.findViewById(R.id.radioLugar);
        goles=v.findViewById(R.id.radioGoles);

        lugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.ordenarLugar();
                Log.d("Hola","soy el buttonlugar" );
            }
        });

        goles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.ordenarGoles();
                Log.d("Hola","soy el buttongoles");
            }
        });

        partidos = new ArrayList<Partido>();

        adapter= new CalendarioAdapter(getContext(),R.layout.calendario_item,partidos);
        obtenerPartidos();


        return v;
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
                    String fecha,lugar,gol1,gol2;
                    if(status.equals("Success")){
                        JSONArray jsonArray = new JSONArray(json.getString("data"));
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            id1 = Integer.parseInt(jsonObject1.getString("equipo1"));
                            id2 = Integer.parseInt(jsonObject1.getString("equipo2"));
                            fecha= jsonObject1.getString("fecha");
                            lugar= jsonObject1.getString("lugar");
                            gol1=jsonObject1.getString("golesEquipo1");
                            gol2= jsonObject1.getString("golesEquipo2");
                            partidos.add(new Partido(id1, id2,lugar,fecha,gol1,gol2));
                        }
                        lv.setAdapter(adapter);


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
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(registroRequest);

    }


}
