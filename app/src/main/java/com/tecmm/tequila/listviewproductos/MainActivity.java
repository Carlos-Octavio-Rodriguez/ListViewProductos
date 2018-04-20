package com.tecmm.tequila.listviewproductos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    ListView lista;

    ///private String productos[] = {"Computadora", "Mouse", "Dulces", "Lapices", "Hojas", "Horno", "Televisión", "Reloj", "Cucharas"};
    ///private String categoria[] = {"Electronica", "Electronica", "Dulceria", "Papeleria", "Papeleria", "Cocina", "Electronica", "Moda", "Cocina"};
    private List<String> lProductos = new ArrayList<>();
    private List<String> lcategoria = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView) findViewById(R.id.txt);
        lista = (ListView) findViewById(R.id.lista);



      /* ArrayAdapter<String>adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item ,productos);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txt.setText("Categoria elegida: " + categoria[position]);
            }
        });*/

    }

    private void actualizarTabla(){
        String productos[] = new String[lProductos.size()];
        lProductos.toArray(productos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, productos);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txt.setText("Categoria elegida: " + lcategoria.get(position));
            }
        });
    }

    ///boton
    public void llamarActividad(View x){
        Intent i = new Intent(this, actividad.class);
        startActivityForResult(i, 123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        lProductos.add(data.getStringExtra("nombre"));
        lcategoria.add(data.getStringExtra("categoria"));
        actualizarTabla();
    }
}
