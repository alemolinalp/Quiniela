package com.example.alexandramolina.quiniela;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GrupoActivity extends AppCompatActivity {

    GridLayout gl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo);

        ArrayList<Integer> equipos= new ArrayList<Integer>();
        equipos.add(R.drawable.rus);
        equipos.add(R.drawable.ksa);
        equipos.add(R.drawable.egy);
        equipos.add(R.drawable.uru);

        gl=findViewById(R.id.tablap);


        for(int x=0;x<4;x++){
            for(int y=0;y<9;y++){


                if(y==0){
                    ImageView tv = new ImageView(this);
                    tv.setImageResource(equipos.get(x));
                    gl.addView(tv);
                }
                else{
                    TextView tv= new TextView(this);
                    tv.setText("0");
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
                    tv.setTextColor(getResources().getColor(R.color.white));

                    gl.addView(tv);
                }




            }
        }
    }
}
