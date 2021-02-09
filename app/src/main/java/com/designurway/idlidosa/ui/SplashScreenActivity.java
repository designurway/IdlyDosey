package com.designurway.idlidosa.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.activity.MobileVerificationActivity;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.ui.auth.AuthActivity;
import com.designurway.idlidosa.ui.home_page.HomePageActivity;

import butterknife.ButterKnife;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    Thread.sleep(1000);
                    launchNextActivity();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        th.start();
    }


    private void launchNextActivity() {
        PreferenceManager.initialize(this);
        Log.d("login", "per" + PreferenceManager.getIsUserLoggedIn());
        if (PreferenceManager.getIsUserLoggedIn()) {
            Intent intent = new Intent(this, HomePageActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, AuthActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
