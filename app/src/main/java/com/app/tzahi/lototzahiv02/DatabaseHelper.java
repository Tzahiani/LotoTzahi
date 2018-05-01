package com.app.tzahi.lototzahiv02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "lotoDB.db";
    public static final String TABLE_NAME = "loto_table";
    public static final String ID = "ID";
    public static final String NUM1 = "NUM1";
    public static final String NUM2 = "NUM2";
    public static final String NUM3 = "NUM3";
    public static final String NUM4 = "NUM4";
    public static final String NUM5 = "NUM5";
    public static final String NUM6 = "NUM6";
    public static final String NUM_STRONG = "NUM_STRONG";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NUM1 TEXT,NUM2 TEXT,NUM3 TEXT,NUM4 TEXT,NUM5 TEXT,NUM6 TEXT,NUM_STRONG TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String num1,String num2,String num3,String num4,String num5,String num6,String numStrong){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NUM1,num1);
        contentValues.put(NUM2,num2);
        contentValues.put(NUM3,num3);
        contentValues.put(NUM4,num4);
        contentValues.put(NUM5,num5);
        contentValues.put(NUM6,num6);
        contentValues.put(NUM_STRONG,numStrong);

        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " +TABLE_NAME,null);
        return res;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[] {id});
    }
}