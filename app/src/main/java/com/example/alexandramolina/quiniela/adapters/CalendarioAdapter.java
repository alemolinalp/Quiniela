package com.example.alexandramolina.quiniela.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CalendarioAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Partido> arrayList;
    private LayoutInflater inflater;
    private ArrayList<Partido> temporal= new ArrayList<Partido>();


    public CalendarioAdapter(Context context, int layout, ArrayList<Partido> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
        temporal=arrayList;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Partido getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        ImageView equipo1;
        ImageView equipo2;
        TextView goles1,goles2,lugar,fecha;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 500;
    }

    public void ordenarLugar(){

        Collections.sort(arrayList, new Comparator<Partido>() {
            public int compare(Partido obj1, Partido obj2) {
                return obj1.getLugar().compareTo(obj2.getLugar());
            }
        });

        notifyDataSetChanged();

    }
    public void ordenarGoles(){

        Collections.sort(arrayList, new Comparator<Partido>() {
            public int compare(Partido obj1, Partido obj2) {

                return obj1.getTotal().compareTo(obj2.getTotal());
            }
        });

        notifyDataSetChanged();

    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final CalendarioAdapter.ViewHolder viewHolder;

        if(view == null){
            viewHolder = new CalendarioAdapter.ViewHolder();
            view = inflater.inflate(layout,null);
            viewHolder.equipo1= view.findViewById(R.id.equipo1img);
            viewHolder.equipo2= view.findViewById(R.id.equipo2img);
            viewHolder.fecha= view.findViewById(R.id.fechaCalendario);
            viewHolder.goles1= view.findViewById(R.id.goleq1);
            viewHolder.goles2= view.findViewById(R.id.goleq2);
            viewHolder.lugar= view.findViewById(R.id.lugarCalendario);


            view.setTag(viewHolder);
        }
        else{
            viewHolder = (CalendarioAdapter.ViewHolder) view.getTag();
        }

        final Partido partido = arrayList.get(i);
        Resources res = view.getResources();
        TypedArray icons = res.obtainTypedArray(R.array.banderas);
        Drawable drawable = icons.getDrawable(partido.getIdEquipo1()-1);
        Log.d("Prueba",drawable.toString());
        viewHolder.equipo1.setImageDrawable(drawable);
        drawable = icons.getDrawable(partido.getIdEquipo2()-1);
        viewHolder.equipo2.setImageDrawable(drawable);
        viewHolder.fecha.setText(partido.getFecha());
        viewHolder.lugar.setText(partido.getLugar());
        viewHolder.goles1.setText(partido.getGolesequipo1());
        viewHolder.goles2.setText(partido.getGetGolesequipo2());
        return view;
    }
}
