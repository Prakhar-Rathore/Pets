package com.example.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.pets.data.PetsContract.PetEntry;

public class PetsDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PetShop.db";
    public String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + PetEntry.TABLE_NAME + " ("
            + PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PetEntry.NAME + " TEXT NOT NULL, "
            + PetEntry.BREED + " TEXT, "
            + PetEntry.GENDER + " INTEGER NOT NULL, "
            + PetEntry.WEIGHT + " INTEGER NOT NULL DEFAULT 0);";

    private static final String SQL_DELETE_PETS_TABLE =
            "DROP TABLE IF EXISTS " + PetEntry.TABLE_NAME;

    public PetsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PETS_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_PETS_TABLE);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
