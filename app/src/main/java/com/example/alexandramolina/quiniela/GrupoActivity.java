package com.example.alexandramolina.quiniela;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alexandramolina.quiniela.clases.Equipo;
import com.example.alexandramolina.quiniela.clases.Partido;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GrupoActivity extends AppCompatActivity {

    GridLayout gl;
    ArrayList<Equipo> equipos;
    ArrayList<Equipo> grup;
    String grupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo);

        grupo= getIntent().getStringExtra("grupo");

        equipos= new ArrayList<Equipo>();
        grup= new ArrayList<Equipo>();
        gl=findViewById(R.id.tablap);

        obtenerEquipos();



    }

    public void obtenerEquipos(){

        StringRequest registroRequest = new StringRequest(Request.Method.GET, "https://quinielaapp.herokuapp.com/v1/equipos", new Response.Listener<String>() {
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

                    String id,nombrem,jugados,ganados,empatados,perdidos,favor,contra,puntos,g;
                    if(status.equals("Success")){
                        JSONArray jsonArray = new JSONArray(json.getString("data"));
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            id= jsonObject1.getString("id");
                            nombrem= jsonObject1.getString("nombre");
                            jugados=jsonObject1.getString("partidosJugados");
                            ganados= jsonObject1.getString("partidosGanados");
                            empatados= jsonObject1.getString("partidosEmpatados");
                            perdidos= jsonObject1.getString("partidosPerdidos");
                            favor= jsonObject1.getString("golesFavor");
                            contra= jsonObject1.getString("golesContra");
                            puntos= jsonObject1.getString("puntos");
                            g =jsonObject1.getString("idGrupo");
                            grup.add(new Equipo(id,nombrem,puntos,jugados,ganados,empatados,perdidos,favor,contra,g));
                        }

                        llenarTabla();



                    }
                    else {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        Log.d("ERROR",message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                Log.d("Params",grupo);

                params.put("grupo",grupo);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.getCache().clear();
        requestQueue.add(registroRequest);

    }

    public void llenarTabla(){

        for(Equipo e:grup){
            if(e.getGrupo().equals(grupo)){
                equipos.add(e);
            }
        }


        for(int x=0;x<4;x++){
            for(int y=0;y<9;y++){


                if(y==0){
                    ImageView tv = new ImageView(this);
                    Resources res = getResources();
                    TypedArray icons = res.obtainTypedArray(R.array.banderas);
                    int ide=Integer.parseInt(equipos.get(x).getId())-1;
                    Drawable drawable = icons.getDrawable(ide);
                    tv.setImageDrawable(drawable);
                    gl.addView(tv);
                }
                else if(y==1){
                    TextView tv= new TextView(this);
                    tv.setText(equipos.get(x).getJugados());
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
                    tv.setTextColor(getResources().getColor(R.color.white));

                    gl.addView(tv);
                }
                else if(y==2){
                    TextView tv= new TextView(this);
                    tv.setText(equipos.get(x).getGanados());
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
                    tv.setTextColor(getResources().getColor(R.color.white));

                    gl.addView(tv);
                }
                else if(y==3){
                    TextView tv= new TextView(this);
                    tv.setText(equipos.get(x).getPerdidos());
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
                    tv.setTextColor(getResources().getColor(R.color.white));

                    gl.addView(tv);
                }
                else if(y==4){
                    TextView tv= new TextView(this);
                    tv.setText(equipos.get(x).getEmpatados());
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
                    tv.setTextColor(getResources().getColor(R.color.white));

                    gl.addView(tv);
                }
                else if(y==5){
                    TextView tv= new TextView(this);
                    tv.setText(equipos.get(x).getFavor());
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
                    tv.setTextColor(getResources().getColor(R.color.white));

                    gl.addView(tv);
                }
                else if(y==6){
                    TextView tv= new TextView(this);
                    tv.setText(equipos.get(x).getContra());
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
                    tv.setTextColor(getResources().getColor(R.color.white));

                    gl.addView(tv);
                }
                else if(y==7){
                    TextView tv= new TextView(this);
                    int f= Integer.parseInt(equipos.get(x).getFavor());
                    int c= Integer.parseInt(equipos.get(x).getContra());
                    tv.setText(Integer.toString(f-c));
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
                    tv.setTextColor(getResources().getColor(R.color.white));

                    gl.addView(tv);
                }
                else{
                    TextView tv= new TextView(this);
                    tv.setText(equipos.get(x).getPuntos());
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
                    tv.setTextColor(getResources().getColor(R.color.white));

                    gl.addView(tv);
                }




            }
        }
    }




}
