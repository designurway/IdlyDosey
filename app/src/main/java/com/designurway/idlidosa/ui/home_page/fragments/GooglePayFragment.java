package com.designurway.idlidosa.ui.home_page.fragments;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.model.ErrorMessageModel;
import com.designurway.idlidosa.a.model.GetNotificationResponse;
import com.designurway.idlidosa.a.model.OrderStatusModel;
import com.designurway.idlidosa.a.model.StatusAndMessageModel;
import com.designurway.idlidosa.a.paytmallinonesdk.PaytmActivity;
import com.designurway.idlidosa.a.utils.AndroidUtils;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.databinding.FragmentGooglePayBinding;
import com.designurway.idlidosa.model.PaymentModel;
import com.designurway.idlidosa.model.StatusMessageModel;
import com.designurway.idlidosa.retrofit.BaseClient;
import com.designurway.idlidosa.retrofit.RetrofitApi;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class GooglePayFragment extends Fragment {

    FragmentGooglePayBinding binding;
    GooglePayFragmentArgs args;

    ImageView arrowImg;
    TextView amountEt, upiEt, txtNumber;
    //    EditText  note, name;
    Button payBtn;
    String TAG = "main";
    final int UPI_PAYMENT = 0;
    String orderId, amount;
    CircleImageView personPayImgv;
    String address;
    LatLng lat;
    NavDirections action;

    public GooglePayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_google_pay, container, false);
        binding = FragmentGooglePayBinding.inflate(inflater, container, false);


        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        args = GooglePayFragmentArgs.fromBundle(getArguments());
        amount = args.getAmount();
        orderId = args.getOrderId();
        address = args.getAddress();

        amountEt = binding.txtAmount;
//        upiEt = binding.upiEt;
//        payBtn = binding.payBtn;
        payBtn = binding.payAmount;
        txtNumber = binding.txtNumber;


        lat = getLocationFromAddress(getActivity(), address);

