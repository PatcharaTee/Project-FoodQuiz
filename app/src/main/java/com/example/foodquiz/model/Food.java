package com.example.foodquiz.model;

public class Food {
    public int foodId, foodImgId;
    public String foodName;

    public Food(int foodId, String foodName, int foodImgId) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodImgId = foodImgId;
    }
}
