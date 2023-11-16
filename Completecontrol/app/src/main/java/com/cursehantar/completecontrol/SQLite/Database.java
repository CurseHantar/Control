package com.cursehantar.completecontrol.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DB_control";
    private static final int DATABASE_VERSION = 1;
    private static String TABLE_NAME = "login";
    private static String id = "id";
    private static String usuario = "usuario";
    private static String correo = "correo";
    private static String contra = "contra";
    private static String numero = "numero";
    private static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    usuario + " TEXT, " +
                    correo + " TEXT, " +
                    contra + " TEXT, " +
                    numero + " TEXT);";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Maneja la actualización de la base de datos si hay cambios en la estructura
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void registro(String usuario,String correo,String contra, String numero){
        ContentValues cv = new ContentValues();
        cv.put("usuario",usuario);
        cv.put("correo",correo);
        cv.put("contra",contra);
        cv.put("numero",numero);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME,null,cv);
        db.close();
    }

    public int buscarUsuario(String usuario ,String password){
        int result = 0;
        String[] str = new  String[2];
        str[0] = usuario;
        str[1] = password;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from login where usuario=? and contra=?",str);
        if (c.moveToFirst()){
            result  = 1;
        }
        return result;
    }

    // Método para obtener todos los registros
    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    // Método para actualizar un registro
    public int updateData(int id, String usuario,String correo,String contra, String numero) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("usuario",usuario);
        cv.put("correo",correo);
        cv.put("contra",contra);
        cv.put("numero",numero);

        return db.update(TABLE_NAME, cv, id + "=?", new String[]{String.valueOf(id)});
    }

    public int deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, id + "=?", new String[]{String.valueOf(id)});
    }

    public long registrarUsuario(String usuario, String correo, String contra, String numero) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("usuario", String.valueOf(usuario));
        cv.put("correo", String.valueOf(correo));
        cv.put("contra", String.valueOf(contra));
        cv.put("numero", String.valueOf(numero));

        long newRowId = db.insert(TABLE_NAME, null, cv);
        db.close();
        return newRowId;
    }

    public boolean verificarCredenciales(String usuario, String contra) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {"id"};
        String selection = "usuario=? AND contra=?";
        String[] selectionArgs = {usuario, contra};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int numberOfRows = cursor.getCount();
        cursor.close();

        return numberOfRows > 0;
    }
}
