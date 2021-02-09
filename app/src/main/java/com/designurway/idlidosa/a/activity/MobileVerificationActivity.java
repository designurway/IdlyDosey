package com.designurway.idlidosa.a.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.model.ErrorMessageModel;
import com.designurway.idlidosa.a.model.StatusOTPModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.AndroidUtils;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.a.utils.UtilConstant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobileVerificationActivity extends AppCompatActivity {

    private static final String TAG = "MobVerificationActivity";
    @BindView(R.id.phone_et)
    EditText phoneEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mobile_verification);
        ButterKnife.bind(this);

        if (PreferenceManager.getIsUserLoggedIn()) {
//
            finish();
            Toast.makeText(this, String.valueOf(PreferenceManager.getIsUserLoggedIn()), Toast.LENGTH_SHORT).show();
        }


    }

    @OnClick(R.id.get_otp_btn)
    public void getOtp() {

        String phone = phoneEt.getText().toString().trim();
        if (!AndroidUtils.isNetworkAvailable(this)) {
            Toast.makeText(getApplicationContext(), this.getText(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
        if (phone.isEmpty()) {
            Toast.makeText(this, this.getText(R.string.fill_credentials), Toast.LENGTH_SHORT).show();

        }
        if (!AndroidUtils.validMobileNumber(phone)) {
            Toast.makeText(this, this.getText(R.string.valid_phone), Toast.LENGTH_SHORT).show();
        }
        else {

            verifyPhoneApi(phone);
        }

    }

    private void verifyPhoneApi(String phone) {

//        Toast.makeText(this, "Phone : "+phone, Toast.LENGTH_SHORT).show();

        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);

        Call<ErrorMessageModel> call = retrofitApi.verifyCustomerPhone(phone);


        call.enqueue(new Callback<ErrorMessageModel>() {
            @Override
            public void onResponse(Call<ErrorMessageModel> call, Response<ErrorMessageModel> response) {
                if (response.isSuccessful()) {

                    if (response.body().getMessage().contains("success")) {

//                        getOtpForPhone(phone);
                        Intent intent = new Intent(MobileVerificationActivity.this, OTPVerificationActivity.class);
                        intent.putExtra(UtilConstant.FROM_MOB_VERIFICATION,phone);
                        if (getIntent().hasExtra(UtilConstant.FOR_FORGOT_PWD)){

                            intent.putExtra("forgot_pwd","forgot");
                        }

                        startActivity(intent);


                    } else if (response.body().getMessage().contains("mobile number not found")) {

                        Intent intent = new Intent(MobileVerificationActivity.this, ReferralCodeActivity.class);
                        intent.putExtra("isFromVerify",phone);
                        startActivity(intent);

                    }

                }
                else{
                    Toast.makeText(MobileVerificationActivity.this, getString(R.string.failed_to_verify),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ErrorMessageModel> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());

            }
        });
    }

    private void getOtpForPhone(String phone) {
       RetrofitApi retrofitApi=BaseClient.getClient().create(RetrofitApi.class);
       Call<StatusOTPModel> call=retrofitApi.getOtpForPhone(phone);
       call.enqueue(new Callback<StatusOTPModel>() {
           @Override
           public void onResponse(Call<StatusOTPModel> call, Response<StatusOTPModel> response) {
               if (response.isSuccessful()){
                   Toast.makeText(MobileVerificationActivity.this, "otp sent success", Toast.LENGTH_SHORT).show();
               }
               else{
                   Toast.makeText(MobileVerificationActivity.this, "failure", Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<StatusOTPModel> call, Throwable t) {
               Log.d(TAG, "onFailure" + t.getMessage());
           }
       });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (PreferenceManager.getIsUserLoggedIn()) {
            finish();
        }
    }
}