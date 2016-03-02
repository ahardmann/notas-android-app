package com.notas.faculdade.minhasnotas.materias;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import com.notas.faculdade.minhasnotas.R;
import com.notas.faculdade.minhasnotas.db.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MateriasListActivity extends ListActivity implements  AdapterView.OnItemClickListener, DialogInterface.OnClickListener {

    private List<Map<String, Object>> materias;
    private DatabaseHelper helper;
    private AlertDialog alertDialog, confirmDialog;
    private int materiaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        helper = new DatabaseHelper(this);

        String[] de = { "disciplina", "semestre", "professor", "carga_hr" };
        int[] para = { R.id.disciplina, R.id.semestre, R.id.professor, R.id.carga_hr};
        SimpleAdapter adapter = new SimpleAdapter(this, listarMaterias()
                , R.layout.materia_list, de, para);

        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

        this.alertDialog = criaAlertDialog();
        this.confirmDialog = dialogConfirmacao();
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
        switch (which){
            case 0:
//                startActivity(new Intent(this, NotasActivity.class));
                break;
            case 1:
//                startActivity(new Intent(this, FaltasActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, CadMateriaActivity.class));
                break;
            case 3:
                confirmDialog.show();
                break;
            case DialogInterface.BUTTON_POSITIVE:
                materias.remove(this.materiaSelecionada);
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

        SQLiteDatabase db = helper.getReadableDatabase();
        String tabela = "materias";
        String[] colunas = new String[]{"_id", "disciplina", "semestre",
                "professor","carga_hr"};

        String selecao = null;
        String[] selecaoArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = "semestre DESC";

        Cursor cursor = db.query(tabela, colunas, selecao, selecaoArgs, groupBy, having, orderBy);
        cursor.moveToFirst();

        materias = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < cursor.getCount(); i++){
            Map<String, Object> item = new HashMap<String, Object>();

            String id = cursor.getString(0);
            String disciplina = cursor.getString(1);
            int semestre = cursor.getInt(2);
            String profesor = cursor.getString(3);
            int carga_hr = cursor.getInt(4);

            item.put("id", id);
            item.put("disciplina", disciplina);
            item.put("semestre", semestre);
            item.put("professor", profesor);
            item.put("carga_hr", carga_hr);

            materias.add(item);
            cursor.moveToNext();
        }
        cursor.close();

        return materias;
    }

    private int getNumFaltas(SQLiteDatabase db, String id){
        Cursor cursor = db.rawQuery("SELECT faltas FROM materias WHERE _id = ?", new String[]{id});
        cursor.moveToFirst();
        int faltas = cursor.getInt(0);
        cursor.close();
        return faltas;
    }

}