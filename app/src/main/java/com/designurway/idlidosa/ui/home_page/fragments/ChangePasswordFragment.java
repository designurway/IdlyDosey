package com.designurway.idlidosa.ui.home_page.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.activity.MobileVerificationActivity;
import com.designurway.idlidosa.a.model.StatusAndMessageModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.AndroidUtils;
import com.designurway.idlidosa.a.utils.PreferenceManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChangePasswordFragment extends Fragment {
    private static final String TAG ="ChangePwdFragment" ;
    @BindView(R.id.old_pwd_et)
    EditText oldPwdEt;

    @BindView(R.id.new_pwd_et)
    EditText newPwdEt;

    @BindView(R.id.confirm_pwd_et)
    EditText confirmPwdEt;
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_change_password, container, false);
        ButterKnife.bind(this, view);
        context=container.getContext();
        return view;
    }
    
    @OnClick(R.id.submit_btn)
    public void submitData(){
        String oldPwd=oldPwdEt.getText().toString().trim();
        String newPwd=newPwdEt.getText().toString().trim();
        String confirmPwd=confirmPwdEt.getText().toString().trim();

        if (!AndroidUtils.isNetworkAvailable(context)){
            Toast.makeText(getContext(), this.getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }

        if (oldPwd.isEmpty() && newPwd.isEmpty() && confirmPwd.isEmpty()){
            Toast.makeText(getContext(), this.getString(R.string.fill_credentials), Toast.LENGTH_SHORT).show();
          
        }
        else{
            changePwd(oldPwd,newPwd,confirmPwd);
        }
    }

    private void changePwd(String oldPwd, String newPwd, String confirmPwd) {
        RetrofitApi retrofitApi = BaseClient.getClient().create(RetrofitApi.class);
        Call<StatusAndMessageModel> call = retrofitApi.changePwd(PreferenceManager.getCustomerPhone(),oldPwd,newPwd,confirmPwd);
        call.enqueue(new Callback<StatusAndMessageModel>() {
            @Override
            public void onResponse(Call<StatusAndMessageModel> call, Response<StatusAndMessageModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getMessage().contains("Incorrect old password")){
                        Toast.makeText(getContext(), "Incorrect previous password", Toast.LENGTH_SHORT).show();
                } else if (response.body().getMessage().contains("Passwords do not match")){
                        Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
else {

                        startActivity(new Intent(getContext(), MobileVerificationActivity.class));
                    }
                } else {
                    Log.d(TAG, "failure");

                }
            }

            @Override
            public void onFailure(Call<StatusAndMessageModel> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        if (context==null){
            context=getActivity().getApplicationContext();
        }
        }

}