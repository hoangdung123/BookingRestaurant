package com.example.bookingrestaurant;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import FoodFirebaseHelper.FastFoodFirebase;
import Model.Food;
import RecyclerView.FastFoodConfig;

public class FastFoodActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fastfood);
        RecyclerView listFastFood = (RecyclerView) findViewById(R.id.recycler_fastfood);
        new FastFoodFirebase().getList(new FastFoodFirebase.DataStatus() {
            @Override
            public void DataLoaded(List<Food> foods, List<String> key) {
                new FastFoodConfig().setConfig(listFastFood, FastFoodActivity.this, foods, key);
            }
        });
    }
}
