package com.example.android.osmdroidofflinedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class FormularioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Bundle extras = getIntent().getExtras();
        String lat = extras.getString("lat");
        EditText editTextNombre = (EditText) findViewById(R.id.editTextLat);
        editTextNombre.setText(lat);  //intent.putExtra("Direccion", direction);
    }
}
