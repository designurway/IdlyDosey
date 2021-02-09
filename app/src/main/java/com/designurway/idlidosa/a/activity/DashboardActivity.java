//package com.designurway.idlidosa.a.activity;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.ScrollView;
//import android.widget.TextView;
//
//import com.designurway.idlidosa.R;
//import com.designurway.idlidosa.ui.home_page.fragments.HomeFragment;
//import com.designurway.idlidosa.ui.home_page.fragments.NotificationFragment;
//import com.designurway.idlidosa.ui.home_page.fragments.SettingsFragment;
//import com.designurway.idlidosa.ui.home_page.fragments.ViewCartItemsFragment;
//import com.designurway.idlidosa.a.utils.SharedPrefManager;
//import com.designurway.idlidosa.databinding.ActivityDashBoardBinding;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//import static com.designurway.idlidosa.a.utils.PreferenceManager.PREF_TOTAL_KEY;
//
//public class DashboardActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
//
//    @BindView(R.id.toolbar_title_tv)
//    TextView titleTv;
//
//    @BindView(R.id.add_symbol_imgv)
//    ImageView notificationIv;
//
//    private static final String TAG = "DashboardActivity";
//    BottomNavigationView bottom_navigation;
//
//    FragmentTransaction cartTransaction, settingsTransaction, profileTransaction;
//    FragmentManager cartManager, settingsManager, profileManager;
//    FrameLayout frameLayout;
//    BottomNavigationView navListener;
//    private Context context;
//    ScrollView scroll_view;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        ActivityDashBoardBinding binding;
//
//        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
////        setContentView(R.layout.activity_dashboard);
//
//        ButterKnife.bind(this);
//        titleTv.setText("Home");
//        navListener = findViewById(R.id.bottom_view);
//        frameLayout = findViewById(R.id.frame_lt);
//        scroll_view = findViewById(R.id.scroll_view_dashboard);
//        navListener.setOnNavigationItemSelectedListener(naListener);
//
//        if (getIntent().hasExtra("fromRegister")) {
//            Intent intent=new Intent(DashboardActivity.this,ProfileActivity.class);
//            startActivity(intent);
//
//        } else {
//
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            HomeFragment homeFragment = new HomeFragment();
//            fragmentManager.beginTransaction().replace(R.id.frame_lt, homeFragment).commit();
//
//        }
//
//
//        context = this;
//
//
//        notificationIv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                gotToNotification();
//            }
//        });
//
//        int count = SharedPrefManager.loadFrompref(context);
//
//        if (count > 0) {
//            navListener.getOrCreateBadge(R.id.cart).setNumber(SharedPrefManager.loadFrompref(context));
//
//        } else {
//            navListener.removeBadge(R.id.cart);
//        }
//
//
//    }
//
//
//    private BottomNavigationView.OnNavigationItemSelectedListener naListener =
//            new BottomNavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    switch (item.getItemId()) {
//                        case R.id.home:
//                            titleTv.setText("Home");
//                            goToCart(new HomeFragment());
//                            break;
//
//                        case R.id.cart:
//                            titleTv.setText("Cart");
//                            goToCart(new ViewCartItemsFragment());
//                            SharedPrefManager.Clear(DashboardActivity.this);
//                            navListener.removeBadge(R.id.cart);
//                            break;
//
//                        case R.id.profile:
//                            titleTv.setText("Profile");
//                            Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
//                            startActivity(intent);
//                            break;
//
//                        case R.id.settings:
//                            titleTv.setText("Setting");
//                            goToSettings();
//                            break;
//                    }
//                    return true;
//
//
//                }
//            };
//
//
//    private void goToCart(Fragment fragment) {
//        Fragment fragment1;
//        Log.d(TAG, "new_fragment");
//        FragmentManager fragmentManager = this.getSupportFragmentManager();
//        FragmentTransaction cartTransaction = fragmentManager.beginTransaction();
//        fragment1 = fragment;
//        cartTransaction.replace(R.id.frame_lt, fragment1);
//        cartTransaction.addToBackStack(null);
//        cartTransaction.commit();
//
//        frameLayout.setVisibility(View.VISIBLE);
//    }
//
//    private void goToSettings() {
//        settingsManager = this.getSupportFragmentManager();
//        settingsTransaction = settingsManager.beginTransaction();
//        SettingsFragment settingsFragment = new SettingsFragment();
//        settingsTransaction.replace(R.id.frame_lt, settingsFragment);
//        settingsTransaction.addToBackStack(null);
//        settingsTransaction.commit();
//
//        frameLayout.setVisibility(View.VISIBLE);
//    }
//
//    private void gotToNotification() {
//        titleTv.setText("Notification");
//        Fragment notificationFragment = new NotificationFragment();
//        FragmentManager fragmentManager = this.getSupportFragmentManager();
//        FragmentTransaction notificationTrancation = fragmentManager.beginTransaction();
//        notificationTrancation.replace(R.id.frame_lt, notificationFragment);
//        notificationTrancation.addToBackStack(null).commit();
//
//    }
//
//    @Override
//    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//        if (key.equals(PREF_TOTAL_KEY)) {
//            int count = SharedPrefManager.loadFrompref(context);
//
//            if (count > 0) {
//                navListener.getOrCreateBadge(R.id.cart).setNumber(SharedPrefManager.loadFrompref(context));
//
//            } else {
//                navListener.removeBadge(R.id.cart);
//            }
//        }
//
//    }
//
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        titleTv.setText("Home");
//        SharedPrefManager.registerPrif(this, this);
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        SharedPrefManager.unregisterPref(this, this);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
////        FragmentManager fragmentManager = getSupportFragmentManager();
////        HomeFragment homeFragment = new HomeFragment();
////        fragmentManager.beginTransaction().replace(R.id.frame_lt, homeFragment).addToBackStack(null).commit();
//    }
//
//    @Override
//    public void onBackPressed() {
//
//       super.onBackPressed();
//
//    }
//
//}