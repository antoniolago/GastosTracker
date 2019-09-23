package com.example.gastostracker;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> listitem;
    ArrayAdapter adapter;
    DbHelper db = new DbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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
                Intent it = new Intent(MainActivity.this, ListActivity.class);
                startActivity(it);
            }
        });
    }


}
