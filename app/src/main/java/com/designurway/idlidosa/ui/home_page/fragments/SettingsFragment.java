package com.designurway.idlidosa.ui.home_page.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.activity.MobileVerificationActivity;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.databinding.FragmentSettingsBinding;
import com.designurway.idlidosa.ui.auth.AuthActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsFragment extends Fragment {

    FragmentSettingsBinding binding;
    NavDirections action;

    private static final String TAG ="SettingsFragment" ;
    ConstraintLayout consHistory,consTrack,constPickReward,consRefer,consEdtAd,consSupport,consSigout;
    String orderId = "none";



    /* @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

     }
 */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSettingsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        consHistory = binding.consHistory;
        consTrack = binding.consTrack;
        constPickReward = binding.constPickReward;
        consRefer = binding.consRefer;
        consEdtAd = binding.consEdtAd;
        consSupport = binding.consSupport;
        consSigout = binding.consSigout;

        consSigout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        consHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = SettingsFragmentDirections.actionSettingsFragmentToOrderHistoryFragment();
                Navigation.findNavController(getView()).navigate(action);
            }
        });

        consEdtAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = SettingsFragmentDirections.actionSettingsFragmentToAddressBookFragment("00","setting",orderId);
                Navigation.findNavController(getView()).navigate(action);
            }
        });

        consTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = SettingsFragmentDirections.actionSettingsFragmentToTrackOrderListFragment();
                Navigation.findNavController(getView()).navigate(action);
            }
        });

        consSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = SettingsFragmentDirections.actionSettingsFragmentToSupportFragment();
                Navigation.findNavController(getView()).navigate(action);
            }
        });

        constPickReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = SettingsFragmentDirections.actionSettingsFragmentToReceiveComboFragment();
                Navigation.findNavController(getView()).navigate(action);
            }
        });

        consRefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = SettingsFragmentDirections.actionSettingsFragmentToReferFriendFragment();
                Navigation.findNavController(getView()).navigate(action);
            }
        });

    }

    public void logout(){

        PreferenceManager.checkUserLoggedIn(false);
        PreferenceManager.clearCustomer();
        Intent intent=new Intent(getContext(), AuthActivity.class);
        startActivity(intent);
        getActivity().finish();


    }


}