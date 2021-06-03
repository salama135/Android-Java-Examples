package com.example.android_java_examples;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android_java_examples.helper.CompanyDBHelper;

public class EmployeesActivity extends AppCompatActivity {

    ArrayAdapter<String> employeesNamesAdapter;
    CompanyDBHelper companyDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);

        EditText editText_EmployeeName = (EditText) findViewById(R.id.editText_EmployeeName);
        Button button_SearchEmployees = (Button) findViewById(R.id.button_SearchEmployees);
        ListView listView_Employees = (ListView) findViewById(R.id.listView_Employess);

        companyDBHelper = new CompanyDBHelper(this);
        companyDBHelper.createData();

        employeesNamesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);

        listView_Employees.setAdapter(employeesNamesAdapter);

        button_SearchEmployees.setOnClickListener(v -> {
            employeesNamesAdapter.clear();
            if(!editText_EmployeeName.getText().toString().isEmpty())
            {
                Cursor cursor = companyDBHelper.getEmployees(editText_EmployeeName.getText().toString());

                while(!cursor.isAfterLast()){
                    employeesNamesAdapter.add(cursor.getString(cursor.getColumnIndex("name")));
                    cursor.moveToNext();
                }
                editText_EmployeeName.getText().clear();
            } else {
                Toast.makeText(getApplicationContext(), "Invalid Data - try again", Toast.LENGTH_LONG).show();
            }
        });
    }
}