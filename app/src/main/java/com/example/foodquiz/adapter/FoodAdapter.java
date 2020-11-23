package com.example.foodquiz.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodquiz.R;
import com.example.foodquiz.model.Food;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {
    private final Food[] mFoods;

    public FoodAdapter(Food[] mFoods) {
        this.mFoods = mFoods;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.foodPicture.setImageResource(mFoods[position].foodImgId);
        holder.foodName.setText(mFoods[position].foodName);
    }

    @Override
    public int getItemCount() {
        return mFoods.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView foodPicture;
        TextView foodName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            foodPicture = itemView.findViewById(R.id.food_picture);
            foodName = itemView.findViewById(R.id.food_name);
        }
    }
}
