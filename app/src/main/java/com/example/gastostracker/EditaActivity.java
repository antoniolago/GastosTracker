package com.example.gastostracker;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
@SuppressLint("Registered")
public class EditaActivity extends AppCompatActivity {
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    EditText edtDescricao;
    EditText edtValor;
    EditText edtData;
    Button btnSave;
    Date minhaData;

    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adiciona_gasto);
        edtDescricao= findViewById(R.id.edtGasto);
        edtValor= findViewById(R.id.edtValor);
        btnSave= findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                final int id = getIntent().getIntExtra("EXTRA_GASTO_ID", 0);
                final String descricao = edtDescricao.getText().toString();
                final String valor = edtValor.getText().toString();
                final Date date = calendar.getTime();

                AsyncTask asyncTask = new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] objects) {

                        Gasto gasto= new Gasto(id, descricao, Double.valueOf(valor), date);
                        GastoDAO gastoDAO = new GastoDAO(getBaseContext());
                        String msg= gastoDAO.editar(gasto);

                        return msg;
                    }

                    @Override
                    protected void onPostExecute(Object o) {

                        Toast.makeText(getBaseContext(), (String) o,Toast.LENGTH_LONG).show();

                        Intent ololinho = new Intent(EditaActivity.this, MainActivity.class);
                        startActivity(ololinho);
                        finish();
                    }
                };

                asyncTask.execute();

            }
        });


    }
}
