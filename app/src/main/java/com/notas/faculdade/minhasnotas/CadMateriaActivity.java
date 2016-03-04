package com.notas.faculdade.minhasnotas;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.notas.faculdade.minhasnotas.dao.AppDAO;
import com.notas.faculdade.minhasnotas.domain.Materia;

public class CadMateriaActivity extends AppCompatActivity {

    private EditText disciplina, professor, semestre, carga_hr;
    private Long id;
    private AppDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_materia);

        disciplina = (EditText) findViewById(R.id.disciplina);
        professor = (EditText) findViewById(R.id.professor);
        semestre = (EditText) findViewById(R.id.semestre);
        carga_hr = (EditText) findViewById(R.id.carga_hr);

        dao = new AppDAO(this);
        id = getIntent().getLongExtra(Constantes.MATERIA_ID, -1);

        if(id != -1){
            editar();
        }
    }

    public void salvarMateria(View view){
        Materia materia = new Materia();
        materia.setDisciplina(disciplina.getText().toString());
        materia.setProfessor(professor.getText().toString());
        materia.setSemestre(Integer.valueOf(semestre.getText().toString()));
        materia.setCarga_hr(Integer.valueOf(carga_hr.getText().toString()));
        materia.setFaltas(0);

        long resultado;

        if(id == -1){
            resultado = dao.inserirMateria(materia);
            new Task().execute(materia);
        }   else{
            materia.setId(id);
            resultado = dao.atualizarMateria(materia);
        }

        if(resultado != -1 ){
            Toast.makeText(this, getString(R.string.registro_salvo),
                    Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MateriasListActivity.class));
        }else{
            Toast.makeText(this, getString(R.string.erro_salvar),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void editar(){
        Materia materia = dao.buscaPorId(id);
        disciplina.setText(materia.getDisciplina());
        semestre.setText(String.valueOf(materia.getSemestre()));
        professor.setText(materia.getProfessor());
        carga_hr.setText(String.valueOf(materia.getCarga_hr()));
    }

    @Override
    protected void onDestroy() {
        dao.close();
        super.onDestroy();
    }

    private class Task extends AsyncTask<Materia, Void, Void> {
        @Override
        protected Void doInBackground(Materia... materias) {
            Materia materia = materias[0];
            return null;
        }
    }
}
