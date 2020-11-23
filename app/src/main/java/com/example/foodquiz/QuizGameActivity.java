package com.example.foodquiz;

import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodquiz.db.AppDatabase;
import com.example.foodquiz.model.Food;
import com.example.foodquiz.model.Score;
import com.example.foodquiz.util.AppExecutors;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuizGameActivity extends AppCompatActivity {
    private Button[] choiceButton;
    private TextView roundTV;
    private ImageView foodPicture;
    private Random rand;
    private final Food[] foods = new Food[FoodListActivity.getFoodList().length];
    private int ansButtonIndex, scoreCount = 0, roundCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_game);
        //Get food list from FoodListActivity
        System.arraycopy(FoodListActivity.getFoodList(), 0, foods, 0, FoodListActivity.getFoodList().length);
        //Create Random
        rand = new Random();

        //ImageView
        foodPicture = findViewById(R.id.quiz_game_activity_food_Image);

        //TextView - to show round count
        roundTV = findViewById(R.id.quiz_game_activity_count_TV);

        //Array Of Button
        choiceButton = new Button[]{
                findViewById(R.id.quiz_game_activity_choice1_Button),
                findViewById(R.id.quiz_game_activity_choice2_Button),
                findViewById(R.id.quiz_game_activity_choice3_Button),
                findViewById(R.id.quiz_game_activity_choice4_Button)
        };
        choiceButton[0].setOnClickListener(v -> checkAns(0));
        choiceButton[1].setOnClickListener(v -> checkAns(1));
        choiceButton[2].setOnClickListener(v -> checkAns(2));
        choiceButton[3].setOnClickListener(v -> checkAns(3));

        //Initiate Game
        startGame();
    }

    public void startGame() {
        List<Food> list = Arrays.asList(foods);
        Collections.shuffle(list);
        list.toArray(foods);
        ansButtonIndex = rand.nextInt(choiceButton.length);
        while (true) {
            int ansIndex = rand.nextInt(foods.length);
            if (foods[ansIndex] != null) {
                choiceButton[ansButtonIndex].setText(foods[ansIndex].foodName);
                foodPicture.setImageResource(foods[ansIndex].foodImgId);
                foods[ansIndex] = null;
                break;
            }
        }
        int fakeAnsIndex = 0;
        for (int i = 0; i < choiceButton.length; i++) {
            if (i != ansButtonIndex) {
                while (true) {
                    if (foods[fakeAnsIndex] != null) {
                        choiceButton[i].setText(foods[fakeAnsIndex].foodName);
                        fakeAnsIndex++;
                        break;
                    } else {
                        fakeAnsIndex++;
                    }
                }
            }
        }
    }

    public void isTheGameFinish() {
        if (roundCount == 5) {
            AlertDialog.Builder finishDialog = new AlertDialog.Builder(QuizGameActivity.this).setTitle("Finish!").setMessage("Your score is " + scoreCount + " out of 5, Good Job!");
            finishDialog.setPositiveButton("New game", (dialog, which) -> {
                roundCount = 1;
                scoreCount = 0;
                roundTV.setText(String.valueOf(roundCount));
                System.arraycopy(FoodListActivity.getFoodList(), 0, foods, 0, FoodListActivity.getFoodList().length);
                startGame();
            });
            finishDialog.setNeutralButton("Save result", (dialog, which) -> {
                EditText playerName = new EditText(QuizGameActivity.this);
                playerName.setInputType(InputType.TYPE_CLASS_TEXT);
                playerName.setGravity(Gravity.CENTER);
                AlertDialog.Builder userInputDialog = new AlertDialog.Builder(QuizGameActivity.this).setTitle("Player name").setView(playerName);
                userInputDialog.setPositiveButton("Save", (dialog1, which1) -> {
                    AppExecutors executors = new AppExecutors();
                    //Worker Thread
                    executors.diskIO().execute(() -> {
                        AppDatabase db = AppDatabase.getInstance(QuizGameActivity.this);
                        Score score = new Score(0, playerName.getText().toString(), scoreCount);
                        System.out.println("new score added");
                        db.scoreDao().insertScore(score);
                    });
                    dialog1.dismiss();
                    finish();
                });
                userInputDialog.setNegativeButton("Cancel", (dialog1, which1) -> {
                    dialog1.dismiss();
                    finish();
                });
                userInputDialog.show();
            });
            finishDialog.setNegativeButton("Back to menu", (dialog, which) -> {
                dialog.dismiss();
                finish();
            });
            finishDialog.show();
        } else {
            roundCount++;
            roundTV.setText(String.valueOf(roundCount));
            startGame();
        }
    }

    public void checkAns(int choiceNumber) {
        if (choiceNumber == ansButtonIndex) {
            scoreCount++;
            Toast.makeText(QuizGameActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(QuizGameActivity.this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }
        isTheGameFinish();
    }
}