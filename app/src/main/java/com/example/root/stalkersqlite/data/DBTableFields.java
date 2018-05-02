package com.example.root.stalkersqlite.data;

import android.provider.BaseColumns;

public class DBTableFields {

    public static final class TableVitima implements BaseColumns {
        public static final String tableName = "vitima";

        public static final String nome = "v_nome";
        public static final String emprego = "v_emprego";
        public static final String dataNasc = "v_data_nasc";
        public static final String telefone = "v_telefone";
        public static final String descricao = "v_descricao";

        public static String createQuery() {
            return "CREATE TABLE " + tableName + " ( " +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    nome + " TEXT NOT NULL," +
                    emprego + " TEXT," +
                    dataNasc + " TEXT," +
                    telefone + " TEXT," +
                    descricao + " TEXT" +
                    " );";
        }

    }

}
