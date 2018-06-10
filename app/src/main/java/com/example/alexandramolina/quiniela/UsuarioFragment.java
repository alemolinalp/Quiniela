package com.example.alexandramolina.quiniela;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class UsuarioFragment extends Fragment {

    ImageView foto;
    SharedPreferences sharedPreferences;
    TextView correo,nombre;


    public UsuarioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_usuario, container, false);

        foto=v.findViewById(R.id.foto);
        correo=v.findViewById(R.id.emailUsuario);
        nombre= v.findViewById(R.id.nameUsuario);


        sharedPreferences = getActivity().getSharedPreferences("com.example.alexandramolina.quiniela", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        String name = sharedPreferences.getString("name", "");

        nombre.setText(name);
        correo.setText(email);

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);

            }
        });


        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap=(Bitmap)data.getExtras().get("data");
        foto.setImageBitmap(bitmap);
    }
}
