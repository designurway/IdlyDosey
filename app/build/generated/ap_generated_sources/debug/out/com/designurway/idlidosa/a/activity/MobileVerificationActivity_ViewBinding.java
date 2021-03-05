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

public class MobileVerificationActivity_ViewBinding implements Unbinder {
  private MobileVerificationActivity target;

  private View view7f0a0154;

  @UiThread
  public MobileVerificationActivity_ViewBinding(MobileVerificationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MobileVerificationActivity_ViewBinding(final MobileVerificationActivity target,
      View source) {
    this.target = target;

    View view;
    target.phoneEt = Utils.findRequiredViewAsType(source, R.id.phone_et, "field 'phoneEt'", EditText.class);
    view = Utils.findRequiredView(source, R.id.get_otp_btn, "method 'getOtp'");
    view7f0a0154 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.getOtp();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MobileVerificationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.phoneEt = null;

    view7f0a0154.setOnClickListener(null);
    view7f0a0154 = null;
  }
}
