package com.designurway.idlidosa.ui.home_page.fragments;

import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.model.CustomerAddress;
import com.designurway.idlidosa.a.model.TrackOrderModel;
import com.designurway.idlidosa.a.model.TrackingModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.ui.DirectionsJSONParser;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TrackOrderFragment extends Fragment implements OnMapReadyCallback {
    String color = null;
    private GoogleMap mMap;
    private static final int ACCESS_LOCATION_REQUEST_CODE = 10001;
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;
    Marker driverLocationMarker;
    MarkerOptions markerOptions;
    Circle userLocationAccuracyCirlce;
    LatLng CustomerLocation, LiveLocation, PickupLocation;
    Location currentlocation;
    private LocationSettingsRequest locationSettingsRequest;
    Button btn;
    BottomSheetDialog bottomSheetDialog;
    CardView card_order_detail;
    private BottomSheetBehavior bottomSheetBehavior;
    //    Get Updated Current Location related api
    private FusedLocationProviderClient mfusedLocationProviderClient;
    public SettingsClient msettingsClient;
    JSONObject jsonObject;
    String latLong;
    String pickupLat, pickupLong, customerLat, customerLang;

    TrackOrderFragmentArgs args;

    String orderId, amount, image;
    TextView txt_recived,text_cmpt,txt_dispatch,txt_on_ur_way,txt_preparing,text_delivered;


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_track_order, container, false);
        ButterKnife.bind(this, view);

        txt_dispatch=view.findViewById(R.id.txt_dispatch);
        text_cmpt=view.findViewById(R.id.text_cmpt);
        txt_on_ur_way=view.findViewById(R.id.txt_on_ur_way);
        txt_preparing=view.findViewById(R.id.txt_preparing);
        text_delivered=view.findViewById(R.id.text_delivered);
        txt_recived=view.findViewById(R.id.txt_recived);

        args = TrackOrderFragmentArgs.fromBundle(getArguments());
        orderId = args.getOrderId();
        amount = args.getAmount();

        getTrackingLocation();

       // LatLng latlang = getLocationFromAddress(getContext(), "#4642,near gnesh temple,n r mohalla,mysore,pin 57007");
//        Toast.makeText(getContext(), latlang.toString(), Toast.LENGTH_SHORT).show();
        GetOrderStatus();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPusher();

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);


        mapFragment.getMapAsync(this);

        // here we are calling this method to initalize the FusedLocation Apis to get Updated Current Location
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());


    }

    private void getPusher() {

        PusherOptions options = new PusherOptions();
        options.setCluster("ap2");

        Pusher pusher = new Pusher("370eabe1a4edd8bc1445", options);

        pusher.connect(new ConnectionEventListener() {

            @Override
            public void onConnectionStateChange(ConnectionStateChange change) {

                Log.i("Pusher", "State changed from " + change.getPreviousState() +
                        " to " + change.getCurrentState());

            }

            @Override
            public void onError(String message, String code, Exception e) {

                Log.i("Pusher", "There was a problem connecting! " +
                        "\ncode: " + code +
                        "\nmessage: " + message +
                        "\nException: " + e
                );

            }
        }, ConnectionState.ALL);

        Channel channel = pusher.subscribe("my-channel");

        channel.bind("my-event", new SubscriptionEventListener() {
            @Override
            public void onEvent(PusherEvent event) {

                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                jsonObject = new JSONObject(event.toString());
                                String abc = event.toString();

                                String str = event.toString();
                                String[] arrOfStr = str.split("data=", 2);
                                String[] a = arrOfStr[1].split(", channel=my-channel", 2);

//                             latLong = jsonObject.getString("delivery_boy_lat_lang");
//                            setDriverLocationMarker();
                                Log.d("events", event.toString());
                                JSONObject jsonObject = new JSONObject(a[0]);
                                String deliveryLat = jsonObject.getString("delivery_latitude");
                                String deliveryLong = jsonObject.getString("delivery_longitude");
                                LiveLocation = new LatLng(Double.parseDouble(deliveryLat), Double.parseDouble(deliveryLong));
                                Log.d("abc", deliveryLat + " " + deliveryLong);

                                if (LiveLocation != null) {
                                    setDriverLocationMarker();
                                } else {
                                    Toast.makeText(getContext(), "Live Location is null", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    Log.i("Pusher", "Received event with data: " + event.toString());

                }
            }
        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        if (CustomerLocation != null) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(CustomerLocation, 16));
        } else {
            Toast.makeText(getContext(), "Customer location is null", Toast.LENGTH_SHORT).show();
        }
    }


    private void setDriverLocationMarker() {

//        mMap.addMarker(new MarkerOptions().position(CurrentLatLng));


        // here will chek if the marker is null or not
        if (driverLocationMarker == null) {
            // if marker is null then we will create new marker
            markerOptions = new MarkerOptions();
            markerOptions.position(LiveLocation);
            //Custom marker
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.bike_red));
            //rotation of rarker
            // to make custom icon in center of user loction system icon
            markerOptions.anchor((float) 0.5, (float) 0.5);
            driverLocationMarker = mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LiveLocation, 18));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LiveLocation, 18));

        } else {
            // use the previously create marker
            driverLocationMarker.setPosition(LiveLocation);

        }
