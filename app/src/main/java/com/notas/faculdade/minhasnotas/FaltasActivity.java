package com.notas.faculdade.minhasnotas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.notas.faculdade.minhasnotas.dao.AppDAO;
import com.notas.faculdade.minhasnotas.domain.Materia;

public class FaltasActivity extends Activity {
    private Long id;
    private AppDAO dao;
    private int faltas;
    private int carga_hr, limiteFaltas;
    private long resultado = 0;
    private CheckBox um, dois, tres, quatro;
    private Materia materia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_falta);
        dao = new AppDAO(this);

        um = (CheckBox) findViewById(R.id.um);
        dois = (CheckBox) findViewById(R.id.dois);
        tres = (CheckBox) findViewById(R.id.tres);
        quatro = (CheckBox) findViewById(R.id.quatro);

        id = getIntent().getLongExtra(Constantes.MATERIA_ID, -1);

        if(id != -1){
            materia = dao.buscaPorId(id);
            carga_hr = materia.getCarga_hr();
            faltas = materia.getFaltas();
            limiteFaltas = carga_hr * 25/100;
        }
    }

    public void registraFalta(int nova_falta){
        faltas = faltas + nova_falta;
        materia.setFaltas(faltas);
        resultado = dao.atualizarMateria(materia);
        if (faltas > limiteFaltas){
            Toast.makeText(this, getString(R.string.reprovado),
                    Toast.LENGTH_SHORT).show();
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

    public void salvarFalta (View view){
        if(um.isChecked()){
            registraFalta(1);
        }
        if(dois.isChecked()){
            registraFalta(2);
        }
        if(tres.isChecked()){
            registraFalta(3);
        }
        if (quatro.isChecked()){
            registraFalta(4);
        }
    }
}
