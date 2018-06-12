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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.alexandramolina.quiniela.R;
import com.example.alexandramolina.quiniela.clases.Partido;
import com.example.alexandramolina.quiniela.clases.Posicion;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PosicionAdapter extends BaseAdapter implements Filterable{

    private Context context;
    private int layout;
    private ArrayList<Posicion> arrayList;
    private ArrayList<Posicion> temporal= new ArrayList<Posicion>();

    private LayoutInflater inflater;

    public PosicionAdapter(Context context, int layout, ArrayList<Posicion> arrayList) {
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
    public Posicion getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public Filter getFilter() {

        Filter myFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if(constraint != null) {
                    ArrayList<Posicion> filteredProducts = new ArrayList<Posicion>();
                    for(Posicion p : temporal){
                        if(p.getNombre().contains(constraint)){
                            filteredProducts.add(p);
                            Log.d("PRUEBA",p.getNombre());
                        }
                    }
                    // Now assign the values and count to the FilterResults object
                    if(filteredProducts!=null){
                        //arrayList=filteredProducts;
                    }
                    filterResults.values = filteredProducts;
                    filterResults.count = filteredProducts.size();

                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence contraint, FilterResults results) {
                if(results != null && results.count > 0) {
                    Log.d("PRUEBA","entra");
                    //ArrayList<Posicion> tmp= new ArrayList<Posicion>();
                    //tmp=(ArrayList) arrayList.clone();
                    arrayList =  (ArrayList<Posicion>) results.values;
                    notifyDataSetChanged();
                    //arrayList=tmp;
                }
                else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return myFilter;

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

        arrayList.get(i).setPosicion(i+1);
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

        viewHolder.pos.setText(Integer.toString(posicion.getPosicion())+".");
        viewHolder.nombre.setText(posicion.getNombre());
        viewHolder.aciertos.setText("Aciertos: "+ posicion.getAciertos());

        return view;
    }



}
