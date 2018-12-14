package rboock.marketapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvBienvenido;
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

        tvBienvenido.setText("Bienvenido, " + getIntent().getStringExtra("usuario"));

    }

    public void onClickVendedor(View v){
        Intent i = new Intent(this,Vendedor.class);
        startActivity(i);
    }
}
