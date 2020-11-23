package com.example.foodquiz;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodquiz.adapter.FoodAdapter;
import com.example.foodquiz.model.Food;

public class FoodListActivity extends AppCompatActivity {
    private static final Food[] foods = {
            new Food(0, "Burger", R.drawable.burger),
            new Food(1, "Fries", R.drawable.fries),
            new Food(2, "Pasta", R.drawable.pasta),
            new Food(3, "Pizza", R.drawable.pizza),
            new Food(4, "Ramen", R.drawable.ramen),
            new Food(5, "Sushi", R.drawable.sushi),
            new Food(6, "Barbecue", R.drawable.barbecue),
            new Food(7, "Salad", R.drawable.salad),
            new Food(8, "Steak", R.drawable.steak),
            new Food(0, "Japanese Curry", R.drawable.japanese_curry),
            new Food(0, "Taco", R.drawable.taco),
            new Food(0, "Tom yum kung", R.drawable.tom_yum_kung),
            new Food(0, "Bibimbap", R.drawable.bibimbap),
            new Food(0, "Fish and chips", R.drawable.fish_and_chips),
            new Food(0, "Gyoza", R.drawable.gyoza),
            new Food(0, "Mushroom soup", R.drawable.mushroom_soup),
            new Food(0, "Burrito", R.drawable.burrito),
            new Food(0, "Hot dog", R.drawable.hot_dog),
            new Food(0, "Pad thai", R.drawable.pad_thai),
            new Food(0, "Papaya salad", R.drawable.papaya_salad)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        //RecyclerView
        RecyclerView foodRecyclerView = findViewById(R.id.food_list_activity_item_RV);
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(FoodListActivity.this));

        //Adapter
        FoodAdapter adapter = new FoodAdapter(foods);
        foodRecyclerView.setAdapter(adapter);
    }

    public static Food[] getFoodList() {
        return foods;
    }
}