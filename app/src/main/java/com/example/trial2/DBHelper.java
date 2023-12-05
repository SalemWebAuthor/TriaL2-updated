package com.example.trial2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context, "Userdata.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(name TEXT primary key, category TEXT, stocks TEXT, price TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertuserdata (String name, String category, String stocks, String price){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("category", category);
        contentValues.put("stocks", stocks);
        contentValues.put("price", price);
        long result=DB.insert("Userdetails", null, contentValues);
        if (result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updateuserdata (String name, String category, String stocks, String price) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("category", category);
        contentValues.put("stocks", stocks);
        contentValues.put("price", price);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {

            long result = DB.update("Userdetails", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }

        } else {
            return false;
        }
    }

    public Boolean deletedata (String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[] {name});
        if (cursor.getCount() > 0)
        {

            long result=DB.delete("Userdetails", "name=?", new String[] {name});
            if (result==-1){
                return false;
            }else{
                return true;
            }

        }else {
            return false;
        }
    }

    public Cursor getdata () {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;
    }

    Cursor readAllData(){
        SQLiteDatabase DB = this.getReadableDatabase();

        Cursor cursor = null;
        if (DB != null){
            cursor = DB.rawQuery("Select * from Userdetails", null);
        }
        return cursor;
    }
}
