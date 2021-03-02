package com.designurway.idlidosa.ui.home_page.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.adapters.ReceivedComboAdapter;
import com.designurway.idlidosa.a.model.ReceivedComboDataModel;
import com.designurway.idlidosa.a.model.ReceivedComboModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.databinding.FragmentReceiveComboBinding;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReceiveComboFragment extends Fragment {
    private static final String TAG = "ReceiveComboFragment";
    FragmentReceiveComboBinding binding;
    RecyclerView rewardsRv;

    ImageView imgIv;

    ImageView imgSadFace;
    RelativeLayout headerRl;
    NavDirections actions;

    ArrayList<ReceivedComboModel> receivedComboModels;
    ReceivedComboAdapter receivedComboAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReceiveComboBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rewardsRv = binding.rewardsRv;
        imgSadFace = binding.imgSadFace;
        headerRl = binding.headerRl;

        setComboWonDetails();
    }





    private void setComboWonDetails() {
        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<ReceivedComboDataModel> call = retrofitApi.getComboWonDetails(PreferenceManager.getCustomeReferenceCode());
        Log.d(TAG, "preference" + PreferenceManager.getCustomeReferenceCode());
        Log.d(TAG, "referralCode" + PreferenceManager.getReferred_from());
        call.enqueue(new Callback<ReceivedComboDataModel>() {
            @Override
            public void onResponse(Call<ReceivedComboDataModel> call, Response<ReceivedComboDataModel> response) {
                if (response.isSuccessful() && response.body().getMessage().contains("success")) {
                    ReceivedComboDataModel receivedComboDataModel = response.body();
                    ArrayList<ReceivedComboModel> receivedComboModels = receivedComboDataModel.getData();
                    receivedComboAdapter = new ReceivedComboAdapter(receivedComboModels, getContext());
                    rewardsRv.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    rewardsRv.setAdapter(receivedComboAdapter);

                    receivedComboAdapter.sendToFragment(new ReceivedComboAdapter.setFragmentTransaction() {
                        @Override
                        public void sendPosition(int position, String comboId, String productid) {
                            actions = ReceiveComboFragmentDirections.actionReceiveComboFragmentToReciveComboAddressFragment(comboId, productid);
                            Navigation.findNavController(getView()).navigate(actions);
                        }
                    });
                } else {
                    imgSadFace.setVisibility(View.VISIBLE);
                    rewardsRv.setVisibility(View.GONE);
                    headerRl.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(Call<ReceivedComboDataModel> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());
            }
        });

    }


}