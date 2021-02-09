package com.designurway.idlidosa.ui.home_page.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.activity.PaymentSucessfulActivity;
import com.designurway.idlidosa.a.model.ErrorMessageModel;
import com.designurway.idlidosa.a.model.GetNotificationResponse;
import com.designurway.idlidosa.a.model.OrderStatusModel;
import com.designurway.idlidosa.a.paytmallinonesdk.PaytmActivity;
import com.designurway.idlidosa.a.paytmallinonesdk.ServiceWrapper;
import com.designurway.idlidosa.a.paytmallinonesdk.Token_Res;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.AndroidUtils;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.databinding.FragmentPaymentBinding;
import com.designurway.idlidosa.model.CustomerAddress;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;
import com.paytm.pgsdk.TransactionManager;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PaymentFragment extends Fragment {

    FragmentPaymentBinding binding;
    NavDirections action;
    PaymentFragmentArgs args;
    String name, address, amount, phone;
    private final String TAG = "MainActivity";
    private final String midString = "uHykBm20360288053432";
    private String txnAmountString = "";
    private String orderIdString = "";
    private String txnTokenString = "";
    private final Integer ActivityRequestCode = 2;
    TextView totalAmount, textSubtotal, txtName, txtMobile, textAddress, orderId;
    Button btnPayment;
    String jsonString;
    PreferenceManager preferenceManager;
    JSONObject jsonObject;
    String comboId, productId, OrderID;
    BottomSheetDialog bottomSheetDialog;

    public PaymentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPaymentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        args = PaymentFragmentArgs.fromBundle(getArguments());
        name = args.getName();
        address = args.getAddress();
        amount = args.getAmount();
        phone = args.getPhone();
        comboId = args.getComboId();
        productId = args.getProductId();
        OrderID = args.getOrderId();

        orderId = binding.orderId;


        totalAmount = binding.totalAmount;
        textSubtotal = binding.textSubtotal;
        txtName = binding.txtName;
        txtMobile = binding.txtMobile;
        textAddress = binding.textAddress;
        btnPayment = binding.btnPayment;

/*        if (!orderId.equals("none")) {
            btnPayment.setText("Place Order");

            textAddress.setText(address);
            txtMobile.setText(phone);
            orderId.setText(OrderID);
        }
        */
        if (amount.equals("0")){
            btnPayment.setText("Place Order");

            textAddress.setText(address);
            txtMobile.setText(phone);
            orderId.setText(OrderID);
        }


        if (!comboId.equals("none")) {
            String generateOrderId = AndroidUtils.randomName(5);

            btnPayment.setText("Redeem Combo");

            textAddress.setText(address);
            txtMobile.setText(phone);
            orderId.setText(generateOrderId);

        }


        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
        String date = df.format(c.getTime());
        Random rand = new Random();
        int min = 1000, max = 9999;
// nextInt as provided by Random is exclusive of the top value so you need to add 1
        int randomNum = rand.nextInt((max - min) + 1) + min;
        orderIdString = date + randomNum;

        preferenceManager = new PreferenceManager();

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!comboId.equals("none") || amount.equals("0")) {
                    String Oid = orderId.getText().toString().trim();
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    if (!comboId.equals("none")) {
                        builder.setMessage("Are you sure you want to redeem your combo?");
                    } else {
                        builder.setMessage("Are you sure you want to place the order");
                    }
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if (!comboId.equals("none")) {
                                postItems(comboId, productId, Oid);
                            } else {
                                ConfromOrder();
                            }


                            action = PaymentFragmentDirections.actionPaymentFragmentToPaymentSucessfulFragment("none");
                            Navigation.findNavController(getView()).navigate(action);

                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {

                    ImageView google_pay_img, paytm_img;
                    Button cancel_btn;

                     bottomSheetDialog = new BottomSheetDialog(getContext());
                    bottomSheetDialog.setContentView(R.layout.bottom_sheet);
                    bottomSheetDialog.setCanceledOnTouchOutside(false);

                    google_pay_img = bottomSheetDialog.findViewById(R.id.google_pay_img);
                    paytm_img = bottomSheetDialog.findViewById(R.id.paytm_img);
                    cancel_btn = bottomSheetDialog.findViewById(R.id.cancel_btn);

                    google_pay_img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String OrderId = AndroidUtils.randomName(5);
                            action = PaymentFragmentDirections.actionPaymentFragmentToGooglePayFragment(OrderId, amount);
                            Navigation.findNavController(getView()).navigate(action);
                            bottomSheetDialog.dismiss();

                        }
                    });

                    paytm_img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Bundle bundle = new Bundle();
                            Intent intent = new Intent(getActivity(), PaytmActivity.class);
                            bundle.putString("amount",amount);
                            bundle.putString("address",address);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });

                    cancel_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomSheetDialog.dismiss();
                        }
                    });

                    bottomSheetDialog.show();


                }
            }
        });


        txtName.setText(name);
        textAddress.setText(address);

        txtMobile.setText(phone);
