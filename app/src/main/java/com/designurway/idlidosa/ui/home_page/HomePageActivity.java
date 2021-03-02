package com.designurway.idlidosa.ui.home_page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.utils.SharedPrefManager;
import com.designurway.idlidosa.a.utils.UtilConstant;
import com.designurway.idlidosa.databinding.ActivityHomePageBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.designurway.idlidosa.a.utils.SharedPrefManager.PREF_TOTAL_KEY;

public class HomePageActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

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

                R.id.homeFragment, R.id.viewCartItemsFragment
                ,R.id.settingsFragment, R.id.profileFragment4
        ).build();


        //Setting toolbar
        navigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        //Bottom navigation
        NavigationUI.setupWithNavController(bottomView, navController);


        int count = SharedPrefManager.loadFrompref(HomePageActivity.this);

        if (count > 0)
            bottomView.getOrCreateBadge(R.id.viewCartItemsFragment).setNumber(SharedPrefManager.loadFrompref(HomePageActivity.this));
    }

    @Override
    public boolean onSupportNavigateUp() {
        // for navDrawer
        NavController navController = Navigation.findNavController(this, R.id.homeNavHostFragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
        //for bottom navigation only
//        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(PREF_TOTAL_KEY)) {
            bottomView.getOrCreateBadge(R.id.viewCartItemsFragment).setNumber(SharedPrefManager.loadFrompref(HomePageActivity.this));
            int count = SharedPrefManager.loadFrompref(HomePageActivity.this);

            if (count < 0){
                bottomView.removeBadge(R.id.viewCartItemsFragment);
            }

        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPrefManager.registerPrif(this, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPrefManager.unregisterPref(this, this);
    }


}