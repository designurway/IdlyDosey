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
import android.widget.Toast;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.adapters.ProductDisplayAdapter;
import com.designurway.idlidosa.a.model.MenuDataModel;
import com.designurway.idlidosa.a.model.Menumodel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.databinding.FragmentDisplayProductBinding;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DisplayProductFragment extends Fragment {

    private static final String TAG = "DisplayProductFragment";

    RecyclerView recyclerDisplayProduct;
    ProductDisplayAdapter adapter;
    String itemType;
    FeaturedFragment featuredFragment;
    DisplayProductFragmentArgs args;
    FragmentDisplayProductBinding binding;
    NavDirections action;

    public DisplayProductFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDisplayProductBinding.inflate(inflater,container,false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        args = DisplayProductFragmentArgs.fromBundle(getArguments());
        itemType = args.getItemType();

        recyclerDisplayProduct = binding.recyclerDisplayProduct;
        recyclerDisplayProduct.setLayoutManager(new GridLayoutManager(getContext(), 2));
        GetOrder();
    }

    public void GetOrder() {
        RetrofitApi api = BaseClient.getClient().create(RetrofitApi.class);
        Call<MenuDataModel> call = api.GetDashboard(itemType, PreferenceManager.getCustomerId());
        call.enqueue(new Callback<MenuDataModel>() {
            @Override
            public void onResponse(Call<MenuDataModel> call, Response<MenuDataModel> response) {
                MenuDataModel menuDetailsModels=response.body();
                ArrayList<Menumodel> menumodel = response.body().getData();
                if (response.isSuccessful() && menuDetailsModels.getStatus().contains("1")){

                    adapter = new ProductDisplayAdapter(menumodel, getContext());
                    adapter.sendToFragment(new ProductDisplayAdapter.setFragmentTransaction() {
                        @Override
                        public void sendPosition(int position, String id, View view,String present) {

                          /*  action = DisplayProductFragmentDirections.actionDisplayProductFragmentToProductDetailfragment(id,present);
                            Navigation.findNavController(getView()).navigate(action);*/
                        }

                        @Override
                        public void sendView(int position,View view) {


                        }
                    });
                    recyclerDisplayProduct.setAdapter(adapter);

                } else {
                    Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MenuDataModel> call, Throwable t) {
              Log.d(TAG,"onFailure"+t.getMessage());

            }
        });
    }


}

