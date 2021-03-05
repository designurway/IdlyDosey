package com.designurway.idlidosa.a.paytmallinonesdk;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import com.designurway.idlidosa.HomeNavGraphDirections;

public class PaytmActivityDirections {
  private PaytmActivityDirections() {
  }

  @NonNull
  public static NavDirections actionGlobalNotificationListFragment() {
    return HomeNavGraphDirections.actionGlobalNotificationListFragment();
  }
}
