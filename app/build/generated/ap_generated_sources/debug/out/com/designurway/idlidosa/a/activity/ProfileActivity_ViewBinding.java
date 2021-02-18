// Generated code from Butter Knife. Do not modify!
package com.designurway.idlidosa.a.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.designurway.idlidosa.R;
import com.google.android.material.textfield.TextInputEditText;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProfileActivity_ViewBinding implements Unbinder {
  private ProfileActivity target;

  private View view7f0a02b2;

  @UiThread
  public ProfileActivity_ViewBinding(ProfileActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ProfileActivity_ViewBinding(final ProfileActivity target, View source) {
    this.target = target;

    View view;
    target.userNameTv = Utils.findRequiredViewAsType(source, R.id.name_field_et, "field 'userNameTv'", TextInputEditText.class);
    target.emailEt = Utils.findRequiredViewAsType(source, R.id.email_field_et, "field 'emailEt'", TextInputEditText.class);
    target.phoneEt = Utils.findRequiredViewAsType(source, R.id.phone_num_field_et, "field 'phoneEt'", TextInputEditText.class);
    target.addressEt = Utils.findRequiredViewAsType(source, R.id.address_field_et, "field 'addressEt'", TextInputEditText.class);
    target.profileImageCiv = Utils.findRequiredViewAsType(source, R.id.person_pic_imgv, "field 'profileImageCiv'", CircleImageView.class);
    target.titleTv = Utils.findRequiredViewAsType(source, R.id.toolbar_title_tv, "field 'titleTv'", TextView.class);
    target.iv_camera = Utils.findRequiredViewAsType(source, R.id.iv_camera, "field 'iv_camera'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.save_btn, "method 'saveProfileDetails'");
    view7f0a02b2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.saveProfileDetails();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ProfileActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.userNameTv = null;
    target.emailEt = null;
    target.phoneEt = null;
    target.addressEt = null;
    target.profileImageCiv = null;
    target.titleTv = null;
    target.iv_camera = null;

    view7f0a02b2.setOnClickListener(null);
    view7f0a02b2 = null;
  }
}
