package rboock.marketapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import rboock.marketapp.modelo.Producto;

public class Vendedor extends AppCompatActivity {
    private List<Producto> productos = new ArrayList<>();
    ArrayAdapter<Producto> adapterProductos;
    ListView lvProductos;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendedor);

        lvProductos = findViewById(R.id.lvProductos);

        SharedPreferences prefs = getSharedPreferences("shPref", MODE_PRIVATE);
        usuario = prefs.getString("usuario", null);

        iniciarFirebase();
        listarProductos();

    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
    }


    private void listarProductos() {
        myRef.child("Producto").child(usuario).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productos.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Producto p = objSnapshot.getValue(Producto.class);
                    productos.add(p);

                    adapterProductos = new ArrayAdapter<>(Vendedor.this,android.R.layout.simple_list_item_1,productos);
                    lvProductos.setAdapter(adapterProductos);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void onClickPublicar(View v){
        Intent i = new Intent(this,PublicarProducto.class);
        startActivity(i);
    }

    public void compradorOnClick(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
