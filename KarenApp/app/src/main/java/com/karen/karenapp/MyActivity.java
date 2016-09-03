package com.karen.karenapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import utilidades.ValidatorUtil;


public class MyActivity extends Activity {

    private EditText etPlaca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        etPlaca = (EditText) findViewById(R.id.et_placa);

    }

    public void consulta(View v) {

        String placa = etPlaca.getText().toString();
        etPlaca.setBackgroundColor(Color.WHITE);

        if (ValidatorUtil.esVacio(placa)){
            Toast.makeText(this, "El Numero de Placa es requerido", Toast.LENGTH_SHORT).show();
            etPlaca.setBackgroundColor(Color.RED);
            etPlaca.requestFocus();
            return;
        }

        Intent registros_intent = new Intent(this, CatRegistros.class);

        registros_intent.putExtra("placa", placa);
        startActivity(registros_intent);
    }
}