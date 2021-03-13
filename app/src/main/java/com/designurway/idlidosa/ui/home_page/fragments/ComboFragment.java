package com.designurway.idlidosa.ui.home_page.fragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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

import static android.content.ContentValues.TAG;


public class ComboFragment extends Fragment {
    MenuFragmentAdapter adapter;
    RecyclerView recycler_combo_fragment;
RelativeLayout no_data_ly;
    public ComboFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_combo, container, false);
        recycler_combo_fragment = view.findViewById(R.id.recycler_combo_fragment);
        no_data_ly=view.findViewById(R.id.no_data_ly);

        setFeaturedMenu();
        return view;
    }

    public void setFeaturedMenu() {
        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<MenuDataModel> call = retrofitApi.GetDashboard("combo", PreferenceManager.getCustomerId());
        call.enqueue(new Callback<MenuDataModel>() {
            @Override
            public void onResponse(Call<MenuDataModel> call, Response<MenuDataModel> response) {
                if (response.isSuccessful() && response.body().getStatus().equals("1")) {
                    MenuDataModel dataModel = response.body();

                    ArrayList<Menumodel> menumodel = dataModel.getData();
                    adapter = new MenuFragmentAdapter(menumodel, getContext());
                    adapter.sendToFragment(new MenuFragmentAdapter.setFragmentTransaction() {
                        @Override
                        public void sendPosition(int position, String id) {
                            Bundle bundle = new Bundle();
                            bundle.putString("one", "combo");
                            DisplayProductFragment fragment = new DisplayProductFragment();
                            fragment.setArguments(bundle);
                            getFragmentManager().beginTransaction().replace(R.id.frame_lt, fragment).addToBackStack(null).commit();
                        }

                        @Override
                        public void sendView(int position, View view) {
                            ProgressBar progressBar=view.findViewById(R.id.progress_bulk);
                            CardView card=view.findViewById(R.id.menu_overall_layout_cv);
                            progressBar.setVisibility(View.INVISIBLE);
                            card.setVisibility(View.VISIBLE);
                        }
                    });
                    recycler_combo_fragment.setLayoutManager(new LinearLayoutManager(getContext(),
                            LinearLayoutManager.HORIZONTAL, false));
                    recycler_combo_fragment.setAdapter(adapter);
                } else {

                    recycler_combo_fragment.setVisibility(View.GONE);
                    no_data_ly.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure(Call<MenuDataModel> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());

            }
        });

    }
}