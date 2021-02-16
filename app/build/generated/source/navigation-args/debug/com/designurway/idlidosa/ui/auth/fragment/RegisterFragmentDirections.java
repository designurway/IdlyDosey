package com.designurway.idlidosa.ui.auth.fragment;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.designurway.idlidosa.R;

public class RegisterFragmentDirections {
  private RegisterFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionRegisterFragmentToSelectLocationFragment() {
    return new ActionOnlyNavDirections(R.id.action_registerFragment_to_selectLocationFragment);
  }
}