/*
        if (userLocationAccuracyCirlce==null){
            CircleOptions circleOptions = new CircleOptions();
            circleOptions.center(latLng);
            circleOptions.strokeWidth(8);
            circleOptions.strokeColor(Color.argb(225,255,0,0));
            circleOptions.fillColor(Color.argb(32,255,0,0));
            circleOptions.radius(location.getAccuracy());
            userLocationAccuracyCirlce = mMap.addCircle(circleOptions);
        }else {
            userLocationAccuracyCirlce.setCenter(latLng);
            userLocationAccuracyCirlce.setRadius(location.getAccuracy());
        }*/
    }

    public void GetOrderStatus() {
        RetrofitApi api = BaseClient.getClient().create(RetrofitApi.class);

        Call<TrackOrderModel> call = api.GetOrderStatus(PreferenceManager.getCustomerId(), orderId);
        call.enqueue(new Callback<TrackOrderModel>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<TrackOrderModel> call, Response<TrackOrderModel> response) {
                TrackOrderModel trackOrderModel = response.body();
                if (response.isSuccessful() && trackOrderModel.getStatus().equals("1")) {

                    color = trackOrderModel.getOrder_status();
                    if (color.equals("received")) {
                        txt_recived.setTextColor(getResources().getColor(R.color.darkGreen));
                        txt_preparing.setTextColor(getResources().getColor(R.color.darkGreen));
                        txt_preparing.setVisibility(View.VISIBLE);


                    } else if (color.equals("dispatched")) {

                        txt_dispatch.setTextColor(getResources().getColor(R.color.darkGreen));
                        txt_on_ur_way.setTextColor(getResources().getColor(R.color.darkGreen));
                        txt_on_ur_way.setVisibility(View.VISIBLE);

                    } else if (color.equals("completed")) {
                        text_cmpt.setTextColor(getResources().getColor(R.color.darkGreen));
                        text_delivered.setVisibility(View.VISIBLE);
                        text_delivered.setTextColor(getResources().getColor(R.color.darkGreen));
                    } else {
                        Toast.makeText(getContext(), "Place an order", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<TrackOrderModel> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            String data = "";

            try {
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("abc", e.toString());
            }

            Log.d("abc", "data : " + data);

            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            ParserTask parserTask = new ParserTask();
            parserTask.execute(result);
        }
    }

    /**
     * A class to parse the JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Log.d("abc", "route : " + routes);

            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList points = new ArrayList();
            PolylineOptions lineOptions = new PolylineOptions();

            for (int i = 0; i < result.size(); i++) {

                List<HashMap<String, String>> path = result.get(i);

                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                lineOptions.addAll(points);
                lineOptions.width(19);
                lineOptions.color(Color.BLUE);
                lineOptions.geodesic(true);

            }
            mMap.addPolyline(lineOptions);
            // Drawing polyline in the Google Map
            if (points.size() != 0) {
//                mMap.addPolyline(lineOptions);
            }

        }
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        Log.d("avd", "Destition : " + PickupLocation.toString() + " Current : " + CustomerLocation);
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;


        // Sensor enabled
        String sensor = "sensor=false";
        String mode = "mode=driving";
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + mode;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + "AIzaSyBx2YQMquqxwOPuA7m_O4bwo-0tiiqB4lY";
//        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + "AIzaSyAMNkekG6kz9pl_SKQfugNfZv3iSpS5AmA";

        Log.d("abc", "url : " + url);

        return url;
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.connect();

            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }

        Log.d("abc", "Downloadurl : " + data);
        return data;
    }

   /* public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude());

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        Toast.makeText(context, "this is" + p1.toString(), Toast.LENGTH_SHORT).show();
        Log.d("LATLANG", "this is" + p1.toString());


        return p1;
    }*/

    public void getTrackingLocation() {

        RetrofitApi api = BaseClient.getClient().create(RetrofitApi.class);
        Call<TrackingModel> call = api.GetTrackingLocation(orderId); //OAU27
        call.enqueue(new Callback<TrackingModel>() {
            @Override
            public void onResponse(Call<TrackingModel> call, Response<TrackingModel> response) {
                if (response.isSuccessful()) {

                    TrackingModel model = response.body();
                    pickupLat = model.getPick_up_lat();
                    pickupLong = model.getPick_up_lang();
                    customerLat = model.getLat();
                    customerLang = model.getLang();


                    if (pickupLat != null && !pickupLat.isEmpty() && pickupLong!=null && customerLat != null && customerLang!=null) {
                        PickupLocation = new LatLng(Double.parseDouble(pickupLat), Double.parseDouble(pickupLong));
                        CustomerLocation = new LatLng(Double.parseDouble(customerLat), Double.parseDouble(customerLang));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(CustomerLocation, 16));

                        String url = getDirectionsUrl(CustomerLocation, PickupLocation);

                        DownloadTask downloadTask = new DownloadTask();

                        // Start downloading json data from Google Directions API
                        downloadTask.execute(url);

//                        setDriverLocationMarker();
                    }else {
                        Toast.makeText(getContext(), "Data Null", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(getActivity(), "fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TrackingModel> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}