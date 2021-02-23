// Generated by view binder compiler. Do not edit!
package com.designurway.idlidosa.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.designurway.idlidosa.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentMobileVerificationBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final CircleImageView appLogoHere;

  @NonNull
  public final ImageView curveBg;

  @NonNull
  public final Button getOtpBtn;

  @NonNull
  public final EditText phoneEt;

  @NonNull
  public final TextView signInTv;

  private FragmentMobileVerificationBinding(@NonNull ConstraintLayout rootView,
      @NonNull CircleImageView appLogoHere, @NonNull ImageView curveBg, @NonNull Button getOtpBtn,
      @NonNull EditText phoneEt, @NonNull TextView signInTv) {
    this.rootView = rootView;
    this.appLogoHere = appLogoHere;
    this.curveBg = curveBg;
    this.getOtpBtn = getOtpBtn;
    this.phoneEt = phoneEt;
    this.signInTv = signInTv;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentMobileVerificationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentMobileVerificationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_mobile_verification, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentMobileVerificationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.app_logo_here;
      CircleImageView appLogoHere = rootView.findViewById(id);
      if (appLogoHere == null) {
        break missingId;
      }

      id = R.id.curve_bg;
      ImageView curveBg = rootView.findViewById(id);
      if (curveBg == null) {
        break missingId;
      }

      id = R.id.get_otp_btn;
      Button getOtpBtn = rootView.findViewById(id);
      if (getOtpBtn == null) {
        break missingId;
      }

      id = R.id.phone_et;
      EditText phoneEt = rootView.findViewById(id);
      if (phoneEt == null) {
        break missingId;
      }

      id = R.id.sign_in_tv;
      TextView signInTv = rootView.findViewById(id);
      if (signInTv == null) {
        break missingId;
      }

      return new FragmentMobileVerificationBinding((ConstraintLayout) rootView, appLogoHere,
          curveBg, getOtpBtn, phoneEt, signInTv);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
