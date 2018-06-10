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

import com.example.alexandramolina.quiniela.R;
import com.example.alexandramolina.quiniela.clases.Partido;

import java.util.ArrayList;

/**
 * Created by Usuario on 09/06/2018.
 */

public class PartidoAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Partido> arrayList;
    private LayoutInflater inflater;


    public PartidoAdapter(Context context, int layout, ArrayList<Partido> arrayList) {
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
        Spinner spinner;
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
        final ViewHolder viewHolder;

        if(view == null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(layout,null);
            viewHolder.equipo1= view.findViewById(R.id.equipo1);
            viewHolder.equipo2= view.findViewById(R.id.equipo2);
            viewHolder.spinner=view.findViewById(R.id.spinner);

            viewHolder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                    arrayList.get(i).setResultado(viewHolder.spinner.getSelectedItem().toString());


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        final Partido partido = arrayList.get(i);
        Resources res = view.getResources();
        TypedArray icons = res.obtainTypedArray(R.array.banderas);
        Drawable drawable = icons.getDrawable(partido.getIdEquipo1()-1);
        viewHolder.equipo1.setImageDrawable(drawable);
        drawable = icons.getDrawable(partido.getIdEquipo2()-1);
        viewHolder.equipo2.setImageDrawable(drawable);

        return view;
    }
}
