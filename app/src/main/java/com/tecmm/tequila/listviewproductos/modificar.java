package com.tecmm.tequila.listviewproductos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class modificar extends AppCompatActivity {

    Spinner spinner;
    EditText txtnombre;

    private List<String> lProductos = new ArrayList<>();
    private List<String> lcategoria = new ArrayList<>();

    int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        String categorias[] = {"Electronica", "Hogar", "Papeleria", "Moda", "Dulceria", "Jardineria"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, categorias);

        spinner = (Spinner) findViewById(R.id.spnProducto);
        spinner.setAdapter(adapter);
        txtnombre = (EditText) findViewById(R.id.edTxtNombre);

        Intent i = getIntent();

        txtnombre.setText(i.getStringExtra("nombre"));
        String categoriax = i.getStringExtra("categoria");
        posicion = i.getIntExtra("posicion", -1);


        Arrays.sort(categorias);
        int f = Arrays.binarySearch(categorias, categoriax);
        spinner.setSelection(f);

    }

    public void modificar(View v){
        Intent i = new Intent();
        i.putExtra("nombre", txtnombre.getText().toString());
        i.putExtra("categoria", spinner.getSelectedItem().toString());
        i.putExtra("posicion", posicion);
        setResult(RESULT_OK, i);
        finish();
    }


}
