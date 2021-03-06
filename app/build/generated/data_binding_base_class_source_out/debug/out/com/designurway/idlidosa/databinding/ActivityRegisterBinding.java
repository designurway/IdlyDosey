// Generated by view binder compiler. Do not edit!
package com.designurway.idlidosa.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.designurway.idlidosa.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegisterBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final EditText cPasswordEt;

  @NonNull
  public final EditText emailEt;

  @NonNull
  public final ImageView imageCurve;

  @NonNull
  public final LinearLayout linearCPassword;

  @NonNull
  public final LinearLayout linearEmail;

  @NonNull
  public final LinearLayout linearPassword;

  @NonNull
  public final LinearLayout linearPhone;

  @NonNull
  public final EditText passwordEt;

  @NonNull
  public final EditText phoneEt;

  @NonNull
  public final CircleImageView profImageCiv;

  @NonNull
  public final Button signUpBtn;

  @NonNull
  public final TextView signUpTv;

  private ActivityRegisterBinding(@NonNull RelativeLayout rootView, @NonNull EditText cPasswordEt,
      @NonNull EditText emailEt, @NonNull ImageView imageCurve,
      @NonNull LinearLayout linearCPassword, @NonNull LinearLayout linearEmail,
      @NonNull LinearLayout linearPassword, @NonNull LinearLayout linearPhone,
      @NonNull EditText passwordEt, @NonNull EditText phoneEt,
      @NonNull CircleImageView profImageCiv, @NonNull Button signUpBtn,
      @NonNull TextView signUpTv) {
    this.rootView = rootView;
    this.cPasswordEt = cPasswordEt;
    this.emailEt = emailEt;
    this.imageCurve = imageCurve;
    this.linearCPassword = linearCPassword;
    this.linearEmail = linearEmail;
    this.linearPassword = linearPassword;
    this.linearPhone = linearPhone;
    this.passwordEt = passwordEt;
    this.phoneEt = phoneEt;
    this.profImageCiv = profImageCiv;
    this.signUpBtn = signUpBtn;
    this.signUpTv = signUpTv;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.c_password_et;
      EditText cPasswordEt = rootView.findViewById(id);
      if (cPasswordEt == null) {
        break missingId;
      }

      id = R.id.email_et;
      EditText emailEt = rootView.findViewById(id);
      if (emailEt == null) {
        break missingId;
      }

      id = R.id.image_curve;
      ImageView imageCurve = rootView.findViewById(id);
      if (imageCurve == null) {
        break missingId;
      }

      id = R.id.linear_c_password;
      LinearLayout linearCPassword = rootView.findViewById(id);
      if (linearCPassword == null) {
        break missingId;
      }

      id = R.id.linear_email;
      LinearLayout linearEmail = rootView.findViewById(id);
      if (linearEmail == null) {
        break missingId;
      }

      id = R.id.linear_password;
      LinearLayout linearPassword = rootView.findViewById(id);
      if (linearPassword == null) {
        break missingId;
      }

      id = R.id.linear_phone;
      LinearLayout linearPhone = rootView.findViewById(id);
      if (linearPhone == null) {
        break missingId;
      }

      id = R.id.password_et;
      EditText passwordEt = rootView.findViewById(id);
      if (passwordEt == null) {
        break missingId;
      }

      id = R.id.phone_et;
      EditText phoneEt = rootView.findViewById(id);
      if (phoneEt == null) {
        break missingId;
      }

      id = R.id.prof_image_civ;
      CircleImageView profImageCiv = rootView.findViewById(id);
      if (profImageCiv == null) {
        break missingId;
      }

      id = R.id.sign_up_btn;
      Button signUpBtn = rootView.findViewById(id);
      if (signUpBtn == null) {
        break missingId;
      }

      id = R.id.sign_up_tv;
      TextView signUpTv = rootView.findViewById(id);
      if (signUpTv == null) {
        break missingId;
      }

      return new ActivityRegisterBinding((RelativeLayout) rootView, cPasswordEt, emailEt,
          imageCurve, linearCPassword, linearEmail, linearPassword, linearPhone, passwordEt,
          phoneEt, profImageCiv, signUpBtn, signUpTv);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
