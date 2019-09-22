package com.example.gastostracker;

import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ClienteDAO {

    private final String TABLE_CLIENTES = "Clientes";
    private DbGateway gw;
    Button btnSalvar = (Button)findViewById(R.id.btnSalvar);
    public ClienteDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);
    }
    public boolean salvar(String nome, String sexo, String uf){
        ContentValues cv = new ContentValues();
        cv.put("Nome", nome);
        cv.put("Sexo", sexo);
        cv.put("UF", uf);
        return gw.getDatabase().insert(TABLE_CLIENTES, null, cv) > 0;
    }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //carregando os campos
            EditText txtNome = (EditText)findViewById(R.id.txtNome);
            Spinner spnEstado = (Spinner)findViewById(R.id.spnEstado);
            RadioGroup rgSexo = (RadioGroup)findViewById(R.id.rgSexo);
            CheckBox chkVip = (CheckBox)findViewById(R.id.chkVip);

            //pegando os valores
            String nome = txtNome.getText().toString();
            String uf = spnEstado.getSelectedItem().toString();
            boolean vip = chkVip.isChecked();
            String sexo = rgSexo.getCheckedRadioButtonId() == R.id.rbMasculino ? "M" : "F";

            //salvando os dados
            ClienteDAO dao = new ClienteDAO(getBaseContext());
            boolean sucesso = dao.salvar(nome, sexo, uf, vip);
            if(sucesso) {
                //limpa os campos
                txtNome.setText("");
                rgSexo.setSelected(false);
                spnEstado.setSelection(0);
                chkVip.setChecked(false);

                Snackbar.make(view, "Salvou!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                findViewById(R.id.includemain).setVisibility(View.VISIBLE);
                findViewById(R.id.includecadastro).setVisibility(View.INVISIBLE);
                findViewById(R.id.fab).setVisibility(View.VISIBLE);
            }else{
                Snackbar.make(view, "Erro ao salvar, consulte os logs!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
    });
}