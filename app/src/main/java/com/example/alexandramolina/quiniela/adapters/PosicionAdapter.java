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
import com.example.alexandramolina.quiniela.clases.Posicion;

import java.util.ArrayList;

public class PosicionAdapter extends BaseAdapter{

    private Context context;
    private int layout;
    private ArrayList<Posicion> arrayList;
    private LayoutInflater inflater;

    public PosicionAdapter(Context context, int layout, ArrayList<Posicion> arrayList) {
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
    public Posicion getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView pos,nombre,aciertos;
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
        final PosicionAdapter.ViewHolder viewHolder;

        if(view == null){
            viewHolder = new PosicionAdapter.ViewHolder();
            view = inflater.inflate(layout,null);
            viewHolder.pos= view.findViewById(R.id.posicion);
            viewHolder.nombre= view.findViewById(R.id.nombrePosicion);
            viewHolder.aciertos=view.findViewById(R.id.aciertos);



            view.setTag(viewHolder);
        }
        else{
            viewHolder = (PosicionAdapter.ViewHolder) view.getTag();
        }

        final Posicion posicion = arrayList.get(i);

        viewHolder.pos.setText(Integer.toString(i+1)+".");
        viewHolder.nombre.setText(posicion.getNombre());
        viewHolder.aciertos.setText("Aciertos: "+ posicion.getAciertos());

        return view;
    }


}
