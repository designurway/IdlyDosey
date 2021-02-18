package com.designurway.idlidosa.ui.home_page.fragments;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.model.ErrorMessageModel;
import com.designurway.idlidosa.a.model.ProfileDataModel;
import com.designurway.idlidosa.a.model.ProfileModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.AndroidUtils;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.a.utils.UtilConstant;
import com.designurway.idlidosa.databinding.FragmentProfileBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import com.itextpdf.text.pdf.codec.Base64;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    private static final String TAG = "ProfileFragment";
    TextInputEditText nameFieldEt;

    TextInputEditText emailFieldEt;
    TextInputEditText phoneNumFieldEt;
    Button saveBtn;
    TextInputEditText addressFieldEt;
    NavDirections action;
    CircleImageView personPicImgv, ivCamera;
    Context context;
    File file;
    String path;
    Uri imageUri;
    Bitmap bitmap;
    String email,phoneNum,address,name,pincode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        context = binding.addressFieldEt.getContext();
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameFieldEt = binding.nameFieldEt;
        emailFieldEt = binding.emailFieldEt;
        phoneNumFieldEt = binding.phoneNumFieldEt;
        addressFieldEt = binding.addressFieldEt;
        personPicImgv = binding.personPicImgv;
        ivCamera = binding.ivCamera;
        saveBtn = binding.saveBtn;

        phoneNumFieldEt.setText(PreferenceManager.getCustomerPhone());

        setProfileDetails();


        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 102);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getContext(), "Save Bttn", Toast.LENGTH_SHORT).show();

                if (bitmap!=null){
                    saveProfileImage(bitmap);
                }else {

                    name = nameFieldEt.getText().toString().trim();
                    email = emailFieldEt.getText().toString().trim();
                    address = addressFieldEt.getText().toString().trim();
                    phoneNum = phoneNumFieldEt.getText().toString().trim();

                    updateProfile(name,email,phoneNum,address);
                }


            }
        });

    }

    private void setProfileDetails() {
        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<ProfileDataModel> call = retrofitApi.getProfileDetails(PreferenceManager.getCustomerPhone());
        call.enqueue(new Callback<ProfileDataModel>() {
            @Override
            public void onResponse(Call<ProfileDataModel> call, Response<ProfileDataModel> response) {
                if (response.isSuccessful()) {
                    ProfileDataModel dataModel = response.body();
                    ProfileModel model = dataModel.getData();
                     email = model.getEmail();
                    emailFieldEt.setText(email);
                     phoneNum = model.getPhone();
                    phoneNumFieldEt.setText(phoneNum);
                     address = model.getHomeAddress();
                    addressFieldEt.setText(address);
                     name = model.getName();
                    nameFieldEt.setText(name);

                    pincode = model.getPin_code();
                    if (model.getProfileImage().isEmpty()) {
//                        Picasso.with(getContext()).load("http://192.168.4.168/API/idli_dosa/profile/user_profile.png").into(profileImageCiv);
                    } else {
                        Picasso.get().load(model.getProfileImage()).into(personPicImgv);
                    }
                } else {

                    Toast.makeText(getContext(), "no data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileDataModel> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());

            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 102 && resultCode == RESULT_OK) {

            path = data.getData().toString();
            imageUri = data.getData();

            file = new File(path);

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),imageUri);
                personPicImgv.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }


    private void saveProfileImage(Bitmap bitmap) {

        Bitmap image = bitmap;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
        byte[] imageinbyte = byteArrayOutputStream.toByteArray();
        String imageInString = Base64.encodeBytes(imageinbyte, android.util.Base64.DEFAULT);

        Log.d("imageInString", imageInString);

        if (!imageInString.isEmpty()) {
            RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
            Call<ErrorMessageModel> call = retrofitApi.postImage(PreferenceManager.getCustomerPhone(),
                    file.getName(), imageInString);
            call.enqueue(new Callback<ErrorMessageModel>() {
                @Override
                public void onResponse(Call<ErrorMessageModel> call, Response<ErrorMessageModel> response) {
                    if (response.isSuccessful()) {

                        action = ProfileFragmentDirections.actionProfileFragment4ToHomeFragment();
                        Navigation.findNavController(getView()).navigate(action);

                    } else {
                        Toast.makeText(context, "fail", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ErrorMessageModel> call, Throwable t) {

                    Toast.makeText(context, "On Failure "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void updateProfile(String name, String email, String phone, String address) {
        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<ProfileModel> call = retrofitApi.postProfile(name, email, phone, address,pincode);
        call.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                if (response.isSuccessful()) {
                    ProfileModel model = response.body();
                    String name = model.getName();
                    String email = model.getEmail();
                    String phone = model.getPhone();
                    String id = model.getId();
                    String code = model.getReferralCode();
                    String pwd = model.getPassword();
                    String refCode = model.getReferredFrom();
                    Log.d(TAG, "referred" + refCode);

                    PreferenceManager.saveCustomer(id, name, email, phone, pwd, code);

                    action = ProfileFragmentDirections.actionProfileFragment4ToHomeFragment();
                    Navigation.findNavController(getView()).navigate(action);

                } else {

                    Log.d(TAG, "no Data");
                }
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());

            }
        });
    }



}