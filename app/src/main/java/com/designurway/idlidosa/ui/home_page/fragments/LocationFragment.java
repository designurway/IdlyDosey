package com.designurway.idlidosa.ui.home_page.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.databinding.FragmentLocationBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class LocationFragment extends Fragment implements OnMapReadyCallback,GoogleMap.OnCameraIdleListener {

    FragmentLocationBinding binding;
    Context context;
    FusedLocationProviderClient fusedLocationProviderClient;
    LatLng CustomerLocation;
    GoogleMap googleMap;
    TextView selectedLocation;
    View mapView;
    Button selectLocBtn;
    double latitude, longitude;
    NavDirections action;
    LocationFragmentArgs args;
    String name, address, amount, phone,OrderID;

    public LocationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_location, container, false);
        binding = FragmentLocationBinding.inflate(inflater,container,false);
        context = container.getContext();
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        args = LocationFragmentArgs.fromBundle(getArguments());
        name = args.getName();
        amount = args.getAmount();
        phone = args.getPhone();
        OrderID = args.getOrderId();
        selectedLocation = binding.selectedLocation;
        selectLocBtn=binding.selectLocBtn;
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);


        mapFragment.getMapAsync(this);

        //Setting current location button in buttom
        View locationButton = ((View) mapFragment.getView().findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);

        rlp.setMargins(0, 0, 0, 150);
        rlp.setMarginEnd(80);
        // here we are calling this method to initalize the FusedLocation Apis to get Updated Current Location
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        getCurrentLoc();

        selectLocBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    action=LocationFragmentDirections.actionLocationFragmentToPaymentFragment(name,address,amount,phone,"none","none",OrderID);
                    Navigation.findNavController(v).navigate(action);


            }
        });

    }

    @Override
    public void onMapReady(GoogleMap mMap) {
        // Add a marker in Sydney and move the camera
        googleMap =mMap;

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
            if ( ContextCompat.checkSelfPermission ( context,
                    Manifest.permission.ACCESS_FINE_LOCATION )
                    == PackageManager.PERMISSION_GRANTED ) {


                googleMap.setMyLocationEnabled ( true );
            }
        } else {

            googleMap.setMyLocationEnabled ( true );
        }

        //get LatLang of Center of Map
        googleMap.setOnCameraIdleListener ( this );


    }


    private void getCurrentLoc() {



        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(context, "No Permission", Toast.LENGTH_SHORT).show();

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

                    CustomerLocation = new LatLng(location.getLatitude(),location.getLongitude());
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(CustomerLocation, 16));


                    Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
                    try {
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        String pincode=null;
                        if ( addresses != null && addresses.size ( ) > 0 ) {
                            pincode = addresses.get ( 0 ).getPostalCode ( );
                            Log.d("pincode","current address"+pincode);
                        }
                        address = addresses.get(0).getAddressLine(0);

                        selectedLocation.setText(address);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(context, "adbfa "+location, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    @Override
    public void onCameraIdle() {

        LatLng center = googleMap.getCameraPosition ( ).target;
        latitude = center.latitude;
        longitude = center.longitude;


        getAddress ( context, latitude, longitude );

    }

    // Getting Addtress from Latitude and Longitude
    public void getAddress ( Context context, double LATITUDE, double LONGITUDE ) {

        //Set Address
        try {
            Geocoder geocoder = new Geocoder ( context, Locale.getDefault ( ) );
            List<Address> addresses = geocoder.getFromLocation ( LATITUDE, LONGITUDE, 1 );
            if ( addresses != null && addresses.size ( ) > 0 ) {


                address = addresses.get ( 0 ).getAddressLine ( 0 ); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                /*String city = addresses.get ( 0 ).getLocality ( );
                String premises = addresses.get ( 0 ).getPremises ( );
                String subLocality = addresses.get ( 0 ).getSubLocality ( );
                String state = addresses.get ( 0 ).getAdminArea ( );
                String country = addresses.get ( 0 ).getCountryName ( );
                Pincode = addresses.get ( 0 ).getPostalCode ( );
                String knownName = addresses.get ( 0 ).getFeatureName ( ); // Only if available else return NULL*/

                selectedLocation.setText ( address );


            }
        } catch (IOException e) {
            e.printStackTrace ( );
        }
        return;
    }

}