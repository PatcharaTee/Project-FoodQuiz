package com.example.foodquiz.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodquiz.model.Score;

@Database(entities = {Score.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "food_quiz_scores.db";
    private static AppDatabase sInstance;

    public static synchronized AppDatabase getInstance(final Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME).build();
        }
        return sInstance;
    }

    public abstract ScoreDao scoreDao();
}
