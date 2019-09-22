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

    private final String CRIA_GASTOS = "CREATE TABLE tbGastos (" +
            "pkGasto INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Descricao TEXT NOT NULL, " +
            "Valor TEXT, " +
            "fkCliente INTEGER NOT NULL, " +
            "fkCategoria INTEGER NOT NULL," +
            "FOREIGN KEY(fkCategoria) REFERENCES tbCategorias(pkCategoria)" +
            "FOREIGN KEY(fkCliente) REFERENCES tbClientes(pkCliente))";

    private final String CRIA_CATEGORIAS = "CREATE TABLE tbCategorias (" +
            "pkCategoria INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Nome TEXT NOT NULL, Descricao TEXT, UF TEXT NOT NULL, ";
    private final String POPULA_CATEGORIAS = "" +
            "INSERT INTO tbCategorias (Nome)\n" +
            "VALUES ('Saude'); " +
            "INSERT INTO tbCategorias (Nome)\n" +
            "VALUES ('Gerais'); " +
            "INSERT INTO tbCategorias (Nome)\n" +
            "VALUES ('Alimentacao'); " +
            "INSERT INTO tbCategorias (Nome)\n" +
            "VALUES ('Transporte'); " +
            "INSERT INTO tbCategorias (Nome)\n" +
            "VALUES ('Doacoes'); " +
            "INSERT INTO tbCategorias (Nome)\n" +
            "VALUES ('Contas'); ";

    public DbHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CRIA_CATEGORIAS);
        db.execSQL(CRIA_CLIENTE);
        db.execSQL(CRIA_GASTOS);
        db.execSQL(POPULA_CATEGORIAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists tbGastos" );
        onCreate(db);
    }
}