package com.designurway.idlidosa.ui.home_page.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavAction;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.adapters.TrackOrderListAdapter;
import com.designurway.idlidosa.a.model.TrackOrderListData;
import com.designurway.idlidosa.a.model.TrackOrderListModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.PreferenceManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TrackOrderListFragment extends Fragment {

    TrackOrderListAdapter trackOrderListAdapter;
    RecyclerView tackOrderRecycler;
    NavDirections action;

    public TrackOrderListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_track_order_list, container, false);

        tackOrderRecycler = view.findViewById(R.id.tackOrderRecycler);

        getOrderList();

        return  view;
    }

    private void getOrderList() {

        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<TrackOrderListModel> call = retrofitApi.
                getOnGoingOrder(PreferenceManager.getCustomerId());
        call.enqueue(new Callback<TrackOrderListModel>() {
            @Override
            public void onResponse(Call<TrackOrderListModel> call, Response<TrackOrderListModel> response) {
                if (response.isSuccessful()){

                    TrackOrderListModel trackOrderList = response.body();
                    String status = trackOrderList.getStatus();

                    if (status.equals("1")){

                        List<TrackOrderListData> orderListModelsData = trackOrderList.getData();
                        trackOrderListAdapter = new TrackOrderListAdapter(orderListModelsData, getContext());
                        tackOrderRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                        tackOrderRecycler.setAdapter(trackOrderListAdapter);
                        
                        trackOrderListAdapter.setTrackOrderClickListner(new TrackOrderListAdapter.TrackOrderClickListner() {
                            @Override
                            public void onTrackOrderBtnClicked(int position, View itemView) {
                                String orderId = trackOrderList.getData().get(position).getOrder_id();
                                String amount = trackOrderList.getData().get(position).getAmount();

                                action = TrackOrderListFragmentDirections.actionTrackOrderListFragmentToTrackOrderFragment(orderId,amount);
                                Navigation.findNavController(getView()).navigate(action);

                            }
                        });
                        
                    }else {
                        Toast.makeText(getActivity(), "No Data Found", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TrackOrderListModel> call, Throwable t) {
                Toast.makeText(getActivity(), "On Failure "+t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });

    }
/*

    private void gotoTrackOrder(String OrderId,String Amount,String Image){

        Bundle bundle = new Bundle();
        bundle.putString("orderId", OrderId);
        bundle.putString("amount", Amount);
        bundle.putString("image", Image);
        TrackOrderFragment fragment = new TrackOrderFragment();
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.frame_lt, fragment).addToBackStack(null).commit();

    }
*/

}