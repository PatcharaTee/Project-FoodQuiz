package com.example.foodquiz.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodquiz.model.Score;

@Dao
public interface ScoreDao {

    @Query("SELECT * FROM player_score")
    Score[] getAllScores();

    @Insert
    void insertScore(Score score);

    @Delete
    void deleteScore(Score score);
}
