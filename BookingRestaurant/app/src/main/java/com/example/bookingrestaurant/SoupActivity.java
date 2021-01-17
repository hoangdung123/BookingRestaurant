package com.example.bookingrestaurant;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import FoodFirebaseHelper.SoupFirebase;
import Model.Food;
import RecyclerView.SoupConfig;

public class SoupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soup);
        RecyclerView listSoup = (RecyclerView) findViewById(R.id.recycler_soup);
        new SoupFirebase().getList(new SoupFirebase.DataStatus() {
            @Override
            public void DataLoaded(List<Food> foods, List<String> key) {
                new SoupConfig().setConfig(listSoup,SoupActivity.this,foods,key);
            }
        });
    }
}
