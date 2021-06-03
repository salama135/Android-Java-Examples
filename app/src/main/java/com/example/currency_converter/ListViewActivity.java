package com.example.currency_converter;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        EditText todoItemText = (EditText) findViewById(R.id.Todo_Item_text);

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        ListView listView = (ListView) findViewById(R.id.ListView);
        listView.setAdapter(itemsAdapter);

        listView.setAdapter(itemsAdapter);

        Button buttonAddTodoItem = (Button) findViewById(R.id.buttonAddTodoItem);

        buttonAddTodoItem.setOnClickListener(v -> {

            if(todoItemText.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(), "Invalid Data - try again", Toast.LENGTH_SHORT).show();
            } else {
                itemsAdapter.add(todoItemText.getText().toString());
                todoItemText.getText().clear();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView)view).getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        Button button_GridView = (Button) findViewById(R.id.button_GridView);

        button_GridView.setOnClickListener(v -> {
            Intent intent_GridView = new Intent(ListViewActivity.this, GridViewActivity.class);
            startActivity(intent_GridView);
        });
    }
}