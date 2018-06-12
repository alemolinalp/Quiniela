package com.example.alexandramolina.quiniela;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
public class GruposFragment extends Fragment {

    ImageView a,b,c,d,e,f,g,h;
    String grupo;


    public GruposFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_grupos, container, false);

        a= v.findViewById(R.id.a);
        b= v.findViewById(R.id.b);
        c= v.findViewById(R.id.c);
        d= v.findViewById(R.id.d);
        e= v.findViewById(R.id.e);
        f= v.findViewById(R.id.f);
        g= v.findViewById(R.id.g);
        h= v.findViewById(R.id.h);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grupo="1";
                abrirGrupoActivity();
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grupo="2";
                abrirGrupoActivity();
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grupo="3";
                abrirGrupoActivity();
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grupo="4";
                abrirGrupoActivity();
            }
        });
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grupo="5";
                abrirGrupoActivity();
            }
        });
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grupo="6";
                abrirGrupoActivity();
            }
        });
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grupo="7";
                abrirGrupoActivity();
            }
        });
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grupo="8";
                abrirGrupoActivity();
            }
        });





        return v;
    }
    public void abrirGrupoActivity(){
        Intent intent = new Intent(getContext(), GrupoActivity.class);
        intent.putExtra("grupo", grupo);
        startActivity(intent);

    }



}
