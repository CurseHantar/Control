package com.cursehantar.completecontrol.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "create table users(usuario text,email,password text)";
        sqLiteDatabase.execSQL(qry1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void registro(String usuario,String email,String password){
        ContentValues cv = new ContentValues();
        cv.put("usuario",usuario);
        cv.put("email",email);
        cv.put("password",password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users",null,cv);
        db.close();
    }

    public int login(String usuario ,String password){
        int result = 0;
        String str[] = new  String[2];
        str[0] = usuario;
        str[1] = password;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from users where usuario=? and password=?",str);
        if (c.moveToFirst()){
            result  = 1;
        }
        return result;
    }
}
