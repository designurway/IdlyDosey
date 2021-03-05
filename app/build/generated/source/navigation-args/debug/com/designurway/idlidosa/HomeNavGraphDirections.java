package com.designurway.idlidosa;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

public class HomeNavGraphDirections {
  private HomeNavGraphDirections() {
  }

  @NonNull
  public static NavDirections actionGlobalNotificationListFragment() {
    return new ActionOnlyNavDirections(R.id.action_global_notificationListFragment);
  }
}
