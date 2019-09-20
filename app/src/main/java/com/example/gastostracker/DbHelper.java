package com.example.gastostracker;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.view.View;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "banco.db";
    private static final int VERSAO = 1;
    private final String CRIA_CLIENTE = "CREATE TABLE tbCliente (" +
            "pkCliente INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Nome TEXT NOT NULL, Sexo TEXT, UF TEXT NOT NULL)";

    private final String CRIA_GASTOS = "CREATE TABLE tbGastos (pkGasto INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Descricao TEXT NOT NULL, Valor TEXT, UF TEXT NOT NULL, fkCliente INTEGER FOREIGN KEY NOT NULL, " +
            "fkCategoria INTEGER FOREIGN KEY NOT NULL)" +
            "fkGasto INTEGER NOT NULL," +
            "CONSTRAINT fkGasto(pkGasto) " +
            "REFERENCES tbGastos (pkGasto))" +
            "CONSTRAING fkCategoria (pkCategoria)" +
            "REFERENCES tbCategorias";

    private final String CRIA_CATEGORIAS = "CREATE TABLE tbCategorias (" +
            "pkCategoria INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Nome TEXT NOT NULL, Descricao TEXT, UF TEXT NOT NULL, " +
            "fkGasto INTEGER FOREIGN KEY NOT NULL)" +
            "CONSTRAINT fkGasto(pkGasto)" +
            "REFERENCES tbGastos;";

    public DbHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CRIA_CATEGORIAS);
        db.execSQL(CRIA_CLIENTE);
        db.execSQL(CRIA_GASTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists tbCliente" );
        onCreate(db);
    }
}