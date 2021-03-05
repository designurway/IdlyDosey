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

public class RegisterActivity_ViewBinding implements Unbinder {
  private RegisterActivity target;

  private View view7f0a031c;

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegisterActivity_ViewBinding(final RegisterActivity target, View source) {
    this.target = target;

    View view;
    target.emailEt = Utils.findRequiredViewAsType(source, R.id.email_et, "field 'emailEt'", EditText.class);
    target.phoneEt = Utils.findRequiredViewAsType(source, R.id.phone_et, "field 'phoneEt'", EditText.class);
    target.pwdEt = Utils.findRequiredViewAsType(source, R.id.password_et, "field 'pwdEt'", EditText.class);
    target.confirmPwdEt = Utils.findRequiredViewAsType(source, R.id.c_password_et, "field 'confirmPwdEt'", EditText.class);
    view = Utils.findRequiredView(source, R.id.sign_up_btn, "method 'customerRegister'");
    view7f0a031c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.customerRegister();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    RegisterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.emailEt = null;
    target.phoneEt = null;
    target.pwdEt = null;
    target.confirmPwdEt = null;

    view7f0a031c.setOnClickListener(null);
    view7f0a031c = null;
  }
}
