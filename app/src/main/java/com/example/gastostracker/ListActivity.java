package com.example.gastostracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    ArrayList<Gasto> listitem;
    GastosAdapter adapter;
    DbHelper db = new DbHelper(this);
    ListView listaGastos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_gastos);
        final ListView listView = findViewById(R.id.listaGastos);

        //meusGastos.add(new  Gasto("Palio",2010,"FIAT"));
        //meusGastos.add(new  Gasto("Astra",2000,"GM"));
        //meusGastos.add(new  Gasto("2008",1989,"PEUGEOT"));
        //meusGastos.add(new  Gasto("308",2000,"PEUGEOT"));

        listitem = new ArrayList<>();
        final GastoDAO gastoDao= new GastoDAO(getApplicationContext());
        listitem= this.list();
        adapter = new GastosAdapter(this,listitem);
        listView.setAdapter(adapter);
        final AlertDialog[] alerta = new AlertDialog[1];
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {

                final Gasto a = (Gasto) parent.getItemAtPosition(position);
                listitem = list();
                //Cria o gerador do AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
                //define o titulo
                builder.setTitle("Opções");
                //define a mensagem
                builder.setMessage("Qual ação você deseja para este gasto?");
                //define um botão como positivo
                builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        final EditText edtDescricao;
                        final EditText edtValor;
                        Button btnSave;
                        setContentView(R.layout.adiciona_gasto);
                        edtDescricao= findViewById(R.id.edtGasto);
                        edtValor= findViewById(R.id.edtValor);
                        btnSave= findViewById(R.id.btnSave);
                        btnSave.setOnClickListener(new View.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(ListActivity.this,EditaActivity.class);
                                startActivity(it);
                                listitem= list();
                                adapter = new GastosAdapter(getApplicationContext(),listitem);
                                listView.setAdapter(adapter);
                            }
                        });
                    }
                });
                //define um botão como negativo.
                builder.setNegativeButton("Deletar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        gastoDao.deletar(a);
                        Toast.makeText(getApplicationContext(), "removido", Toast.LENGTH_LONG).show();
                        listitem= list();
                        adapter = new GastosAdapter(getApplicationContext(),listitem);
                        listView.setAdapter(adapter);
                    }
                });
                //cria o AlertDialog
                alerta[0] = builder.create();
                //Exibe
                alerta[0].show();
            }
        });
    }
        //listaGastos(listView, listitem);
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
