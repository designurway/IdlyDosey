package com.designurway.idlidosa.ui.home_page.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.adapters.MenuFragmentAdapter;
import com.designurway.idlidosa.a.model.MenuDataModel;
import com.designurway.idlidosa.a.model.Menumodel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.PreferenceManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeaturedFragment extends Fragment {
    private static final String TAG = "FeaturedFragment";
    MenuFragmentAdapter adapter;
    RecyclerView recycler_featurefragment;
    RelativeLayout noDataLv;


    public FeaturedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_featured, container, false);
        recycler_featurefragment = view.findViewById(R.id.recycler_featurefragment);
        noDataLv = view.findViewById(R.id.no_data_ly);
        Log.d("ckeckInFeatured","onCreateView");
        setFeaturedMenu();


        return view;

    }

    public void setFeaturedMenu() {

        Log.d("ckeckInFeatured","inMetod");

        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<MenuDataModel> call = retrofitApi.GetDashboard("featured", PreferenceManager.getCustomerId());
        call.enqueue(new Callback<MenuDataModel>() {
            @Override
            public void onResponse(Call<MenuDataModel> call, Response<MenuDataModel> response) {
                MenuDataModel MenuDataModel = response.body();
                if (response.isSuccessful() && MenuDataModel.getStatus().equals("1")) {
                    MenuDataModel dataModel = response.body();
                    Log.d(TAG, "response" + response.body());
                    ArrayList<Menumodel> menumodel = dataModel.getData();
                    adapter = new MenuFragmentAdapter(menumodel, getContext());
                    adapter.sendToFragment(new MenuFragmentAdapter.setFragmentTransaction() {
                        @Override
                        public void sendPosition(int position, String id) {


                            Bundle bundle = new Bundle();
                            bundle.putString("one", "featured");
                            DisplayProductFragment fragment = new DisplayProductFragment();
                            fragment.setArguments(bundle);
                            getFragmentManager().beginTransaction().replace(R.id.frame_lt, fragment).addToBackStack(null).commit();
                        }
                    });
                    recycler_featurefragment.setLayoutManager(new LinearLayoutManager(getContext(),
                            LinearLayoutManager.HORIZONTAL, false));
                    recycler_featurefragment.setAdapter(adapter);
                } else {
                    noDataLv.setVisibility(View.VISIBLE);
                    recycler_featurefragment.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(Call<MenuDataModel> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());

            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setFeaturedMenu();
    }

    private void goToProductDetails(Fragment fragment) {
        Fragment fragmentproduct;
        FragmentManager productManager = getActivity().getSupportFragmentManager();
        FragmentTransaction productTransaction = productManager.beginTransaction();
        fragmentproduct = fragment;
        productTransaction.replace(R.id.frame_lt, fragmentproduct);
        productTransaction.addToBackStack(null);
        productTransaction.commit();

    }

    @Override
    public void onResume() {
        super.onResume();
        setFeaturedMenu();
    }

    @Override
    public void onStart() {
        super.onStart();
        setFeaturedMenu();
    }
}