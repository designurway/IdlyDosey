package com.designurway.idlidosa.ui.home_page.fragments;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.designurway.idlidosa.R;

public class PaymentSucessfulFragmentDirections {
  private PaymentSucessfulFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionPaymentSucessfulFragmentToHomeFragment() {
    return new ActionOnlyNavDirections(R.id.action_paymentSucessfulFragment_to_homeFragment);
  }
}
