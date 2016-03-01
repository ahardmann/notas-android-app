package com.notas.faculdade.minhasnotas.materias;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.notas.faculdade.minhasnotas.R;
import com.notas.faculdade.minhasnotas.db.DatabaseHelper;

public class CadMateriaActivity extends AppCompatActivity {

    private EditText disciplina, professor, semestre, carga_hr;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_materia);

        disciplina = (EditText) findViewById(R.id.disciplina);
        professor = (EditText) findViewById(R.id.professor);
        semestre = (EditText) findViewById(R.id.semestre);
        carga_hr = (EditText) findViewById(R.id.carga_hr);

        helper = new DatabaseHelper(this);
    }

    public void salvarMateria(View view){
        String TAG = "teste";

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("disciplina", disciplina.getText().toString());
        values.put("professor", professor.getText().toString());
        values.put("semestre", semestre.getText().toString());
        values.put("carga_hr", carga_hr.getText().toString());
        values.put("faltas", "0".toString());

        long resultado = db.insert("materias", null, values);
        if(resultado != -1 ){
            Toast.makeText(this, getString(R.string.registro_salvo),
                    Toast.LENGTH_SHORT).show();

            Log.i(TAG, "salvarMateria: " + values);
            startActivity(new Intent(this, MateriasListActivity.class));

        }else{
            Toast.makeText(this, getString(R.string.erro_salvar),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        helper.close();
        super.onDestroy();
    }
}
