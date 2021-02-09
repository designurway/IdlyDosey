package com.designurway.idlidosa.ui.auth.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.activity.MobileVerificationActivity;
import com.designurway.idlidosa.a.activity.OTPVerificationActivity;
import com.designurway.idlidosa.a.activity.ReferralCodeActivity;
import com.designurway.idlidosa.a.model.ErrorMessageModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.AndroidUtils;
import com.designurway.idlidosa.a.utils.UtilConstant;
import com.designurway.idlidosa.databinding.FragmentMobileVerificationBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MobileVerificationFragment extends Fragment {

    FragmentMobileVerificationBinding binding;
    NavDirections action;
    private static final String TAG = "MobVerificationActivity";
    EditText phoneEt;
    Button getOtpBtn;
    TextView sign_in_tv;

    public MobileVerificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_mobile_verification, container, false);
        binding = FragmentMobileVerificationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        phoneEt = binding.phoneEt;
        getOtpBtn = binding.getOtpBtn;
        sign_in_tv = binding.signInTv;



        getOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneEt.getText().toString().trim();
                if (!AndroidUtils.isNetworkAvailable(getContext())) {
                    Toast.makeText(getContext().getApplicationContext(), getContext().getText(R.string.no_internet), Toast.LENGTH_SHORT).show();
                }
                if (phone.isEmpty()) {
                    Toast.makeText(getContext(), getContext().getText(R.string.fill_credentials), Toast.LENGTH_SHORT).show();

                }
                if (!AndroidUtils.validMobileNumber(phone)) {
                    Toast.makeText(getContext(), getContext().getText(R.string.valid_phone), Toast.LENGTH_SHORT).show();
                } else {

                    verifyPhoneApi(phone);
                }
            }
        });
    }

    private void verifyPhoneApi(String phone) {

        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);

        Call<ErrorMessageModel> call = retrofitApi.verifyCustomerPhone(phone);


        call.enqueue(new Callback<ErrorMessageModel>() {
            @Override
            public void onResponse(Call<ErrorMessageModel> call, Response<ErrorMessageModel> response) {
                if (response.isSuccessful()) {

                    if (response.body().getMessage().contains("success")) {
                        action = MobileVerificationFragmentDirections.actionMobileVerificationFragment2ToOtpVerficationFragment(phone);
                        Navigation.findNavController(getView()).navigate(action);
                    } else if (response.body().getMessage().contains("mobile number not found")) {

                        action = MobileVerificationFragmentDirections.actionMobileVerificationFragment2ToReferalCodeFragment(phone);
                        Navigation.findNavController(getView()).navigate(action);

                    }

                } else {
                    Toast.makeText(getContext(), getString(R.string.failed_to_verify),
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