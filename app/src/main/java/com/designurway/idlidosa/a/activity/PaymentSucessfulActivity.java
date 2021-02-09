package com.designurway.idlidosa.a.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.model.PaymentSucessfulModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.ui.home_page.HomePageActivity;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentSucessfulActivity extends AppCompatActivity {

    TextView transactionID;
    Button paymentSucessBtn;
    String customer_id, bank_name,bank_transaction_id,currency_type,gateway,mid,order_id,payment_mode,
            resp_code,resp_message,status,transaction_amount,transaction_date,transaction_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_sucessful);

        transactionID = findViewById(R.id.transactionID);
        paymentSucessBtn = findViewById(R.id.paymentSucessBtn);


        Bundle bundle = getIntent().getExtras();
        String data = bundle.getString("dataa");
//        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
        Log.d("dataa",data);

        try {
            JSONObject jsonObject = new JSONObject(data);
            customer_id = "50 ";
            bank_name = jsonObject.getString("BANKNAME");
            bank_transaction_id = jsonObject.getString("BANKTXNID");
            currency_type = jsonObject.getString("CURRENCY");
            gateway = jsonObject.getString("GATEWAYNAME");
            mid = jsonObject.getString("MID");
            order_id = jsonObject.getString("ORDERID");
            payment_mode = jsonObject.getString("PAYMENTMODE");
            resp_code = jsonObject.getString("RESPCODE");
            resp_message = jsonObject.getString("RESPMSG");
            status = jsonObject.getString("STATUS");
            transaction_amount = jsonObject.getString("TXNAMOUNT");
            transaction_date = jsonObject.getString("TXNDATE");
            transaction_id = jsonObject.getString("TXNID");
            transactionID.setText(transaction_id);



//            Toast.makeText(this, "BANKTXNID : "+bank_transaction_id,
//                    Toast.LENGTH_SHORT).show();
//            transactionID.setText(bank_transaction_id);
            Log.d("dataa","Json : "+bank_transaction_id);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("dataa","Malformed JSON : "+e.getMessage());
        }

        postPaymentDetails();

        paymentSucessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PaymentSucessfulActivity.this, HomePageActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void postPaymentDetails() {
        RetrofitApi api = BaseClient.getClient().create(RetrofitApi.class);
        Call<PaymentSucessfulModel> call = api.postPaymentDetails(
                customer_id,bank_name,bank_transaction_id,currency_type,gateway,
                mid,order_id,payment_mode,resp_code, resp_message,status,transaction_amount,
                transaction_date,transaction_id);

        call.enqueue(new Callback<PaymentSucessfulModel>() {
            @Override
            public void onResponse(Call<PaymentSucessfulModel> call, Response<PaymentSucessfulModel> response) {
                PaymentSucessfulModel paymentSucessfulResponse = response.body();

                if (response.isSuccessful() ){
                    if ( paymentSucessfulResponse.getStatus().contains("1")){
//                        Toast.makeText(PaymentSucessfulActivity.this, "Data Inserted Succesfully", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(PaymentSucessfulActivity.this, "Message : "+paymentSucessfulResponse.getMessage() +" Status : "+paymentSucessfulResponse.getStatus(), Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(PaymentSucessfulActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PaymentSucessfulModel> call, Throwable t) {

                Toast.makeText(PaymentSucessfulActivity.this, "On Failure"+ t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}