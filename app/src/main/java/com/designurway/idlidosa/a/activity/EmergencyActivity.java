package com.designurway.idlidosa.a.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.model.StatusAndMessageModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.AndroidUtils;
import com.designurway.idlidosa.a.utils.PreferenceManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static easypay.manager.PaytmAssist.getContext;

public class EmergencyActivity extends AppCompatActivity {

    Button abc;
    final int IMAGE_PICK_REQUEST_CODE = 101;
    ImageView upload_imgv;
    Uri imageuri;
    Bitmap bitmap;
    String filepath;
    Bitmap imageBitmap;
    Button send_m_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        upload_imgv = findViewById(R.id.upload_imgv);
        send_m_order = findViewById(R.id.send_m_order);

        upload_imgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();

            }
        });

        send_m_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmap != null) {
                    SendMedicineImage(bitmap);
                    Log.d("IMagerun","Image");
                } else {
                    InsertToDatabase("10", "tab");
                    Log.d("IMagerun","text");

                }

            }
        });
    }

    private void selectImage() {
        Intent intent_pick_image = new Intent(Intent.ACTION_PICK);
        intent_pick_image.setType("image/*");
        startActivityForResult(intent_pick_image, IMAGE_PICK_REQUEST_CODE);
    }


    public void SendMedicineImage(Bitmap bitmap) {

//        File file=new File(filepath);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageInbyte = byteArrayOutputStream.toByteArray();
        String ImageInString = Base64.encodeToString(imageInbyte, Base64.DEFAULT);

        RetrofitApi api = BaseClient.getClient().create(RetrofitApi.class);

        Call<StatusAndMessageModel> call = api.PostMedicineImage(PreferenceManager.getCustomerId(), AndroidUtils.randomName(5),
                AndroidUtils.randomName(5), ImageInString);

        call.enqueue(new Callback<StatusAndMessageModel>() {
            @Override
            public void onResponse(Call<StatusAndMessageModel> call, Response<StatusAndMessageModel> response) {
                if (response.isSuccessful()) {
                   /* Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(EmergencyActivity.this,DashboardActivity.class);
                    startActivity(intent);
                    finish();*/
                } else {
                    Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<StatusAndMessageModel> call, Throwable t) {
                Log.d("onFailure", "check" + t.getMessage());
                Toast.makeText(getContext(), "Onfailed", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_PICK_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filepath = data.getData().toString();
            imageuri = Uri.parse(filepath);

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageuri);
                upload_imgv.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void InsertToDatabase(String quantity, String medicine_name) {

        RetrofitApi api = BaseClient.getClient().create(RetrofitApi.class);
        Call<StatusAndMessageModel> call = api.PostMedicineOrder(AndroidUtils.randomName(5), PreferenceManager.getCustomerId(),
                quantity, medicine_name);

        call.enqueue(new Callback<StatusAndMessageModel>() {
            @Override
            public void onResponse(Call<StatusAndMessageModel> call, Response<StatusAndMessageModel> response) {
                if (response.isSuccessful()) {
                   /* Intent intent=new Intent(EmergencyActivity.this,DashboardActivity.class);
                    startActivity(intent);
                    finish();*/

                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StatusAndMessageModel> call, Throwable t) {
                Toast.makeText(getContext(), "Onfail", Toast.LENGTH_SHORT).show();

            }
        });
    }

}