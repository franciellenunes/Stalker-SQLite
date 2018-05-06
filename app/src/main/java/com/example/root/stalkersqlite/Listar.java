package com.example.root.stalkersqlite;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.root.stalkersqlite.data.DBHelper;
import com.example.root.stalkersqlite.data.DBStalker;
import com.example.root.stalkersqlite.data.DBStalkerException;

public class Listar extends AppCompatActivity implements SearchView.OnQueryTextListener, RegsAdapter.RegsListener {

    public static final String EXTRA_MESSAGE = "br.edu.iftm.pdm.stalker.ID_VITIMA";
    private SQLiteDatabase stalkerDB;

    private RecyclerView regsList;
    private RegsAdapter regsAdapter;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        this.contruirLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.contruirLista();
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

    private void contruirLista(){
        DBStalker.dbInit(this);
        this.regsList = (RecyclerView) findViewById(R.id.rv_regs); //Pegando referência do Recycler view para configurá-lo
        LinearLayoutManager layoutManager = new LinearLayoutManager(this); //Usando o LinearLayout para o RecyclerView
        this.regsList.setLayoutManager(layoutManager);
        this.regsList.setHasFixedSize(true); //Determinando que as views serão de tamanho fixo

        try {
            this.regsAdapter = new RegsAdapter(this); //O Adapter é responsável por dispor cada view com o seu respectivo valor
        } catch (DBStalkerException e) {

        }
        this.regsList.setAdapter(this.regsAdapter);
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

    @Override
    public void onClickRegs(Vitima vit) {
        Intent intent = new Intent(this, MostrarVitima.class);
        intent.putExtra(Listar.EXTRA_MESSAGE, vit.getId());
        startActivity(intent);
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

