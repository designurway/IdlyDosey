package com.designurway.idlidosa.a.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.model.ErrorMessageModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.AndroidUtils;
import com.designurway.idlidosa.a.utils.UtilConstant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPwdActivity extends AppCompatActivity {

    private static final String TAG ="ForgotPwdActivity" ;
    @BindView(R.id.new_pwd_et)
    EditText newPwdEt;

    @BindView(R.id.conf_password_et)
    EditText confPwdEt;

    String phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pwd);
        ButterKnife.bind(this);
        if (getIntent().hasExtra(UtilConstant.PHONE_FROM_ENTER_OTP)){
          phoneNum=getIntent().getStringExtra(UtilConstant.PHONE_FROM_ENTER_OTP);
        }
    }

    @OnClick(R.id.change_pwd_btn)
    public  void changePwd(){
        String newPwd = newPwdEt.getText().toString().trim();
        String confPwd = confPwdEt.getText().toString().trim();

        if (!AndroidUtils.isNetworkAvailable(this)) {
            Toast.makeText(getApplicationContext(),
                    this.getText(R.string.no_internet),
                    Toast.LENGTH_SHORT).show();
        }
        if (newPwd.isEmpty() && confPwd.isEmpty()) {
            Toast.makeText(this,
                    this.getText(R.string.fill_credentials),
                    Toast.LENGTH_SHORT).show();

        }
        if (!newPwd.equals(confPwd)){
            Toast.makeText(this,
                    this.getText(R.string.password_mismatch),
                    Toast.LENGTH_SHORT).show();
        }
        else{
            changePwdApi(newPwd);
        }
    }

    private void changePwdApi(String newPwd) {
        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<ErrorMessageModel> call = retrofitApi.setCustomerForgotPwd(phoneNum,newPwd);
        Toast.makeText(this, phoneNum, Toast.LENGTH_SHORT).show();

        call.enqueue(new Callback<ErrorMessageModel>() {
            @Override
            public void onResponse(Call<ErrorMessageModel> call, Response<ErrorMessageModel> response) {
                if (response.isSuccessful()) {

                    if (response.body().getMessage().contains("password updated")) {

                        Intent intent = new Intent(ForgotPwdActivity.this, MobileVerificationActivity.class);
                        startActivity(intent);

                    } else  {

                        Toast.makeText(ForgotPwdActivity.this, getString(R.string.failed_update),
                                Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(ForgotPwdActivity.this, getString(R.string.failed_to_verify),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ErrorMessageModel> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());

            }
        });
    }
}