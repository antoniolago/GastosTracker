package com.example.gastostracker;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GridActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        ArrayList<Gasto> meusGastos= new ArrayList<>();
        //meusGastos.add(new  Gasto("Corsa",2000,"GM"));
        //meusGastos.add(new  Gasto("Palio",2010,"FIAT"));
        //meusGastos.add(new  Gasto("Astra",2000,"GM"));
        //meusGastos.add(new  Gasto("2008",1989,"PEUGEOT"));
        //meusGastos.add(new  Gasto("308",2000,"PEUGEOT"));
        final GastosAdapter adapter = new GastosAdapter(this,meusGastos);

        GridView grid = findViewById(R.id.grid);
        grid.setAdapter(adapter);


    }
}
