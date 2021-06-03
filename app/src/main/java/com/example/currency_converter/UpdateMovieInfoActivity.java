package com.example.currency_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.currency_converter.helper.MovieDBHelper;

public class UpdateMovieInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_movie_info);

        EditText editText_MovieName = (EditText) findViewById(R.id.editTextMovieName);
        EditText editText_MovieDescription = (EditText) findViewById(R.id.editTextMovieDescription);
        final MovieDBHelper movieDBHelper = new MovieDBHelper(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String movieName = extras.getString("movieName");
            String movieDescription = extras.getString("movieDescription");
            //The key argument here must match that used in the other activity

            editText_MovieName.setText(movieName);
            editText_MovieDescription.setText(movieDescription);
        }

        Button button_UpdateMovie = (Button) findViewById(R.id.buttonUpdateMovie);

        button_UpdateMovie.setOnClickListener(v -> {
            if (extras != null) {
                String movieName = extras.getString("movieName");
                movieDBHelper.updateMovie(movieName, editText_MovieName.getText().toString(), editText_MovieDescription.getText().toString());
                Toast.makeText(getApplicationContext(), "Movie Updated Successfully", Toast.LENGTH_LONG).show();
            }
        });

        Button button_BackToShowMovies = (Button) findViewById(R.id.buttonBackToShowMovies);

        button_BackToShowMovies.setOnClickListener(v -> {
            super.onBackPressed();
        });

    }
}