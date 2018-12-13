package rboock.marketapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Publicado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicado);
    }

    public void onClickVerPublicacion(View v){
        Intent i = new Intent(this, Vendedor.class);
        startActivity(i);
    }
}
