// Generated code from Butter Knife. Do not modify!
package com.designurway.idlidosa.a.activity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.designurway.idlidosa.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OTPVerificationActivity_ViewBinding implements Unbinder {
  private OTPVerificationActivity target;

  private View view7f0a030c;

  private View view7f0a0149;

  @UiThread
  public OTPVerificationActivity_ViewBinding(OTPVerificationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OTPVerificationActivity_ViewBinding(final OTPVerificationActivity target, View source) {
    this.target = target;

    View view;
    target.emailEt = Utils.findRequiredViewAsType(source, R.id.email_et, "field 'emailEt'", EditText.class);
    target.passwordEt = Utils.findRequiredViewAsType(source, R.id.password_et, "field 'passwordEt'", EditText.class);
    view = Utils.findRequiredView(source, R.id.sign_in_btn, "field 'signin' and method 'goToHomeActivity'");
    target.signin = Utils.castView(view, R.id.sign_in_btn, "field 'signin'", Button.class);
    view7f0a030c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goToHomeActivity();
      }
    });
    target.rememberMeChk = Utils.findRequiredViewAsType(source, R.id.remember_me_cbx, "field 'rememberMeChk'", CheckBox.class);
    view = Utils.findRequiredView(source, R.id.forgot_pwd_tv, "field 'forgotPwdTv' and method 'goToMobileVerify'");
    target.forgotPwdTv = Utils.castView(view, R.id.forgot_pwd_tv, "field 'forgotPwdTv'", TextView.class);
    view7f0a0149 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goToMobileVerify();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    OTPVerificationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.emailEt = null;
    target.passwordEt = null;
    target.signin = null;
    target.rememberMeChk = null;
    target.forgotPwdTv = null;

    view7f0a030c.setOnClickListener(null);
    view7f0a030c = null;
    view7f0a0149.setOnClickListener(null);
    view7f0a0149 = null;
  }
}
