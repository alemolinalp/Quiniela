package com.example.alexandramolina.quiniela;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class GruposFragment extends Fragment {

    ImageView a,b,c,d,e,f,g,h;


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
                abrirGrupoActivity();
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirGrupoActivity();
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirGrupoActivity();
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirGrupoActivity();
            }
        });
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirGrupoActivity();
            }
        });
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirGrupoActivity();
            }
        });
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirGrupoActivity();
            }
        });
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirGrupoActivity();
            }
        });





        return v;
    }
    public void abrirGrupoActivity(){
        Intent intent = new Intent(getContext(), GrupoActivity.class);
        startActivity(intent);

    }

}
