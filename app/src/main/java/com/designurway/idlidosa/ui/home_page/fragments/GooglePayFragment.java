package com.designurway.idlidosa.ui.home_page.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.databinding.FragmentGooglePayBinding;
import com.designurway.idlidosa.model.PaymentModel;
import com.designurway.idlidosa.model.StatusMessageModel;
import com.designurway.idlidosa.retrofit.BaseClient;
import com.designurway.idlidosa.retrofit.RetrofitApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class GooglePayFragment extends Fragment {

    FragmentGooglePayBinding binding;
    GooglePayFragmentArgs args;

    ImageView arrowImg;
    TextView amountEt, upiEt;
    //    EditText  note, name;
    Button payBtn;
    String TAG = "main";
    final int UPI_PAYMENT = 0;
    String orderId, amount;
    CircleImageView personPayImgv;


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

        arrowImg = binding.arrowImg;
        amountEt = binding.amountEt;
        upiEt = binding.upiEt;
        payBtn = binding.payBtn;
        personPayImgv = binding.personPayImgv;

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
                payUsingUpi(PreferenceManager.getCustomerName(), upiEt.getText().toString(),
                        "IDLY DOSEY", amountEt.getText().toString());
            }
        });

        goTopayment();
        setImage();
    }

    void payUsingUpi(  String name,String upiId, String note,String amount) {
        Log.e("main ", "--up--"+upiId+"--"+amount);
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
        if(null != chooser.resolveActivity(getActivity().getPackageManager())) {
            startActivityForResult(chooser, UPI_PAYMENT);
        } else {
            Toast.makeText(getContext(),"No UPI app found, please install one to continue",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("main ", "response "+resultCode );
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
            Log.e("UPIPAY", "upiPaymentDataOperation: "+str);
            String paymentCancel = "";
            if(str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if(equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    }
                    else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];
                    }
                }
                else {
                    paymentCancel = "Payment cancelled by user.";
                }
            }
            if (status.equals("success")) {
                try {
                    JSONObject result=new JSONObject(status);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Code to handle successful transaction here.
                Toast.makeText(getContext(), "Transaction successful.", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "payment successfull: "+approvalRefNo);
            }
            else if("Payment cancelled by user.".equals(paymentCancel)) {
                Toast.makeText(getContext(), "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "Cancelled by user: "+approvalRefNo);
            }
            else {
                Toast.makeText(getContext(), "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
                Log.e("UPI", "failed payment: "+approvalRefNo);
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

    public void goTopayment(){

        RetrofitApi retrofitApi= BaseClient.getClient().create(RetrofitApi.class);
        Call<PaymentModel> call=retrofitApi.payment(PreferenceManager.getCustomerId(),orderId);

        call.enqueue(new Callback<PaymentModel>() {
            @Override
            public void onResponse(Call<PaymentModel> call, Response<PaymentModel> response) {
                if (response.isSuccessful()){


                    amountEt.setText(amount);
                }else{
                    Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PaymentModel> call, Throwable t) {
                Toast.makeText(getContext(), "ON Failure "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void setImage(){
        RetrofitApi retrofitApi=BaseClient.getClient().create(RetrofitApi.class);
        Call<StatusMessageModel> call=retrofitApi.image(PreferenceManager.getCustomerPhone());
        call.enqueue(new Callback<StatusMessageModel>() {
            @Override
            public void onResponse(Call<StatusMessageModel> call, Response<StatusMessageModel> response) {
                if (response.isSuccessful()){
                    StatusMessageModel statusMessageModel=response.body();
                    Toast.makeText(getContext(), statusMessageModel.getImage(), Toast.LENGTH_SHORT).show();
                    if (statusMessageModel.getImage().isEmpty()) {
//                        Picasso.with(getContext()).load(statusMessageModel.getImage()).into(person_pay_imgv);
                    }else{
//                        Picasso.with(getContext()).load(statusMessageModel.getImage()).into(person_pay_imgv);
                        Glide.with(getContext()).load(statusMessageModel.getImage()).into(personPayImgv);
                    }
                }else{
                    Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StatusMessageModel> call, Throwable t) {
                Toast.makeText(getContext(), "On Failure"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}