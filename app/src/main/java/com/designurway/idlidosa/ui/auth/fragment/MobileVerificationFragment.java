package com.designurway.idlidosa.ui.auth.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import com.designurway.idlidosa.a.MainActivity;
import com.designurway.idlidosa.a.activity.MobileVerificationActivity;
import com.designurway.idlidosa.a.activity.OTPVerificationActivity;
import com.designurway.idlidosa.a.activity.ReferralCodeActivity;
import com.designurway.idlidosa.a.model.ErrorMessageModel;
import com.designurway.idlidosa.a.model.StatusOTPModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.AndroidUtils;
import com.designurway.idlidosa.a.utils.UtilConstant;
import com.designurway.idlidosa.databinding.FragmentMobileVerificationBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.itextpdf.text.pdf.PdfVisibilityExpression.AND;


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

        permission();
//        storagePermisiom();

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

                        getOtpForPhone(phone);

                        Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();

                    } else if (response.body().getMessage().contains("mobile number not found")) {

                        Toast.makeText(getContext(), "mobile number not found", Toast.LENGTH_SHORT).show();
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

    private void getOtpForPhone(String phone) {
        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<StatusOTPModel> call = retrofitApi.getOtpForPhone(phone);
        call.enqueue(new Callback<StatusOTPModel>() {
            @Override
            public void onResponse(Call<StatusOTPModel> call, Response<StatusOTPModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "otp sent success", Toast.LENGTH_SHORT).show();
                    action = MobileVerificationFragmentDirections.actionMobileVerificationFragment2ToOtpVerficationFragment(phone);
                    Navigation.findNavController(getView()).navigate(action);

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


    public void permission() {
        if (ContextCompat.checkSelfPermission(getActivity()
                , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getActivity()
                        , Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getActivity()
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getActivity()
                        , Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
        } else {
            // if the permission is not granted yet. we want to request this permission
            requestContactPermission();
        }
    }

    private void requestContactPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) &&
                ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA) &&
                ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_PHONE_STATE)
                && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            // This method will show user a dialog which explain why we need this permission
            //this happen when user already denied the permission before and tries to acess it again

//            new AlertDialog.Builder(getActivity())
//                    .setTitle("IdlyDosey needed")
//                    .setMessage("Grant Permission to Continue")
//                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.CAMERA
                            , Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.READ_PHONE_STATE}, 2);

//                        }
//                    })
//                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            //when this cancle butoon is clicke we need to dismiss the dialog
//                            dialog.dismiss();
//                        }
//                    }).create().show();
        } else {
            //Request permission
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                            , Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_PHONE_STATE}, 2);

        }
    }

    private void storagePermisiom() {

        if ( askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE, 2) |
             askForPermission(Manifest.permission.ACCESS_FINE_LOCATION, 2) |
             askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 2) |
             askForPermission(Manifest.permission.READ_PHONE_STATE, 2)
        ) {
            permission();
        } else  {

            displayPermission(Manifest.permission.READ_EXTERNAL_STORAGE, 2);
            displayPermission(Manifest.permission.ACCESS_FINE_LOCATION, 2);
//            displayPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 2);
//            displayPermission(Manifest.permission.READ_PHONE_STATE, 2);
        }
    }

    private boolean askForPermission(String permission, Integer requestCode) {

        if (ContextCompat.checkSelfPermission(getActivity(), permission)
                != PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            Toast.makeText(getActivity(), "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    void displayPermission(String permission, Integer requestCode) {
        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                getActivity(), permission)) {

            //This is called if user has denied the permission before
            //In this case I am just asking the permission again
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{permission}, requestCode);

        } else {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{permission}, requestCode);
        }
    }

}