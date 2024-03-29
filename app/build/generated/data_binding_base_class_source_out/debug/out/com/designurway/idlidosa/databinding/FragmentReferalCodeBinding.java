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
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentReferalCodeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView otpImg;

  @NonNull
  public final TextView otpNumTxt;

  @NonNull
  public final TextView referaalTxt;

  @NonNull
  public final EditText referralCodeEt;

  @NonNull
  public final TextView skipTv;

  @NonNull
  public final Button submitReferralCodeBtn;

  @NonNull
  public final TextView txt1;

  private FragmentReferalCodeBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView otpImg,
      @NonNull TextView otpNumTxt, @NonNull TextView referaalTxt, @NonNull EditText referralCodeEt,
      @NonNull TextView skipTv, @NonNull Button submitReferralCodeBtn, @NonNull TextView txt1) {
    this.rootView = rootView;
    this.otpImg = otpImg;
    this.otpNumTxt = otpNumTxt;
    this.referaalTxt = referaalTxt;
    this.referralCodeEt = referralCodeEt;
    this.skipTv = skipTv;
    this.submitReferralCodeBtn = submitReferralCodeBtn;
    this.txt1 = txt1;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentReferalCodeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentReferalCodeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_referal_code, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentReferalCodeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.otp_img;
      ImageView otpImg = rootView.findViewById(id);
      if (otpImg == null) {
        break missingId;
      }

      id = R.id.otpNumTxt;
      TextView otpNumTxt = rootView.findViewById(id);
      if (otpNumTxt == null) {
        break missingId;
      }

      id = R.id.referaal_txt;
      TextView referaalTxt = rootView.findViewById(id);
      if (referaalTxt == null) {
        break missingId;
      }

      id = R.id.referral_code_et;
      EditText referralCodeEt = rootView.findViewById(id);
      if (referralCodeEt == null) {
        break missingId;
      }

      id = R.id.skip_tv;
      TextView skipTv = rootView.findViewById(id);
      if (skipTv == null) {
        break missingId;
      }

      id = R.id.submit_referral_code_btn;
      Button submitReferralCodeBtn = rootView.findViewById(id);
      if (submitReferralCodeBtn == null) {
        break missingId;
      }

      id = R.id.txt1;
      TextView txt1 = rootView.findViewById(id);
      if (txt1 == null) {
        break missingId;
      }

      return new FragmentReferalCodeBinding((ConstraintLayout) rootView, otpImg, otpNumTxt,
          referaalTxt, referralCodeEt, skipTv, submitReferralCodeBtn, txt1);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
