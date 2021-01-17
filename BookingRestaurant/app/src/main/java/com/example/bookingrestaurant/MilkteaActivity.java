package com.example.bookingrestaurant;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import FoodFirebaseHelper.MilkteaFirebase;
import Model.Food;
import RecyclerView.MilkteaConfig;

public class MilkteaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milktea);
        RecyclerView listMilktea = (RecyclerView) findViewById(R.id.recycler_milktea);
        new MilkteaFirebase().getList(new MilkteaFirebase.DataStatus(){
            @Override
            public void DataLoaded(List<Food> foods, List<String> key) {
                new MilkteaConfig().setConfig(listMilktea, MilkteaActivity.this, foods, key);
            }
        });
    }
}
