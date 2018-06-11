package com.example.alexandramolina.quiniela;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoRusiaFragment extends Fragment {

    TextView nombre,codigo,region,subregion,moneda,simbolo,nativo,area;


    public InfoRusiaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_info_rusia, container, false);

        nombre=v.findViewById(R.id.Rusianombre);
        codigo=v.findViewById(R.id.Rusiacodigo);
        region=v.findViewById(R.id.Rusiaregion);
        subregion=v.findViewById(R.id.Rusiasubregion);
        moneda=v.findViewById(R.id.Rusiamoneda);
        simbolo=v.findViewById(R.id.Rusiasimbolomoneda);
        nativo=v.findViewById(R.id.Rusianativo);
        area=v.findViewById(R.id.Rusiaarea);

        rusia();




        return v;
    }

    public void rusia(){

        StringRequest loginRequest = new StringRequest(Request.Method.GET, "http://countryapi.gear.host/v1/Country/getCountries", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject json = null;

                try {
                    json = new JSONObject(response);
                    JSONArray array= json.getJSONArray("Response");
                    JSONObject jason= array.getJSONObject(183);
                    String nom= jason.getString("Name");
                    String code= jason.getString("Alpha3Code");
                    String nat= jason.getString("NativeName");
                    String reg= jason.getString("Region");
                    String sub= jason.getString("SubRegion");
                    String a= jason.getString("Area");
                    String mon= jason.getString("CurrencyName");
                    String sym= jason.getString("CurrencySymbol");

                    nombre.setText("Nombre: " + nom);
                    codigo.setText("Código: "+code);
                    nativo.setText("Nombre Nativo: "+ nat);
                    region.setText("Region: " + reg);
                    subregion.setText("Sub Region: "+ sub);
                    area.setText("Area: " + a);
                    moneda.setText("Moneda: " + mon);
                    simbolo.setText("Símbolo Moneda: "+ sym);

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
                params.put("pName","Costa Rica");

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(loginRequest);

    }

}
