package com.notas.faculdade.minhasnotas.materias;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.notas.faculdade.minhasnotas.R;
import com.notas.faculdade.minhasnotas.db.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MateriasListActivity extends ListActivity implements  AdapterView.OnItemClickListener {

    private List<Map<String, Object>> materias;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        helper = new DatabaseHelper(this);
//        SharedPreferences preferencias  = PreferenceManager.getDefaultSharedPreferences(this);

        String[] de = { "disciplina", "semestre", "professor", "carga_hr" };
        int[] para = { R.id.disciplina, R.id.semestre, R.id.professor, R.id.carga_hr};
        SimpleAdapter adapter = new SimpleAdapter(this, listarMaterias()
                , R.layout.materia_list, de, para);

        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

        registerForContextMenu(getListView());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map<String, Object> map = materias.get(position);
        String disciplina = (String) map.get("disciplina");
        String mensagem = "Materia selecionada: " + disciplina;
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_materias, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.remover) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                    .getMenuInfo();
            materias.remove(info.position);

            //redesenhar as linhas da list
            getListView().invalidateViews();
            return true;
        }
        return super.onContextItemSelected(item);
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

}
