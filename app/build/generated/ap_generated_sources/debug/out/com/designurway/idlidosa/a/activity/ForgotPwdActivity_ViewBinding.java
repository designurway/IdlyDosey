// Generated code from Butter Knife. Do not modify!
package com.designurway.idlidosa.a.activity;

import android.view.View;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.designurway.idlidosa.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ForgotPwdActivity_ViewBinding implements Unbinder {
  private ForgotPwdActivity target;

  private View view7f0a00c1;

  @UiThread
  public ForgotPwdActivity_ViewBinding(ForgotPwdActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ForgotPwdActivity_ViewBinding(final ForgotPwdActivity target, View source) {
    this.target = target;

    View view;
    target.newPwdEt = Utils.findRequiredViewAsType(source, R.id.new_pwd_et, "field 'newPwdEt'", EditText.class);
    target.confPwdEt = Utils.findRequiredViewAsType(source, R.id.conf_password_et, "field 'confPwdEt'", EditText.class);
    view = Utils.findRequiredView(source, R.id.change_pwd_btn, "method 'changePwd'");
    view7f0a00c1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.changePwd();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ForgotPwdActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.newPwdEt = null;
    target.confPwdEt = null;

    view7f0a00c1.setOnClickListener(null);
    view7f0a00c1 = null;
  }
}
