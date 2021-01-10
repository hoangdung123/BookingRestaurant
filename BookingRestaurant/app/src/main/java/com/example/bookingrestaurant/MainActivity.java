package com.example.bookingrestaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Button btn01, btn02, btn03, btn04;
    private ActionBar toolBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolBar = getSupportActionBar();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_bottom);
        navigation.setOnNavigationItemSelectedListener(mOnNavigation);

        btn01 = (Button)findViewById(R.id.btn_fastfood);
        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, fastfood.class);
                startActivity(intent);
            }
        });

        btn02 = (Button)findViewById(R.id.btn_milktea);
        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, milktea.class);
                startActivity(intent);
            }
        });

        btn03 = (Button)findViewById(R.id.btn_rice);
        btn03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, rice.class);
                startActivity(intent);
            }
        });

        btn04 = (Button)findViewById(R.id.btn_soup);
        btn04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, soup.class);
                startActivity(intent);
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigation = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId())
            {
                case R.id.navigation_shop:
                    toolBar.setTitle("Home");
                    return true;
                case R.id.navigaiton_calendar:
                    toolBar.setTitle("Calendar");
                    return true;
                case R.id.navigation_person:
                    toolBar.setTitle("User");
                    return true;
            }
            return false;
        }
    };
}