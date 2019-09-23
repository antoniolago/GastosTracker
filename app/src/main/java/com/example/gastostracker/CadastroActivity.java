package com.example.gastostracker;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
@SuppressLint("Registered")
public class CadastroActivity extends AppCompatActivity {
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
                Gasto gasto= new Gasto(edtDescricao.getText().toString(), Double.valueOf(edtValor.getText().toString()));
                GastoDAO gastoDAO = new GastoDAO(getBaseContext());
                String msg= gastoDAO.salvar(gasto);
                Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
            }
        });


    }
}
