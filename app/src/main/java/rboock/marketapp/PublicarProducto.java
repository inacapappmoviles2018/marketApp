package rboock.marketapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import rboock.marketapp.modelo.Producto;

public class PublicarProducto extends AppCompatActivity {

    Spinner categorias;
    EditText edtNombre,edtPrecio,edtDescripcion;
    String usuario;
    // Write a message to the database
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar_producto);

        edtNombre = findViewById(R.id.edtNombre);
        edtPrecio = findViewById(R.id.edtPrecio);
        edtDescripcion = findViewById(R.id.edtDescripcion);
        categorias = findViewById(R.id.categorias);

        SharedPreferences prefs = getSharedPreferences("shPref", MODE_PRIVATE);
        usuario = prefs.getString("usuario", null);

        iniciarFirebase();

        categorias.setAdapter(cargarCategorias());
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
    }

    public void onClickPublicar(View v){
        if (validar()){
            Producto p = new Producto();
            p.setVendedor(usuario);
            p.setId(UUID.randomUUID().toString());
            p.setNombre(edtNombre.getText().toString());
            p.setPrecio(edtPrecio.getText().toString());
            p.setDescripcion(edtDescripcion.getText().toString());
            p.setCategoria(categorias.getSelectedItem().toString());
            myRef.child("Producto").child(usuario).child(p.getId()).setValue(p);
            Toast.makeText(this,"¡Producto publicado!",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,Publicado.class);
            startActivity(i);
        }
    }

    private ArrayAdapter<String> cargarCategorias() {
        //Las categorias se deberian cargar desde la bd
        String[] categorias = {"Vestimenta","Tecnología","Juguetes","Vehículos","Otros"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, categorias);
        return adapter;
    }

    public boolean validar(){
        boolean validacion=true;
        if (edtNombre.getText().toString().equals("")){
            edtNombre.setError("Nombre requerido");
            validacion=false;
        }
        if (edtPrecio.getText().toString().equals("")){
            edtPrecio.setError("Precio requerido");
            validacion=false;
        }
        if (edtDescripcion.getText().toString().equals("")){
            edtDescripcion.setError("Descripción requerida");
            validacion=false;
        }
        return validacion;
    }
}
