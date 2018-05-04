package com.tecmm.tequila.listviewproductos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class actividad extends AppCompatActivity {

    Spinner spinner;
    EditText txtnombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad);
        String categorias[] = {"Electronica", "Hogar", "Papeleria", "Moda", "Dulceria", "Jardineria"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, categorias);

        spinner = (Spinner) findViewById(R.id.spnProducto);
        spinner.setAdapter(adapter);
        txtnombre = (EditText) findViewById(R.id.edTxtNombre);
    }



    public void agregar(View v){
        Intent i = new Intent();
        i.putExtra("nombre", txtnombre.getText().toString());
        i.putExtra("categoria", spinner.getSelectedItem().toString());
        setResult(RESULT_OK,i);

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, null);

        TextView text = (TextView) layout.findViewById(R.id.txt);
        text.setText("Se ha agregado " + txtnombre.getText() + " correctamente");

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

        finish();
    }
}
