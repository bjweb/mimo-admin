package com.bjweb.brunelljosny.mimoadmin;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String sender, String montant, String statut, String date, String trans){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO liste VALUES (NULL, ?,?,?,?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, sender);
        statement.bindString(2, statut);
        statement.bindString(3, date);
        statement.bindString(4, montant);
        statement.bindString(5, trans);
        statement.executeInsert();
    }

    public void insertData2(String libelle, String date, String magasin, String details, String total){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO commande VALUES (NULL, ?,?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, libelle);
        statement.bindString(2, date);
        statement.bindString(3, magasin);
        statement.bindString(4, details);
        statement.bindString(5, total);
        statement.executeInsert();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
