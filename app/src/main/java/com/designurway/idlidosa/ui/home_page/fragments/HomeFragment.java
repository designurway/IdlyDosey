package com.designurway.idlidosa.ui.home_page.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.designurway.idlidosa.BuildConfig;
import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.activity.EmergencyActivity;
import com.designurway.idlidosa.a.adapters.HomeTabAdapter;
import com.designurway.idlidosa.a.adapters.MenuFragmentAdapter;
import com.designurway.idlidosa.a.model.MenuDataModel;
import com.designurway.idlidosa.a.model.Menumodel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.AndroidUtils;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayout;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.designurway.idlidosa.BuildConfig.VERSION_NAME;

public class HomeFragment extends Fragment {

    NavDirections action;
    FragmentHomeBinding binding;
    RecyclerView recyclerViewBulk, recyclerViewFeatured, recyclerViewCombo;
    Button btnEmergency;
    TextView addressLayout;
    Context context;
    ProgressBar progress;
    NestedScrollView scroll_view_dashboard;
    boolean bulk = false, featured = false, combo = false;


    private static final String TAG = "FeaturedFragment";
//    DashBoardFragment.AdapterPager adapterPager;

    MenuFragmentAdapter adapter;


    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getLatestAppUpdate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        progress=binding.progress;
        scroll_view_dashboard=binding.scrollViewDashboard;


        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = view.getContext();

        btnEmergency = binding.btnEmergency;
        addressLayout = binding.addressLayout;
        recyclerViewBulk = binding.recyclerViewBulk;
        recyclerViewFeatured = binding.recyclerViewFeatured;
        recyclerViewCombo = binding.recyclerViewCombo;

