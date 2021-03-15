package com.designurway.idlidosa.ui.home_page;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.SharedPreferences;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.adapters.ViewCartAdapter;
import com.designurway.idlidosa.a.model.ViewCartModel;
import com.designurway.idlidosa.a.model.ViewCartModelResponse;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.a.utils.SharedPrefManager;
import com.designurway.idlidosa.a.utils.UtilConstant;
import com.designurway.idlidosa.databinding.ActivityHomePageBinding;
import com.designurway.idlidosa.model.StatusAndMessageModel;
import com.designurway.idlidosa.retrofit.BaseClient;
import com.designurway.idlidosa.retrofit.RetrofitApi;
import com.designurway.idlidosa.ui.home_page.fragments.NotificationListFragment;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.designurway.idlidosa.a.utils.SharedPrefManager.PREF_TOTAL_KEY;

public class HomePageActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    ActivityHomePageBinding binding;
    BottomNavigationView bottomView;
    Toolbar tool_bar;
    NavController navController;
    NavigationUI navigationUI;
    AppBarConfiguration appBarConfiguration;
    ImageView ImgBell;
    TextView txtNotifyNo, toolbar_title_tv;
    TextView badgeCounter;
    int pendingNotification = 0;
    MenuItem menuitem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bottomView = binding.bottomView;
//        txtNotifyNo=binding.txtNotifyNo;
        tool_bar = binding.toolBar;
//        ImgBell=binding.notificationImgv;
//        toolbar_title_tv = binding.toolbarTitleTv;
        setSupportActionBar(tool_bar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.homeNavHostFragment);
        navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(
                tool_bar, navController);


        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            String title = bundle.getString("Title");

//            NavDirections directions = HomeFragmentDirections.actionGlobalNotificationListFragment();
//            navController.navigate(directions);
//            txtNotifyNo.setVisibility(View.GONE);


            FragmentManager fragmentManager = getSupportFragmentManager();
            NotificationListFragment notificationListFragment = new NotificationListFragment();
            fragmentManager.beginTransaction().replace(R.id.homeNavHostFragment, notificationListFragment).addToBackStack(null)
                    .commit();
        }

//        navController = Navigation.findNavController(this,R.id.homeNavHostFragment);

        appBarConfiguration = new AppBarConfiguration.Builder(

                R.id.homeFragment, R.id.viewCartItemsFragment
                , R.id.settingsFragment, R.id.profileFragment4
        ).build();
        getNotificationCount();
        //Setting toolbar
        navigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


        //Bottom navigation
        NavigationUI.setupWithNavController(bottomView, navController);


        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
//            toolbar_title_tv.setText(controller.getCurrentDestination().getLabel());

        });


        int count = SharedPrefManager.loadFrompref(HomePageActivity.this);

        if (count > 0)
            bottomView.getOrCreateBadge(R.id.viewCartItemsFragment).setNumber(SharedPrefManager.loadFrompref(HomePageActivity.this));

//        ImgBell.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                NavDirections directions = HomeFragmentDirections.actionGlobalNotificationListFragment();
////               navController.navigate(directions);
//                txtNotifyNo.setVisibility(View.GONE);
//                FragmentManager fragmentManager=getSupportFragmentManager();
//                NotificationListFragment notificationListFragment=new NotificationListFragment();
//                fragmentManager.beginTransaction().replace(R.id.homeNavHostFragment,notificationListFragment).addToBackStack(null)
//                        .commit();
//            }
//        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        // for navDrawer
        NavController navController = Navigation.findNavController(this, R.id.homeNavHostFragment);
//        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
        //for bottom navigation only
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(PREF_TOTAL_KEY)) {

            int count = SharedPrefManager.loadFrompref(HomePageActivity.this);

            if (count > 0) {
                bottomView.getOrCreateBadge(R.id.viewCartItemsFragment).setNumber(count);
            } else {
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

    public void getNotificationCount() {
        RetrofitApi api = BaseClient.getClient().create(RetrofitApi.class);
        Call<StatusAndMessageModel> call = api.getNotificationCount(PreferenceManager.getCustomerId());
        call.enqueue(new Callback<StatusAndMessageModel>() {
            @Override
            public void onResponse(Call<StatusAndMessageModel> call, Response<StatusAndMessageModel> response) {
                if (response.isSuccessful()) {
                    if (!response.body().getUnread().equals("0")) {
//                   txtNotifyNo.setText(response.body().getUnread());
                        pendingNotification= Integer.valueOf(response.body().getUnread());
                    } else {
//                   txtNotifyNo.setVisibility(View.GONE);
                        pendingNotification= Integer.valueOf(response.body().getUnread());
                    }

                } else {
                    Toast.makeText(HomePageActivity.this, "fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StatusAndMessageModel> call, Throwable t) {
                Toast.makeText(HomePageActivity.this, "Onfail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        menuitem = menu.findItem(R.id.notificationListFragment);

        if (pendingNotification == 0) {

            menuitem.setActionView(null);
        }else{
            menuitem.setActionView(R.layout.notification_badge);
            View view =menuitem.getActionView();
            badgeCounter=view.findViewById(R.id.badgeCounter);
            ImgBell=view.findViewById(R.id.imgBell);
            badgeCounter.setText(String.valueOf(pendingNotification));

            ImgBell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    NotificationListFragment notificationListFragment = new NotificationListFragment();
                    fragmentManager.beginTransaction().replace(R.id.homeNavHostFragment, notificationListFragment).addToBackStack(null)
                            .commit();
                }
            });
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }
}