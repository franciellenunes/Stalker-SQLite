package com.example.root.stalkersqlite.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.root.stalkersqlite.Vitima;

import java.util.ArrayList;

public class DBStalker {

    private static DBHelper dbHelper;

    public  static  void dbInit(Context context) {
        dbHelper = new DBHelper(context);
    }

    public  static  boolean isThereVitimas() throws DBStalkerException {
        if(dbHelper == null){
            throw new DBStalkerException("DBStalker não foi inicializado!");
        }

        SQLiteDatabase stalkerDB = dbHelper.getReadableDatabase();

        String query = "SELECT COUNT(*) FROM "+ DBTableFields.TableVitima.tableName +";";
        Cursor cursor = stalkerDB.rawQuery(query, null); //Runs the provided SQL and returns a Cursor over the result set.
        cursor.moveToFirst();
        boolean test = (cursor.getInt(0) > 0);
        cursor.close();
        stalkerDB.close();
        return test;
    }

    public  static void insertVitima(Vitima vit) throws DBStalkerException {
        if (dbHelper == null){
            throw new DBStalkerException("DBStalker não foi inicializado!");
        }

        SQLiteDatabase stalkerDB = dbHelper.getWritableDatabase();

        try {
            stalkerDB.beginTransaction();

            ContentValues cv = new ContentValues();
            cv.put(DBTableFields.TableVitima.nome, vit.getNome());
            cv.put(DBTableFields.TableVitima.emprego, vit.getEmprego());
            cv.put(DBTableFields.TableVitima.dataNasc, vit.getDataNasc());
            cv.put(DBTableFields.TableVitima.telefone, vit.getTelefone());
            cv.put(DBTableFields.TableVitima.descricao, vit.getDescricao());

            stalkerDB.insert(DBTableFields.TableVitima.tableName, null, cv);

            stalkerDB.setTransactionSuccessful();
        } catch (SQLException e){
            throw new DBStalkerException("SQLError during insertion: " + e.getMessage());
        } finally {
            stalkerDB.endTransaction();
            stalkerDB.close();
        }
    }

    public  static void updateVitima(Vitima vit) throws DBStalkerException {
        if (dbHelper == null){
            throw new DBStalkerException("DBStalker não foi inicializado!");
        }

        SQLiteDatabase stalkerDB = dbHelper.getWritableDatabase();

        try {
            stalkerDB.beginTransaction();

            ContentValues cv = new ContentValues();
            cv.put(DBTableFields.TableVitima.nome, vit.getNome());
            cv.put(DBTableFields.TableVitima.emprego, vit.getEmprego());
            cv.put(DBTableFields.TableVitima.dataNasc, vit.getDataNasc());
            cv.put(DBTableFields.TableVitima.telefone, vit.getTelefone());
            cv.put(DBTableFields.TableVitima.descricao, vit.getDescricao());

            stalkerDB.update(DBTableFields.TableVitima.tableName,
                    cv,
                    DBTableFields.TableVitima._ID + " = " + Long.toString(vit.getId()),
                    null );

            stalkerDB.setTransactionSuccessful();
        } catch (SQLException e){
            throw new DBStalkerException("SQLError during insertion: " + e.getMessage());
        } finally {
            stalkerDB.endTransaction();
            stalkerDB.close();
        }
    }

    public static void deleteVitima(long id) throws DBStalkerException {
        if (dbHelper == null){
            throw new DBStalkerException("DBStalker não foi inicializado!");
        }

        SQLiteDatabase stalkerDB = dbHelper.getWritableDatabase();

        try {
            stalkerDB.beginTransaction();

            stalkerDB.delete(DBTableFields.TableVitima.tableName,
                    DBTableFields.TableVitima._ID + " = " + Long.toString(id),
                    null );

            stalkerDB.setTransactionSuccessful();
        } catch (SQLException e){
            throw new DBStalkerException("SQLError during insertion: " + e.getMessage());
        } finally {
            stalkerDB.endTransaction();
            stalkerDB.close();
        }

    }

