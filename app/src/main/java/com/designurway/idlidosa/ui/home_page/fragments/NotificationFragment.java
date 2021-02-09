package com.designurway.idlidosa.ui.home_page.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.adapters.NotificationListAdapter;
import com.designurway.idlidosa.a.model.NotificationListData;
import com.designurway.idlidosa.a.model.NotificationListModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.PreferenceManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationFragment extends Fragment {
    NotificationListAdapter notificationListAdapter;
    RecyclerView noificationListRecycler;
  ImageView img_sad_face;
    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_notification, container, false);

        noificationListRecycler = view.findViewById(R.id.noificationListRecycler);
        img_sad_face = view.findViewById(R.id.img_sad_face);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        noificationListRecycler.setLayoutManager(layoutManager);
        getNotificationList();
        return  view;
    }

    private void getNotificationList() {

        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<NotificationListModel> call = retrofitApi.getNotificationList(PreferenceManager.getCustomerId());

        call.enqueue(new Callback<NotificationListModel>() {
            @Override
            public void onResponse(Call<NotificationListModel> call, Response<NotificationListModel> response) {
                if (response.isSuccessful()){
                    NotificationListModel notificationListModel = response.body();
                    if (notificationListModel.getStatus().equals("1")){
                        List<NotificationListData> notificationListData = notificationListModel.getData();
                        notificationListAdapter = new NotificationListAdapter(notificationListData,getContext());
                        noificationListRecycler.setAdapter(notificationListAdapter);
                    }else {
                        Toast.makeText(getContext(),notificationListModel.getMessage() , Toast.LENGTH_SHORT).show();
                        img_sad_face.setVisibility(View.VISIBLE);
                    }
                }else {
                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NotificationListModel> call, Throwable t) {
                Toast.makeText(getContext(), "On Failure "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}