
//customer


package com.designurway.idlidosa.a.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.model.ErrorMessageModel;
import com.designurway.idlidosa.a.model.ProfileDataModel;
import com.designurway.idlidosa.a.model.ProfileModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.AndroidUtils;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.a.utils.UtilConstant;
import com.google.android.material.textfield.TextInputEditText;
import com.itextpdf.text.pdf.codec.Base64;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileFragment";
    @BindView(R.id.name_field_et)
    TextInputEditText userNameTv;

    @BindView(R.id.email_field_et)
    TextInputEditText emailEt;

    @BindView(R.id.phone_num_field_et)
    TextInputEditText phoneEt;

    @BindView(R.id.address_field_et)
    TextInputEditText addressEt;

    @BindView(R.id.person_pic_imgv)
    CircleImageView profileImageCiv;

    @BindView(R.id.toolbar_title_tv)
    TextView titleTv;
    @BindView(R.id.iv_camera)
    ImageView iv_camera;

    File file;
    String path;
    Uri imageUri;
    Bitmap bitmap;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        phoneEt.setText(PreferenceManager.getCustomerPhone());
        titleTv.setText("Profile");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            phone = bundle.getString("one");
//            Toast.makeText(ProfileActivity.this, phone, Toast.LENGTH_SHORT).show();

        }
        iv_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 102);
            }
        });

        setProfileDetails();
//        Toast.makeText(this, PreferenceManager.getCustomerId(), Toast.LENGTH_SHORT).show();

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
                    String address = model.getHomeAddress();
                    addressEt.setText(address);
                    String name = model.getName();
                    userNameTv.setText(name);
                    if (model.getProfileImage().isEmpty()) {
//                        Picasso.with(getContext()).load("http://192.168.4.168/API/idli_dosa/profile/user_profile.png").
//                        into(profileImageCiv);
                    } else {
                        Picasso.get().load(model.getProfileImage()).into(profileImageCiv);
                    }
                } else {

                    Toast.makeText(ProfileActivity.this, "no data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileDataModel> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());

            }
        });
    }

    @OnClick(R.id.save_btn)
    public void saveProfileDetails() {
        String name = userNameTv.getText().toString().trim();
        String email = emailEt.getText().toString().trim();
        String phone = phoneEt.getText().toString().trim();
        String address = addressEt.getText().toString().trim();

        if (!AndroidUtils.isNetworkAvailable(this)) {
            Toast.makeText(this, "Check with Your Connection",
                    Toast.LENGTH_SHORT).show();
        }
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Enter All Fields", Toast.LENGTH_SHORT).show();
            return;
        } else {
            updateProfile(name, email, phone, address);
            if (bitmap != null) {
                saveProfileImage(bitmap);
            } else {
                Log.d("TAG", "Empty");
            }
        }

    }

    private void updateProfile(String name, String email, String phone, String address) {
        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<ProfileModel> call = retrofitApi.postProfile(name, email, phone, address,"");
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
                    /*Intent intent = new Intent(ProfileActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();*/
//                    goToNext(new DashBoardFragment());
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

//                        Toast.makeText(ProfileActivity.this, "success", Toast.LENGTH_SHORT).show();
                        /*Intent intent = new Intent(ProfileActivity.this, DashboardActivity.class);
                        startActivity(intent);
//                        goToNext(new DashBoardFragment());
                        finish();*/

                    } else {
                        Toast.makeText(ProfileActivity.this, "fail", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ErrorMessageModel> call, Throwable t) {

                    Toast.makeText(ProfileActivity.this, "On Failure "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public boolean isReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(ProfileActivity.this, new String[]
                                {Manifest.permission.READ_EXTERNAL_STORAGE},
                        UtilConstant.READ_PERMISSION);
                return false;
            }
        } else {
            return true;
        }
    }

    public boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {

                return true;
            } else {
                ActivityCompat.requestPermissions(ProfileActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        } else {

            return true;
        }
    }

    private void checkCamera() {
        if (checkSelfPermission(Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    8);
        }
    }


    private void openGallery() {

        Log.d("OpeningGallery", "open2nd");
        final CharSequence[] options = {
                getString(R.string.choose_from_gallery)
        };


        AlertDialog.Builder builder = new AlertDialog.Builder
                (this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (options[i].toString().equals(getString(R.string.choose_from_gallery))) {

                    Log.d("Takephoto", "open2nd");
                    Intent gallery = new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                    startActivityForResult(gallery, UtilConstant.PICK_IMAGE);
                } else {
                    Log.d("Takephoto", "elseBlock");
                }
            }
        });
        builder.show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 102 && resultCode == RESULT_OK) {
            String path = data.getData().toString();
            Uri uri = data.getData();
            file = new File(path);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(ProfileActivity.this.getContentResolver(), uri);
                profileImageCiv.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void goToNext(Fragment fragment) {
        Fragment fragmentproduct;
        FragmentManager productManager = ProfileActivity.this.getSupportFragmentManager();
        FragmentTransaction productTransaction = productManager.beginTransaction();
        fragmentproduct = fragment;
        productTransaction.replace(R.id.frame_lt, fragmentproduct);
        productTransaction.addToBackStack(null);
        productTransaction.commit();

    }
}