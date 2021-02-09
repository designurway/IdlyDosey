package com.designurway.idlidosa.ui.home_page.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.adapters.OrderHistoryAdapter;
import com.designurway.idlidosa.a.model.GetOrderHistoryResponseModel;
import com.designurway.idlidosa.a.model.GetorderHistoryModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.databinding.FragmentOrderHistoryBinding;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderHistoryFragment extends Fragment {

    private static final String TAG ="OrderHistoryFragment";
    FragmentOrderHistoryBinding binding;
    NavDirections action;
    RecyclerView orderHistoryRv;
    TextView cartTotalTv;
    ImageView imgSadFace;

    OrderHistoryAdapter orderHistoryAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding = FragmentOrderHistoryBinding.inflate(inflater,container,false);
       return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        orderHistoryRv = binding.orderHistoryRv;
        cartTotalTv = binding.cartTotalTv;
        imgSadFace = binding.imgSadFace;

        getOrderHistory();
    }

    public void getOrderHistory() {

        RetrofitApi api = BaseClient.getClient().create(RetrofitApi.class);
        Call<GetOrderHistoryResponseModel> call = api.GetOrderHistory(PreferenceManager.getCustomerId());
        call.enqueue(new Callback<GetOrderHistoryResponseModel>() {
            @Override
            public void onResponse(Call<GetOrderHistoryResponseModel> call, Response<GetOrderHistoryResponseModel> response) {
                GetOrderHistoryResponseModel getOrderHistoryResponseModel = response.body();
                ArrayList<GetorderHistoryModel> getorderHistoryModels = getOrderHistoryResponseModel.getData();

                if (response.isSuccessful()  && response.body().getMessage().contains("Successful")) {

                    orderHistoryAdapter = new OrderHistoryAdapter(getorderHistoryModels, getContext());
                    cartTotalTv.setText(String.valueOf(getorderHistoryModels.size()));
                    orderHistoryRv.setLayoutManager(new LinearLayoutManager(getContext()));
                    orderHistoryRv.setAdapter(orderHistoryAdapter);

                } else {
                    imgSadFace.setVisibility(View.VISIBLE);
                    orderHistoryRv.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<GetOrderHistoryResponseModel> call, Throwable t) {
                Log.d(TAG,"onFailure"+t.getMessage());

            }
        });
    }
}