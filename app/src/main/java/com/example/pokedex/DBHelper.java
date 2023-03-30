package com.example.pokedex;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // this table must be created first due to constraints
        String query = "CREATE TABLE `element_type` (`type` int NOT NULL,`type_name` varchar(8) NOT NULL,PRIMARY KEY (`type`))";
        sqLiteDatabase.execSQL(query);

        // now we can make the actual pokemon table
        String query2 = "CREATE TABLE `pokedex` (`number` int NOT NULL,`name` varchar(30) NOT NULL,`description` varchar(100) NOT NULL,`entry_data` varchar(200) NOT NULL,`type1` int NOT NULL,`type2` int DEFAULT NULL,`height` float NOT NULL,`weight` float NOT NULL,PRIMARY KEY (`number`),FOREIGN KEY (`type1`) REFERENCES `element_type` (`type`),FOREIGN KEY (`type2`) REFERENCES `element_type` (`type`))";
        sqLiteDatabase.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //dropping the pokedex table
        String query = "DROP TABLE IF EXISTS 'pokedex'";
        sqLiteDatabase.execSQL(query);
        //dropping the element_type table (remember the order of dropping due to constraints**)
        String query2 = "DROP TABLE IF EXISTS 'element_type'";
        sqLiteDatabase.execSQL(query2);
        //recreating both databases
        onCreate(sqLiteDatabase);
    }

    /**
    //entering data into the element_type table
    public long fillElementTypeTable() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
    }
     **/

    public Cursor displayData() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM pokedex", null);
        return cursor;
    }

    public long updatePokedexEntry(int pokemonNumber, String pokemonName, String pokemonDescription,
                                   float pokemonHeight, float pokemonWeight, String pokemonEntryData) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", pokemonName);
        contentValues.put("description", pokemonDescription);
        contentValues.put("entry_data", pokemonEntryData);
        contentValues.put("height", pokemonHeight);
        contentValues.put("weight", pokemonWeight);
        return sqLiteDatabase.update("pokedex", contentValues, "number=?", new String[]{Integer.toString(pokemonNumber)});
    }
}