    public static ArrayList<Vitima> getVitimasInfo() throws DBStalkerException{
        if(dbHelper == null){
            throw new DBStalkerException("DBStalker não foi inicializado!");
        }

        ArrayList<Vitima> vitimas = new ArrayList<Vitima>();
        SQLiteDatabase stalkerDB = dbHelper.getReadableDatabase();

        String[] columns = new String[]{
                DBTableFields.TableVitima._ID,
                DBTableFields.TableVitima.nome
        };

        Cursor cursor = stalkerDB.query(DBTableFields.TableVitima.tableName,
                columns,
                null,
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()){
            long id = cursor.getLong(cursor.getColumnIndex(DBTableFields.TableVitima._ID));
            String nome = cursor.getString(cursor.getColumnIndex(DBTableFields.TableVitima.nome));
            Vitima vit = new Vitima(id, nome);
            vitimas.add(vit);
        }

        cursor.close();
        stalkerDB.close();
        return vitimas;
    }

    public static Vitima getVitimaById(long id) throws DBStalkerException{
        if(dbHelper == null){
            throw new DBStalkerException("DBStalker não foi inicializado!");
        }

        SQLiteDatabase stalkerDB = dbHelper.getReadableDatabase();
        String[] columns = new String[]{
                DBTableFields.TableVitima._ID,
                DBTableFields.TableVitima.nome,
                DBTableFields.TableVitima.emprego,
                DBTableFields.TableVitima.dataNasc,
                DBTableFields.TableVitima.telefone,
                DBTableFields.TableVitima.descricao,
        };

        Cursor cursor = stalkerDB.query(DBTableFields.TableVitima.tableName,
                columns,
                DBTableFields.TableVitima._ID + " = " + Long.toString(id),
                null,
                null,
                null,
                null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            long idVit = cursor.getLong(cursor.getColumnIndex(DBTableFields.TableVitima._ID));
            String nome = cursor.getString(cursor.getColumnIndex(DBTableFields.TableVitima.nome));
            String emprego = cursor.getString(cursor.getColumnIndex(DBTableFields.TableVitima.emprego));
            String dataNasc = cursor.getString(cursor.getColumnIndex(DBTableFields.TableVitima.dataNasc));
            String telefone = cursor.getString(cursor.getColumnIndex(DBTableFields.TableVitima.telefone));
            String descricao = cursor.getString(cursor.getColumnIndex(DBTableFields.TableVitima.descricao));
            cursor.close();
            return new Vitima(idVit, nome, emprego, dataNasc, telefone, descricao);
        }
        cursor.close();
        return null;
    }

    public static Vitima getVitimaByNome(String contains) throws DBStalkerException {
        if(dbHelper == null){
            throw new DBStalkerException("DBStalker não foi inicializado!");
        }

        SQLiteDatabase stalkerDB = dbHelper.getReadableDatabase();
        String[] columns = new String[]{
                DBTableFields.TableVitima._ID,
                DBTableFields.TableVitima.nome,
                DBTableFields.TableVitima.emprego,
                DBTableFields.TableVitima.dataNasc,
                DBTableFields.TableVitima.telefone,
                DBTableFields.TableVitima.descricao
        };

        Cursor cursor = stalkerDB.query(DBTableFields.TableVitima.tableName,
                columns,
                DBTableFields.TableVitima.nome + " LIKE '%" +contains +"%'",
                null,
                null,
                null,
                null
        );

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            long idVit = cursor.getLong(cursor.getColumnIndex(DBTableFields.TableVitima._ID));
            String nome = cursor.getString(cursor.getColumnIndex(DBTableFields.TableVitima.nome));
            String emprego = cursor.getString(cursor.getColumnIndex(DBTableFields.TableVitima.emprego));
            String dataNasc = cursor.getString(cursor.getColumnIndex(DBTableFields.TableVitima.dataNasc));
            String telefone = cursor.getString(cursor.getColumnIndex(DBTableFields.TableVitima.telefone));
            String descricao = cursor.getString(cursor.getColumnIndex(DBTableFields.TableVitima.descricao));
            cursor.close();
            return new Vitima(idVit, nome, emprego, dataNasc, telefone, descricao);
        }
        cursor.close();
        return null;
    }

}
