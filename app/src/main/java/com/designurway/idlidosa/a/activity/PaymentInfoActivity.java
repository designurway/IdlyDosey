package com.designurway.idlidosa.a.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.designurway.idlidosa.R;

import java.util.ArrayList;

public class PaymentInfoActivity extends AppCompatActivity {
    EditText user_name_et,amount_et,upi_et,complete_address_et;
    Button proceed_btn;

    final int UPI_PAYMENT=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_info);

        initializeViews();

        proceed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount=amount_et.getText().toString();
                String userName=user_name_et.getText().toString();
                String upiID=upi_et.getText().toString();
                String address=complete_address_et.getText().toString();
                paymentUsingUpi(amount,userName,upiID,address);


            }
        });

    }

    void initializeViews(){
        proceed_btn=findViewById(R.id.proceed_btn);
        user_name_et=findViewById(R.id.user_name_et);
        amount_et=findViewById(R.id.amount_et);
        upi_et=findViewById(R.id.upi_et);
        complete_address_et=findViewById(R.id.complete_address_et);
    }

    void paymentUsingUpi(String amount,String userName,String upiID,String address){
        Uri uri=Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa",upiID)
                .appendQueryParameter("pn",userName)
                .appendQueryParameter("tn",address)
                .appendQueryParameter("am",amount)
                .appendQueryParameter("cu","INR")
                .build();

        Intent upiPayIntent= new Intent(Intent.ACTION_VIEW);
        upiPayIntent.setData(uri);

        Intent chooser=Intent.createChooser(upiPayIntent,"Pay with");

        if(null!=chooser.resolveActivity(getPackageManager())){
            startActivityForResult(chooser,UPI_PAYMENT);
        } else{
            Toast.makeText(PaymentInfoActivity.this,"No UPI app found, please install one to continue",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        switch (requestCode){
            case UPI_PAYMENT:
                if((RESULT_OK==resultCode) || (resultCode==11)) {
                    if(data!=null){
                        String trxt=data.getStringExtra("response");
                        Log.d("tag","onActivityResult:"+trxt);
                        ArrayList<String> dataList= new ArrayList<>();
                        dataList.add(trxt);
                        upiPaymentDataOperation(dataList);
                    } else {
                        Log.d("tag","onActivityResult:"+"Return data is null");
                        ArrayList<String> dataList= new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentDataOperation(dataList);
                    }
                } else {
                    Log.d("tag","onActivityResult:"+"Return data is null");
                    ArrayList<String> dataList= new ArrayList<>();
                    dataList.add("nothing");
                    upiPaymentDataOperation(dataList);
                }
                break;
        }
    }
    private void upiPaymentDataOperation(ArrayList<String> data){
        if(isConnectionAvailable(PaymentInfoActivity.this)){
            String str=data.get(0);
            Log.d("UPIPAY","upiPaymentDataoperation:"+str);
            String paymentCancel="";
            if(str==null) str="discard";
            String status="";
            String approvalRefno="";
            String[] response =str.split("&");
            for (int i=0;i<response.length;i++){
                String[] equalStr =response[i].split("m");
                if(equalStr.length>=2){
                    if(equalStr[0].toLowerCase().equals("status".toLowerCase())){
                        status=equalStr[1].toLowerCase();
                    }
                    else if(equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())){
                        approvalRefno=equalStr[1];
                    }
                }
                else{
                    paymentCancel="payment cancelled by user.";
                }
            }
            if(status.equals("success")){
                Toast.makeText(PaymentInfoActivity.this,"trancastion successful",Toast.LENGTH_SHORT).show();
                Log.d("UPI","responseStr:"+approvalRefno);
            }
            else if("payment cancelled by user.".equals(paymentCancel)){
                Toast.makeText(PaymentInfoActivity.this,"payment cancelled by user",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(PaymentInfoActivity.this,"Transaction Failed, please try again",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(PaymentInfoActivity.this,"No internet, check again",Toast.LENGTH_SHORT).show();
        }
    }
    public static boolean isConnectionAvailable(Context context){
        ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager!=null){
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected() && networkInfo.isConnectedOrConnecting() && networkInfo.isAvailable();
        }
        return false;
    }

}