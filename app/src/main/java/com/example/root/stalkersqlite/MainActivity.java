package com.example.root.stalkersqlite;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.root.stalkersqlite.data.DBStalker;
import com.example.root.stalkersqlite.data.DBStalkerException;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase stalkerDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        DBStalker.dbInit(this);
    }

    public void clickCadastrar(View view) {
        Intent intent = new Intent(this, Cadastrar.class);
        this.startActivity(intent);
    }

    public void clickListar(View view) {
        try {
            if (!DBStalker.isThereVitimas()) {
                Toast.makeText(this, "Não há registros!", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, Listar.class);
                this.startActivity(intent);
            }
        } catch (DBStalkerException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
