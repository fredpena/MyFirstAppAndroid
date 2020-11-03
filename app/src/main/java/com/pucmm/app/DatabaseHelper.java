package com.pucmm.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "TEST.DB";
    private static final int DB_VERSION = 1;

    private static final String TABLE_COUNTRY = "CREATE TABLE COUNTRY " +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "code TEXT NOT NULL, " +
            "name TEXT NOT NULL) ";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_COUNTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS COUNTRY");
        onCreate(db);
    }
}
