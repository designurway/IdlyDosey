package com.designurway.idlidosa.ui.home_page.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.databinding.FragmentServiceNotAvailableBinding;


public class ServiceNotAvailableFragment extends Fragment {

    FragmentServiceNotAvailableBinding binding;
    ServiceNotAvailableFragmentArgs args;
    String addrss;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentServiceNotAvailableBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        args = ServiceNotAvailableFragmentArgs.fromBundle(getArguments());

        binding.txtAdd.setText(args.getAddress());

        binding.gotToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections navDirections = ServiceNotAvailableFragmentDirections.actionServiceNotAvailableFragmentToHomeFragment();
                Navigation.findNavController(getView()).navigate(navDirections);
            }
        });
    }
}