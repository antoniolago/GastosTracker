package com.example.gastostracker;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.view.View;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "banco.db";
    private static final String TABELA = "tbGastos";
    private static final String ID = "id";
    private static final String VALOR = "valor";
    private static final String DATA = "data";

    private static final int VERSAO = 4;
    private final String CRIA_GASTOS = "CREATE TABLE tbGastos (" +
            "pkGasto INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "descricao TEXT NOT NULL, " +
            "valor TEXT, " +
            "tipoGasto INTEGER," +
            "data DATE)";


    public DbHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    public Cursor listaGastos(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from tbGastos";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CRIA_GASTOS);
        }catch (SQLException e){
            throw e;
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists tbGastos" );
        onCreate(db);
    }
}