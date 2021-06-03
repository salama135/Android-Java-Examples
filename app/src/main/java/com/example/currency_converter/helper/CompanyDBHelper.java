package com.example.currency_converter.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import retrofit2.http.Query;

public class CompanyDBHelper extends SQLiteOpenHelper {

    private static String databaseName = "companyDatabase";
    SQLiteDatabase companyDatabase;

    public CompanyDBHelper(Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists department");
        db.execSQL("drop table if exists employee");
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table department(" +
                "DeptID integer primary key autoincrement, " +
                "name text" +
                ")");

        db.execSQL("create table employee(" +
                "EmpID integer primary key autoincrement, " +
                "name text not null, " +
                "title text not null, " +
                "phone text not null, " +
                "email text not null, " +
                "DeptID integer, " +
                "FOREIGN KEY(DeptID) REFERENCES department (DeptID)" +
                ")");
    }

    public void createData(){

        companyDatabase = getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put("name", "IT");
        getWritableDatabase().insert("department", null, row);
        row = new ContentValues();
        row.put("name", "HR");
        getWritableDatabase().insert("department", null, row);

        row = new ContentValues();
        row.put("name", "Ahmed Salama");
        row.put("title", "senior software engineer");
        row.put("phone", "01093498811");
        row.put("email", "salamaa135@gmail.com");
        row.put("DeptID", 1);
        companyDatabase.insert("employee", null, row);

        row = new ContentValues();
        row.put("name", "Ali Ahmed");
        row.put("title", "software engineer");
        row.put("phone", "01093498811");
        row.put("email", "salamaa135@gmail.com");
        row.put("DeptID", 1);
        companyDatabase.insert("employee", null, row);

        row = new ContentValues();
        row.put("name", "Sayed Ahmed");
        row.put("title", "software engineer");
        row.put("phone", "01093498811");
        row.put("email", "salamaa135@gmail.com");
        row.put("DeptID", 1);
        companyDatabase.insert("employee", null, row);

        companyDatabase.close();

        Log.println(Log.INFO, null, "NO Data");
    }

    public Cursor getEmployees(String name) {
        companyDatabase = getReadableDatabase();
        Cursor cursor = companyDatabase.rawQuery("select name from employee where name like '%" + name + "%'", null );
//        String[] args = {name};
//        Cursor cursor = empDatabase.rawQuery("Select Name from employee where name like ?", args);
        if (cursor != null){
            cursor.moveToFirst();
        }

        companyDatabase.close();
        return cursor;
    }

    public void addEmployee(String Name, String Phone, String Email){
        ContentValues row = new ContentValues();
        row.put("Name", Name);
        row.put("Phone", Phone);
        row.put("Email", Email);
        row.put("Dept_id", 1);
        getWritableDatabase().insert("employee", null, row);
    }

    public void addDepartment(String Name){
        ContentValues row = new ContentValues();
        row.put("name", Name);
        getWritableDatabase().insert("department", null, row);
    }
}
