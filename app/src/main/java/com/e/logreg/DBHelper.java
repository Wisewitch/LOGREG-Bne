package com.e.logreg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "felhasznalo.db";
    public static final int DB_VERSION = 1;

    public static final String FELHASZNALO_TABLE = "felhasznalo";

    public static final String COL_ID       = "id";
    public static final String COL_EMAIL    = "enmail";
    public static final String COL_FELHNEV   = "felhnev";
    public static final String COL_PASS     = "jelszo";
    public static final String COL_TELJESNEV = "teljesnev";

    public DBHelper( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + FELHASZNALO_TABLE + "(" +
                COL_ID + "INTEGER PRIMARY KEY AUTOINCREMENT , " +
                COL_EMAIL + "VARCHAR(255) NOT NULL UNIQUE, " +
                COL_FELHNEV + "VARCHAR(255) NOT NULL UNIQUE, " +
                COL_PASS + "VARCHAR(255) NOT NULL " +
                COL_TELJESNEV + "VARCHAR(255) NOT NULL " + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "  + FELHASZNALO_TABLE;
        db.execSQL(sql);
        onCreate(db);
    }

    public boolean adatrogzites(String email, String felhnev, String jelszo, String teljesnev) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EMAIL, email);
        values.put(COL_FELHNEV, felhnev);
        values.put(COL_PASS, jelszo);
        values.put(COL_TELJESNEV, teljesnev);
        return db.insert(FELHASZNALO_TABLE, null, values) != -1;
    }

   /* public boolean login(String email, String felhnev, String jelszo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EMAIL, email);
        values.put(COL_FELHNEV, felhnev);
        values.put(COL_PASS, jelszo);

        return db.insert(FELHASZNALO_TABLE, null, values) != -1;
    }*/

    public Cursor loggedin() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(FELHASZNALO_TABLE, new String[]{COL_TELJESNEV}, null, null, null, null, null);
    }

  /*  public Cursor kiiras() {
        SQLiteDatabase db = this.getReadableDatabase();
        // return db.query(FELHASZNALO_TABLE, new String[]{COL_TELJESNEV}, null, null, null, null, null);

       return db.rawQuery(" SELECT * FROM felhasznalo WHERE teljesnev = ?", new String[]{} );

    }*/


    public boolean loginCheck(String felhnev, String jelszo) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor result = db.rawQuery("SELECT * FROM " + FELHASZNALO_TABLE +
                               " WHERE " + COL_FELHNEV + " = ? OR " + COL_EMAIL + " = ? AND " + COL_PASS + " = ?" , new String[]{felhnev, felhnev, jelszo});

        result.moveToFirst();
        return result.getCount() == 1;
    }

}