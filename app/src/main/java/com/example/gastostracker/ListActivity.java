package com.example.gastostracker;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;

public class ListActivity extends AppCompatActivity {
    ArrayList<Gasto> listitem;
    GastosAdapter adapter;
    DbHelper db = new DbHelper(this);
    ListView listaGastos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_gastos);
        ListView listView = findViewById(R.id.listaGastos);

        //meusGastos.add(new  Gasto("Palio",2010,"FIAT"));
        //meusGastos.add(new  Gasto("Astra",2000,"GM"));
        //meusGastos.add(new  Gasto("2008",1989,"PEUGEOT"));
        //meusGastos.add(new  Gasto("308",2000,"PEUGEOT"));

        listitem = new ArrayList<>();
        final GastoDAO gastoDao= new GastoDAO(getApplicationContext());
        listitem= this.list();
        adapter = new GastosAdapter(this,listitem);
        listView.setAdapter(adapter);
        //listaGastos(listView, listitem);


    }
    public ArrayList<Gasto> list(){
        ArrayList<Gasto> lista= new ArrayList<>();
        DbHelper banco = new DbHelper(this);
        SQLiteDatabase db1 = banco.getReadableDatabase();
        String query = "Select * from tbGastos";
        Cursor cursor = db1.rawQuery(query, null);
        if (cursor!=null){
            cursor.moveToFirst();
            while (cursor.moveToNext()){
                int id= cursor.getInt(0) ;
                String descricao = cursor.getString(1) ;
                Double valor = cursor.getDouble(2) ;
                java.util.Date data = new Date(cursor.getLong(3)*1000);
                Gasto c= new Gasto(id,descricao,valor,data);
                lista.add(c);
            }
            return lista;
        }
        return null;
    }

}
