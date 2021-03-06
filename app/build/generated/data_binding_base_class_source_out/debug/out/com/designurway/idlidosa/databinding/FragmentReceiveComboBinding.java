// Generated by view binder compiler. Do not edit!
package com.designurway.idlidosa.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.designurway.idlidosa.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentReceiveComboBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final RelativeLayout headerRl;

  @NonNull
  public final ImageView imgSadFace;

  @NonNull
  public final RelativeLayout layoutRl;

  @NonNull
  public final TextView receivedComboTv;

  @NonNull
  public final RecyclerView rewardsRv;

  private FragmentReceiveComboBinding(@NonNull RelativeLayout rootView,
      @NonNull RelativeLayout headerRl, @NonNull ImageView imgSadFace,
      @NonNull RelativeLayout layoutRl, @NonNull TextView receivedComboTv,
      @NonNull RecyclerView rewardsRv) {
    this.rootView = rootView;
    this.headerRl = headerRl;
    this.imgSadFace = imgSadFace;
    this.layoutRl = layoutRl;
    this.receivedComboTv = receivedComboTv;
    this.rewardsRv = rewardsRv;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentReceiveComboBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentReceiveComboBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_receive_combo, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentReceiveComboBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.header_rl;
      RelativeLayout headerRl = rootView.findViewById(id);
      if (headerRl == null) {
        break missingId;
      }

      id = R.id.img_sad_face;
      ImageView imgSadFace = rootView.findViewById(id);
      if (imgSadFace == null) {
        break missingId;
      }

      RelativeLayout layoutRl = (RelativeLayout) rootView;

      id = R.id.received_combo_tv;
      TextView receivedComboTv = rootView.findViewById(id);
      if (receivedComboTv == null) {
        break missingId;
      }

      id = R.id.rewards_rv;
      RecyclerView rewardsRv = rootView.findViewById(id);
      if (rewardsRv == null) {
        break missingId;
      }

      return new FragmentReceiveComboBinding((RelativeLayout) rootView, headerRl, imgSadFace,
          layoutRl, receivedComboTv, rewardsRv);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
