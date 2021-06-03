package com.example.android_java_examples.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDBHelper extends SQLiteOpenHelper {

    private static String databaseName = "movieDatabase";
    SQLiteDatabase movieDatabase;

    public MovieDBHelper(Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table movie(id integer primary key, " + "name text not null, description text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists movie");
        onCreate(db);
    }

    public void createNewMovie(String name, String description) {
        ContentValues row = new ContentValues();
        row.put("name", name);
        row.put("description", description);
        movieDatabase = getWritableDatabase();
        movieDatabase.insert("movie", null, row);
        movieDatabase.close();
    }

    public Cursor fetchMovie(int id) {
        movieDatabase = getReadableDatabase();
        Cursor cursor =  movieDatabase.rawQuery( "select * from movie where id="+id+"", null );
        return cursor;
    }

    public Cursor fetchAllMovies() {
        movieDatabase = getReadableDatabase();
        String[] rowDetails = {"name", "description", "id"};
        Cursor cursor = movieDatabase.query("movie", rowDetails, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }

        movieDatabase.close();
        return cursor;
    }

    public void deleteMovie(String name){
        movieDatabase = getWritableDatabase();
        movieDatabase.delete("movie", "name='" + name + "'", null);
        movieDatabase.close();
    }

    public void updateMovie(String oldName, String newName, String newDescription){
        movieDatabase = getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put("name", newName);
        row.put("description", newDescription);
        movieDatabase.update("movie", row, "name like ?", new String[]{oldName});
        movieDatabase.close();
    }

}