//        send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Getting the values from the EditTexts
//                if (TextUtils.isEmpty(name.getText().toString().trim())){
//                    Toast.makeText(getContext()," Name is invalid", Toast.LENGTH_SHORT).show();
//                }else if (TextUtils.isEmpty(upivirtualid.getText().toString().trim())){
//                    Toast.makeText(getContext()," UPI ID is invalid", Toast.LENGTH_SHORT).show();
//                }else if (TextUtils.isEmpty(note.getText().toString().trim())){
//                    Toast.makeText(getContext()," Note is invalid", Toast.LENGTH_SHORT).show();
//                }else if (TextUtils.isEmpty(amount.getText().toString().trim())){
//                    Toast.makeText(getContext()," Amount is invalid", Toast.LENGTH_SHORT).show();
//                }else{
//                    payUsingUpi(name.getText().toString(), upivirtualid.getText().toString(),
//                            note.getText().toString(), amount.getText().toString());
//                }
//            }
//        });
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payUsingUpi(PreferenceManager.getCustomerName(), txtNumber.getText().toString(),
                        "IDLY DOSEY", amountEt.getText().toString());


            }
        });

        goTopayment();
        setImage();

    }

    void payUsingUpi(String name, String upiId, String note, String amount) {
        Log.e("main ", "--up--" + upiId + "--" + amount);
        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", "name")
                .appendQueryParameter("mc", "")
                //.appendQueryParameter("tid", "02125412")
                .appendQueryParameter("tr", "12345678")
                .appendQueryParameter("tn", "IDLY DOSEY")
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                //.appendQueryParameter("refUrl", "blueapp")
                .build();
        Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
        upiPayIntent.setData(uri);
        // will always show a dialog to user to choose an app
        Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");
        // check if intent resolves
        if (null != chooser.resolveActivity(getActivity().getPackageManager())) {
            startActivityForResult(chooser, UPI_PAYMENT);
        } else {
            Toast.makeText(getContext(), "No UPI app found, please install one to continue", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("main ", "response " + resultCode);
//        Toast.makeText(getContext(), data.toString(), Toast.LENGTH_SHORT).show();
        /*
       E/main: response -1
       E/UPI: onActivityResult: txnId=AXI4a3428ee58654a938811812c72c0df45&responseCode=00&Status=SUCCESS&txnRef=922118921612
       E/UPIPAY: upiPaymentDataOperation: txnId=AXI4a3428ee58654a938811812c72c0df45&responseCode=00&Status=SUCCESS&txnRef=922118921612
       E/UPI: payment successfull: 922118921612
         */
        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        Log.e("UPI", "onActivityResult: " + trxt);
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add(trxt);
                        upiPaymentDataOperation(dataList);


                    } else {
                        Log.e("UPI", "onActivityResult: " + "Return data is null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentDataOperation(dataList);
                    }
                } else {
                    //when user simply back without payment
                    Log.e("UPI", "onActivityResult: " + "Return data is null");
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    upiPaymentDataOperation(dataList);
                }
                break;
        }
    }

    private void upiPaymentDataOperation(ArrayList<String> data) {
        if (isConnectionAvailable(getContext())) {
            String str = data.get(0);
            Log.e("UPIPAY", "upiPaymentDataOperation: " + str);
            String paymentCancel = "";
            if (str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");

            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if (equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                        Log.d("status", status);
                    } else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];
                    }
                } else {
                    paymentCancel = "Payment cancelled by user.";
                }
            }
            if (status.equals("success")) {
                chechIsComboPresent();
//                postComboWonDetails();

                NavDirections navDirections = GooglePayFragmentDirections.actionGooglePayFragmentToPaymentSucessfulFragment(str);
                Navigation.findNavController(getView()).navigate(navDirections);

                //Code to handle successful transaction here.
                Toast.makeText(getContext(), "order placed.", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "payment successfull: " + approvalRefNo);
            } else if ("Payment cancelled by user.".equals(paymentCancel)) {

                Toast.makeText(getContext(), "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "Cancelled by user: " + approvalRefNo);

            } else {
                Toast.makeText(getContext(), "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "failed payment: " + approvalRefNo);
            }
        } else {
            Log.e("UPI", "Internet issue: ");
            Toast.makeText(getContext(), "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }

    public void goTopayment() {

        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<PaymentModel> call = retrofitApi.payment(PreferenceManager.getCustomerId(), orderId);

        call.enqueue(new Callback<PaymentModel>() {
            @Override
            public void onResponse(Call<PaymentModel> call, Response<PaymentModel> response) {
                if (response.isSuccessful()) {


                    amountEt.setText(amount);
                } else {
                    Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PaymentModel> call, Throwable t) {
                Toast.makeText(getContext(), "ON Failure " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void setImage() {
        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<StatusMessageModel> call = retrofitApi.image(PreferenceManager.getCustomerPhone());
        call.enqueue(new Callback<StatusMessageModel>() {
            @Override
            public void onResponse(Call<StatusMessageModel> call, Response<StatusMessageModel> response) {
                if (response.isSuccessful()) {
                    StatusMessageModel statusMessageModel = response.body();
                    if (statusMessageModel.getImage().isEmpty()) {
//                        Picasso.with(getContext()).load(statusMessageModel.getImage()).into(person_pay_imgv);
                    } else {
//                        Picasso.with(getContext()).load(statusMessageModel.getImage()).into(person_pay_imgv);
//                        Glide.with(getContext()).load(statusMessageModel.getImage()).into(personPayImgv);
                    }
                } else {
                    Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StatusMessageModel> call, Throwable t) {
                Toast.makeText(getContext(), "On Failure" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void ConfromOrder() {
        Log.d("orderId", orderId);
        String orderid = AndroidUtils.randomName(5);
        com.designurway.idlidosa.a.retrofit.RetrofitApi api = com.designurway.idlidosa.a.retrofit.BaseClient.getClient().create(com.designurway.idlidosa.a.retrofit.RetrofitApi.class);
      /*  Log.d("OrderId", orderId);
        Log.d("OrderId", address);
        Log.d("OrderId", lat.toString());*/

        if (!address.equals("")) {
            Call<OrderStatusModel>
                    call = api.postOrderDetails(PreferenceManager.getCustomerId(), args.getOrderId(), amount, address, String.valueOf(lat), String.valueOf(lat.latitude), String.valueOf(lat.longitude));

            call.enqueue(new Callback<OrderStatusModel>() {

                @Override
                public void onResponse(Call<OrderStatusModel> call, Response<OrderStatusModel> response) {
                    Log.d("confirmorder", "success");
                    if (response.isSuccessful()) {
                        Log.d("confirmorder", "success");
                        OrderStatusModel orderStatusModel = response.body();
                        Log.d("orderId", orderId);
                        getNotification(args.getOrderId());
                    } else {
                        Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<OrderStatusModel> call, Throwable t) {
                    Log.d(TAG, "ConfirmOrder Onfail" + t.getMessage());


                }
            });

        } else {
            Toast.makeText(getActivity(), "Address is Empty", Toast.LENGTH_SHORT).show();
        }
    }

    public void getNotification(String orderId) {
        Log.d("orderId", orderId);
        Log.d("confirmorder", "Nmethod");
        com.designurway.idlidosa.a.retrofit.RetrofitApi api = com.designurway.idlidosa.a.retrofit.BaseClient.getClient().create(com.designurway.idlidosa.a.retrofit.RetrofitApi.class);
        Call<GetNotificationResponse> call = api.getNotification(args.getOrderId(), "new order");
        call.enqueue(new Callback<GetNotificationResponse>() {
            @Override
            public void onResponse(Call<GetNotificationResponse> call, Response<GetNotificationResponse> response) {
                if (response.isSuccessful()) {

                    action = GooglePayFragmentDirections.actionGooglePayFragmentToPaymentSucessfulFragment("none");
                    Navigation.findNavController(getView()).navigate(action);

                } else {


                }
            }

            @Override
            public void onFailure(Call<GetNotificationResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Notification Onfail" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void postComboWonDetails() {
        Log.d(TAG, "postCombo");
        String totalAmount = amount;
        String orderid = AndroidUtils.randomName(5);
        com.designurway.idlidosa.a.retrofit.RetrofitApi retrofitApi = com.designurway.idlidosa.a.retrofit.BaseClient.getClient().create(com.designurway.idlidosa.a.retrofit.RetrofitApi.class);
        Call<ErrorMessageModel> call = retrofitApi.updateComboWonDetails(orderid,
                PreferenceManager.getCustomerId(),
                totalAmount);
        call.enqueue(new Callback<ErrorMessageModel>() {
            @Override
            public void onResponse(Call<ErrorMessageModel> call, Response<ErrorMessageModel> response) {
                if (response.isSuccessful()) {
//                    goToNext(new DashBoardFragment());

//                    getNotification(orderid);
//                    ConfromOrder(orderId);
                } else {
//                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
//                    ConfromOrder(orderId);
                }
            }

            @Override
            public void onFailure(Call<ErrorMessageModel> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());

            }
        });

    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {

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

        // Toast.makeText(context, "this is"+p1.toString(), Toast.LENGTH_SHORT).show();
        Log.d("LATLANG", "this is" + p1.toString());

        return p1;
    }

    public void chechIsComboPresent() {


        com.designurway.idlidosa.a.retrofit.RetrofitApi retrofitApi = com.designurway.idlidosa.a.retrofit.BaseClient.getClient().create(com.designurway.idlidosa.a.retrofit.RetrofitApi.class);
        Call<StatusAndMessageModel> call = retrofitApi.checkCombo(PreferenceManager.getReferred_from());

        call.enqueue(new Callback<StatusAndMessageModel>() {
            @Override
            public void onResponse(Call<StatusAndMessageModel> call, Response<StatusAndMessageModel> response) {
                if (response.isSuccessful()) {


                    if (response.body().getStatus().equals("1")) {
                        ConfromOrder();


                    } else if (response.body().getStatus().equals("2")) {

                        postComboWonDetails();


                    } else if (response.body().getStatus().equals("3")) {
                        ConfromOrder();

                    }

                } else {
                    Log.d(TAG, "Nothing found");
                }
            }

            @Override
            public void onFailure(Call<StatusAndMessageModel> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}