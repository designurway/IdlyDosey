// Generated by view binder compiler. Do not edit!
package com.designurway.idlidosa.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.designurway.idlidosa.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentReciveComboAddressBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final LinearLayout LinearOffice;

  @NonNull
  public final CardView cardHomeAddress;

  @NonNull
  public final CardView cardOfcAddress;

  @NonNull
  public final TextView homeAddressTv;

  @NonNull
  public final ImageView homeChk;

  @NonNull
  public final TextView homePhoneTv;

  @NonNull
  public final ImageView imgEditHome;

  @NonNull
  public final ImageView imgEditOffice;

  @NonNull
  public final ImageView imgSadFace;

  @NonNull
  public final LinearLayout linearHome;

  @NonNull
  public final TextView nameTv;

  @NonNull
  public final TextView ofcPhoneTv;

  @NonNull
  public final TextView officeAddressTv;

  @NonNull
  public final ImageView officeChk;

  @NonNull
  public final TextView officeNameTv;

  @NonNull
  public final TextView textAddress;

  private FragmentReciveComboAddressBinding(@NonNull ConstraintLayout rootView,
      @NonNull LinearLayout LinearOffice, @NonNull CardView cardHomeAddress,
      @NonNull CardView cardOfcAddress, @NonNull TextView homeAddressTv, @NonNull ImageView homeChk,
      @NonNull TextView homePhoneTv, @NonNull ImageView imgEditHome,
      @NonNull ImageView imgEditOffice, @NonNull ImageView imgSadFace,
      @NonNull LinearLayout linearHome, @NonNull TextView nameTv, @NonNull TextView ofcPhoneTv,
      @NonNull TextView officeAddressTv, @NonNull ImageView officeChk,
      @NonNull TextView officeNameTv, @NonNull TextView textAddress) {
    this.rootView = rootView;
    this.LinearOffice = LinearOffice;
    this.cardHomeAddress = cardHomeAddress;
    this.cardOfcAddress = cardOfcAddress;
    this.homeAddressTv = homeAddressTv;
    this.homeChk = homeChk;
    this.homePhoneTv = homePhoneTv;
    this.imgEditHome = imgEditHome;
    this.imgEditOffice = imgEditOffice;
    this.imgSadFace = imgSadFace;
    this.linearHome = linearHome;
    this.nameTv = nameTv;
    this.ofcPhoneTv = ofcPhoneTv;
    this.officeAddressTv = officeAddressTv;
    this.officeChk = officeChk;
    this.officeNameTv = officeNameTv;
    this.textAddress = textAddress;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentReciveComboAddressBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentReciveComboAddressBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_recive_combo_address, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentReciveComboAddressBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Linear_office;
      LinearLayout LinearOffice = rootView.findViewById(id);
      if (LinearOffice == null) {
        break missingId;
      }

      id = R.id.card_home_address;
      CardView cardHomeAddress = rootView.findViewById(id);
      if (cardHomeAddress == null) {
        break missingId;
      }

      id = R.id.card_ofc_address;
      CardView cardOfcAddress = rootView.findViewById(id);
      if (cardOfcAddress == null) {
        break missingId;
      }

      id = R.id.home_address_tv;
      TextView homeAddressTv = rootView.findViewById(id);
      if (homeAddressTv == null) {
        break missingId;
      }

      id = R.id.home_chk;
      ImageView homeChk = rootView.findViewById(id);
      if (homeChk == null) {
        break missingId;
      }

      id = R.id.home_phone_tv;
      TextView homePhoneTv = rootView.findViewById(id);
      if (homePhoneTv == null) {
        break missingId;
      }

      id = R.id.img_edit_home;
      ImageView imgEditHome = rootView.findViewById(id);
      if (imgEditHome == null) {
        break missingId;
      }

      id = R.id.img_edit_office;
      ImageView imgEditOffice = rootView.findViewById(id);
      if (imgEditOffice == null) {
        break missingId;
      }

      id = R.id.img_sad_face;
      ImageView imgSadFace = rootView.findViewById(id);
      if (imgSadFace == null) {
        break missingId;
      }

      id = R.id.linear_home;
      LinearLayout linearHome = rootView.findViewById(id);
      if (linearHome == null) {
        break missingId;
      }

      id = R.id.name_tv;
      TextView nameTv = rootView.findViewById(id);
      if (nameTv == null) {
        break missingId;
      }

      id = R.id.ofc_phone_tv;
      TextView ofcPhoneTv = rootView.findViewById(id);
      if (ofcPhoneTv == null) {
        break missingId;
      }

      id = R.id.office_address_tv;
      TextView officeAddressTv = rootView.findViewById(id);
      if (officeAddressTv == null) {
        break missingId;
      }

      id = R.id.office_chk;
      ImageView officeChk = rootView.findViewById(id);
      if (officeChk == null) {
        break missingId;
      }

      id = R.id.office_name_tv;
      TextView officeNameTv = rootView.findViewById(id);
      if (officeNameTv == null) {
        break missingId;
      }

      id = R.id.text_address;
      TextView textAddress = rootView.findViewById(id);
      if (textAddress == null) {
        break missingId;
      }

      return new FragmentReciveComboAddressBinding((ConstraintLayout) rootView, LinearOffice,
          cardHomeAddress, cardOfcAddress, homeAddressTv, homeChk, homePhoneTv, imgEditHome,
          imgEditOffice, imgSadFace, linearHome, nameTv, ofcPhoneTv, officeAddressTv, officeChk,
          officeNameTv, textAddress);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
