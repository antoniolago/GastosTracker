package com.example.gastostracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.Objects;

public class GastoDAO {
    private SQLiteDatabase db;
    private DbHelper banco;
    private final String TABLE_CLIENTES = "tbClientes";
    private DbGateway gw;

    GastoDAO(Context baseContext) {

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    String salvar(Gasto gasto){
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();
        try{
            valores=new ContentValues();
            valores.put("descricao", gasto.getDescricao());
            valores.put("valor", gasto.getValor());
            valores.put("data",gasto.getData().toString() );
            resultado= db.insert("tbGastos",null,valores);
            db.close();
            if (resultado !=-1){

                return "Gasto inserido ="+ gasto.getDescricao();
            }
        }catch (SQLException e){
            Log.e("ERRO", Objects.requireNonNull(e.getMessage()));
        }

        return "Erro!";

    }
    public String deletar(Gasto c){
        String where = "id = " +c.getId();
        db= banco.getReadableDatabase();
        db.delete("tbGastos",where,null);
        db.close();
        return "Removido";
    }
}

