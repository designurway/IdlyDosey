package com.designurway.idlidosa.ui.home_page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
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
    NavigationUI navigationUI;
    AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bottomView = binding.bottomView;
        homeToolBar = binding.homeToolBar;
        setSupportActionBar(homeToolBar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.homeNavHostFragment);
        navController = navHostFragment.getNavController();


//        navController = Navigation.findNavController(this,R.id.homeNavHostFragment);

         appBarConfiguration = new AppBarConfiguration.Builder(
            R.id.homeFragment,R.id.viewCartItemsFragment,R.id.settingsFragment,R.id.profileFragment4
        ).build();

        //Setting toolbar
        navigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        //Bottom navigation
        NavigationUI.setupWithNavController(bottomView,navController);



    }

    @Override
    public boolean onSupportNavigateUp() {
        // for navDrawer
        NavController navController = Navigation.findNavController(this, R.id.homeNavHostFragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
        //for bottom navigation only
//        return navController.navigateUp() || super.onSupportNavigateUp();
    }

}