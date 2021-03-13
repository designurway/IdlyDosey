package com.designurway.idlidosa.ui.home_page.fragments;

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
import android.widget.TextView;
import android.widget.Toast;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.activity.PaymentSucessfulActivity;
import com.designurway.idlidosa.a.model.PaymentSucessfulModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.databinding.FragmentPaymentSucessfulBinding;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PaymentSucessfulFragment extends Fragment {

    PaymentSucessfulFragmentArgs args;
    NavDirections action;
    FragmentPaymentSucessfulBinding binding;
    String jsonString;
    TextView transactionID;
    Button paymentSucessBtn;


    public PaymentSucessfulFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPaymentSucessfulBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments()!=null){

            args = PaymentSucessfulFragmentArgs.fromBundle(getArguments());
            jsonString = args.getJsonString();

        }


        transactionID = binding.transactionID;
        paymentSucessBtn = binding.paymentSucessBtn;


        paymentSucessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


             action = PaymentSucessfulFragmentDirections.actionPaymentSucessfulFragmentToHomeFragment();
             Navigation.findNavController(getView()).navigate(action);


            }
        });


    }

}