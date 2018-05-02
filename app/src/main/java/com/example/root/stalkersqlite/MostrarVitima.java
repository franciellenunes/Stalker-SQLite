package com.example.root.stalkersqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.stalkersqlite.data.DBStalker;
import com.example.root.stalkersqlite.data.DBStalkerException;

public class MostrarVitima extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "br.edu.iftm.pdm.stalker.EXTRA_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_vitima);
    }

    @Override
    protected void onStart() {
        super.onStart();
        long id = getIntent().getLongExtra(Listar.EXTRA_MESSAGE, 0);
        this.preencherCampos(id);
    }

    private void preencherCampos(long idVitima){
        TextView txtNomeRes = (TextView) findViewById(R.id.txtNomeRes);
        TextView txtEmpRes = (TextView) findViewById(R.id.txtEmpRes);
        TextView txtDataNascRes = (TextView) findViewById(R.id.txtDataNascRes);
        TextView txtTelRes = (TextView) findViewById(R.id.txtTelRes);
        TextView txtDescRes = (TextView) findViewById(R.id.txtDescRes);

        try {
            Vitima vit = DBStalker.getVitimaById(idVitima);
            txtNomeRes.setText(vit.getNome());
            txtEmpRes.setText(vit.getEmprego());
            txtDataNascRes.setText(vit.getDataNasc());
            txtTelRes.setText(vit.getTelefone());
            txtDescRes.setText(vit.getDescricao());
        } catch (DBStalkerException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void clickEditar(View view) {
        Intent intent = new Intent(this, EditarVitima.class);
        long id = getIntent().getLongExtra(Listar.EXTRA_MESSAGE,0);
        intent.putExtra(MostrarVitima.EXTRA_MESSAGE, id);
        this.startActivity(intent);
    }

    public void clickRemover(View view) {
        long id = getIntent().getLongExtra(Listar.EXTRA_MESSAGE,0);

        try {
            DBStalker.deleteVitima(id);
            Toast.makeText(this, "Vítima excluída com sucesso!", Toast.LENGTH_SHORT).show();
            finish();
        } catch (DBStalkerException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
