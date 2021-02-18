package com.designurway.idlidosa.ui.home_page.fragments;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.designurway.idlidosa.R;

public class ServiceNotAvailableFragmentDirections {
  private ServiceNotAvailableFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionServiceNotAvailableFragmentToHomeFragment() {
    return new ActionOnlyNavDirections(R.id.action_serviceNotAvailableFragment_to_homeFragment);
  }
}
