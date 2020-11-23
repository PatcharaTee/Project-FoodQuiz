package com.example.foodquiz.adapter;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodquiz.R;
import com.example.foodquiz.db.AppDatabase;
import com.example.foodquiz.model.Score;
import com.example.foodquiz.util.AppExecutors;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.MyViewHolder> {
    private final Score[] mScores;

    public ScoreAdapter(Score[] mScores) {
        this.mScores = mScores;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.playerName.setText(mScores[position].playerName);
        holder.playerScore.setText(String.valueOf(mScores[position].score));
        holder.itemView.setOnClickListener(v -> {
            AlertDialog.Builder confirmDialog = new AlertDialog.Builder(holder.itemView.getContext());
            confirmDialog.setTitle("Do you want to delete this record?");
            confirmDialog.setPositiveButton("Yes", (dialog, which) -> {
                AppExecutors executors = new AppExecutors();
                //Worker Thread
                executors.diskIO().execute(() -> {
                    AppDatabase db = AppDatabase.getInstance(holder.itemView.getContext());
                    db.scoreDao().deleteScore(mScores[position]);
                });
            });
            confirmDialog.setNegativeButton("No", null);
            confirmDialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return mScores.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView playerName;
        TextView playerScore;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.player_name);
            playerScore = itemView.findViewById(R.id.player_score);
        }
    }
}
