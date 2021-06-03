package com.example.android_java_examples;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_java_examples.helper.MovieDBHelper;

public class MoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        Button button_addMovie = (Button) findViewById(R.id.button_AddMovie);
        Button button_showMovies = (Button) findViewById(R.id.button_ShowMovies);

        final EditText editText_MovieName = (EditText) findViewById(R.id.editText_MovieName);
        final EditText editText_MovieDescription = (EditText) findViewById(R.id.editText_MoiveDescription);
        final MovieDBHelper movieDBHelper = new MovieDBHelper(this);

        button_addMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editText_MovieName.getText().toString().isEmpty() && !editText_MovieDescription.getText().toString().isEmpty())
                {
                    movieDBHelper.createNewMovie(editText_MovieName.getText().toString(), editText_MovieDescription.getText().toString());
                    Toast.makeText(getApplicationContext(), "Movie Added Successfully", Toast.LENGTH_LONG).show();
                    editText_MovieName.setText("");
                    editText_MovieDescription.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Data - try again", Toast.LENGTH_LONG).show();
                }
            }
        });

        button_showMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_ShowMoviesView = new Intent(MoviesActivity.this, ShowMoviesActivity.class);
                startActivity(intent_ShowMoviesView);
            }
        });

    }
}