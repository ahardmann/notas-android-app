package com.notas.faculdade.minhasnotas.materias;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.notas.faculdade.minhasnotas.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MateriasListActivity extends ListActivity implements  AdapterView.OnItemClickListener {

    private List<Map<String, Object>> materias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] de = { "disciplina", "semestre", "professor" };
        int[] para = { R.id.disciplina, R.id.semestre, R.id.professor};
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
        materias = new ArrayList<Map<String, Object>>();
        Map<String, Object> item = new HashMap<String, Object>();
        item.put("disciplina", "android");
        item.put("semestre", "5");
        item.put("professor", "portuga");
        materias.add(item);

        item = new HashMap<String, Object>();
        item.put("disciplina", "android");
        item.put("semestre", "5");
        item.put("professor", "portuga");
        materias.add(item);

        item = new HashMap<String, Object>();
        item.put("disciplina", "android");
        item.put("semestre", "5");
        item.put("professor", "portuga");
        materias.add(item);

        return materias;
    }
}
