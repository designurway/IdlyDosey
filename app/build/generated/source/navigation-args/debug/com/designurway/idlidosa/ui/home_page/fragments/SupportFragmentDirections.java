package com.designurway.idlidosa.ui.home_page.fragments;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import com.designurway.idlidosa.HomeNavGraphDirections;

public class SupportFragmentDirections {
  private SupportFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionGlobalNotificationListFragment() {
    return HomeNavGraphDirections.actionGlobalNotificationListFragment();
  }
}
