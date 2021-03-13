package com.designurway.idlidosa.ui.home_page.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.designurway.idlidosa.a.adapters.ViewCartAdapter;
import com.designurway.idlidosa.a.model.ErrorMessageModel;
import com.designurway.idlidosa.a.model.GetNotificationResponse;
import com.designurway.idlidosa.a.model.GetTotalAmountModel;
import com.designurway.idlidosa.a.model.OrderStatusModel;
import com.designurway.idlidosa.a.model.ViewCartModel;
import com.designurway.idlidosa.a.model.ViewCartModelResponse;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.AndroidUtils;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.a.utils.SharedPrefManager;
import com.designurway.idlidosa.a.utils.UtilConstant;
import com.designurway.idlidosa.databinding.FragmentViewCartItemsBinding;
import com.designurway.idlidosa.ui.home_page.HomePageActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static easypay.manager.PaytmAssist.getContext;


public class ViewCartItemsFragment extends Fragment {
    private static final String TAG = "ViewCartItemsFragment";

    FragmentViewCartItemsBinding binding;
    NavDirections action;

    RecyclerView cartItemsRv;
    TextView txtRupee,txtNum,textSubTotal,txtNumTxt,rupeesTv;
    ImageView imgSadFace;
    String amount;
    Button btnCheck;
    String orderId = "none";
    ViewCartAdapter viewCartAdapter;
    int Total = 0;
    Context context;
    ConstraintLayout constraintRecycler;
    ProgressBar progressCart;
    int amt = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentViewCartItemsBinding.inflate(inflater,container,false);
        context=container.getContext();
        return binding.getRoot();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cartItemsRv = binding.cartItemsRv;
        txtRupee = binding.txtRupee;
        txtNum = binding.txtNum;
        imgSadFace=  binding.imgSadFace;
        btnCheck = binding.btnCheck;
        textSubTotal = binding.textSubTotal;
        txtNumTxt = binding.txtNumTxt;
        rupeesTv = binding.rupeesTv;
        constraintRecycler = binding.constraintRecycler;
        progressCart = binding.progressCart;

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount = txtRupee.getText().toString();

                    action = ViewCartItemsFragmentDirections.actionViewCartItemsFragmentToAddressBookFragment(amount,"viewCart",orderId);
                    Navigation.findNavController(getView()).navigate(action);

            }
        });

        GetCartItem();
        GetAmount();
        SharedPrefManager.Clear(getActivity());
        UtilConstant.clear=true;
    }
    public void GetCartItem() {
        GetAmount();
        RetrofitApi api = BaseClient.getClient().create(RetrofitApi.class);
        Call<ViewCartModelResponse> call = api.GetCartItem(PreferenceManager.getCustomerId());
        call.enqueue(new Callback<ViewCartModelResponse>() {
            @Override
            public void onResponse(Call<ViewCartModelResponse> call, Response<ViewCartModelResponse> response) {
                ArrayList<ViewCartModel> viewCartModel = response.body().getData();
                if (response.isSuccessful() && response.body().getStatus().equals("1")) {
                    constraintRecycler.setVisibility(View.VISIBLE);
                    progressCart.setVisibility(View.INVISIBLE);
                    orderId = viewCartModel.get(0).getOrder_id();

                    viewCartAdapter = new ViewCartAdapter(getContext(), viewCartModel);
                    cartItemsRv.setLayoutManager(new LinearLayoutManager(getContext()));

                    cartItemsRv.addItemDecoration(new DividerItemDecoration(context, LinearLayout.VERTICAL));
                    cartItemsRv.setAdapter(viewCartAdapter);
                    txtNum.setText(String.valueOf(viewCartModel.size()));
                    int count = viewCartModel.size();

                    SharedPrefManager.SaveTotalKey(context, count);

                  viewCartAdapter.sendToFragment(new ViewCartAdapter.setFragmentTransaction() {
                      @Override
                      public void sendPosition(String id,String OrderId, View view) {
                          deleteFromCart(OrderId, id);
                          GetCartItem();
                          viewCartAdapter.notifyDataSetChanged();


                      }

                      @Override
                      public void sendAmount(int amount) {

                          txtRupee.setText(String.valueOf(amount));
                      }
                  });

                } else {
                    btnCheck.setVisibility(View.VISIBLE);
                    cartItemsRv.setVisibility(View.GONE);
                    txtNum.setVisibility(View.GONE);
                    txtRupee.setVisibility(View.GONE);
                    textSubTotal.setVisibility(View.GONE);
                    rupeesTv.setVisibility(View.GONE);
                    rupeesTv.setVisibility(View.GONE);
                    btnCheck.setVisibility(View.GONE);
                    imgSadFace.setVisibility(View.VISIBLE);
                    txtNumTxt.setVisibility(View.GONE);
                    progressCart.setVisibility(View.INVISIBLE);
                    int count = 0;
                    SharedPrefManager.SaveTotalKey(context, count);

                }
            }

            @Override
            public void onFailure(Call<ViewCartModelResponse> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());

            }
        });

    }


    public void GetAmount() {
        RetrofitApi api = BaseClient.getClient().create(RetrofitApi.class);

        Call<GetTotalAmountModel> call = api.getAmount(PreferenceManager.getCustomerId());
        call.enqueue(new Callback<GetTotalAmountModel>() {
            @Override
            public void onResponse(Call<GetTotalAmountModel> call, Response<GetTotalAmountModel> response) {
                if (response.isSuccessful()) {
                    txtRupee.setText(response.body().getTotal_amount());

                } else {
                    Toast.makeText(context, "fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetTotalAmountModel> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void deleteFromCart(String order_id, String product_id) {
        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<ErrorMessageModel> call = retrofitApi.deleteCartItem(PreferenceManager.getCustomerId(), order_id, product_id);
        Log.d("cartAdapter", "order_id" + order_id);
        Log.d("cartAdapter", "prod_id" + product_id);
        call.enqueue(new Callback<ErrorMessageModel>() {
            @Override
            public void onResponse(Call<ErrorMessageModel> call, Response<ErrorMessageModel> response) {
                if (response.isSuccessful()) {


                    GetAmount();
                    GetCartItem();
                    viewCartAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "failed to delete", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ErrorMessageModel> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}