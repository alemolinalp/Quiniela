package com.example.alexandramolina.quiniela;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class CrearQuinielaFragment extends Fragment {

    EditText nombre,monto,participantes;
    TextView code;
    Button crear;
    SharedPreferences sharedPreferences;
    View v;
    String codigo=" ";
    String id="";
    public CrearQuinielaFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_crear_quiniela,container,false);

        nombre= v.findViewById(R.id.nombreQuiniela);
        monto= v.findViewById(R.id.monto);
        participantes= v.findViewById(R.id.participantes);
        code=v.findViewById(R.id.codigo_txt);
        crear= v.findViewById(R.id.crearQuiniela);
        code.setText(codigo);


        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                crearQuiniela(nombre.getText().toString(),monto.getText().toString(),participantes.getText().toString());
                Log.d("CODIGO",codigo);

                nombre.setText("");
                monto.setText("");
                participantes.setText("");

            }
        });

        return v;
    }

    public void crearQuiniela(final String n, final String m, final String p){

        StringRequest registroRequest = new StringRequest(Request.Method.POST, "https://quinielaapp.herokuapp.com/v1/juegos", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject json = null;
                String name="";
                codigo="";
                id="";
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
                        name = main.getString("nombre");
                        codigo = main.getString("codigo");
                        id = main.getString("id");
                        authentication_token = json.getString("authentication_token");
                        Log.d("PRUEBA",authentication_token);
                        Log.d("idJEUGo",id);
                        Log.d("PRUEBA",name);
                        sharedPreferences = v.getContext().getSharedPreferences("com.example.alexandramolina.quiniela", Context.MODE_PRIVATE);
                        sharedPreferences.edit().putString("authentication_token", authentication_token).apply();
                        sharedPreferences.edit().putString("idJuego",id).apply();
                        code.setText(codigo);

                        abrirPrediccion(id);

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

                Log.d("PRUEBA",authentication_token);
                Log.d("PRUEBA",user_id);
                Log.d("PRUEBA",monto.getText().toString());
                Log.d("PRUEBA",nombre.getText().toString());
                Log.d("PRUEBA",participantes.getText().toString());

                params.put("id",user_id);
                params.put("authentication_token",authentication_token);
                params.put("monto",m);
                params.put("nombre",n);
                params.put("participantes",p);



                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(registroRequest);

    }

    public void abrirPrediccion(String i){

        Intent intent;
        intent = new Intent(getActivity(),PrediccionActivity.class);
        sharedPreferences = v.getContext().getSharedPreferences("com.example.alexandramolina.quiniela", Context.MODE_PRIVATE);
        String idJuego = sharedPreferences.getString("idJuego", "");




        intent.putExtra("idJuego", i);
        Log.d("IIID",idJuego);
        startActivity(intent);
    }

}
