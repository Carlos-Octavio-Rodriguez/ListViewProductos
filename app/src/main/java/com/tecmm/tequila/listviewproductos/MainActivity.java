package com.tecmm.tequila.listviewproductos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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

        registerForContextMenu(lista);


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
                txt.setText("Categoria Elegida: " + lcategoria.get(position));
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
        if(requestCode == 123){
            lProductos.add(data.getStringExtra("nombre"));
            lcategoria.add(data.getStringExtra("categoria"));
            actualizarTabla();
        }
        else if(requestCode== 987){
            lProductos.set(data.getIntExtra("posicion", -1), data.getStringExtra("nombre"));
            lcategoria.set(data.getIntExtra("posicion", -1), data.getStringExtra("categoria"));
            actualizarTabla();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater icono = getMenuInflater();
        icono.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.op_salir:
                Toast.makeText(this, "Saliendo", Toast.LENGTH_LONG).show();
                finish();
                break;
            case R.id.op_info:
                Toast.makeText(this, "Informacion", Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, acerca.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        menu.setHeaderTitle(lista.getAdapter().getItem(info.position).toString());
        getMenuInflater().inflate(R.menu.menu_emergente, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.emergente_1:
                String nombre = lProductos.get(info.position);
                Toast.makeText(this, "Se modificara " + nombre , Toast.LENGTH_LONG).show();

                ///mandar datos
                Intent i = new Intent(this, modificar.class);
                i.putExtra("nombre", lProductos.get(info.position));
                i.putExtra("categoria", lcategoria.get(info.position));
                i.putExtra("posicion", info.position);

                startActivityForResult(i, 987);
                break;
            case R.id.emergente_2:
                String nombre2 = lProductos.get(info.position);
                Toast.makeText(this, "Se eliminara " + nombre2, Toast.LENGTH_SHORT).show();
                lProductos.remove(info.position);
                lcategoria.remove(info.position);
                actualizarTabla();
                Toast.makeText(this, "Se ha eliminado " + nombre2, Toast.LENGTH_LONG).show();
                txt.setText("Categoria Elegida: ");
                break;
        }

        return true;
    }
}
