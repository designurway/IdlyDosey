// Generated by view binder compiler. Do not edit!
package com.designurway.idlidosa.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public final class FragmentGooglePayBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView amountEt;

  @NonNull
  public final ImageView arrowImg;

  @NonNull
  public final ImageView backImgv;

  @NonNull
  public final CircleImageView logPayImgv;

  @NonNull
  public final Button payBtn;

  @NonNull
  public final TextView paymentToEt;

  @NonNull
  public final CircleImageView personPayImgv;

  @NonNull
  public final TextView rupeesTv;

  @NonNull
  public final TextView upiEt;

  private FragmentGooglePayBinding(@NonNull ConstraintLayout rootView, @NonNull TextView amountEt,
      @NonNull ImageView arrowImg, @NonNull ImageView backImgv, @NonNull CircleImageView logPayImgv,
      @NonNull Button payBtn, @NonNull TextView paymentToEt, @NonNull CircleImageView personPayImgv,
      @NonNull TextView rupeesTv, @NonNull TextView upiEt) {
    this.rootView = rootView;
    this.amountEt = amountEt;
    this.arrowImg = arrowImg;
    this.backImgv = backImgv;
    this.logPayImgv = logPayImgv;
    this.payBtn = payBtn;
    this.paymentToEt = paymentToEt;
    this.personPayImgv = personPayImgv;
    this.rupeesTv = rupeesTv;
    this.upiEt = upiEt;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentGooglePayBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentGooglePayBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_google_pay, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentGooglePayBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.amount_et;
      TextView amountEt = rootView.findViewById(id);
      if (amountEt == null) {
        break missingId;
      }

      id = R.id.arrow_img;
      ImageView arrowImg = rootView.findViewById(id);
      if (arrowImg == null) {
        break missingId;
      }

      id = R.id.back_imgv;
      ImageView backImgv = rootView.findViewById(id);
      if (backImgv == null) {
        break missingId;
      }

      id = R.id.log_pay_imgv;
      CircleImageView logPayImgv = rootView.findViewById(id);
      if (logPayImgv == null) {
        break missingId;
      }

      id = R.id.pay_btn;
      Button payBtn = rootView.findViewById(id);
      if (payBtn == null) {
        break missingId;
      }

      id = R.id.payment_to_et;
      TextView paymentToEt = rootView.findViewById(id);
      if (paymentToEt == null) {
        break missingId;
      }

      id = R.id.person_pay_imgv;
      CircleImageView personPayImgv = rootView.findViewById(id);
      if (personPayImgv == null) {
        break missingId;
      }

      id = R.id.rupees_tv;
      TextView rupeesTv = rootView.findViewById(id);
      if (rupeesTv == null) {
        break missingId;
      }

      id = R.id.upi_et;
      TextView upiEt = rootView.findViewById(id);
      if (upiEt == null) {
        break missingId;
      }

      return new FragmentGooglePayBinding((ConstraintLayout) rootView, amountEt, arrowImg, backImgv,
          logPayImgv, payBtn, paymentToEt, personPayImgv, rupeesTv, upiEt);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
