package com.designurway.idlidosa.a.paytmallinonesdk;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.activity.PaymentSucessfulActivity;
import com.designurway.idlidosa.a.model.CustomerAddress;
import com.designurway.idlidosa.a.model.ErrorMessageModel;
import com.designurway.idlidosa.a.model.GetNotificationResponse;
import com.designurway.idlidosa.a.model.OrderStatusModel;
import com.designurway.idlidosa.a.model.StatusAndMessageModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.AndroidUtils;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.ui.home_page.fragments.PaymentFragmentArgs;
import com.google.android.gms.maps.model.LatLng;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;
import com.paytm.pgsdk.TransactionManager;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaytmActivity extends AppCompatActivity {

    private final String midString = "uHykBm20360288053432";
    private final String TAG = "MainActivity";
    private final Integer ActivityRequestCode = 2;
    private String txnTokenString = "";
    String jsonString;
    PreferenceManager preferenceManager;
    JSONObject jsonObject;
    private String orderIdString = "";
    String amount, address;
    TextView paymtAmount;
    Button paytmPay;
    LatLng lat;
    String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paytm);

        paytmPay = findViewById(R.id.paytmPay);
        paymtAmount = findViewById(R.id.paymtAmount);

        Bundle bundle = getIntent().getExtras();

        amount = bundle.getString("amount");
        address = bundle.getString("address");

        lat = getLocationFromAddress(this, address);
        paymtAmount.setText(amount);

//        Toast.makeText(this, lat.toString(), Toast.LENGTH_SHORT).show();

//        Toast.makeText(this, amount +" "+address, Toast.LENGTH_SHORT).show();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
        String date = df.format(c.getTime());
        Random rand = new Random();
        int min = 1000, max = 9999;
