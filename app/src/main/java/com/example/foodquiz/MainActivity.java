package com.example.foodquiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button play_button = findViewById(R.id.main_activity_play_button);
        play_button.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, QuizGameActivity.class);
            startActivity(i);
        });

        Button list_button = findViewById(R.id.main_activity_list_button);
        list_button.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, FoodListActivity.class);
            startActivity(i);
        });

        Button score_button = findViewById(R.id.main_activity_score_button);
        score_button.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, ScoreListActivity.class);
            startActivity(i);
        });
    }
}