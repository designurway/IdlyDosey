// Generated by view binder compiler. Do not edit!
package com.designurway.idlidosa.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.designurway.idlidosa.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityHomePageBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final BottomNavigationView bottomView;

  @NonNull
  public final Toolbar homeToolBar;

  private ActivityHomePageBinding(@NonNull ConstraintLayout rootView,
      @NonNull BottomNavigationView bottomView, @NonNull Toolbar homeToolBar) {
    this.rootView = rootView;
    this.bottomView = bottomView;
    this.homeToolBar = homeToolBar;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityHomePageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityHomePageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_home_page, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityHomePageBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bottom_view;
      BottomNavigationView bottomView = rootView.findViewById(id);
      if (bottomView == null) {
        break missingId;
      }

      id = R.id.homeToolBar;
      Toolbar homeToolBar = rootView.findViewById(id);
      if (homeToolBar == null) {
        break missingId;
      }

      return new ActivityHomePageBinding((ConstraintLayout) rootView, bottomView, homeToolBar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
