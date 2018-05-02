package com.example.root.stalkersqlite;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.root.stalkersqlite.data.DBHelper;
import com.example.root.stalkersqlite.data.DBStalker;
import com.example.root.stalkersqlite.data.DBStalkerException;

public class Listar extends AppCompatActivity implements SearchView.OnQueryTextListener {

    public static final String EXTRA_MESSAGE = "br.edu.iftm.pdm.stalker.ID_VITIMA";
    private SQLiteDatabase stalkerDB;

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        this.construirLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbHelper = new DBHelper(this);
        stalkerDB = dbHelper.getReadableDatabase();
        this.construirLista();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        if(toast != null){
            toast.cancel();
        }

        try {
            Vitima vit = DBStalker.getVitimaByNome(s);

            if(vit != null) {
                Intent intent = new Intent(this, MostrarVitima.class);
                intent.putExtra(EXTRA_MESSAGE, vit.getId());
                startActivity(intent);
                return true;
            } else {
                toast = Toast.makeText(this, "Não encontrado!", Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        } catch (DBStalkerException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        this.getMenuInflater().inflate(R.menu.main, menu);
        SearchView sview = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sview.setOnQueryTextListener(this);
        return true;
    }

    private void construirLista(){
        LinearLayout layout = (LinearLayout) findViewById(R.id.layLista);

        if(layout.getChildCount() > 0){
            layout.removeAllViews();
        }
        try {
            for(Vitima vit: DBStalker.getVitimasInfo()){
                Button btn = new Button(this);
                btn.setText(vit.getNome());
                btn.setOnClickListener(new btnListener(vit.getId()));
                layout.addView(btn);
            }
        } catch (DBStalkerException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

}

class btnListener implements View.OnClickListener{

    private long id;

    btnListener(long id) {
        this.id = id;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), MostrarVitima.class);
        intent.putExtra(Listar.EXTRA_MESSAGE, this.id);
        view.getContext().startActivity(intent);
    }
}

