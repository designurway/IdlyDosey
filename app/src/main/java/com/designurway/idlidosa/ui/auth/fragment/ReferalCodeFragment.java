package com.designurway.idlidosa.ui.auth.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.activity.ReferralCodeActivity;
import com.designurway.idlidosa.a.activity.RegisterActivity;
import com.designurway.idlidosa.a.utils.AndroidUtils;
import com.designurway.idlidosa.databinding.FragmentReferalCodeBinding;


public class ReferalCodeFragment extends Fragment {


    FragmentReferalCodeBinding binding;
    ReferalCodeFragmentArgs args;
    NavDirections action;
    String phone;
    EditText referralCodeEt;
    Button submitReferralCodeBtn;
    TextView skipTv;



    public ReferalCodeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_referal_code, container, false);

        binding = FragmentReferalCodeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        args = ReferalCodeFragmentArgs.fromBundle(getArguments());
        phone = args.getPhone();

        referralCodeEt = binding.referralCodeEt;
        submitReferralCodeBtn = binding.submitReferralCodeBtn;
        skipTv = binding.skipTv;


        submitReferralCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=referralCodeEt.getText().toString().trim();
                if (code.isEmpty()){
                    Toast.makeText(getContext(), getContext().getString(R.string.fill_credentials), Toast.LENGTH_SHORT).show();
                }
                else if(!AndroidUtils.isNetworkAvailable(getContext())){
                    Toast.makeText(getContext(), getContext().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
                }
                else{

                    action = ReferalCodeFragmentDirections.actionReferalCodeFragmentToRegisterFragment(phone,code);
                    Navigation.findNavController(getView()).navigate(action);

                }
            }
        });

        skipTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = ReferalCodeFragmentDirections.actionReferalCodeFragmentToRegisterFragment(phone,"none");
                Navigation.findNavController(getView()).navigate(action);
            }
        });

    }
}