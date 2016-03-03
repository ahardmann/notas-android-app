package com.notas.faculdade.minhasnotas.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "MinhasNotas";
    private static final int VERSAO = 1;
//    id,semestre,faltas,carga,disc,prof
    public static class Materia{
        public static final String TABELA = "MATERIAS";
        public static final String _ID= "_ID";
        public static final String DISCIPLINA = "DISCIPLINA";
        public static final String PROFESSOR = "PROFESSOR";
        public static final String SEMESTRE = "SEMESTRE";
        public static final String CARGA_HR = "CARGA_HR";
        public static final String FALTAS = "FALTAS";

        public static final String[] COLUNAS = new String[]{
            _ID,SEMESTRE,FALTAS,CARGA_HR,DISCIPLINA,PROFESSOR
        };
    }

    public static class Notas{
        public static final String TABELA = "NOTAS";
        public static final String _ID = "_ID";
        public static final String NOTAQT1 = "NOTAQT1";
        public static final String NOTAQL1 = "NOTAQL1";
        public static final String NOTAQT2 = "NOTAQT2";
        public static final String NOTAQL2 = "NOTAQL2";
        public static final String NOTAQT3 = "NOTAQT3";
        public static final String NOTAQL3 = "NOTAQL3";
        public static final String MEDIA1 = "MEDIA1";
        public static final String MEDIA2 = "MEDIA2";
        public static final String MEDIA3 = "MEDIA3";
        public static final String MEDIA_FINAL= "MEDIA_FINAL";
        public static final String MATERIA_ID = "MATERIA_ID";

        public static final String[] COLUNAS = new String[]{
                _ID,NOTAQT1,NOTAQL1,NOTAQT2,NOTAQL2,NOTAQT3,NOTAQL3
                ,MEDIA1,MEDIA2,MEDIA3,MEDIA_FINAL,MATERIA_ID
        };
    }

    public DatabaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE MATERIAS (_ID INTEGER PRIMARY KEY autoincrement,"+
                "SEMESTRE INTEGER, FALTAS INTEGER, CARGA_HR INTEGER," +
                " DISCIPLINA TEXT, PROFESSOR TEXT);");

        db.execSQL("CREATE TABLE NOTAS (_ID INTEGER PRIMARY KEY autoincrement,"+
                " NOTAQT1 DOUBLE, NOTAQL1 DOUBLE, MEDIA1 DOUBLE," +
                " NOTAQT2 DOUBLE, NOTAQL2 DOUBLE, MEDIA2 DOUBLE," +
                " NOTAQT3 DOUBLE, NOTAQL3 DOUBLE, MEDIA3 DOUBLE," +
                " MEDIA_FINAL DOUBLE, MATERIA_ID INTEGER," +
                " FOREIGN KEY(MATERIA_ID) REFERENCES MATERIAS (_ID));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
