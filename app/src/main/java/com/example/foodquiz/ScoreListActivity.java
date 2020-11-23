package com.example.foodquiz;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodquiz.adapter.ScoreAdapter;
import com.example.foodquiz.db.AppDatabase;
import com.example.foodquiz.model.Score;
import com.example.foodquiz.util.AppExecutors;

public class ScoreListActivity extends AppCompatActivity {
    private RecyclerView scoreRecyclerView;

    @Override
    protected void onResume() {
        super.onResume();
        AppExecutors executors = new AppExecutors();
        //Worker Thread
        executors.diskIO().execute(() -> {
            AppDatabase db = AppDatabase.getInstance(ScoreListActivity.this);
            Score[] scores = db.scoreDao().getAllScores();
            //Main Thread
            executors.mainThread().execute(() -> {
                ScoreAdapter adapter = new ScoreAdapter(scores);
                scoreRecyclerView.setAdapter(adapter);
            });
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_list);

        //RecyclerView
        scoreRecyclerView = findViewById(R.id.score_list_activity_item_RV);
        scoreRecyclerView.setLayoutManager(new LinearLayoutManager(ScoreListActivity.this));

        //Button
        Button refreshButton = findViewById(R.id.score_list_activity_refresh_button);
        refreshButton.setOnClickListener(v -> {
            finish();
            startActivity(getIntent());
        });
    }
}