package com.designurway.idlidosa.ui.home_page.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.model.AddressModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.databinding.FragmentAddressBookBinding;
import com.designurway.idlidosa.databinding.FragmentReciveComboAddressBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReciveComboAddressFragment extends Fragment {

    FragmentReciveComboAddressBinding binding;
    NavDirections action;
    ReciveComboAddressFragmentArgs args;

    String comboId,productId;
    private static final String TAG = "AddressFragment";
    ImageView checkBox;
    ImageView homeChk;
    TextView nameTv,addressTv,homePhoneTv,ofcNameTv;
    TextView ofcAddressTv,ofcPhoneTv;
    ImageView imgSadFace;
    CardView cardHomeAddress;
    CardView cardOfcAddress;
    String name,address,phone;
    ImageView imgEditHome;
    ImageView imgEditOffice;
    LinearLayout linearHome;
    LinearLayout LinearOffice;
    ImageView officeChk;
    TextView officeNameTv, officeAddressTv, ofc_phone_tv;
    String orderId="none";
    public ReciveComboAddressFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_recive_combo_address, container, false);
        binding = FragmentReciveComboAddressBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        args = ReciveComboAddressFragmentArgs.fromBundle(getArguments());
        comboId = args.getComboId();
        productId = args.getProductId();

        officeChk = binding.officeChk;
        officeNameTv = binding.officeNameTv;
        officeAddressTv = binding.officeAddressTv;
        ofc_phone_tv = binding.ofcPhoneTv;
        homeChk = binding.homeChk;
        nameTv = binding.nameTv;
        addressTv = binding.homeAddressTv;
        homePhoneTv = binding.homePhoneTv;
        ofcNameTv = binding.officeNameTv;
        ofcAddressTv = binding.officeAddressTv;
        ofcPhoneTv = binding.ofcPhoneTv;
        imgSadFace= binding.imgSadFace;
        cardHomeAddress = binding.cardHomeAddress;
        cardOfcAddress = binding.cardOfcAddress;
        imgEditHome = binding.imgEditHome;
        imgEditOffice = binding.imgEditOffice;
        linearHome = binding.linearHome;
        LinearOffice = binding.LinearOffice;

        getAddressDetails();

        linearHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addressTv.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "add missing address", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                           /* CustomerAddress customerAddress = new CustomerAddress(officeNameTv.getText().toString(),
                                    addressTv.getText().toString(),
                                    amount, ofc_phone_tv.getText().toString());
                            Parcelable parcelable = Parcels.wrap(customerAddress);

                            Intent intent = new Intent(getContext(), PaytmActivity.class);
                            intent.putExtra("DATA_KEY", parcelable);
                            startActivity(intent);*/
                    name = officeNameTv.getText().toString();
                    address = addressTv.getText().toString();
                    phone = ofc_phone_tv.getText().toString();
                    action = ReciveComboAddressFragmentDirections.actionReciveComboAddressFragmentToPaymentFragment(name,address,"0",phone,comboId,productId,orderId);
                    Navigation.findNavController(getView()).navigate(action);
                }


            }
        });

        LinearOffice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( officeAddressTv.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "add missing address", Toast.LENGTH_SHORT).show();
                    return;
                }else{

                          /* CustomerAddress customerAddress = new CustomerAddress(nameTv.getText().toString(),
                                   officeAddressTv.getText().toString(), amount, homePhoneTv.getText().toString());
                           Parcelable parcelable = Parcels.wrap(customerAddress);

                           Intent intent = new Intent(getContext(), PaytmActivity.class);
                           intent.putExtra("DATA_KEY", parcelable);
                           startActivity(intent);*/

                    name = nameTv.getText().toString();
                    address = officeAddressTv.getText().toString();
                    phone = homePhoneTv.getText().toString();
                    action = ReciveComboAddressFragmentDirections.actionReciveComboAddressFragmentToPaymentFragment(name,address,"0",phone,comboId,productId,orderId);
                    Navigation.findNavController(getView()).navigate(action);
                }


            }
        });

    }
    public void getAddressDetails() {
        com.designurway.idlidosa.a.retrofit.RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<com.designurway.idlidosa.a.model.AddressModel> call = retrofitApi.getAddress(PreferenceManager.getCustomerPhone());
        call.enqueue(new Callback<com.designurway.idlidosa.a.model.AddressModel>() {
            @Override
            public void onResponse(Call<com.designurway.idlidosa.a.model.AddressModel> call, Response<com.designurway.idlidosa.a.model.AddressModel> response) {
                if (response.isSuccessful()) {
                    nameTv.setText(response.body().getName());
                    addressTv.setText(response.body().getHomeAddress());
                    homePhoneTv.setText(response.body().getPhone());

                    ofcNameTv.setText(response.body().getName());
                    ofcAddressTv.setText(response.body().getOtherAddress());
                    ofcPhoneTv.setText(response.body().getPhone());

                } else {
                    imgSadFace.setVisibility(View.VISIBLE);
                    cardHomeAddress.setVisibility(View.GONE);
                    cardOfcAddress.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(Call<AddressModel> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());

            }
        });
    }

}