        ButterKnife.bind(this, view);
        btnEmergency.setEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalTime time = LocalTime.now();
            int a = time.getHour();
            btnEmergency.setEnabled(a >= 17 || a <= 5);
        }


        btnEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                action = HomeFragmentDirections.actionHomeFragmentToEmergencyFragment();
                Navigation.findNavController(getView()).navigate(action);


            }
        });

        GetBulkOrder();
        setFeaturedMenu();
        setComboMenu();




    }

    public void GetBulkOrder() {

        RetrofitApi api = BaseClient.getClient().create(RetrofitApi.class);
        Call<MenuDataModel> call = api.GetDashboard("bulk", PreferenceManager.getCustomerId());
        call.enqueue(new Callback<MenuDataModel>() {
            @Override
            public void onResponse(Call<MenuDataModel> call, Response<MenuDataModel> response) {

                if (response.isSuccessful() && response.body().getStatus().equals("1")) {
                    bulk = true;
                    ArrayList<Menumodel> menumodel = response.body().getData();
                    adapter = new MenuFragmentAdapter(menumodel, getContext());
                    recyclerViewBulk.setVisibility(View.VISIBLE);
                    recyclerViewBulk.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                    recyclerViewBulk.setAdapter(adapter);

                    if (featured && combo && bulk) {
                        progress.setVisibility(View.INVISIBLE);
                        scroll_view_dashboard.setVisibility(View.VISIBLE);

                    }

                    adapter.sendToFragment(new MenuFragmentAdapter.setFragmentTransaction() {
                        @Override
                        public void sendPosition(int position, String id) {
                           /* Bundle bundle = new Bundle();
                            bundle.putString("one", "bulk");
                            DisplayProductFragment fragment = new DisplayProductFragment();
                            fragment.setArguments(bundle);
                            getFragmentManager().beginTransaction().replace(R.id.frame_lt, fragment).addToBackStack(null).commit();*/

                            action = HomeFragmentDirections.actionHomeFragmentToDisplayProductFragment("bulk");
                            Navigation.findNavController(getView()).navigate(action);


                        }

                        @Override
                        public void sendView(int position, View view) {

                            ProgressBar progressBar = view.findViewById(R.id.progress_bulk);
                            CardView card = view.findViewById(R.id.menu_overall_layout_cv);
                            progressBar.setVisibility(View.INVISIBLE);
                            card.setVisibility(View.VISIBLE);
                        }
                    });
                } else {
                    Toast.makeText(context, "no data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MenuDataModel> call, Throwable t) {
                Toast.makeText(context, "Onfailure" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void setFeaturedMenu() {

        Log.d("ckeckInFeatured", "inMetod");

        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<MenuDataModel> call = retrofitApi.GetDashboard("featured", PreferenceManager.getCustomerId());
        call.enqueue(new Callback<MenuDataModel>() {
            @Override
            public void onResponse(Call<MenuDataModel> call, Response<MenuDataModel> response) {

                MenuDataModel MenuDataModel = response.body();
                if (response.isSuccessful() && MenuDataModel.getStatus().equals("1")) {
                    featured = true;
                    if (featured && combo && bulk) {
                        progress.setVisibility(View.INVISIBLE);
                        scroll_view_dashboard.setVisibility(View.VISIBLE);

                    }

                    MenuDataModel dataModel = response.body();
                    Log.d(TAG, "response" + response.body());
                    ArrayList<Menumodel> menumodel = dataModel.getData();
                    adapter = new MenuFragmentAdapter(menumodel, getContext());

                    adapter.sendToFragment(new MenuFragmentAdapter.setFragmentTransaction() {
                        @Override
                        public void sendPosition(int position, String id) {


                            action = HomeFragmentDirections.actionHomeFragmentToDisplayProductFragment("featured");
                            Navigation.findNavController(getView()).navigate(action);


                           /* Bundle bundle = new Bundle();
                            bundle.putString("one", "featured");
                            DisplayProductFragment fragment = new DisplayProductFragment();
                            fragment.setArguments(bundle);
                            getFragmentManager().beginTransaction().replace(R.id.frame_lt, fragment).addToBackStack(null).commit();*/
                        }

                        @Override
                        public void sendView(int position, View view) {
                            ProgressBar progressBar = view.findViewById(R.id.progress_bulk);
                            CardView card = view.findViewById(R.id.menu_overall_layout_cv);
                            progressBar.setVisibility(View.INVISIBLE);
                            card.setVisibility(View.VISIBLE);
                        }
                    });
                    recyclerViewFeatured.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    recyclerViewFeatured.setAdapter(adapter);
                } else {


                }
            }

            @Override
            public void onFailure(Call<MenuDataModel> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());

            }
        });

    }

    public void setComboMenu() {

        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<MenuDataModel> call = retrofitApi.GetDashboard("combo", PreferenceManager.getCustomerId());
        call.enqueue(new Callback<MenuDataModel>() {
            @Override
            public void onResponse(Call<MenuDataModel> call, Response<MenuDataModel> response) {
                if (response.isSuccessful() && response.body().getStatus().equals("1")) {
                    combo = true;
                    MenuDataModel dataModel = response.body();

                    ArrayList<Menumodel> menumodel = dataModel.getData();
                    adapter = new MenuFragmentAdapter(menumodel, getContext());
                    if (featured && combo && bulk) {
                        progress.setVisibility(View.INVISIBLE);
                        scroll_view_dashboard.setVisibility(View.VISIBLE);

                    }
                    adapter.sendToFragment(new MenuFragmentAdapter.setFragmentTransaction() {
                        @Override
                        public void sendPosition(int position, String id) {
                          /*  Bundle bundle = new Bundle();
                            bundle.putString("one", "combo");
                            DisplayProductFragment fragment = new DisplayProductFragment();
                            fragment.setArguments(bundle);
                            getFragmentManager().beginTransaction().replace(R.id.frame_lt, fragment).addToBackStack(null).commit();*/


                            action = HomeFragmentDirections.actionHomeFragmentToDisplayProductFragment("combo");
                            Navigation.findNavController(getView()).navigate(action);


                        }

                        @Override
                        public void sendView(int position, View view) {
                            ProgressBar progressBar = view.findViewById(R.id.progress_bulk);
                            CardView card = view.findViewById(R.id.menu_overall_layout_cv);
                            progressBar.setVisibility(View.INVISIBLE);
                            card.setVisibility(View.VISIBLE);
                        }
                    });
                    recyclerViewCombo.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                    recyclerViewCombo.setAdapter(adapter);
                } else {


                }
            }

            @Override
            public void onFailure(Call<MenuDataModel> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());

            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();

        context = getActivity().getApplicationContext();
    }
}


