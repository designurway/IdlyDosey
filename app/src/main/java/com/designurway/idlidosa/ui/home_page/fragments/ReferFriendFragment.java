package com.designurway.idlidosa.ui.home_page.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.databinding.FragmentReferFriendBinding;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ReferFriendFragment extends Fragment {
    private static final String TAG = "RefereFrindFragment";

    FragmentReferFriendBinding binding;

    ImageView referIv;

    TextView referalCode;

    TextView shareReferalCode;
    Button signUpBtn;

    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentReferFriendBinding.inflate(inflater,container,false);
        context = container.getContext();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        referalCode = binding.referalCode;
        shareReferalCode = binding.shareReferalCode;

        referalCode.setText(PreferenceManager.getCustomeReferenceCode());
        Log.d(TAG, "refrererererere" + PreferenceManager.getCustomeReferenceCode());


        shareReferalCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareCode();
            }
        });

    }


    public void shareCode() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Referral Code to Idli Dosa" + " " + PreferenceManager.getCustomeReferenceCode());
        String app_url = "Referral Code to Idli Dosa" + " " + PreferenceManager.getCustomeReferenceCode()+"\n"+"https://play.google.com/store/apps/details?id=com.designurway.idlidosa";
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, app_url);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }


}