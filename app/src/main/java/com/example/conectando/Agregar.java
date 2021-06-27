package com.example.conectando;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Agregar extends AppCompatActivity {

    EditText txtName,txtEmail,txtContact,txtAddress,txtPass,txtUser;
    Button btn_insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__data_);

        txtName     = findViewById(R.id.edtName);
        txtEmail    = findViewById(R.id.edtEmail);
        txtContact  = findViewById(R.id.edtContact);
        txtAddress  = findViewById(R.id.edtAddress);
        btn_insert = findViewById(R.id.btnInsert);
        txtPass = findViewById(R.id.edtPass);
        txtUser = findViewById(R.id.edtUser);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertData();
            }
        });
    }

    private void insertData() {

        final String nombre = txtName.getText().toString().trim();
        final String usuario = txtUser.getText().toString().trim();
        final String password = txtPass.getText().toString().trim();
        final String email = txtEmail.getText().toString().trim();
        final String contacto = txtContact.getText().toString().trim();
        final String direccion = txtAddress.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("cargando...");

        if(nombre.isEmpty()){


            txtName.setError("complete los campos");
            return;
        }
        else if(email.isEmpty()){

            txtEmail.setError("complete los campos");
            return;
        }
        else if(contacto.isEmpty()){
            txtContact.setError("complete los campos");
            return;
        }
        else if(direccion.isEmpty()){
            txtAddress.setError("complete los campos");
            return;
        }

        else{
                progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.1.67/crud2/insert.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.equalsIgnoreCase("El usuario se ha creado satisfactoriamente")){
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                finish();
                                Toast.makeText(com.example.conectando.Agregar.this, "Usuario creado", Toast.LENGTH_SHORT).show();

                                progressDialog.dismiss();
                            }
                            else{
                                Toast.makeText(com.example.conectando.Agregar.this, response, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(com.example.conectando.Agregar.this, "Error al agregar", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<String, String>();

                    params.put("nombre",nombre);
                    params.put("usuario", usuario);
                    params.put("contraseña",password);
                    params.put("email",email);
                    params.put("contacto",contacto);
                    params.put("direccion",direccion);
                    //params.put("distrito",distrito);



                    return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(com.example.conectando.Agregar.this);
            requestQueue.add(request);



        }




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public void limpiar(){
        Intent intent=new Intent(com.example.conectando.Agregar.this,MainActivity.class);
        startActivity(intent);
        txtName.setText("");
        txtUser.setText("");
        txtPass.setText("");
        txtContact.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
    }
}
