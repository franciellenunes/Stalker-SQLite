package com.example.root.stalkersqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.root.stalkersqlite.data.DBStalker;
import com.example.root.stalkersqlite.data.DBStalkerException;

public class EditarVitima extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_vitima);
    }

    @Override
    protected void onStart() {
        super.onStart();
        long id = getIntent().getLongExtra(MostrarVitima.EXTRA_MESSAGE, 0);

        try {
            Vitima vit = DBStalker.getVitimaById(id);
            ((EditText)findViewById(R.id.txtNome)).setText(vit.getNome());
            ((EditText)findViewById(R.id.txtEmprego)).setText(vit.getEmprego());
            ((EditText)findViewById(R.id.txtDataNasc)).setText(vit.getDataNasc());
            ((EditText)findViewById(R.id.txtTel)).setText(vit.getTelefone());
            ((EditText)findViewById(R.id.txtDesc)).setText(vit.getDescricao());
        } catch (DBStalkerException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void clickSalvar(View view){

        EditText txtNome = (EditText) findViewById(R.id.txtNome);
        EditText txtEmprego = (EditText) findViewById(R.id.txtEmprego);
        EditText txtDataNasc = (EditText) findViewById(R.id.txtDataNasc);
        EditText txtTel = (EditText) findViewById(R.id.txtTel);
        EditText txtDesc = (EditText) findViewById(R.id.txtDesc);

        String nome = txtNome.getText().toString();
        String emprego = txtEmprego.getText().toString();
        String dataNasc = txtDataNasc.getText().toString();
        String tel = txtTel.getText().toString();
        String desc = txtDesc.getText().toString();

        long id = getIntent().getLongExtra(MostrarVitima.EXTRA_MESSAGE,0);
        if(!nome.isEmpty()) {
            Vitima vit = new Vitima(id, nome, emprego, dataNasc, tel, desc);
            try {
                DBStalker.updateVitima(vit);
            } catch (DBStalkerException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "A vítima precisa ter um nome", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this, "Vítima editada com sucesso!", Toast.LENGTH_SHORT).show();
        finish();
    }

}
