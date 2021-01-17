package com.example.bookingrestaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import FoodFirebaseHelper.FoodFirebase;
import Model.Food;
import RecyclerView.FoodConfig;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseUser mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Button btnFastFood;
    //MenuItem nav_reg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        createMenu();

        RecyclerView listRecycler = (RecyclerView) findViewById(R.id.RecyclerView);
        new FoodFirebase().getList(new FoodFirebase.DataStatus() {
            @Override
            public void DataLoaded(List<Food> foods, List<String> key) {
                findViewById(R.id.progressBar).setVisibility(View.GONE);
                new FoodConfig().setConfig(listRecycler, MainActivity.this, foods, key);
            }
        });

        btnFastFood.setOnClickListener(fastFood);
        hideMenu();

    }

    private void AnhXa(){
        drawerLayout = (DrawerLayout)findViewById(R.id.drawlayout);
        navigationView =(NavigationView)findViewById(R.id.nav_view);
        toolbar =findViewById(R.id.toolbar);
        btnFastFood = findViewById(R.id.btn_fastfood);
    }

    private void createMenu(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
    }

    private View.OnClickListener fastFood = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), FastFoodActivity.class));
        }
    };

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen((GravityCompat.START))){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_home:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            /*case R.id.nav_reg:
                startActivity(new Intent(getApplicationContext(), Reg_users.class));
                break;*/
            case R.id.nav_cart:
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
                break;
            case R.id.nav_reg:
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private void hideMenu(){
        Menu menu = navigationView.getMenu();
        if(mCurrentUser == null){
            menu.findItem(R.id.nav_profile).setVisible(false);
        }
    }
}