// nextInt as provided by Random is exclusive of the top value so you need to add 1
        int randomNum = rand.nextInt((max - min) + 1) + min;
        orderIdString = date + randomNum;


        paytmPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getToken();


            }
        });


    }


    private void getToken() {


        Log.e(TAG, " get token start");
//        progressBar.setVisibility(View.VISIBLE);
        ServiceWrapper serviceWrapper = new ServiceWrapper(null);
        Call<Token_Res> call = serviceWrapper.getTokenCall("12345", midString, orderIdString, amount);
        call.enqueue(new Callback<Token_Res>() {
            @Override
            public void onResponse(Call<Token_Res> call, Response<Token_Res> response) {
                Log.e(TAG, "respo " + response.isSuccessful());
//                progressBar.setVisibility(View.GONE);
                try {

                    if (response.isSuccessful() && response.body() != null) {
                        if (response.body().getBody().getTxnToken() != "") {
                            Log.e(TAG, " transaction token : " + response.body().getBody().getTxnToken());
                            startPaytmPayment(response.body().getBody().getTxnToken());
                        } else {
                            Log.e(TAG, " Token status false ");
                        }
                    }
                } catch (Exception e) {
                    Log.e(TAG, " error in Token Res " + e.toString());
                }
            }

            @Override
            public void onFailure(Call<Token_Res> call, Throwable t) {
//                progressBar.setVisibility(View.GONE);
                Log.e(TAG, " response error " + t.toString());
            }
        });

    }


    private void startPaytmPayment(String txnToken) {

        txnTokenString = txnToken;
        String host = "https://securegw.paytm.in/";

        //Log.e(TAG, "order details "+ orderDetails);

        String callBackUrl = host + "theia/paytmCallback?ORDER_ID=" + orderIdString;
        PaytmOrder paytmOrder = new PaytmOrder(orderIdString, midString, txnTokenString, amount, callBackUrl);
        TransactionManager transactionManager = new TransactionManager(paytmOrder, new PaytmPaymentTransactionCallback() {
            @Override
            public void onTransactionResponse(Bundle bundle) {
                Toast.makeText(PaytmActivity.this, "onTransactionResponse : " + bundle.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onTransactionResponse : " + bundle.toString());
            }

            @Override
            public void networkNotAvailable() {
                Toast.makeText(PaytmActivity.this, "networkNotAvailable : ", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "networkNotAvailable : ");
            }

            @Override
            public void onErrorProceed(String s) {
                Toast.makeText(PaytmActivity.this, "onErrorProceed : " + s, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onErrorProceed : " + s);
            }

            @Override
            public void clientAuthenticationFailed(String s) {
                Toast.makeText(PaytmActivity.this, "clientAuthenticationFailed : " + s, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "clientAuthenticationFailed : " + s);
            }

            @Override
            public void someUIErrorOccurred(String s) {
                Toast.makeText(PaytmActivity.this, "someUIErrorOccurred : " + s, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "someUIErrorOccurred : " + s);
            }

            @Override
            public void onErrorLoadingWebPage(int i, String s, String s1) {
                Toast.makeText(PaytmActivity.this, "onErrorLoadingWebPage : " + " s " + s + " S1 : " + s1, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onErrorLoadingWebPage : " + " s " + s + " S1 : " + s1);
            }

            @Override
            public void onBackPressedCancelTransaction() {
                Toast.makeText(PaytmActivity.this, "onBackPressedCancelTransaction : ", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onBackPressedCancelTransaction : ");
            }

            @Override
            public void onTransactionCancel(String s, Bundle bundle) {
                Toast.makeText(PaytmActivity.this, "onTransactionCancel : " + " s " + s + " Bundle : " + bundle.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onTransactionCancel : " + s + " Bundle : " + bundle.toString());
            }
        });


        transactionManager.setShowPaymentUrl(host + "theia/api/v1/showPaymentPage");
        transactionManager.startTransaction(PaytmActivity.this, ActivityRequestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Log.e("RESPCODE", " result code " + resultCode);
        // -1 means successful  // 0 means failed
        // one error is - nativeSdkForMerchantMessage : networkError
        super.onActivityResult(requestCode, resultCode, data);

//        Toast.makeText(this, "payment success" + data.getStringExtra("response").toString(), Toast.LENGTH_SHORT).show();


        if (requestCode == ActivityRequestCode && data != null) {

            Bundle bundle = data.getExtras();
//            String status = bundle.get("RESPCODE").toString();

//            Toast.makeText(this, "RESPCODE "+ status, Toast.LENGTH_SHORT).show();
//            Log.e("RESPCODE", "RESPCODE "+status);
//            Intent intent=new Intent(PaytmActivity.this, DashboardActivity.class);


            if (bundle != null) {

//                Toast.makeText(this, bundle.toString(), Toast.LENGTH_SHORT).show();

                Log.e(TAG, "Bundle = " + bundle.toString());

                for (String key : bundle.keySet()) {

//                    Toast.makeText(this, "Inside For loop", Toast.LENGTH_SHORT).show();

                    Log.e(TAG, "Payment Done" + " : " + (bundle.get(key) != null ? bundle.get(key) : "NULL"));

//

                    jsonString = bundle.getString(key);

                    Log.d(TAG, "JsonString : " + jsonString);

                    Bundle bund = new Bundle();
                    bund.putString("dataa", jsonString);
                    Log.d("abc", "outside try cath");
                    try {
                        jsonObject = new JSONObject(jsonString);
                        Log.d("abc", "inside try cath");
                        if (jsonObject.getString("STATUS").equals("TXN_SUCCESS")) {

                            chechIsComboPresent();
                            Log.d("abc", "Sucess .....");
                            Intent intent = new Intent(PaytmActivity.this,
                                    PaymentSucessfulActivity.class);
                            intent.putExtras(bund);
                            startActivity(intent);
                            finish();
                        } else {

                            Toast.makeText(this, "Payment failed", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        } else {
            Log.e(TAG, " payment failed");
        }
    }


    private void postComboWonDetails() {

        String totalAmount = amount;
        String orderid = AndroidUtils.randomName(5);
        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<ErrorMessageModel> call = retrofitApi.updateComboWonDetails(orderid,
                PreferenceManager.getCustomerId(),
                totalAmount);
        call.enqueue(new Callback<ErrorMessageModel>() {
            @Override
            public void onResponse(Call<ErrorMessageModel> call, Response<ErrorMessageModel> response) {
                if (response.isSuccessful()) {
//                    goToNext(new DashBoardFragment());
                } else {
//                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ErrorMessageModel> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());

            }
        });

    }

    public void ConfromOrder() {


        orderId = AndroidUtils.randomName(5);

        RetrofitApi api = BaseClient.getClient().create(RetrofitApi.class);
        Log.d("OrderId", orderId);
        Log.d("OrderId", address);
        Log.d("OrderId", lat.toString());

        if (!address.equals("")) {
            Call<OrderStatusModel>
                    call = api.postOrderDetails(PreferenceManager.getCustomerId(), orderId, amount, address, String.valueOf(lat), String.valueOf(lat.latitude), String.valueOf(lat.longitude));

            call.enqueue(new Callback<OrderStatusModel>() {
                @Override
                public void onResponse(Call<OrderStatusModel> call, Response<OrderStatusModel> response) {
                    if (response.isSuccessful()) {
                        OrderStatusModel orderStatusModel = response.body();
                        getNotification(orderId);
                        Log.d("OrderId", orderId);
                    } else {
                        Toast.makeText(PaytmActivity.this, response.message(), Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<OrderStatusModel> call, Throwable t) {
                    Log.d(TAG, "onFailure" + t.getMessage());


                }
            });

        } else {
            Toast.makeText(this, "Address is Empty", Toast.LENGTH_SHORT).show();
        }
    }

    public void getNotification(String order_id) {
        Log.d("confirmorder", "Nmethod");
        RetrofitApi api = BaseClient.getClient().create(RetrofitApi.class);
        Call<GetNotificationResponse> call = api.getNotification(order_id, "new order");
        call.enqueue(new Callback<GetNotificationResponse>() {
            @Override
            public void onResponse(Call<GetNotificationResponse> call, Response<GetNotificationResponse> response) {
                if (response.isSuccessful()) {

                } else {

                }
            }

            @Override
            public void onFailure(Call<GetNotificationResponse> call, Throwable t) {
                Toast.makeText(PaytmActivity.this, "Onfail", Toast.LENGTH_SHORT).show();

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
                    Toast.makeText(PaytmActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<StatusAndMessageModel> call, Throwable t) {
                Toast.makeText(PaytmActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}