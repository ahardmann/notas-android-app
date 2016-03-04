package com.notas.faculdade.minhasnotas.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.notas.faculdade.minhasnotas.db.DatabaseHelper;
import com.notas.faculdade.minhasnotas.domain.Materia;

import java.util.ArrayList;
import java.util.List;

public class AppDAO {

    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public AppDAO(Context context){
        helper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDb(){
        if(db == null){
            db = helper.getWritableDatabase();
        }
        return db;
    }

    public void close(){
        helper.close();
        db = null;
    }

    public List<Materia> listarMaterias(){
        String orderBy = "SEMESTRE DESC";

        Cursor cursor = getDb().query(DatabaseHelper.Materia.TABELA
                , DatabaseHelper.Materia.COLUNAS, null, null, null, null, orderBy);
        cursor.moveToFirst();

        List<Materia> materias = new ArrayList<Materia>();

        if(cursor.moveToFirst()&& cursor.getCount() >= 1){
            do{
                Materia materia = criarMateria(cursor);
                materias.add(materia);
            }while(cursor.moveToNext());
        }

        cursor.close();
        return materias;
    }

    public Materia buscaPorId(Long id){
        Cursor cursor = getDb().query(DatabaseHelper.Materia.TABELA, DatabaseHelper.Materia.COLUNAS
                , DatabaseHelper.Materia._ID +" = ?", new String[]{id.toString()}, null, null, null);

        if(cursor.moveToNext()){
            Materia materia = criarMateria(cursor);
            cursor.close();
            return materia;
        }
        return null;
    }

    public long inserirMateria(Materia materia){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.Materia.DISCIPLINA, materia.getDisciplina());
        values.put(DatabaseHelper.Materia.SEMESTRE, materia.getSemestre());
        values.put(DatabaseHelper.Materia.PROFESSOR, materia.getProfessor());
        values.put(DatabaseHelper.Materia.CARGA_HR, materia.getCarga_hr());
        values.put(DatabaseHelper.Materia.FALTAS, materia.getFaltas());

        return getDb().insert(DatabaseHelper.Materia.TABELA, null, values);
    }

    public boolean removerMateria(Long id){
        String whereClause = DatabaseHelper.Materia._ID + " = ?";
        String[] whereArgs = new String[]{id.toString()};
        int remover = getDb().delete(DatabaseHelper.Materia.TABELA, whereClause, whereArgs);

        return remover > 0;
    }

    public long atualizarMateria (Materia materia){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.Materia.DISCIPLINA, materia.getDisciplina());
        values.put(DatabaseHelper.Materia.SEMESTRE, materia.getSemestre());
        values.put(DatabaseHelper.Materia.PROFESSOR, materia.getProfessor());
        values.put(DatabaseHelper.Materia.CARGA_HR, materia.getCarga_hr());
        values.put(DatabaseHelper.Materia.FALTAS, materia.getFaltas());
        return getDb().update(DatabaseHelper.Materia.TABELA,values,DatabaseHelper.Materia._ID + " = ?"
                , new String[]{materia.getId().toString()});
    }

    private Materia criarMateria(Cursor cursor){
        Materia materia = new Materia(
                cursor.getLong(cursor.getColumnIndex(DatabaseHelper.Materia._ID)),
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Materia.FALTAS)),
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Materia.SEMESTRE)),
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Materia.CARGA_HR)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Materia.PROFESSOR)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.Materia.DISCIPLINA))
        );
        return materia;
    }
}
