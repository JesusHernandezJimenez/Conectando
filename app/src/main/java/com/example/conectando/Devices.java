package com.example.conectando;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class Devices extends AppCompatActivity {
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private static String address = null;
    boolean activar;
    Handler bluetoothIn;
    final int handlerState = 0;
    private ConnectedThread MyConexionBT;
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    TextView txtEdit, band;
    Switch conectar;
    LinearLayout apagar;
    LinearLayout encender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);
        txtEdit = findViewById(R.id.editSelec);
        conectar = findViewById(R.id.conectar);
        encender = findViewById(R.id.encender);
        band = findViewById(R.id.band);
        apagar = findViewById(R.id.apagar);
        apagar.setVisibility(View.GONE);
        encender.setVisibility(View.GONE);
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        verificarBluetooth();

        Set<BluetoothDevice> pairedDevicesList = btAdapter.getBondedDevices();
        for (BluetoothDevice pairedDevice : pairedDevicesList){
            if(pairedDevice.getName().equals("HC-05")){
                address = pairedDevice.getAddress();
            }
        }
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        verificarBluetooth();

        Set<BluetoothDevice> pairedDeveicesList = btAdapter.getBondedDevices();

        for(BluetoothDevice pairedDevice : pairedDeveicesList){
            if(pairedDevice.getName().equals("HC-05")){

                address = pairedDevice.getAddress();
            }


        }

        encender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("1");
                encender.setVisibility(View.GONE);
                apagar.setVisibility(View.VISIBLE);
            }
        });
        apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("2");
                apagar.setVisibility(View.GONE);
                encender.setVisibility(View.VISIBLE);
            }
        });

    }

    private BluetoothSocket createBluetoothSocket (BluetoothDevice device) throws IOException {

        return device.createRfcommSocketToServiceRecord(BTMODULEUUID);
    }




    private void verificarBluetooth(){
        if(btAdapter.isEnabled()){


        }else{

            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent,1);
        }

    }
    public void onResume() {
        super.onResume();


        if (activar) {
            BluetoothDevice device = btAdapter.getRemoteDevice(address);

            try {
                btSocket = createBluetoothSocket(device);

            } catch (IOException e) {
                Toast.makeText(getBaseContext(), "La creacción del Socket fallo", Toast.LENGTH_LONG).show();
            }
            // Establece la conexión con el socket Bluetooth.
            try {
                btSocket.connect();
            } catch (IOException e) {
                try {
                    btSocket.close();
                } catch (IOException e2) {

                }
            }
            MyConexionBT = new ConnectedThread(btSocket);
            MyConexionBT.start();
        }

    }

    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {

            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {

            }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[256];
            int bytes;

            // Se mantiene en modo escucha para determinar el ingreso de datos
            while (true) {
                try {

                    bytes = mmInStream.read(buffer);
                    String readMessage = new String(buffer, 0, bytes);
                    // Envia los datos obtenidos hacia el evento via handler
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }

        //Envio de trama
        public void write(String input) {
            try {
                mmOutStream.write(input.getBytes());
            } catch (IOException e) {
                //si no es posible enviar datos se cierra la conexión
                Toast.makeText(getBaseContext(), "La Conexión fallo", Toast.LENGTH_LONG).show();
                finish();
            }
        }



    }
    public void onClick(View view) {
        if(view.getId() == R.id.conectar){
            if(conectar.isChecked()){
                txtEdit.setText("Conectado");
                activar = true;
                onResume();
                encender.setVisibility(View.VISIBLE);
            }else{
                txtEdit.setText("Desconectado");
                encender.setVisibility(View.GONE);
                apagar.setVisibility(View.GONE);
                try{
                    btSocket.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}