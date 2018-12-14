package rboock.marketapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import rboock.marketapp.modelo.*;

public class login extends AppCompatActivity {

    EditText edLoginUsuario, edLoginPass;
    TextView tview;
    private List<Usuario> usuarios = new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edLoginUsuario = (EditText) findViewById(R.id.edUsuario);
        edLoginPass = (EditText) findViewById(R.id.edPass);
        tview = (TextView) findViewById(R.id.tview);

        iniciarFirebase();
    }

    public boolean validarCredenciales(){
        boolean val = false;
        myRef.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usuarios.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Usuario u = objSnapshot.getValue(Usuario.class);
                    usuarios.add(u);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        for (Usuario u : usuarios){
            if (u.getNombre().equals(edLoginUsuario.getText().toString())&&u.getPassword().equals(edLoginPass.getText().toString())){
                val = true;
            }
        }
        return val;
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
    }

    public void ingreso(View view) {

        if (validar()&&validarCredenciales()) {
            Toast.makeText(this, "Ha ingresado correctamente", Toast.LENGTH_SHORT).show();
            Intent main = new Intent(this, MainActivity.class);
            main.putExtra("usuario", edLoginUsuario.getText().toString());
            startActivity(main);
        }else if(validar()){
            Toast.makeText(this,"Usuario y/o contraseña incorrectos, RECUERDA RESPETAR LAS MAYÚSCULAS",Toast.LENGTH_SHORT).show();
        }

    }

    public void crearCuenta(View view) {
        Intent cuenta = new Intent(this, crearCuenta.class);
        startActivity(cuenta);
    }

    public boolean validar() {
        boolean validacion = true;
        if (edLoginUsuario.getText().toString().equals("")) {
            edLoginUsuario.setError("No ingresó usuario... se siente bien?");

            validacion = false;
        }
        if (edLoginPass.getText().toString().equals("")) {
            edLoginPass.setError("Hola? No ingresó su contraseña");
            validacion = false;
        }

        return validacion;
    }
}