package com.notas.faculdade.minhasnotas.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "MinhasNotas";
    private static int VERSAO = 1;

    public DatabaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE materias (_id INTEGER PRIMARY KEY,"+
                " disciplina TEXT, professor TEXT, semestre INTEGER," +
                " carga_hr INTEGER, faltas INTEGER);");

        db.execSQL("CREATE TABLE notas (_id INTEGER PRIMARY KEY,"+
                " nota_qt DOUBLE, nota_ql DOUBLE, media DOUBLE," +
                " media_final DOUBLE, materia_id INTEGER," +
                " FOREIGN KEY(materia_id) REFERENCES materias(_id));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
