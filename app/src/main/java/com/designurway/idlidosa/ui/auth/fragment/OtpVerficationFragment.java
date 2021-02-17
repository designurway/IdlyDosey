package com.designurway.idlidosa.ui.auth.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.activity.ForgotPwdActivity;
import com.designurway.idlidosa.a.activity.OTPVerificationActivity;
import com.designurway.idlidosa.a.model.LoginModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.AndroidUtils;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.a.utils.UtilConstant;
import com.designurway.idlidosa.databinding.FragmentOtpVerficationBinding;
import com.designurway.idlidosa.ui.home_page.HomePageActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


public class OtpVerficationFragment extends Fragment {


    FragmentOtpVerficationBinding binding;
    EditText emailEt, passwordEt;
    Button signInBtn;
    CheckBox rememberMeCbx;
    String phone;
    OtpVerficationFragmentArgs args;

    public OtpVerficationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_otp_verfication, container, false);

        binding = FragmentOtpVerficationBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        args = OtpVerficationFragmentArgs.fromBundle(getArguments());
        phone = args.getPhone();

        emailEt = binding.emailEt;
        passwordEt = binding.passwordEt;
        signInBtn = binding.signInBtn;
        rememberMeCbx = binding.rememberMeCbx;

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHomeActivity();
            }
        });
        emailEt.setText(phone);
    }

    public void goToHomeActivity() {


        String custEmail = emailEt.getText().toString().trim();
        String custOtp = passwordEt.getText().toString().trim();
        String custPhone = emailEt.getText().toString().trim();
        String custPwd = passwordEt.getText().toString().trim();

        if (!AndroidUtils.isNetworkAvailable(getContext())) {
            Toast.makeText(getContext().getApplicationContext(),
                    this.getText(R.string.no_internet),
                    Toast.LENGTH_SHORT).show();
        }
        if (custEmail.isEmpty() && custOtp.isEmpty()) {
            Toast.makeText(getContext(),
                    this.getText(R.string.fill_credentials),
                    Toast.LENGTH_SHORT).show();

        } else {

            loginApp(custPhone, custOtp, custEmail, custPwd);
        }
    }

    public void loginApp(String phone, String otp, String email, String pwd) {



        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<LoginModel> call = retrofitApi.verifyPhoneAndOTP(phone, otp, email, pwd);
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
                        Toast.makeText(getContext(), getString(R.string.invalid_credentials), Toast.LENGTH_SHORT).show();
                    } else if (response.body().getMessage().contains("success")) {

                        if (rememberMeCbx.isChecked()) {
                            PreferenceManager.saveCustomerLogin(prefId, prefEmail, prefPassword, prefName,
                                    prefPhone, prefCode, true);
                            PreferenceManager.checkUserLoggedIn(true);

                        } else {

                            PreferenceManager.saveCustomerLogin(prefId, prefEmail, prefPassword, prefName, prefPhone, prefCode, false);
                            PreferenceManager.checkUserLoggedIn(false);

                        }

                        Intent intent = new Intent(getContext(), HomePageActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }

                }else {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Log.d(TAG, "failure" + t.getMessage());
                Toast.makeText(getContext(), "On Failure "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}