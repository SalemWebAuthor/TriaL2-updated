package com.example.trial2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperStock extends SQLiteOpenHelper {

    public DBHelperStock(Context context){
        super(context, "stock.db",null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase DBStock) {
        DBStock.execSQL("create Table Receive(receive_id TEXT primary key, supplier_name TEXT, prod_name TEXT, order_quantity TEXT, price TXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DBStock, int oldVersion, int newVersion) {
        DBStock.execSQL("drop Table if exists Recieve");
    }

    public Boolean addstock (String receive_id, String supplier_name, String prod_name, String order_quantity, String price){
        SQLiteDatabase DBStock = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("receive_id", receive_id);
        contentValues.put("supplier_name", supplier_name);
        contentValues.put("prod_name", prod_name);
        contentValues.put("order_quantity", order_quantity);
        contentValues.put("price", price);
        long result=DBStock.insert("Receive", null, contentValues);
        if (result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updatestock (String receive_id, String supplier_name, String prod_name, String order_quantity, String price) {
        SQLiteDatabase DBStock = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("receive_id", receive_id);
        contentValues.put("supplier_name", supplier_name);
        contentValues.put("prod_name", prod_name);
        contentValues.put("order_quantity", order_quantity);
        contentValues.put("price", price);
        Cursor cursor = DBStock.rawQuery("Select * from Receive where receive_id = ?", new String[]{receive_id});
        if (cursor.getCount() > 0) {

            long result = DBStock.update("Receive", contentValues, "receive_id=?", new String[]{receive_id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }

        } else {
            return false;
        }
    }

    public Boolean deletestock (String receive_id){
        SQLiteDatabase DBStock = this.getWritableDatabase();
        Cursor cursor = DBStock.rawQuery("Select * from Receive where receive_id = ?", new String[] {receive_id});
        if (cursor.getCount() > 0)
        {

            long result=DBStock.delete("Receive", "name=?", new String[] {receive_id});
            if (result==-1){
                return false;
            }else{
                return true;
            }

        }else {
            return false;
        }
    }

    public Cursor getdatastock () {
        SQLiteDatabase DBStock = this.getWritableDatabase();
        Cursor cursor = DBStock.rawQuery("Select * from Receive", null);
        return cursor;
    }

    Cursor readAllDatastock(){
        SQLiteDatabase DBStock = this.getReadableDatabase();

        Cursor cursor = null;
        if (DBStock != null){
            cursor = DBStock.rawQuery("Select * from Receive", null);
        }
        return cursor;
    }
}
