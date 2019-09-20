package com.example.gastostracker;

import android.content.ContentValues;
import android.content.Context;

public class ClienteDAO {

    private final String TABLE_CLIENTES = "Clientes";
    private DbGateway gw;

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
}