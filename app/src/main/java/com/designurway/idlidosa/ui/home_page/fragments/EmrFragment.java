package com.designurway.idlidosa.ui.home_page.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.designurway.idlidosa.R;

public class  EmrFragment extends Fragment {
Button a;
    final int IMAGE_PICK_REQUEST_CODE=101;

    public EmrFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_emr, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        a = view.findViewById(R.id.a);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               selectImage();


            }
        });
    }


    private void selectImage() {
        // Create the Intent for Image Gallery.
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        // Start new activity with the LOAD_IMAGE_RESULTS to handle back the results when image is picked from the Image Gallery.
        startActivityForResult(i,1); //LOAD_IMAGE_RESULTS
    }
}