package com.designurway.idlidosa.ui.home_page.fragments;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.designurway.idlidosa.HomeNavGraphDirections;
import com.designurway.idlidosa.R;

public class ProfileFragmentDirections {
  private ProfileFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionProfileFragment4ToHomeFragment() {
    return new ActionOnlyNavDirections(R.id.action_profileFragment4_to_homeFragment);
  }

  @NonNull
  public static NavDirections actionGlobalNotificationListFragment() {
    return HomeNavGraphDirections.actionGlobalNotificationListFragment();
  }
}
