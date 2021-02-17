package com.designurway.idlidosa.ui.home_page.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.databinding.FragmentSelectLocationBinding;
import com.designurway.idlidosa.ui.home_page.HomePageActivity;
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

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class SelectLocationFragment extends Fragment {

    ConstraintLayout allowLocationLayout;
    FragmentSelectLocationBinding binding;
    TextView manualLocation;
    String TAG = "tag";
    private static int AUTOCOMPLETE_REQUEST_CODE = 1;
    NavDirections action;
    String address;
    FusedLocationProviderClient fusedLocationProviderClient;
    TextView SkipTxt;

    public SelectLocationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_select_location, container, false);
        binding = FragmentSelectLocationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        allowLocationLayout = binding.allowLocationLayout;
        manualLocation = binding.manualLocation;
        SkipTxt = binding.SearchTxt;

        Places.initialize(getContext().getApplicationContext(), getString(R.string.google_maps_key));

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        manualLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.ADDRESS_COMPONENTS, Place.Field.LAT_LNG);

                // Start the autocomplete intent.
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                        .build(getContext());
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
            }
        });

        allowLocationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentLoc();

            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable Intent data) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                Place place = Autocomplete.getPlaceFromIntent(data);

              LatLng latLng= place.getLatLng();


                try {
                    Geocoder geocoder = new Geocoder ( getActivity(), Locale.getDefault ( ) );
                    List<Address> addresses = geocoder.getFromLocation ( place.getLatLng().latitude, place.getLatLng().longitude, 1 );

                    if ( addresses != null && addresses.size ( ) > 0 ) {
                        String Pincode = addresses.get ( 0 ).getPostalCode ( );
                        Log.d("pincode",Pincode);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (place.getAddress().contains("570002")){
                    Toast.makeText(getActivity(), "present", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), " Not present", Toast.LENGTH_SHORT).show();
                }
                action = SelectLocationFragmentDirections.actionSelectLocationFragmentToProfileFragment(place.getAddress());
                Navigation.findNavController(getView()).navigate(action);
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
//                        txt_1.setText(Html.fromHtml("<font color='#6200EE'><b>Latitiude:</b></br></font>"+addresses.get(0).getLatitude()));
//                        txt_2.setText(Html.fromHtml("<font color='#6200EE'><b>Longitude:</b></br></font>"+addresses.get(0).getLongitude()));
//                        txt_3.setText(Html.fromHtml("<font color='#6200EE'><b>Country name:</b></br></font>"+addresses.get(0).getCountryName()));
//                        txt_4.setText(Html.fromHtml("<font color='#6200EE'><b>Locality :</b></br></font>"+addresses.get(0).getLocality()));
//                        txt_5.setText(Html.fromHtml("<font color='#6200EE'><b>Address :</b></br></font>"+addresses.get(0).getAddressLine(0)));
                        action = SelectLocationFragmentDirections.actionSelectLocationFragmentToProfileFragment(addresses.get(0).getAddressLine(0));
                        Navigation.findNavController(getView()).navigate(action);
                        Toast.makeText(getActivity(), String.valueOf(addresses), Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }



}