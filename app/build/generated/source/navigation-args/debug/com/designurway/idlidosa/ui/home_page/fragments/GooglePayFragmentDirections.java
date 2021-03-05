package com.designurway.idlidosa.ui.home_page.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import com.designurway.idlidosa.HomeNavGraphDirections;
import com.designurway.idlidosa.R;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class GooglePayFragmentDirections {
  private GooglePayFragmentDirections() {
  }

  @NonNull
  public static ActionGooglePayFragmentToPaymentSucessfulFragment actionGooglePayFragmentToPaymentSucessfulFragment(
      @NonNull String JsonString) {
    return new ActionGooglePayFragmentToPaymentSucessfulFragment(JsonString);
  }

  @NonNull
  public static NavDirections actionGlobalNotificationListFragment() {
    return HomeNavGraphDirections.actionGlobalNotificationListFragment();
  }

  public static class ActionGooglePayFragmentToPaymentSucessfulFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionGooglePayFragmentToPaymentSucessfulFragment(@NonNull String JsonString) {
      if (JsonString == null) {
        throw new IllegalArgumentException("Argument \"JsonString\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("JsonString", JsonString);
    }

    @NonNull
    public ActionGooglePayFragmentToPaymentSucessfulFragment setJsonString(
        @NonNull String JsonString) {
      if (JsonString == null) {
        throw new IllegalArgumentException("Argument \"JsonString\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("JsonString", JsonString);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("JsonString")) {
        String JsonString = (String) arguments.get("JsonString");
        __result.putString("JsonString", JsonString);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_googlePayFragment_to_paymentSucessfulFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getJsonString() {
      return (String) arguments.get("JsonString");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionGooglePayFragmentToPaymentSucessfulFragment that = (ActionGooglePayFragmentToPaymentSucessfulFragment) object;
      if (arguments.containsKey("JsonString") != that.arguments.containsKey("JsonString")) {
        return false;
      }
      if (getJsonString() != null ? !getJsonString().equals(that.getJsonString()) : that.getJsonString() != null) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (getJsonString() != null ? getJsonString().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionGooglePayFragmentToPaymentSucessfulFragment(actionId=" + getActionId() + "){"
          + "JsonString=" + getJsonString()
          + "}";
    }
  }
}
