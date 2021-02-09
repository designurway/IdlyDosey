package com.designurway.idlidosa.ui.home_page.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.designurway.idlidosa.R;

import butterknife.ButterKnife;


public class SupportFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_support, container, false);
        ButterKnife.bind(this,view);
        return view;
    }
}