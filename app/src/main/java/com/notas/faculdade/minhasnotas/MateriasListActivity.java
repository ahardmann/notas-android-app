package com.notas.faculdade.minhasnotas;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import com.notas.faculdade.minhasnotas.CadMateriaActivity;
import com.notas.faculdade.minhasnotas.R;
import com.notas.faculdade.minhasnotas.dao.AppDAO;
import com.notas.faculdade.minhasnotas.domain.Materia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MateriasListActivity extends ListActivity implements  AdapterView.OnItemClickListener, DialogInterface.OnClickListener {

    private List<Map<String, Object>> materias;
    private AlertDialog alertDialog, confirmDialog;
    private int materiaSelecionada;
    private AppDAO dao;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dao = new AppDAO(this);
        this.alertDialog = criaAlertDialog();
        this.confirmDialog = dialogConfirmacao();

        String[] de = { "disciplina", "semestre", "professor", "faltas"};
        int[] para = { R.id.disciplina, R.id.semestre, R.id.professor, R.id.faltas};
        SimpleAdapter adapter = new SimpleAdapter(this, listarMaterias()
                , R.layout.materia_list, de, para);

        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    //list
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.materiaSelecionada = position;
        alertDialog.show();
    }

    //dialog
    @Override
    public void onClick(DialogInterface dialog, int which) {
        Long idMateria = (Long) materias.get(materiaSelecionada).get("id");
        switch (which){
            //notas
            case 0:
                intent = new Intent(this, NotasActivity.class );
                intent.putExtra(Constantes.MATERIA_ID, idMateria);
                startActivity(intent);
                break;
            //faltas
            case 1:
                intent = new Intent(this, FaltasActivity.class );
                intent.putExtra(Constantes.MATERIA_ID, idMateria);
                startActivity(intent);
                break;
            //editar
            case 2:
                intent = new Intent(this,CadMateriaActivity.class );
                intent.putExtra(Constantes.MATERIA_ID, idMateria);
                startActivity(intent);
                break;
            //excluir
            case 3:
                confirmDialog.show();
                break;
            case DialogInterface.BUTTON_POSITIVE:
                materias.remove(materiaSelecionada);
                dao.removerMateria(Long.valueOf(idMateria));
                getListView().invalidateViews();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                confirmDialog.dismiss();
                break;
        }
    }

    //itens do dialog
    private AlertDialog criaAlertDialog() {
        final CharSequence[] items = {
                getString(R.string.notas),
                getString(R.string.nova_falta),
                getString(R.string.editar),
                getString(R.string.excluir) };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.opcoes);
        builder.setItems(items, this);
        return builder.create();
    }

    //dialog de confirmação
    private AlertDialog dialogConfirmacao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.confirmacao_exclusao);
        builder.setPositiveButton(getString(R.string.sim), this);
        builder.setNegativeButton(getString(R.string.nao), this);
        return builder.create();
    }

    private List<Map<String, Object>> listarMaterias(){
        materias = new ArrayList<Map<String, Object>>();
        List<Materia> listaMateria = dao.listarMaterias();

        for(Materia materia : listaMateria){
            Map<String, Object> item = new HashMap<String, Object>();

            item.put("id", materia.getId());
            item.put("semestre", materia.getSemestre());
            item.put("carga_hr", materia.getCarga_hr());
            item.put("disciplina", materia.getDisciplina());
            item.put("professor", materia.getProfessor());
            item.put("faltas", materia.getFaltas());

            materias.add(item);
        }

        return materias;
    }
}