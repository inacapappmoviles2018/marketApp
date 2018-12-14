package rboock.marketapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText edLoginUsuario, edLoginPass;
    TextView tview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edLoginUsuario = (EditText) findViewById(R.id.edUsuario);
        edLoginPass = (EditText) findViewById(R.id.edPass);
        tview = (TextView) findViewById(R.id.tview);
    }

    public void ingreso(View view) {

        if (validar()) {
            Toast.makeText(this, "Ha ingresado correctamente", Toast.LENGTH_SHORT).show();
            Intent main = new Intent(this, MainActivity.class);
            main.putExtra("dato", edLoginUsuario.getText().toString());
            startActivity(main);
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