// Generated by view binder compiler. Do not edit!
package com.designurway.idlidosa.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public final class FragmentReferFriendBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView inviteTxt;

  @NonNull
  public final ImageView referImg;

  @NonNull
  public final ConstraintLayout referRl;

  @NonNull
  public final TextView referTxt;

  @NonNull
  public final TextView referalCode;

  @NonNull
  public final TextView referdis;

  @NonNull
  public final TextView shareReferalCode;

  private FragmentReferFriendBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView inviteTxt, @NonNull ImageView referImg, @NonNull ConstraintLayout referRl,
      @NonNull TextView referTxt, @NonNull TextView referalCode, @NonNull TextView referdis,
      @NonNull TextView shareReferalCode) {
    this.rootView = rootView;
    this.inviteTxt = inviteTxt;
    this.referImg = referImg;
    this.referRl = referRl;
    this.referTxt = referTxt;
    this.referalCode = referalCode;
    this.referdis = referdis;
    this.shareReferalCode = shareReferalCode;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentReferFriendBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentReferFriendBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_refer_friend, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentReferFriendBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.inviteTxt;
      TextView inviteTxt = rootView.findViewById(id);
      if (inviteTxt == null) {
        break missingId;
      }

      id = R.id.referImg;
      ImageView referImg = rootView.findViewById(id);
      if (referImg == null) {
        break missingId;
      }

      ConstraintLayout referRl = (ConstraintLayout) rootView;

      id = R.id.referTxt;
      TextView referTxt = rootView.findViewById(id);
      if (referTxt == null) {
        break missingId;
      }

      id = R.id.referalCode;
      TextView referalCode = rootView.findViewById(id);
      if (referalCode == null) {
        break missingId;
      }

      id = R.id.referdis;
      TextView referdis = rootView.findViewById(id);
      if (referdis == null) {
        break missingId;
      }

      id = R.id.shareReferalCode;
      TextView shareReferalCode = rootView.findViewById(id);
      if (shareReferalCode == null) {
        break missingId;
      }

      return new FragmentReferFriendBinding((ConstraintLayout) rootView, inviteTxt, referImg,
          referRl, referTxt, referalCode, referdis, shareReferalCode);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
