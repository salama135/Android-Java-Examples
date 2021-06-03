package com.example.currency_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;

public class GridViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        String stringArray[] = getResources().getStringArray(R.array.grid);
        final ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(stringArray));

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);

        GridView gridView = (GridView) findViewById(R.id.GridView);

        gridView.setAdapter(arrayAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String arrayItem = arrayList.get(position);

                if(Character.isLowerCase(arrayItem.charAt(0))){
                    arrayList.set(position, arrayItem.toUpperCase());
                } else {
                    arrayList.set(position, arrayItem.toLowerCase());
                }

                arrayAdapter.notifyDataSetChanged();
            }
        });

        Button button_Back = (Button) findViewById(R.id.button_Back);

        button_Back.setOnClickListener(v -> {
            super.onBackPressed();
        });
    }
}