package com.designurway.idlidosa.ui.home_page.fragments;

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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.adapters.PraductRrecyclerAdaprter;
import com.designurway.idlidosa.a.model.ErrorMessageModel;
import com.designurway.idlidosa.a.model.MenuDetailsDataModel;
import com.designurway.idlidosa.a.model.MenuDetailsModel;

import com.designurway.idlidosa.a.model.StatusAndMessageModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.AndroidUtils;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.a.utils.SharedPrefManager;
import com.designurway.idlidosa.databinding.FragmentProductDetailfragmentBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductDetailfragment extends Fragment {
    private static final String TAG = "ProductDetailFragment";
    PraductRrecyclerAdaprter adapter;
    FragmentProductDetailfragmentBinding binding;
    ProductDetailfragmentArgs args;

    String id;
    ImageView dishImage;
    TextView txtItemName;
    TextView rupee;
    TextView txtDescription;
    ImageView plusImgIv;
    ImageView minusImgIv;
    TextView itemQtyTv;
    Button btnAddTocart;
    LinearLayout linearButtonDetail;
    ProgressBar progressBar;


    int count = 0;
    int amount = 0;
    String pro_id = null,present;
    String orderid=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProductDetailfragmentBinding.inflate(inflater,container,false);
        return binding.getRoot();

        /*View view = inflater.inflate(R.layout.fragment_product_detailfragment, container,
                false);
        ButterKnife.bind(this, view);



        setLisner();
        Bundle bundle = getArguments();
        if (bundle != null) {
            pro_id=bundle.getString("pro_id");
            present=bundle.getString("present");
            btn_addTocart.setText("Already In Cart");
            linear_button.setVisibility(View.GONE);

        }


        setMenuDetails();
        return view;*/

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dishImage = binding.dishImage;
        progressBar = binding.progressBar;
        linearButtonDetail = binding.linearButtonDetail;
        btnAddTocart = binding.btnAddTocart;
        itemQtyTv = binding.itemQtyTv;
        minusImgIv = binding.minusImgIv;
        plusImgIv = binding.plusImgIv;
        txtDescription = binding.txtDescription;
        rupee = binding.rupee;
        txtItemName = binding.txtItemName;

        args = ProductDetailfragmentArgs.fromBundle(getArguments());
        pro_id = args.getProductId();
        present = args.getPresent();

        progressBar.setVisibility(View.VISIBLE);
        setMenuDetails();
    }

    private void setMenuDetails() {

        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<MenuDetailsDataModel> call = retrofitApi.setMenuDetails(pro_id);
        call.enqueue(new Callback<MenuDetailsDataModel>() {
            @Override
            public void onResponse(Call<MenuDetailsDataModel> call, Response<MenuDetailsDataModel> response) {
                MenuDetailsDataModel detailsDataModel = response.body();
                ArrayList<MenuDetailsModel> menumodel = detailsDataModel.getData();
                if (response.isSuccessful() && detailsDataModel.getStatus().equals("1")) {
                    progressBar.setVisibility(View.GONE);
                    adapter = new PraductRrecyclerAdaprter(menumodel, getContext());
                    txtItemName.setText(menumodel.get(0).getName());
                    rupee.setText(menumodel.get(0).getPrice());
                    txtDescription.setText(menumodel.get(0).getDescription());
                    amount = Integer.valueOf(menumodel.get(0).getPrice());
                    Log.d("printamount",menumodel.get(0).getPrice());
                    Picasso.get().load(menumodel.get(0).getImage()).into(dishImage);

                } else {
                    Toast.makeText(getContext(), "No Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MenuDetailsDataModel> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());

            }
        });

    }

    public void setLisner() {
        plusImgIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                itemQtyTv.setText(String.valueOf(count));
                postCartQuantity(String.valueOf(count),String.valueOf(amount*count));
                rupee.setText(String.valueOf(amount * count));
            }
        });
        minusImgIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (count > 0) {
                    count--;
                    itemQtyTv.setText(String.valueOf(count));
                    postCartQuantity(String.valueOf(count),String.valueOf(amount*count));
                    rupee.setText(String.valueOf(amount * count));
                }
            }
        });

        btnAddTocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (present.equals("yes")){
                    linearButtonDetail.setVisibility(View.GONE);
                }else if(btnAddTocart.getText().toString().equals("Already In Cart")){
                    Toast.makeText(getContext(), "Already in Cart", Toast.LENGTH_SHORT).show();
                    linearButtonDetail.setVisibility(View.GONE);
                }else{
                    linearButtonDetail.setVisibility(View.VISIBLE);
                }
                AddToCart(String.valueOf(amount), String.valueOf(count));
            }
        });

    }

    public void postCartQuantity(String qnty,String amount) {


        RetrofitApi api = BaseClient.getClient().create(RetrofitApi.class);
        Log.d("printamount",amount);
        Call<ErrorMessageModel> call = api.postCartQuantity(PreferenceManager.getCustomerId(), pro_id, orderid,amount,qnty);

        call.enqueue(new Callback<ErrorMessageModel>() {
            @Override
            public void onResponse(Call<ErrorMessageModel> call, Response<ErrorMessageModel> response) {
                if (response.isSuccessful()) {

                } else {
                    Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ErrorMessageModel> call, Throwable t) {

                Log.d(TAG, "onFailure" + t.getMessage());
            }
        });
    }

    public void AddToCart(String amount, String quantity) {
        int count= SharedPrefManager.loadFrompref(getContext());
        count++;
        SharedPrefManager.SaveTotalKey(getContext(),count);
        String order_id= AndroidUtils.randomName(5);
        orderid=order_id;
        RetrofitApi api = BaseClient.getClient().create(RetrofitApi.class);
//        Log.d("customer_id","customer_id"+PreferenceManager.getCustomerId());
        Call<StatusAndMessageModel> call = api.AddtoCart(PreferenceManager.getCustomerId(), pro_id,
                amount,order_id ,quantity);

        call.enqueue(new Callback<StatusAndMessageModel>() {
            @Override
            public void onResponse(Call<StatusAndMessageModel> call, Response<StatusAndMessageModel> response) {

                    orderid=response.body().getOrder_id();

            }

            @Override
            public void onFailure(Call<StatusAndMessageModel> call, Throwable t) {
                Log.d(TAG,"onFailure"+t.getMessage());
            }
        });

    }




}