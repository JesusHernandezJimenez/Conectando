package com.example.conectando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Opciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
    }

    public void usuario(View view){
        Intent sig = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(sig);
    }

    public void devices(View view){
        Intent sig = new Intent(getApplicationContext(),Devices.class);
        startActivity(sig);
    }
}