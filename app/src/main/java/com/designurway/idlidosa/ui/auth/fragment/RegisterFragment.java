package com.designurway.idlidosa.ui.auth.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.activity.RegisterActivity;
import com.designurway.idlidosa.a.model.RegisterDataModel;
import com.designurway.idlidosa.a.model.StatusAndMessageModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.AndroidUtils;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.databinding.FragmentRegisterBinding;

import java.security.SecureRandom;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterFragment extends Fragment {

    FragmentRegisterBinding binding;
    EditText emailEt,passwordEt,cPasswordEt,phoneEt;
    Button signUpBtn;
    private static final String TAG = "RegisterActivity";
    String refCode;
    String referredFrom;
    NavDirections action;
    RegisterFragmentArgs args;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_register, container, false);

        binding = FragmentRegisterBinding.inflate(inflater,container,false);
        View view =  binding.getRoot();

        args = RegisterFragmentArgs.fromBundle(getArguments());
        referredFrom = args.getPhone();
        refCode = args.getReferralCode();

        emailEt =  binding.emailEt;
        passwordEt = binding.passwordEt;
        cPasswordEt = binding.cPasswordEt;
        phoneEt = binding.phoneEt;
        signUpBtn =binding.signUpBtn;

        phoneEt.setText(referredFrom);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailEt.getText().toString().trim();
                String phone = phoneEt.getText().toString().trim();
                String password = passwordEt.getText().toString().trim();
                String confPwd = cPasswordEt.getText().toString().trim();

                refCode = createRandomCode(7);

//        Toast.makeText(this, refCode, Toast.LENGTH_SHORT).show();

                if (!AndroidUtils.isNetworkAvailable(getContext())) {

                    Toast.makeText(getContext(), getString(R.string.no_internet), Toast.LENGTH_SHORT).show();

                } else if (!AndroidUtils.validEmail(email)) {
                    Toast.makeText(getContext(), getString(R.string.valid_email), Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confPwd)) {
                    Toast.makeText(getContext(), getString(R.string.password_mismatch), Toast.LENGTH_SHORT).show();
                } else if (!AndroidUtils.validMobileNumber(phone)) {
                    Toast.makeText(getContext(), getString(R.string.valid_phone), Toast.LENGTH_SHORT).show();
                } else {
                    Register(email, phone, password, refCode);
//                    Toast.makeText(getContext(), "All Data", Toast.LENGTH_SHORT).show();
                }
            }
        });


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
                    RegisterDataModel dataModel = response.body();
                    if (dataModel.getStatus().equals("1")){

                        Toast.makeText(getContext(), "Sucess", Toast.LENGTH_SHORT).show();

                    insertRefCode();



                    String phoneNum = dataModel.getPhone();
                    String email = dataModel.getEmail();
                    String name = dataModel.getName();
                    String id = dataModel.getCustomer_id();
                    String pwd = dataModel.getPassword();
                    String referralCode = dataModel.getReferral_code();
                    String referFrom = referredFrom;

                    Toast.makeText(getContext(), "Sucesssssssssssssss", Toast.LENGTH_SHORT).show();

                    PreferenceManager.saveCustomerLogin(id,email,password,name,phoneNum,referralCode,true);
//                    Toast.makeText(RegisterActivity.this, PreferenceManager.getCustomerId(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "referredid" + PreferenceManager.getCustomerId());
                    Log.d(TAG, "referredname" + name);
                    PreferenceManager.saveCustomerReferred(referredFrom);

                    action = RegisterFragmentDirections.actionRegisterFragmentToProfileFragment();
                    Navigation.findNavController(getView()).navigate(action);


                    }else {
                        Toast.makeText(getContext(), "Something went Wrong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), getString(R.string.failed_register),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterDataModel> call, Throwable t) {
                Log.d(TAG, "onFailure while registring" + t.getLocalizedMessage());
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

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
//                    Toast.makeText(getContext(), "Success Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Failed",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StatusAndMessageModel> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}