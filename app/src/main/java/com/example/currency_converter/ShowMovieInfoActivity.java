package com.example.currency_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ShowMovieInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movie_info);

        TextView textView_MovieName = (TextView) findViewById(R.id.textView_MovieName);
        TextView textView_MovieDescription = (TextView) findViewById(R.id.textView_MovieDescription);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String movieName = extras.getString("movieName");
            String movieDescription = extras.getString("movieDescription");
            //The key argument here must match that used in the other activity

            textView_MovieName.setText(movieName);
            textView_MovieDescription.setText(movieDescription);
        }

        Button button_BackToShowMovies = (Button) findViewById(R.id.button_BackToShowMovies);

        button_BackToShowMovies.setOnClickListener(v -> {
            super.onBackPressed();
        });

    }
}