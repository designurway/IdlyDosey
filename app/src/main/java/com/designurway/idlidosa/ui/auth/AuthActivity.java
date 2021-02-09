package com.designurway.idlidosa.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.databinding.ActivityAuthBinding;

public class AuthActivity extends AppCompatActivity {

    ActivityAuthBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_auth);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}