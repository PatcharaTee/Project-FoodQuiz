package com.example.foodquiz.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "player_score")
public class Score {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "score_id")
    public final int scoreId;

    @ColumnInfo(name = "player_name")
    public final String playerName;

    @ColumnInfo(name = "score")
    public final int score;

    public Score(int scoreId, String playerName, int score) {
        this.scoreId = scoreId;
        this.playerName = playerName;
        this.score = score;
    }
}
