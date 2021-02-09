package com.designurway.idlidosa.ui.home_page.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.os.Parcelable;
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
import com.designurway.idlidosa.a.model.CustomerAddress;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.databinding.FragmentAddressBookBinding;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressBookFragment extends Fragment {

    FragmentAddressBookBinding binding;
    AddressBookFragmentArgs args;
    NavDirections action;

    String amount,FromSetting,orderId;
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        binding= FragmentAddressBookBinding.inflate(inflater,container,false);

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


        args = AddressBookFragmentArgs.fromBundle(getArguments());
        amount = args.getAmount();
        FromSetting = args.getFromSetting();
        orderId= args.getOrderId();

        checkAddressEmpty();
        Bundle bundle = getArguments();

        if (FromSetting.equals("viewCart")) {

            if (FromSetting.equals("viewCart")) {

                linearHome.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (addressTv.getText().toString().isEmpty()){
                            Toast.makeText(getContext(), "add missing address", Toast.LENGTH_SHORT).show();
                            return;
                        }else{

                            name = officeNameTv.getText().toString();
                            address = addressTv.getText().toString();
                            phone = ofc_phone_tv.getText().toString();
                            action = AddressBookFragmentDirections.actionAddressBookFragmentToPaymentFragment(name,address,amount,phone,"none","none",orderId);
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

                           name = nameTv.getText().toString();
                           address = officeAddressTv.getText().toString();
                           phone = homePhoneTv.getText().toString();
                           action = AddressBookFragmentDirections.actionAddressBookFragmentToPaymentFragment(name,address,amount,phone,"none","none",orderId);
                           Navigation.findNavController(getView()).navigate(action);
                       }


                    }
                });
            } else {
                Toast.makeText(getContext(), "From cart", Toast.LENGTH_SHORT).show();

            }
        } else {
            homeChk.setVisibility(View.GONE);
            officeChk.setVisibility(View.GONE);
            imgEditHome.setVisibility(View.VISIBLE);
            imgEditOffice.setVisibility(View.VISIBLE);

            imgEditOffice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    action = AddressBookFragmentDirections.actionAddressBookFragmentToChangeAddressFragment("office");
                    Navigation.findNavController(getView()).navigate(action);
                }
            });
            imgEditHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    action = AddressBookFragmentDirections.actionAddressBookFragmentToChangeAddressFragment("home");
                    Navigation.findNavController(getView()).navigate(action);
                }
            });
        }
        getAddressDetails();
        return binding.getRoot();
    }

    public void getAddressDetails() {
        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<AddressModel> call = retrofitApi.getAddress(PreferenceManager.getCustomerPhone());
        call.enqueue(new Callback<AddressModel>() {
            @Override
            public void onResponse(Call<AddressModel> call, Response<AddressModel> response) {
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

    public void checkAddressEmpty(){

        if (ofcAddressTv.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "please add missing address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (addressTv.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "please add missing address", Toast.LENGTH_SHORT).show();
            return;
        }
    }

}