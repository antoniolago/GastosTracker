package com.example.gastostracker;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Date;
import java.util.ArrayList;

public class GridActivity extends AppCompatActivity {
    ArrayList<String> listitem;
    ArrayAdapter adapter;
    DbHelper db = new DbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        ListView listView = findViewById(R.id.listaGastos);
        ArrayList<Gasto> meusGastos= new ArrayList<>();
        meusGastos.add(new  Gasto("Corsa",Double.valueOf(2000)));
        //meusGastos.add(new  Gasto("Palio",2010,"FIAT"));
        //meusGastos.add(new  Gasto("Astra",2000,"GM"));
        //meusGastos.add(new  Gasto("2008",1989,"PEUGEOT"));
        //meusGastos.add(new  Gasto("308",2000,"PEUGEOT"));
        final GastosAdapter adapter = new GastosAdapter(this,meusGastos);
        listitem = new ArrayList<>();
        listaGastos(listView);


    }
    private void listaGastos(ListView listView) {
        Cursor cursor = db.listaGastos();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Não foram registrados gastos.", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                listitem.add(cursor.getString(1));
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.list_content, listitem);
            listView.setAdapter(adapter);
        }
    }
}
