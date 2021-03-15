package com.designurway.idlidosa.ui.home_page.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.activity.EmergencyActivity;
import com.designurway.idlidosa.a.adapters.PrescriptionAdapter;
import com.designurway.idlidosa.a.model.PrescriptionModel;
import com.designurway.idlidosa.a.model.StatusAndMessageModel;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.AndroidUtils;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.databinding.FragmentEmergencyBinding;
import com.designurway.idlidosa.retrofit.BaseClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class EmergencyFragment extends Fragment {

    FragmentEmergencyBinding binding;

    ImageView uploadImgv;
    Button sendMOrder,addMoreItem;
    Uri imageuri;
    Bitmap bitmap;
    String filepath;
    Bitmap imageBitmap;
    File file;
    EditText addPrescriptionEt,prescriptionQtnEt;
    ArrayList<PrescriptionModel> list;
    ArrayList<String> medName = new ArrayList<>();
    ArrayList<String> qty = new ArrayList<>();
    PrescriptionAdapter prescriptionAdapter;
    RecyclerView addItmsRv;
    NavDirections action;



    public EmergencyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEmergencyBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        uploadImgv =  binding.uploadImgv;
        sendMOrder = binding.sendMOrder;
        addMoreItem = binding.addMoreItem;
        addPrescriptionEt = binding.addPrescriptionEt;
        prescriptionQtnEt = binding.prescriptionQtnEt;
        addItmsRv =  binding.addItmsRv;

        list = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        addItmsRv.setLayoutManager(layoutManager);

        uploadImgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        sendMOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bitmap!=null){
                    SendMedicineImage(bitmap);

                }else {
                    InsertToDatabase();
                }
            }
        });

        addMoreItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String desp = addPrescriptionEt.getText().toString().trim();
                String qoty = prescriptionQtnEt.getText().toString().trim();


                medName.add(desp);
                qty.add(qoty);

                list.add(new PrescriptionModel(desp, qoty));
                prescriptionAdapter = new PrescriptionAdapter(list, getActivity());
                addItmsRv.setAdapter(prescriptionAdapter);
                prescriptionAdapter.notifyDataSetChanged();

            }
        });
    }

    private void selectImage() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,111);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==111 && resultCode== RESULT_OK && data!=null && data.getData()!=null){
            filepath = data.getData().toString();
            imageuri = data.getData();

            file = new File(filepath);

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),imageuri);
                uploadImgv.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void SendMedicineImage(Bitmap bitmap) {


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageInbyte = byteArrayOutputStream.toByteArray();
        String ImageInString = Base64.encodeToString(imageInbyte, Base64.DEFAULT);

        RetrofitApi api = BaseClient.getClient().create(RetrofitApi.class);

        Call<StatusAndMessageModel> call = api.PostMedicineImage(PreferenceManager.getCustomerId(),
                AndroidUtils.randomName(10),
                AndroidUtils.randomName(10), ImageInString);

        call.enqueue(new Callback<StatusAndMessageModel>() {
            @Override
            public void onResponse(Call<StatusAndMessageModel> call,
                                   Response<StatusAndMessageModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals("1")){


                            action = EmergencyFragmentDirections.actionEmergencyFragmentToViewCartItemsFragment2();
                            Navigation.findNavController(getView()).navigate(action);


                    }else {
                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<StatusAndMessageModel> call, Throwable t) {
                Log.d("onFailure", "check" + t.getMessage());
                Toast.makeText(getContext(), "On Failure "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void InsertToDatabase() {

        String order_id = AndroidUtils.randomName(10);
        String cust_id = PreferenceManager.getCustomerId();
        final int random = new Random().nextInt((99 - 1) + 1) + 1;



        RetrofitApi api = BaseClient.getClient().create(RetrofitApi.class);
        Call<StatusAndMessageModel> call = api.postMedicineOrder(medName, qty, order_id, random, cust_id
        );

        call.enqueue(new Callback<StatusAndMessageModel>() {
            @Override
            public void onResponse(Call<StatusAndMessageModel> call, Response<StatusAndMessageModel> response) {
                if (response.isSuccessful()) {

                        action = EmergencyFragmentDirections.actionEmergencyFragmentToViewCartItemsFragment2();
                        Navigation.findNavController(getView()).navigate(action);



                } else {
                    Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StatusAndMessageModel> call, Throwable t) {
                Toast.makeText(getContext(), "Onfail" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}