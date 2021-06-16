package com.example.conectando;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class Editar extends AppCompatActivity {

    EditText edId,edName, edUser, edPass,edContact,edEmail,edAddress;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_);

        edId = findViewById(R.id.ed_id);
        edName = findViewById(R.id.ed_name);
        edUser = findViewById(R.id.ed_user);
        edPass = findViewById(R.id.ed_pass);
        edContact = findViewById(R.id.ed_contact);
        edEmail = findViewById(R.id.ed_email);
        edAddress = findViewById(R.id.ed_address);


        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");


        edId.setText(MainActivity.users.get(position).getId());
        edName.setText(MainActivity.users.get(position).getNombre());
        edUser.setText(MainActivity.users.get(position).getUsuario());
        edPass.setText(MainActivity.users.get(position).getPassword());
        edEmail.setText(MainActivity.users.get(position).getEmail());
        edContact.setText(MainActivity.users.get(position).getContacto());
        edAddress.setText(MainActivity.users.get(position).getDireccion());




    }

    public void btn_updateData(View view) {

        final String nombre = edName.getText().toString();
        final String usuario = edUser.getText().toString();
        final String contrasena = edPass.getText().toString();
        final String email = edEmail.getText().toString();
        final String contacto = edContact.getText().toString();
        final String direccion = edAddress.getText().toString();
        final String id = edId.getText().toString();





        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Actualizando Datos....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.1.67/crud2/update.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(com.example.conectando.Editar.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
                Toast.makeText(com.example.conectando.Editar.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("id",id);
                params.put("nombre",nombre);
                params.put("usuario", usuario);
                params.put("contrasena", contrasena);
                params.put("email",email);
                params.put("contacto",contacto);
                params.put("direccion",direccion);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(com.example.conectando.Editar.this);
        requestQueue.add(request);





    }
    public void limpiar(){
        Intent intent=new Intent(com.example.conectando.Editar.this,MainActivity.class);
        startActivity(intent);

    }
}
