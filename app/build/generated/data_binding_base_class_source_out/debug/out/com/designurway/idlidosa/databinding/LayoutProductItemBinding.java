// Generated by view binder compiler. Do not edit!
package com.designurway.idlidosa.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public final class LayoutProductItemBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final CircleImageView itemImgCiv;

  @NonNull
  public final TextView itemNameTv;

  @NonNull
  public final TextView rsTv;

  @NonNull
  public final TextView txtPrice;

  private LayoutProductItemBinding(@NonNull ConstraintLayout rootView,
      @NonNull CircleImageView itemImgCiv, @NonNull TextView itemNameTv, @NonNull TextView rsTv,
      @NonNull TextView txtPrice) {
    this.rootView = rootView;
    this.itemImgCiv = itemImgCiv;
    this.itemNameTv = itemNameTv;
    this.rsTv = rsTv;
    this.txtPrice = txtPrice;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static LayoutProductItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LayoutProductItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.layout_product_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LayoutProductItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.item_img_civ;
      CircleImageView itemImgCiv = rootView.findViewById(id);
      if (itemImgCiv == null) {
        break missingId;
      }

      id = R.id.item_name_tv;
      TextView itemNameTv = rootView.findViewById(id);
      if (itemNameTv == null) {
        break missingId;
      }

      id = R.id.rs_tv;
      TextView rsTv = rootView.findViewById(id);
      if (rsTv == null) {
        break missingId;
      }

      id = R.id.txt_price;
      TextView txtPrice = rootView.findViewById(id);
      if (txtPrice == null) {
        break missingId;
      }

      return new LayoutProductItemBinding((ConstraintLayout) rootView, itemImgCiv, itemNameTv, rsTv,
          txtPrice);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
