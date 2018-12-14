package rboock.marketapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView Bienvenido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String dato = getIntent().getStringExtra("dato");

        if(dato.equals(null)){     // despues validare si a iniciado sesion antes y si lo ha hecho debera guardarlo
            Intent i = new Intent(this,login.class);
            startActivity(i);
        }

        Bienvenido = (TextView) findViewById(R.id.Bienvenido);
        Bienvenido.setText("Bienvenido, " + dato);

    }

    public void onClickVendedor(View v) {
    }
    }