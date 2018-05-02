package com.example.root.stalkersqlite;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.root.stalkersqlite.data.DBHelper;
import com.example.root.stalkersqlite.data.DBStalker;
import com.example.root.stalkersqlite.data.DBStalkerException;

public class Cadastrar extends AppCompatActivity {

    private SQLiteDatabase stalkerDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbHelper = new DBHelper(this);
        stalkerDB = dbHelper.getWritableDatabase();
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

        if(!nome.isEmpty()) {
            Vitima vit = new Vitima(nome, emprego, dataNasc, tel, desc);
            try {
                DBStalker.insertVitima(vit);
            } catch (DBStalkerException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
            finish();
        } else {
            Toast.makeText(this, "Informe o nome da vítima!", Toast.LENGTH_LONG).show();
        }

    }
}
