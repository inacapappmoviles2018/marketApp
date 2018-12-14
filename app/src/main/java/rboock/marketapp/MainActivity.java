package rboock.marketapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edtNombre;
    EditText edtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtNombre = (EditText) findViewById(R.id.edtNombre);
        edtPass = (EditText) findViewById(R.id.edtPass);
    }

    public void Ingresar(View view){
        
    }
}