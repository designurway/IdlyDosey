package com.designurway.idlidosa.ui.auth.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.activity.ProfileActivity;
import com.designurway.idlidosa.a.model.ErrorMessageModel;
import com.designurway.idlidosa.a.model.ProfileDataModel;
import com.designurway.idlidosa.a.model.ProfileModel;
import com.designurway.idlidosa.a.model.StatusAndMessageModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.AndroidUtils;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.databinding.FragmentProfileAuthBinding;
import com.designurway.idlidosa.databinding.FragmentProfileBinding;
import com.designurway.idlidosa.ui.home_page.HomePageActivity;
import com.google.android.libraries.places.api.Places;
import com.google.android.material.textfield.TextInputEditText;
import com.itextpdf.text.pdf.codec.Base64;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class AuthProfileFragment extends Fragment {

    FragmentProfileAuthBinding binding;
    NavDirections action;
    private static final String TAG = "ProfileFragment";
    EditText userNameTv,emailEt,phoneEt,addressEt;
    CircleImageView iv_camera,profileImageCiv;
    Button saveBtn;
    TextView titleTv;
    File file;
    String path;
    Uri imageUri;
    Bitmap bitmap;
    String phone,address,pincode,refrralCode;
    AuthProfileFragmentArgs args;
    String refCode;
   Context context;

    public AuthProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_profile, container, false);
        binding = FragmentProfileAuthBinding.inflate(inflater,container,false);
        context=container.getContext();
        return  binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userNameTv = binding.profileNameEt;
        emailEt = binding.profileEmailEt;
        phoneEt = binding.profilePhoneEt;
        addressEt = binding.profileAddressEt;
        saveBtn = binding.saveProfileBtn;
        iv_camera = binding.otpImg;
        profileImageCiv = binding.profileImg;

        args = AuthProfileFragmentArgs.fromBundle(getArguments());
        address = args.getAddress();
        pincode = args.getPinCode();
        phone = args.getPhone();
        refrralCode = args.getReferralCode();
        addressEt.setText(address);
        phoneEt.setText(phone);



        setProfileDetails();


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfileDetails();

//                Log.d("data", phone + " "+pincode+" "+refrralCode+ " "+address);

//                Toast.makeText(getContext(), "Pincode : "+pincode, Toast.LENGTH_SHORT).show();
            }
        });

        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 102);
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
                    String email = model.getEmail();
                    emailEt.setText(email);
                    String phoneNum = model.getPhone();
                    phoneEt.setText(phoneNum);

                    String name = model.getName();
                    userNameTv.setText(name);
                    if (model.getProfileImage().isEmpty()) {
//                        Picasso.with(getContext()).load("http://192.168.4.168/API/idli_dosa/profile/user_profile.png").
//                        into(profileImageCiv);
                    } else {
                        Picasso.get().load(model.getProfileImage()).into(profileImageCiv);
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

    public void saveProfileDetails() {


        refCode = createRandomCode(7);

        String name = userNameTv.getText().toString().trim();
        String email = emailEt.getText().toString().trim();
        String phone = phoneEt.getText().toString().trim();
        String address = addressEt.getText().toString().trim();

        if (!AndroidUtils.isNetworkAvailable(context)) {

            Toast.makeText(getContext(), "Check with Your Connection",
                    Toast.LENGTH_SHORT).show();
        }
        phoneEt.setEnabled(false);
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//                Toast.makeText(getContext(), "Save Bttn", Toast.LENGTH_SHORT).show();
//        name = userNameTv.getText().toString().trim();
//        email = emailEt.getText().toString().trim();
//        address = addressEt.getText().toString().trim();
//        phoneNum = phoneEt.getText().toString().trim();

        if (name.isEmpty()){
            userNameTv.setFocusable(true);
            userNameTv.setError("reqruire");
            return;
        }
        if (!email.matches(emailPattern) ||email.isEmpty()){

            emailEt.setFocusable(true);
            emailEt.setError("Enter valid email");
            return;
        }
        if (address.isEmpty()){
            addressEt.setFocusable(true);
            addressEt.setError("reqruire");
            return;
        }
        if (phone.isEmpty()){
            phoneEt.setFocusable(true);
            phoneEt.setError("reqruire");
            return;
        }

        if (bitmap!=null){
            saveProfileImage(bitmap);
            updateProfile(name,email,phone,address);
        }else {
            updateProfile(name,email,phone,address);
        }


    }

    private void updateProfile(String name, String email, String phone, String address) {

        Log.d("TAG",name+" "+email+" "+phone+" "+pincode+" "+address);

        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<ProfileModel> call = retrofitApi.postProfile(name, email, phone, address,pincode,refCode);
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

                    insertRefCode(refrralCode,code);

                    PreferenceManager.saveCustomer(id, name, email, phone, pwd, code);

                    Intent intent = new Intent(getContext(), HomePageActivity.class);
                    startActivity(intent);
                    getActivity().finish();

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

                        Intent intent = new Intent(getContext(), HomePageActivity.class);
                        startActivity(intent);
                        getActivity().finish();

                    } else {

                    }
                }

                @Override
                public void onFailure(Call<ErrorMessageModel> call, Throwable t) {

                    Toast.makeText(getContext(), "On Failure "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 102 && resultCode == RESULT_OK) {
            String path = data.getData().toString();
            Uri uri = data.getData();
            file = new File(path);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                profileImageCiv.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
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

    public void insertRefCode(String refRal,String refredFrom) {
        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<StatusAndMessageModel> call = retrofitApi.updateReferralCode(
                refredFrom,refRal);


        call.enqueue(new Callback<StatusAndMessageModel>() {
            @Override
            public void onResponse(Call<StatusAndMessageModel> call,
                                   Response<StatusAndMessageModel> response) {
                if (response.isSuccessful()) {

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
    @Override
    public void onResume() {
        super.onResume();

        if (context==null){
            context=getActivity().getApplicationContext();
        }

    }


}