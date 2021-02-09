package com.designurway.idlidosa.ui.home_page.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.model.StatusAndMessageModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.AndroidUtils;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.databinding.FragmentChangeAddressBinding;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChangeAddressFragment extends Fragment {

    FragmentChangeAddressBinding binding;
    NavDirections action;
    private static final String TAG = "ChangeAddressFragment";

    Button changeBtn;
    Button saveNContinueBtn;
    EditText flatNoEt;
    EditText localityEt;
    RadioGroup radioGroup;

    String checked;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentChangeAddressBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        changeBtn = binding.changeBtn;
        saveNContinueBtn = binding.saveNContinueBtn;
        flatNoEt = binding.flatNoEt;
        localityEt = binding.localityEt;
        radioGroup = binding.radioGroup;

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    checked = String.valueOf(rb.getText());
                }


            }
        });

        saveNContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postAddress();
            }
        });

    }

    public void postAddress() {
        String flatNum = flatNoEt.getText().toString().trim();
        String locality = localityEt.getText().toString().trim();

        if (flatNum.isEmpty() && locality.isEmpty()) {
            Toast.makeText(getContext(), this.getString(R.string.fill_credentials),
                    Toast.LENGTH_SHORT).show();
        } else if (!AndroidUtils.isNetworkAvailable(getContext())) {
            Toast.makeText(getContext(), this.getString(R.string.no_internet),
                    Toast.LENGTH_SHORT).show();
        } else {
            postAddressDetails(flatNum, locality, checked);
        }
    }

    private void postAddressDetails(String flatNum, String locality, String checked) {
        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<StatusAndMessageModel> call = retrofitApi.postCustomerAddress(PreferenceManager.getCustomerPhone(), flatNum, locality, checked);
        Log.d(TAG, "phone" + PreferenceManager.getCustomerPhone());
        Log.d(TAG, "flatNum" + flatNum);
        Log.d(TAG, "locality" + locality);
        Log.d(TAG, "checked" + checked);
        call.enqueue(new Callback<StatusAndMessageModel>() {
            @Override
            public void onResponse(Call<StatusAndMessageModel> call, Response<StatusAndMessageModel> response) {
                if (response.isSuccessful()) {
                    action = ChangeAddressFragmentDirections.actionChangeAddressFragmentToAddressBookFragment("00", "setting","none");
                    Navigation.findNavController(getView()).navigate(action);
                } else {

                    Log.d(TAG, "no Data");
                }
            }

            @Override
            public void onFailure(Call<StatusAndMessageModel> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());

            }
        });
    }


}

