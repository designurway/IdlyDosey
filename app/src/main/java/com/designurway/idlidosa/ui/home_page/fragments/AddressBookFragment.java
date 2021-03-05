package com.designurway.idlidosa.ui.home_page.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
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
import com.designurway.idlidosa.a.model.CheckServiceModel;
import com.designurway.idlidosa.a.model.CustomerAddress;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.databinding.FragmentAddressBookBinding;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class AddressBookFragment extends Fragment {

    FragmentAddressBookBinding binding;
    AddressBookFragmentArgs args;
    NavDirections action;
    private static int AUTOCOMPLETE_REQUEST_CODE = 1;
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
    TextView officeNameTv, officeAddressTv, ofc_phone_tv,manualLocationAdb,TextOr;
    String homePincode,officePincode , pincode;
    ConstraintLayout currentLocationLayout;
    FusedLocationProviderClient fusedLocationProviderClient;
    Context context;


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
        currentLocationLayout = binding.currentLocationLayout;
        manualLocationAdb = binding.manualLocationAdb;
        TextOr = binding.TextOr;
        context = container.getContext();

        args = AddressBookFragmentArgs.fromBundle(getArguments());
        amount = args.getAmount();
        FromSetting = args.getFromSetting();
        orderId= args.getOrderId();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        checkAddressEmpty();
        Bundle bundle = getArguments();

        Geocoder coder = new Geocoder(getActivity());
        List<Address> Homeaddress=null;

        try {
            Homeaddress = coder.getFromLocationName(addressTv.getText().toString(), 5);
        } catch (IOException e) {
            e.printStackTrace();
        }

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

                            checkService(homePincode);
                            /*
                            action = AddressBookFragmentDirections.actionAddressBookFragmentToPaymentFragment(name,address,amount,phone,"none","none",orderId);
                            Navigation.findNavController(getView()).navigate(action);*/
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

                           checkService(officePincode);


                       }


                    }
                });
            } else {
                Toast.makeText(context, "From cart", Toast.LENGTH_SHORT).show();

            }
        } else {
            homeChk.setVisibility(View.GONE);
            officeChk.setVisibility(View.GONE);
            currentLocationLayout.setVisibility(View.GONE);
            manualLocationAdb.setVisibility(View.GONE);
            imgEditHome.setVisibility(View.VISIBLE);
            imgEditOffice.setVisibility(View.VISIBLE);
            TextOr.setVisibility(View.GONE);


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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Places.initialize(getContext().getApplicationContext(), getString(R.string.google_maps_key));
        currentLocationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = officeNameTv.getText().toString();
                address = addressTv.getText().toString();
                phone = ofc_phone_tv.getText().toString();
                action=AddressBookFragmentDirections.actionAddressBookFragmentToLocationFragment(amount,name,phone,orderId);
                Navigation.findNavController(v).navigate(action);
            }
        });

        manualLocationAdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.ADDRESS_COMPONENTS, Place.Field.LAT_LNG);

                // Start the autocomplete intent.
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                        .build(getContext());
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
            }
        });

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

                    homePincode = response.body().getPin_code();
                    officePincode = response.body().getOffice_pin_code();

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


    public void checkService( String pinCode){

        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<CheckServiceModel> call = retrofitApi.checkService(pinCode);
        call.enqueue(new Callback<CheckServiceModel>() {
            @Override
            public void onResponse(Call<CheckServiceModel> call, Response<CheckServiceModel> response) {
                if(response.isSuccessful() && response.body().getMessage().equals("available")){

                    action = AddressBookFragmentDirections.actionAddressBookFragmentToPaymentFragment(name,address,amount,phone,"none","none",orderId);
                    Navigation.findNavController(getView()).navigate(action);

                }else{
                    action = AddressBookFragmentDirections.actionAddressBookFragmentToServiceNotAvailableFragment(address);
                    Navigation.findNavController(getView()).navigate(action);
                }
            }

            @Override
            public void onFailure(Call<CheckServiceModel> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

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


    private void getCurrentLoc() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
                    try {
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        String pincode=null;
                        if ( addresses != null && addresses.size ( ) > 0 ) {
                            pincode = addresses.get ( 0 ).getPostalCode ( );
                            Log.d("pincode","current address"+pincode);
                        }
                       /* action = SelectLocationFragmentDirections.actionSelectLocationFragmentToAuthProfileFragment(addresses.get(0).getAddressLine(0),pincode,referralCode,phone);
                        Navigation.findNavController(getView()).navigate(action);
                        Toast.makeText(getActivity(), String.valueOf(addresses), Toast.LENGTH_SHORT).show();*/
//                        action = AddressBookFragmentDirections.actionAddressBookFragmentToServiceNotAvailableFragment(addresses.get(0).getAddressLine(0) );
//                        Navigation.findNavController(getView()).navigate(action);
                        name = officeNameTv.getText().toString();
                        address = addresses.get(0).getAddressLine(0);
                        phone = ofc_phone_tv.getText().toString();
                        checkService(pincode);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable Intent data) {
        List<Address> addresses=null;
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                Place place = Autocomplete.getPlaceFromIntent(data);

                LatLng latLng= place.getLatLng();


                try {
                    Geocoder geocoder = new Geocoder ( getActivity(), Locale.getDefault ( ) );
                     addresses = geocoder.getFromLocation ( place.getLatLng().latitude, place.getLatLng().longitude, 1 );

                    if ( addresses != null && addresses.size ( ) > 0 ) {
                        pincode = addresses.get ( 0 ).getPostalCode ( );
                        Log.d("pincode",pincode);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                name = officeNameTv.getText().toString();
                address = addresses.get(0).getAddressLine(0);
                phone = ofc_phone_tv.getText().toString();
                checkService(pincode);

            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);

                Log.i(TAG, status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}