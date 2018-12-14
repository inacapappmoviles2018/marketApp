package rboock.marketapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import rboock.marketapp.modelo.Usuario;

public class crearCuenta extends AppCompatActivity {

    EditText edCorreo, edNombre, edPass;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        edCorreo = (EditText) findViewById(R.id.edCorreo);
        edNombre = (EditText) findViewById(R.id.edNombre);
        edPass = (EditText) findViewById(R.id.edPass);

        iniciarFirebase();
    }

    public void creaCuenta(View view) {
        if (validar()) {
            Usuario u = new Usuario();
            u.setEmail(edCorreo.getText().toString());
            u.setNombre(edNombre.getText().toString());
            u.setPassword(edPass.getText().toString());
            myRef.child("Usuario").child(u.getNombre()).setValue(u);
            Toast.makeText(this, "Ha creado su usuario correctamente", Toast.LENGTH_SHORT).show();
            Intent cuenta = new Intent(this, MainActivity.class);
            startActivity(cuenta);
        }
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
    }

    public boolean validar(){
        boolean validacion=true;
        if (edCorreo.getText().toString().equals("")){
            edCorreo.setError("Correo requerido");
            validacion=false;
        }
        if (edNombre.getText().toString().equals("")){
            edNombre.setError("Nombre requerido");
            validacion=false;
        }
        if (edPass.getText().toString().equals("")){
            edPass.setError("Contraseña requerida");
            validacion=false;
        }
        return validacion;

    }

}