//        total_amount.setText(customerAddress.getCityAddress());
        totalAmount.setText(amount);
        textSubtotal.setText(amount);


    }

    private void getToken() {
        Log.e(TAG, " get token start");
//        progressBar.setVisibility(View.VISIBLE);
        ServiceWrapper serviceWrapper = new ServiceWrapper(null);
        Call<Token_Res> call = serviceWrapper.getTokenCall("12345", midString, orderIdString, "1");
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
                            Log.e(TAG, " Token status false");
                        }
                    }
                } catch (Exception e) {
                    Log.e(TAG, " error in Token Res " + e.toString());
                }
            }

            @Override
            public void onFailure(@NotNull Call<Token_Res> call, Throwable t) {
//                progressBar.setVisibility(View.GONE);
                Log.e(TAG, " response error " + t.toString());
            }
        });

    }

    private void startPaytmPayment(String txnToken) {
        bottomSheetDialog.dismiss();
        txnTokenString = txnToken;
        String host = "https://securegw.paytm.in/";
        String orderDetails = "MID: " + midString + ", OrderId: " + orderIdString + ", TxnToken: " + txnTokenString
                + ", Amount: " + txnAmountString;
        //Log.e(TAG, "order details "+ orderDetails);

        String callBackUrl = host + "theia/paytmCallback?ORDER_ID="+orderIdString;
        PaytmOrder paytmOrder = new PaytmOrder(orderIdString, midString, txnTokenString, txnAmountString, callBackUrl);
        TransactionManager transactionManager = new TransactionManager(paytmOrder, new PaytmPaymentTransactionCallback(){
            @Override
            public void onTransactionResponse(Bundle bundle) {
                Toast.makeText(getContext(), "onTransactionResponse : "+bundle.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG,"onTransactionResponse : "+bundle.toString());
            }

            @Override
            public void networkNotAvailable() {
                Toast.makeText(getContext(), "networkNotAvailable : ", Toast.LENGTH_SHORT).show();
                Log.d(TAG,"networkNotAvailable : ");
            }

            @Override
            public void onErrorProceed(String s) {
                Toast.makeText(getContext(), "onErrorProceed : "+s, Toast.LENGTH_SHORT).show();
                Log.d(TAG,"onErrorProceed : "+s);
            }

            @Override
            public void clientAuthenticationFailed(String s) {
                Toast.makeText(getContext(), "clientAuthenticationFailed : "+s, Toast.LENGTH_SHORT).show();
                Log.d(TAG,"clientAuthenticationFailed : "+s);
            }

            @Override
            public void someUIErrorOccurred(String s) {
                Toast.makeText(getContext(), "someUIErrorOccurred : "+s, Toast.LENGTH_SHORT).show();
                Log.d(TAG,"someUIErrorOccurred : "+s);
            }

            @Override
            public void onErrorLoadingWebPage(int i, String s, String s1) {
                Toast.makeText(getContext(), "onErrorLoadingWebPage : "+" s "+s +" S1 : "+s1, Toast.LENGTH_SHORT).show();
                Log.d(TAG,"onErrorLoadingWebPage : "+" s "+s +" S1 : "+s1);
            }

            @Override
            public void onBackPressedCancelTransaction() {
                Toast.makeText(getContext(), "onBackPressedCancelTransaction : ", Toast.LENGTH_SHORT).show();
                Log.d(TAG,"onBackPressedCancelTransaction : ");
            }

            @Override
            public void onTransactionCancel(String s, Bundle bundle) {
                Toast.makeText(getContext(), "onTransactionCancel : "+" s "+s +" Bundle : "+bundle.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG,"onTransactionCancel : "+s +" Bundle : "+bundle.toString());
            }
        });


        transactionManager.setShowPaymentUrl(host + "theia/api/v1/showPaymentPage");
        transactionManager.startTransaction(getActivity(), ActivityRequestCode);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ActivityRequestCode && data != null) {
            Toast.makeText(getActivity(), data.getStringExtra("nativeSdkForMerchantMessage") + data.getStringExtra("response"), Toast.LENGTH_SHORT).show();
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
                    Log.d("confirmorder", "Nresponsemethod");
                    Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                } else {

                }
            }

            @Override
            public void onFailure(Call<GetNotificationResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Onfail", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void postComboWonDetails() {
        Log.d(TAG, "postCombo");
        String totalAmount = amount;
        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<ErrorMessageModel> call = retrofitApi.updateComboWonDetails(AndroidUtils.randomName(5),
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

    private void postItems(String ComboId, String productId, String Oid) {
        String addressTv = textAddress.getText().toString().trim();
        com.designurway.idlidosa.retrofit.RetrofitApi retrofitApi = com.designurway.idlidosa.retrofit.BaseClient.getClient().create(com.designurway.idlidosa.retrofit.RetrofitApi.class);
        Call<com.designurway.idlidosa.model.ErrorMessageModel> call = retrofitApi.postComboWonDetails(PreferenceManager.getCustomerId(),
                Oid,
                addressTv,
                productId,
                ComboId);
        Log.d(TAG, "customerId" + PreferenceManager.getCustomerId());
        Log.d(TAG, "order_id" + Oid);
        Log.d(TAG, "addressTv" + addressTv);
        Log.d(TAG, "productId" + productId);
        Log.d(TAG, "ComboId" + ComboId);
        call.enqueue(new Callback<com.designurway.idlidosa.model.ErrorMessageModel>() {
            @Override
            public void onResponse(Call<com.designurway.idlidosa.model.ErrorMessageModel> call, Response<com.designurway.idlidosa.model.ErrorMessageModel> response) {
                if (response.isSuccessful()) {


                    getNotification(Oid);

                } else {
                    Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<com.designurway.idlidosa.model.ErrorMessageModel> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());

            }
        });
    }

    public void ConfromOrder() {
        Log.d("confirmorder", "method");
        String order_id = AndroidUtils.randomName(5);
        RetrofitApi api = BaseClient.getClient().create(RetrofitApi.class);

        Call<OrderStatusModel>
                call = api.postOrderDetails(PreferenceManager.getCustomerId(), order_id, amount, address, "1111", "1111", "1111");
        Toast.makeText(getContext(), PreferenceManager.getCustomerId(), Toast.LENGTH_SHORT).show();
        call.enqueue(new Callback<OrderStatusModel>() {

            @Override
            public void onResponse(Call<OrderStatusModel> call, Response<OrderStatusModel> response) {
                Log.d("confirmorder", "success");
                if (response.isSuccessful()) {
                    Log.d("confirmorder", "success");
                    OrderStatusModel orderStatusModel = response.body();

                    getNotification(order_id);

                    Toast.makeText(getContext(), orderStatusModel.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<OrderStatusModel> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());


            }
        });
    }
}