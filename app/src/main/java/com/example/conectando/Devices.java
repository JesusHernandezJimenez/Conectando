package com.example.conectando;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class Devices extends AppCompatActivity {

    TextView txtEdit, band;
    Switch aSwitch;
    LinearLayout dis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);
        txtEdit = findViewById(R.id.editSelec);
        aSwitch = findViewById(R.id.switch3);
        dis = findViewById(R.id.layoutDis);
        band = findViewById(R.id.band);
    }


    public void onClick(View view) {
        if(view.getId() == R.id.switch3){
            if(aSwitch.isChecked()){
                txtEdit.setText("Conectado");
            }else{
                txtEdit.setText("No conectado");
            }
        }
    }

    public void encender(View view) {
        if(band.getText() == "Encender"){
            band.setText("Apagar");
        }else{
            band.setText("Encender");
        }
    }
}