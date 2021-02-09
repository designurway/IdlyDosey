package com.designurway.idlidosa.ui.home_page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.databinding.ActivityHomePageBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePageActivity extends AppCompatActivity {

    ActivityHomePageBinding binding;
    BottomNavigationView bottomView;
    Toolbar homeToolBar;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bottomView = binding.bottomView;
        homeToolBar = binding.homeToolBar;
        setSupportActionBar(homeToolBar);

        navController = Navigation.findNavController(this,R.id.homeNavHostFragment);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
            R.id.homeFragment,R.id.viewCartItemsFragment,R.id.settingsFragment,R.id.profileFragment4
        ).build();

        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);

        //Bottom navigation
        NavigationUI.setupWithNavController(bottomView,navController);



    }
}