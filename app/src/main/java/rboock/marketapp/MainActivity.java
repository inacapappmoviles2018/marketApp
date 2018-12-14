package rboock.marketapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import rboock.marketapp.modelo.Producto;
import rboock.marketapp.modelo.Usuario;

public class MainActivity extends AppCompatActivity {

    TextView tvBienvenido;
    private List<Producto> productos = new ArrayList<>();
    ArrayAdapter<Producto> adapterProductos;
    ListView lvProductos;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*Bloque causa crasheo de la app (@rboockv)
        String dato = getIntent().getStringExtra("dato");

        if(dato.equals(null)){     // despues validare si a iniciado sesion antes y si lo ha hecho debera guardarlo
            Intent i = new Intent(this,login.class);
            startActivity(i);
        }*/

        tvBienvenido = (TextView) findViewById(R.id.tvBienvenido);
        lvProductos = findViewById(R.id.lvProductos);

        SharedPreferences prefs = getSharedPreferences("shPref", MODE_PRIVATE);
        String usuario = prefs.getString("usuario", null);
        tvBienvenido.setText("Bienvenido, " + usuario);

        iniciarFirebase();
        listarProductos();

    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
    }

    private void listarProductos() {
        myRef.child("Producto").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //productos.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Producto p = objSnapshot.getValue(Producto.class);
                    productos.add(p);
                    adapterProductos = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, productos);
                    lvProductos.setAdapter(adapterProductos);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void onClickVendedor(View v){
        Intent i = new Intent(this,Vendedor.class);
        startActivity(i);
    }

    public void salirOnClick(View v){
        SharedPreferences.Editor editor = getSharedPreferences("shPref", MODE_PRIVATE).edit();
        editor.putString("usuario", null);
        editor.apply();
        Intent i = new Intent(this,login.class);
        startActivity(i);
    }
}
