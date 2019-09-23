package com.example.gastostracker;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> listitem;
    ArrayAdapter adapter;
    DbHelper db = new DbHelper(this);
    ListView listaGastos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(listaGastos!=null){listaGastos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String texto = listaGastos.getItemAtPosition(i).toString();
                Toast.makeText(MainActivity.this, ""+texto, Toast.LENGTH_SHORT).show();
            }
        });}

        Button btnAddGasto = findViewById(R.id.btnAddGasto);
        btnAddGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,CadastroActivity.class);
                startActivity(it);
            }
        });
        Button btnListaGasto = findViewById(R.id.btnListaGasto);
        btnListaGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,GridActivity.class);
                startActivity(it);
            }
        });
    }


}
