package com.example.android_java_examples;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android_java_examples.helper.MovieDBHelper;

import java.util.ArrayList;

public class ShowMoviesActivity extends AppCompatActivity {

    ListView movieList;
    ArrayAdapter<String> moviesAdapter;
    MovieDBHelper movies;

    ArrayList<Integer> moviesIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movies);

        movieList = (ListView) findViewById(R.id.ListView_Movies);
        moviesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
        movieList.setAdapter(moviesAdapter);

        registerForContextMenu(movieList);

        moviesIDs = new ArrayList<Integer>();

        movies = new MovieDBHelper(getApplicationContext());
        Cursor cursor = movies.fetchAllMovies();

        while(!cursor.isAfterLast()){
            moviesAdapter.add(cursor.getString(cursor.getColumnIndex("name")));

            moviesIDs.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));

            cursor.moveToNext();
        }

        Button button_BackToMovies = (Button) findViewById(R.id.button_backToMovies);

        button_BackToMovies.setOnClickListener(v -> {
            super.onBackPressed();
        });

        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer movieID = moviesIDs.get(position);
                Cursor cursor = movies.fetchMovie(movieID);

                if (cursor != null){
                    cursor.moveToFirst();
                }

                String movieName = cursor.getString(cursor.getColumnIndex("name"));
                String movieDescription = cursor.getString(cursor.getColumnIndex("description"));

                Intent i = new Intent(ShowMoviesActivity.this, ShowMovieInfoActivity.class);
                i.putExtra("movieName", movieName);
                i.putExtra("movieDescription", movieDescription);
                startActivity(i);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_show_movies, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    // menu item select listener
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        String selectedMovie = ((TextView) info.targetView).getText().toString();

        int id = item.getItemId();
        if(id == R.id.itemDelete){
            moviesIDs.remove(moviesIDs.get(info.position));
            moviesAdapter.remove(selectedMovie);
            movies.deleteMovie(selectedMovie);
            return true;
        }else
        if(id == R.id.itemUpdate){

            Integer movieID = moviesIDs.get(info.position);
            Cursor cursor = movies.fetchMovie(movieID);

            if (cursor != null){
                cursor.moveToFirst();
            }

            String movieName = cursor.getString(cursor.getColumnIndex("name"));
            String movieDescription = cursor.getString(cursor.getColumnIndex("description"));

            Intent i = new Intent(ShowMoviesActivity.this, UpdateMovieInfoActivity.class);
            i.putExtra("movieName", movieName);
            i.putExtra("movieDescription", movieDescription);
            startActivity(i);

            return true;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        // put your code here...
        moviesAdapter.clear();

        moviesIDs.clear();

        Cursor cursor = movies.fetchAllMovies();

        while(!cursor.isAfterLast()){
            moviesAdapter.add(cursor.getString(cursor.getColumnIndex("name")));

            moviesIDs.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));

            cursor.moveToNext();
        }
    }
}