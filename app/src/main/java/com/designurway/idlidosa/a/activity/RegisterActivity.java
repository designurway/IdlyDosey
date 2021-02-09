package com.designurway.idlidosa.a.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.designurway.idlidosa.R;

import com.designurway.idlidosa.a.model.RegisterDataModel;
import com.designurway.idlidosa.a.model.StatusAndMessageModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.AndroidUtils;
import com.designurway.idlidosa.a.utils.PreferenceManager;

import java.security.SecureRandom;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    @BindView(R.id.email_et)
    EditText emailEt;

    @BindView(R.id.phone_et)
    EditText phoneEt;

    @BindView(R.id.password_et)
    EditText pwdEt;


    @BindView(R.id.c_password_et)
    EditText confirmPwdEt;
    String refCode;
    String referredFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        if (getIntent().hasExtra("fromReferral")) {
            String ph = getIntent().getStringExtra("fromReferral");
            phoneEt.setText(ph);
        }
        if (getIntent().hasExtra("code")) {
            referredFrom = getIntent().getStringExtra("code");
            Log.d(TAG, "referredFrom" + referredFrom);

        }

    }

    @OnClick(R.id.sign_up_btn)
    public void customerRegister() {

        String email = emailEt.getText().toString().trim();
        String phone = phoneEt.getText().toString().trim();
        String password = pwdEt.getText().toString().trim();
        String confPwd = confirmPwdEt.getText().toString().trim();

            refCode = createRandomCode(7);

    //        Toast.makeText(this, refCode, Toast.LENGTH_SHORT).show();

            if (!AndroidUtils.isNetworkAvailable(this)) {

                Toast.makeText(this, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();

            } else if (!AndroidUtils.validEmail(email)) {
                Toast.makeText(this, getString(R.string.valid_email), Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confPwd)) {
                Toast.makeText(this, getString(R.string.password_mismatch), Toast.LENGTH_SHORT).show();
            } else if (!AndroidUtils.validMobileNumber(phone)) {
                Toast.makeText(this, getString(R.string.valid_phone), Toast.LENGTH_SHORT).show();
            } else {
                Register(email, phone, password, refCode);

            }

    }

    public void Register(String email, String phone, String password, String refCode) {

        RetrofitApi retrofit = BaseClient.getClient().create(RetrofitApi.class);

        Call<RegisterDataModel> call = retrofit.customerRegister(email, phone, password, refCode);
        Log.d(TAG, "email" + email);
        Log.d(TAG, "phone" + phone);
        Log.d(TAG, "password" + password);
        Log.d(TAG, "refCode" + refCode);

        call.enqueue(new Callback<RegisterDataModel>() {
            @Override
            public void onResponse(Call<RegisterDataModel> call, Response<RegisterDataModel> response) {
                if (response.isSuccessful()) {
                    insertRefCode();
                    RegisterDataModel dataModel = response.body();
                   /* Intent intent = new Intent(RegisterActivity.this, DashboardActivity.class);
                    String phoneNum = dataModel.getPhone();
                    String email = dataModel.getEmail();
                    String name = dataModel.getName();
                    String id = dataModel.getCustomer_id();
                    String pwd = dataModel.getPassword();
                    String referralCode = dataModel.getReferral_code();
                    String referFrom = referredFrom;

//                    Toast.makeText(RegisterActivity.this, referredFrom, Toast.LENGTH_SHORT).show();
                    PreferenceManager.saveCustomerLogin(id,email,password,name,phoneNum,referralCode,true);
//                    Toast.makeText(RegisterActivity.this, PreferenceManager.getCustomerId(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "referredid" + PreferenceManager.getCustomerId());
                    Log.d(TAG, "referredname" + name);
                    PreferenceManager.saveCustomerReferred(referredFrom);
                    intent.putExtra("fromRegister", phone);
                    startActivity(intent);
                     finish();
*/
                } else {
                    Toast.makeText(RegisterActivity.this, getString(R.string.failed_register),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterDataModel> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());

            }
        });
    }


    public String createRandomCode(int codeLength) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < codeLength; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        System.out.println(output);
        return output;
    }

    public void insertRefCode() {
        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<StatusAndMessageModel> call = retrofitApi.updateReferralCode(referredFrom,
                refCode);
        call.enqueue(new Callback<StatusAndMessageModel>() {
            @Override
            public void onResponse(Call<StatusAndMessageModel> call,
                                   Response<StatusAndMessageModel> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Failed",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StatusAndMessageModel> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());

            }
        });
    }

}