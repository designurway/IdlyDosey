package com.designurway.idlidosa.ui.home_page.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.model.StatusAndMessageModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.PreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditAddressFragment extends Fragment {

    String  addres;
    EditText edt_flt_no;
    EditText edt_street;
    Button btn_save_continue;
    public EditAddressFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_edit_address, container, false);
        edt_flt_no=view.findViewById(R.id.edt_flt_no);
        edt_street=view.findViewById(R.id.edt_street);
        btn_save_continue=view.findViewById(R.id.btn_save_continue);
        Bundle bundle = getArguments();
        if (bundle != null) {
            addres = bundle.getString("address");
//            Toast.makeText(getContext(), addres, Toast.LENGTH_SHORT).show();

        } else {
//            Toast.makeText(getContext(), "empty", Toast.LENGTH_SHORT).show();
        }



        btn_save_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateAddress();
                AddressBookFragment addressBookFragment=new AddressBookFragment();
                getFragmentManager().beginTransaction().replace(R.id.frame_lt,addressBookFragment).addToBackStack(null).commit();
            }
        });

        return view;
    }

    public void UpdateAddress(){
        String buildin=edt_flt_no.getText().toString();
        String street=edt_street.getText().toString();
        RetrofitApi api= BaseClient.getClient().create(RetrofitApi.class);

        Call<StatusAndMessageModel> call=api.EditAddress(PreferenceManager.getCustomerId(),buildin
                ,street,addres);

        call.enqueue(new Callback<StatusAndMessageModel>() {
            @Override
            public void onResponse(Call<StatusAndMessageModel> call, Response<StatusAndMessageModel> response) {
                if (response.isSuccessful()){
                }else{
                }
            }

            @Override
            public void onFailure(Call<StatusAndMessageModel> call, Throwable t) {
                Toast.makeText(getContext(), "Onfail", Toast.LENGTH_SHORT).show();

            }
        });
    }
}