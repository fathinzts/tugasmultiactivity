package com.example.multiactivityf1d016026;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG="Databasehelper";
    private static final String DATABASE_NAME ="Mahasiswa";

    private static final String TABLE_MAHASISWA="mahasiswa";
    private static final String uCOL1 ="nim";
    private static final String uCOL2 ="nama";
    private static final String uCOL3 ="alamat";
    private static final String uCOL4 ="no_hp";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableMahasiswa = "CREATE TABLE " + TABLE_MAHASISWA + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+ uCOL1 + " TEXT, "+ uCOL2 + " TEXT, "+ uCOL3 + " TEXT, "+ uCOL4 + " TEXT)";
        db.execSQL(createTableMahasiswa);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_MAHASISWA);
        onCreate(db);
    }

    public boolean addDataMahasiswa(String nim, String nama, String alamat,String no_hp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(uCOL1,nim);
        contentValues.put(uCOL2,nama);
        contentValues.put(uCOL3,alamat);
        contentValues.put(uCOL4,no_hp);

        long result = db.insert(TABLE_MAHASISWA,null,contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getDataMahasiswa () {
        SQLiteDatabase db =  this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_MAHASISWA;
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public Cursor getDetailMahasiswa (String nim) {
        SQLiteDatabase db =  this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_MAHASISWA+" WHERE nim='"+nim+"'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public boolean updateDataMahasiswa(String nim, String nama, String alamat, String no_hp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(uCOL2,nama);
        contentValues.put(uCOL3,alamat);
        contentValues.put(uCOL4,no_hp);

        long result = db.update(TABLE_MAHASISWA,contentValues,"nim='"+nim+"'",null);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }



}
