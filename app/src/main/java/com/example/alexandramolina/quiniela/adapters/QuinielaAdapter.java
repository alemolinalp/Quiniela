package com.example.alexandramolina.quiniela.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.alexandramolina.quiniela.R;
import com.example.alexandramolina.quiniela.clases.Partido;
import com.example.alexandramolina.quiniela.clases.Quiniela;

import java.util.ArrayList;

public class QuinielaAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Quiniela> arrayList;
    private LayoutInflater inflater;

    public QuinielaAdapter(Context context, int layout, ArrayList<Quiniela> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Quiniela getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView nombre,monto,participantes,codigo;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 500;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final QuinielaAdapter.ViewHolder viewHolder;

        if(view == null){
            viewHolder = new QuinielaAdapter.ViewHolder();
            view = inflater.inflate(layout,null);
            viewHolder.nombre= view.findViewById(R.id.nomQuiniela);
            viewHolder.codigo= view.findViewById(R.id.codigoQ);
            viewHolder.monto=view.findViewById(R.id.montoQ);
            viewHolder.participantes=view.findViewById(R.id.participantesQ);


            view.setTag(viewHolder);
        }
        else{
            viewHolder = (QuinielaAdapter.ViewHolder) view.getTag();
        }

        final Quiniela quiniela = arrayList.get(i);
        viewHolder.nombre.setText(quiniela.getNombre());
        viewHolder.codigo.setText("Código de invitación: "+quiniela.getCodigo());
        viewHolder.monto.setText(quiniela.getMonto()+ " colones");
        viewHolder.participantes.setText(quiniela.getParticipantes()+ " máximo de participantes");

        return view;
    }
}
