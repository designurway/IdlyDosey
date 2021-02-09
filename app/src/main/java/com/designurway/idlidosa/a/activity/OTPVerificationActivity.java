package com.designurway.idlidosa.a.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.model.LoginModel;
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

public class OTPVerificationActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    @BindView(R.id.email_et)
    EditText emailEt;

    @BindView(R.id.password_et)
    EditText passwordEt;

    @BindView(R.id.sign_in_btn)
    Button signin;

    @BindView(R.id.remember_me_cbx)
    CheckBox rememberMeChk;

    @BindView(R.id.forgot_pwd_tv)
    TextView forgotPwdTv;

    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_otp_verification);
        ButterKnife.bind(this);

        if (getIntent().hasExtra(UtilConstant.FROM_MOB_VERIFICATION)){
             phone=getIntent().getExtras().getString(UtilConstant.FROM_MOB_VERIFICATION);
            emailEt.setText(phone);
        }



    }

    @OnClick(R.id.sign_in_btn)
    public void goToHomeActivity() {

        String custEmail = emailEt.getText().toString().trim();
        String custOtp = passwordEt.getText().toString().trim();
        String custPhone = emailEt.getText().toString().trim();
        String custPwd = passwordEt.getText().toString().trim();
        if (!AndroidUtils.isNetworkAvailable(this)) {
            Toast.makeText(getApplicationContext(),
                    this.getText(R.string.no_internet),
                    Toast.LENGTH_SHORT).show();
        }
        if (custEmail.isEmpty() && custOtp.isEmpty()) {
            Toast.makeText(this,
                    this.getText(R.string.fill_credentials),
                    Toast.LENGTH_SHORT).show();

        } else {

            loginApp(custPhone, custOtp,custEmail,custPwd);


        }
    }

    public void loginApp( String phone, String otp,String email,String pwd) {

        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<LoginModel> call = retrofitApi.verifyPhoneAndOTP(phone,otp,email,pwd);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                if (response.isSuccessful()) {
                    LoginModel model = response.body();
                    String prefEmail = model.getEmailId();
                    String prefPassword = model.getPassword();
                    String prefId = model.getCustomerId();
                    String prefName = model.getName();
                    String prefPhone = model.getPhone();
                    String prefCode = model.getReferralCode();
                    String prefFromCode = model.getReferredFrom();



                    if (response.body().getMessage().contains("enter correct otp and number")) {
                        Toast.makeText(OTPVerificationActivity.this, getString(R.string.invalid_credentials), Toast.LENGTH_SHORT).show();
                    }
                    else if (response.body().getMessage().contains("success")) {
                        if (rememberMeChk.isChecked()) {

                            PreferenceManager.saveCustomerLogin(prefId, prefEmail, prefPassword, prefName,
                                    prefPhone,prefCode, true);
                            PreferenceManager.checkUserLoggedIn(true);

                        } else {

                            PreferenceManager.saveCustomerLogin(prefId, prefEmail, prefPassword, prefName, prefPhone, prefCode,false);
                            PreferenceManager.checkUserLoggedIn(false);

                        }

                        if (getIntent().hasExtra("forgot_pwd")) {
                            Intent intent = new Intent(OTPVerificationActivity.this, ForgotPwdActivity.class);
                            intent.putExtra(UtilConstant.PHONE_FROM_ENTER_OTP,phone);
                            startActivity(intent);
                            finish();
                        } else {

                           /* Intent intent = new Intent(OTPVerificationActivity.this, DashboardActivity.class);
                            startActivity(intent);
                            finish();*/
                        }
                    }


                } else {
                    Toast.makeText(OTPVerificationActivity.this, getString(R.string.failed_login), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Log.d(TAG, "failure" + t.getMessage());

            }
        });


    }


    @OnClick(R.id.forgot_pwd_tv)
    public void goToMobileVerify(){
       Intent intent= new Intent(OTPVerificationActivity.this,MobileVerificationActivity.class);
       intent.putExtra(UtilConstant.FOR_FORGOT_PWD,"forgot");
        startActivity(intent);
        finish();
    }


}