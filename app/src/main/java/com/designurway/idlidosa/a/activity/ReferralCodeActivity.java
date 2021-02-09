package com.designurway.idlidosa.a.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.utils.AndroidUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReferralCodeActivity extends AppCompatActivity {

    @BindView(R.id.submit_referral_code_btn)
    Button submitRefer;

    @BindView(R.id.referral_code_et)
    EditText referralCodeEt;

    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_referral_code);
        ButterKnife.bind(this);


if (getIntent().hasExtra("isFromVerify")){
     phone=getIntent().getStringExtra("isFromVerify");
}



    }

    @OnClick(R.id.submit_referral_code_btn)
    public void submitReferralCode(){
        String code=referralCodeEt.getText().toString().trim();
        if (code.isEmpty()){
            Toast.makeText(this, getString(R.string.fill_credentials), Toast.LENGTH_SHORT).show();
        }
        else if(!AndroidUtils.isNetworkAvailable(this)){
            Toast.makeText(this, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent=new Intent(ReferralCodeActivity.this,RegisterActivity.class);
            intent.putExtra("fromReferral",phone);
            intent.putExtra("code",code);
            startActivity(intent);
            finish();
        }
    }

    @OnClick(R.id.skip_tv)
    public void goToNext(){
        Intent intent=new Intent(ReferralCodeActivity.this,RegisterActivity.class);
        intent.putExtra("fromReferral",phone);
        startActivity(intent);
        finish();
    }
}