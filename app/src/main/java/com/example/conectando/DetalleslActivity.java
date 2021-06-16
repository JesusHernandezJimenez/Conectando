package com.example.conectando;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalleslActivity extends AppCompatActivity {

    TextView tvid,tvname, tvuser,tvemail,tvcontact,tvaddress;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        //Initializing Views
        tvid = findViewById(R.id.txtid);
        tvname = findViewById(R.id.txtname);
        tvuser = findViewById(R.id.txtuser);
        tvemail = findViewById(R.id.txtemail);
        tvcontact = findViewById(R.id.txcontact);
        tvaddress = findViewById(R.id.txtaddress);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvid.setText("ID: "+MainActivity.users.get(position).getId());
        tvname.setText("Nombre: "+MainActivity.users.get(position).getNombre());
        tvuser.setText("Usuario: "+MainActivity.users.get(position).getUsuario());
        tvemail.setText("Email: "+MainActivity.users.get(position).getEmail());
        tvcontact.setText("Teléfono: "+MainActivity.users.get(position).getContacto());
        tvaddress.setText("Dirección: "+MainActivity.users.get(position).getDireccion());





    }
}
