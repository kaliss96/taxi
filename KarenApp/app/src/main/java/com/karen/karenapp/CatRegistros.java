package com.karen.karenapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import utilidades.AdminSQLiteOpenHelper;

public class CatRegistros extends Activity {

    private ListView lstDatos;

    private Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_datos);
        btnRegresar = (Button) findViewById(R.id.btnRegresar);
        cargarDatos();

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registros_intent = new Intent(CatRegistros.this, MyActivity.class);

                // esto para decirle que regrese a la actividad deseada
                registros_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(registros_intent);
            }
        });
    }

    public void cargarDatos() {
        Intent datos_intent = getIntent();
        Bundle parametos = datos_intent.getExtras();

        if (parametos != null) {
            String placa = (String) parametos.get("placa");
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                    "Taxi", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase(); //Create and/or open a database that will be used for reading and writing.

            Cursor fila = bd.rawQuery(  //devuelve 0 o 1 fila //es una consulta
                    "select rowid as _id, * from Registros where placa = '" + placa.toUpperCase() + "'", null);
            if (fila != null) {  //si ha devuelto 1 fila, vamos al primero (que es el unico)
                String[] from = new String[]{"placa", "cooperativa", "nombre", "apellido", "telefono", "marca", "modelo", "color"};
                int[] ids = new int[]{R.id.txtPlaca, R.id.txtCooperativa, R.id.txtNombre, R.id.txtApellido, R.id.txtTelefono, R.id.txtMarca, R.id.txtModelo, R.id.txtColor};
                try {
                    SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.activity_filas_registros, fila, from, ids, 0);
                    lstDatos = (ListView) findViewById(R.id.lstDatos);
                    lstDatos.setAdapter(adapter);
                } catch (Exception e) {
                    Toast.makeText(this, "error " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            } else
                Toast.makeText(this, "No se encontraron registros coincidentes",
                        Toast.LENGTH_SHORT).show();
            bd.close();
        }
    }
}
