// Generated code from Butter Knife. Do not modify!
package com.designurway.idlidosa.a.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.designurway.idlidosa.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReferralCodeActivity_ViewBinding implements Unbinder {
  private ReferralCodeActivity target;

  private View view7f0a02fb;

  private View view7f0a02dc;

  @UiThread
  public ReferralCodeActivity_ViewBinding(ReferralCodeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ReferralCodeActivity_ViewBinding(final ReferralCodeActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.submit_referral_code_btn, "field 'submitRefer' and method 'submitReferralCode'");
    target.submitRefer = Utils.castView(view, R.id.submit_referral_code_btn, "field 'submitRefer'", Button.class);
    view7f0a02fb = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.submitReferralCode();
      }
    });
    target.referralCodeEt = Utils.findRequiredViewAsType(source, R.id.referral_code_et, "field 'referralCodeEt'", EditText.class);
    view = Utils.findRequiredView(source, R.id.skip_tv, "method 'goToNext'");
    view7f0a02dc = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goToNext();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ReferralCodeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.submitRefer = null;
    target.referralCodeEt = null;

    view7f0a02fb.setOnClickListener(null);
    view7f0a02fb = null;
    view7f0a02dc.setOnClickListener(null);
    view7f0a02dc = null;
  }
}
