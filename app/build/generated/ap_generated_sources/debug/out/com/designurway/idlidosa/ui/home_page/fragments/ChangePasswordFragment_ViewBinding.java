// Generated code from Butter Knife. Do not modify!
package com.designurway.idlidosa.ui.home_page.fragments;

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

public class ChangePasswordFragment_ViewBinding implements Unbinder {
  private ChangePasswordFragment target;

  private View view7f0a02fa;

  @UiThread
  public ChangePasswordFragment_ViewBinding(final ChangePasswordFragment target, View source) {
    this.target = target;

    View view;
    target.oldPwdEt = Utils.findRequiredViewAsType(source, R.id.old_pwd_et, "field 'oldPwdEt'", EditText.class);
    target.newPwdEt = Utils.findRequiredViewAsType(source, R.id.new_pwd_et, "field 'newPwdEt'", EditText.class);
    target.confirmPwdEt = Utils.findRequiredViewAsType(source, R.id.confirm_pwd_et, "field 'confirmPwdEt'", EditText.class);
    view = Utils.findRequiredView(source, R.id.submit_btn, "method 'submitData'");
    view7f0a02fa = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.submitData();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ChangePasswordFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.oldPwdEt = null;
    target.newPwdEt = null;
    target.confirmPwdEt = null;

    view7f0a02fa.setOnClickListener(null);
    view7f0a02fa = null;
  }
}
