package com.codingwithpratit.sqlliteproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Address;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.jar.Attributes;

import Model.Data;

public class DatabaseCrud extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Info1.db";
    public static final String TABLE_NAME = "Info_table";
    public static final String COL_1 = "Snn";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "PhoneNumber";
    public static final String COL_4 = "Address";

    public DatabaseCrud(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (Snn text NOT NULL primary key , Name text NOT NULL, PhoneNumber text NOT NULL, Address Text NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(Data obj){


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,obj.getSnn());
        contentValues.put(COL_2, obj.getName());
        contentValues.put(COL_3, obj.getPhoneNumber());
        contentValues.put(COL_4, obj.getAddress());

        long result = db.insert(TABLE_NAME, "", contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }
    public boolean updateData(Data obj) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, obj.getSnn());
        contentValues.put(COL_2, obj.getName());
        contentValues.put(COL_3, obj.getPhoneNumber());
        contentValues.put(COL_4, obj.getAddress());
        db.update(TABLE_NAME, contentValues,"Snn = ?", new String[] {obj.getSnn()});
        return true;

    }
    public Integer deleteData(Data obj) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "Snn = ?", new String[] {obj.getSnn()});
    }
    public Cursor viewAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("Select * from " + TABLE_NAME,null);
        return result;
    }

}
