package com.designurway.idlidosa.ui.home_page.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.adapters.NotificationListAdapter;
import com.designurway.idlidosa.a.model.NotificationListResponseModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.model.NotificationListModel;
import com.designurway.idlidosa.model.StatusAndMessageModel;
import com.designurway.idlidosa.model.StatusMessageModel;
import com.designurway.idlidosa.retrofit.RetrofitApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationListFragment extends Fragment {

    NotificationListAdapter notificationAdapter;
    RecyclerView notification_rv;
    ImageView img_sad_face;

    public NotificationListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification_list, container, false);
        notification_rv = view.findViewById(R.id.noificationListRecycler);
        img_sad_face = view.findViewById(R.id.img_sad_face);
        notification_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        getNotificationList();
        return view;
    }


    public void getNotificationList() {

        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);

        Call<NotificationListModel> call = retrofitApi.getNotificationforCustomer(PreferenceManager.getCustomerId());

        call.enqueue(new Callback<NotificationListModel>() {
            @Override
            public void onResponse(Call<NotificationListModel> call, Response<NotificationListModel> response) {

                if (response.isSuccessful() && response.body().getStatus().equals("1")) {
                    notificationAdapter = new NotificationListAdapter(response.body().getData(), getActivity());
                    notification_rv.setAdapter(notificationAdapter);
                    notificationAdapter.sendToFragment(new NotificationListAdapter.setOnFragmentTransaction() {
                        @Override
                        public void sendPosition(int position, String orderId, View view) {
                            updateUnreadRead(orderId);
                            getNotificationList();
                        }

                        @Override
                        public void sendView(int position, String orderId, View view) {

                            deleteNotification(orderId);
                            getNotificationList();
                            notificationAdapter.notifyDataSetChanged();
                        }


                    });
                } else {

                    img_sad_face.setVisibility(View.VISIBLE);
                    notification_rv.setVisibility(View.GONE);

                }
            }


            @Override
            public void onFailure(Call<NotificationListModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Onfail", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void updateUnreadRead(String OrderId) {
        RetrofitApi api = com.designurway.idlidosa.retrofit.BaseClient.getClient().create(RetrofitApi.class);
        Call<StatusAndMessageModel> call = api.updateUnreadRead(OrderId);
        call.enqueue(new Callback<StatusAndMessageModel>() {
            @Override
            public void onResponse(Call<StatusAndMessageModel> call, Response<StatusAndMessageModel> response) {
                if (response.isSuccessful() && response.body().getStatus().equals("1")) {
                    getNotificationList();
                } else {
//                    img_sad_face.setVisibility(View.VISIBLE);
//                    notification_rv.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StatusAndMessageModel> call, Throwable t) {

                Toast.makeText(getActivity(), "Onfail", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void deleteNotification(String OrderId) {
        RetrofitApi api = com.designurway.idlidosa.retrofit.BaseClient.getClient().create(RetrofitApi.class);
        Call<StatusAndMessageModel> call = api.deleteNotification(OrderId, PreferenceManager.getCustomerId());
        call.enqueue(new Callback<StatusAndMessageModel>() {
            @Override
            public void onResponse(Call<StatusAndMessageModel> call, Response<StatusAndMessageModel> response) {
                if (response.isSuccessful()) {
                    getNotificationList();

                } else {
                    Toast.makeText(getActivity(), " Delete fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StatusAndMessageModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Onfail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}