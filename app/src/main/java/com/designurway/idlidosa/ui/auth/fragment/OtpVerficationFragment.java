package com.designurway.idlidosa.ui.auth.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.designurway.idlidosa.R;

import com.designurway.idlidosa.a.model.LoginModel;
import com.designurway.idlidosa.a.model.StatusOTPModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.AndroidUtils;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.databinding.FragmentOtpVerficationBinding;
import com.designurway.idlidosa.ui.home_page.HomePageActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


public class OtpVerficationFragment extends Fragment {


    FragmentOtpVerficationBinding binding;
    Button verifyOtpBtn;
    String phone;
    EditText otpEt1, otpEt2, otpEt3, otpEt4;
    String otp, otp1, otp2, otp3, otp4,token;
    TextView otpNumTxt,rensend;
    OtpVerficationFragmentArgs args;

    public OtpVerficationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_otp_verfication, container, false);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (task.isSuccessful()){
                            token=task.getResult().getToken();

                            Log.d("token",token);
                        }else{
                            Log.d("TAG","failed to generate");

                        }
                    }
                });
        binding = FragmentOtpVerficationBinding.inflate(inflater, container, false);


        binding.otpEt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditText txt = (EditText) getActivity().getCurrentFocus();
                if (txt != null && txt.length() > 0) {
                    View next = txt.focusSearch(View.FOCUS_RIGHT); // or FOCUS_FORWARD
                    if (next != null)
                        next.requestFocus();
                } else {
                    View next = txt.focusSearch(View.FOCUS_LEFT);
                    if (next != null)
                        next.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                otp1 = s.toString();
            }
        });

        binding.otpEt2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditText txt = (EditText) getActivity().getCurrentFocus();
                if (txt != null && txt.length() > 0) {
                    View next = txt.focusSearch(View.FOCUS_RIGHT); // or FOCUS_FORWARD
                    if (next != null)
                        next.requestFocus();

                } else {
                    View next = txt.focusSearch(View.FOCUS_LEFT);
                    if (next != null)
                        next.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                otp2 = s.toString();
            }
        });
        binding.otpEt3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditText txt = (EditText) getActivity().getCurrentFocus();
                if (txt != null && txt.length() > 0) {
                    View next = txt.focusSearch(View.FOCUS_RIGHT); // or FOCUS_FORWARD
                    if (next != null)
                        next.requestFocus();

                } else {
                    View next = txt.focusSearch(View.FOCUS_LEFT);
                    if (next != null)
                        next.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                otp3 = s.toString();
            }
        });
        binding.otpEt4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditText txt = (EditText) getActivity().getCurrentFocus();
                if (txt != null && txt.length() > 0) {
                    View next = txt.focusSearch(View.FOCUS_RIGHT); // or FOCUS_FORWARD
                    if (next != null)
                        next.requestFocus();

                } else {
                    View next = txt.focusSearch(View.FOCUS_LEFT);
                    if (next != null)
                        next.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                otp4 = s.toString();
            }
        });

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        args = OtpVerficationFragmentArgs.fromBundle(getArguments());
        phone = args.getPhone();

        verifyOtpBtn = binding.verifyOtpBtn;
        otpEt1 = binding.otpEt1;
        otpEt2 = binding.otpEt2;
        otpEt3 = binding.otpEt3;
        otpNumTxt = binding.otpNumTxt;
        rensend = binding.rensend;

        otpNumTxt.setText("to +91" + phone);

        rensend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOtpForPhone(phone);
            }
        });
        verifyOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                otp = otp1+otp2+otp3+otp4;

                goToHomeActivity();
            }
        });
    }

    public void goToHomeActivity() {


        if (!AndroidUtils.isNetworkAvailable(getContext())) {
            Toast.makeText(getContext().getApplicationContext(),
                    this.getText(R.string.no_internet),
                    Toast.LENGTH_SHORT).show();
        }
        if (otp.isEmpty()) {
            Toast.makeText(getContext(),
                    this.getText(R.string.fill_credentials),
                    Toast.LENGTH_SHORT).show();

        } else {

            loginApp(phone, otp, "enter email", "password");
        }
    }

    public void loginApp(String phone, String otp, String email, String pwd) {


        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<LoginModel> call = retrofitApi.verifyPhoneAndOTP(phone, otp, email, pwd,token);
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


                        PreferenceManager.saveCustomerLogin(prefId, prefEmail, prefPassword, prefName,
                                prefPhone, prefCode, true);
                        PreferenceManager.checkUserLoggedIn(true);


                        Intent intent = new Intent(getContext(), HomePageActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }

                } else {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Log.d(TAG, "failure" + t.getMessage());
                Toast.makeText(getContext(), "On Failure " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void getOtpForPhone(String phone) {
        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<StatusOTPModel> call = retrofitApi.getOtpForPhone(phone);
        call.enqueue(new Callback<StatusOTPModel>() {
            @Override
            public void onResponse(Call<StatusOTPModel> call, Response<StatusOTPModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "otp Resent successfully", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StatusOTPModel> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());
            }
        });
    }

}