package com.example.bookingrestaurant;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import FoodFirebaseHelper.RiceFirebase;
import Model.Food;
import RecyclerView.RiceConfig;

public class RiceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rice);

        RecyclerView listRice = (RecyclerView) findViewById(R.id.recycler_rice);
        new RiceFirebase().getList(new RiceFirebase.DataStatus() {
            @Override
            public void DataLoaded(List<Food> foods, List<String> key) {
                new RiceConfig().setConfig(listRice, RiceActivity.this, foods,key);
            }
        });
    }
}
