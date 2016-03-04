package com.notas.faculdade.minhasnotas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicial);
    }

    public void selecionarOpcao(View view) {
        switch (view.getId()) {
            case R.id.nova_materia:
                startActivity(new Intent(this, CadMateriaActivity.class));
                break;

            case R.id.minhas_materias:
                startActivity(new Intent(this, MateriasListActivity.class));
                break;
        }
    }
}
