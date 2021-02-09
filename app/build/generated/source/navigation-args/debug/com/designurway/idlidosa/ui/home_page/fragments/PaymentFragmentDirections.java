package com.designurway.idlidosa.ui.home_page.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import com.designurway.idlidosa.R;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class PaymentFragmentDirections {
  private PaymentFragmentDirections() {
  }

  @NonNull
  public static ActionPaymentFragmentToPaymentSucessfulFragment actionPaymentFragmentToPaymentSucessfulFragment(
      @NonNull String JsonString) {
    return new ActionPaymentFragmentToPaymentSucessfulFragment(JsonString);
  }

  @NonNull
  public static ActionPaymentFragmentToGooglePayFragment actionPaymentFragmentToGooglePayFragment(
      @NonNull String orderId, @NonNull String amount) {
    return new ActionPaymentFragmentToGooglePayFragment(orderId, amount);
  }

  public static class ActionPaymentFragmentToPaymentSucessfulFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionPaymentFragmentToPaymentSucessfulFragment(@NonNull String JsonString) {
      if (JsonString == null) {
        throw new IllegalArgumentException("Argument \"JsonString\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("JsonString", JsonString);
    }

    @NonNull
    public ActionPaymentFragmentToPaymentSucessfulFragment setJsonString(
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
      return R.id.action_paymentFragment_to_paymentSucessfulFragment;
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
      ActionPaymentFragmentToPaymentSucessfulFragment that = (ActionPaymentFragmentToPaymentSucessfulFragment) object;
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
      return "ActionPaymentFragmentToPaymentSucessfulFragment(actionId=" + getActionId() + "){"
          + "JsonString=" + getJsonString()
          + "}";
    }
  }

  public static class ActionPaymentFragmentToGooglePayFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionPaymentFragmentToGooglePayFragment(@NonNull String orderId,
        @NonNull String amount) {
      if (orderId == null) {
        throw new IllegalArgumentException("Argument \"orderId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("orderId", orderId);
      if (amount == null) {
        throw new IllegalArgumentException("Argument \"amount\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("amount", amount);
    }

    @NonNull
    public ActionPaymentFragmentToGooglePayFragment setOrderId(@NonNull String orderId) {
      if (orderId == null) {
        throw new IllegalArgumentException("Argument \"orderId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("orderId", orderId);
      return this;
    }

    @NonNull
    public ActionPaymentFragmentToGooglePayFragment setAmount(@NonNull String amount) {
      if (amount == null) {
        throw new IllegalArgumentException("Argument \"amount\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("amount", amount);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("orderId")) {
        String orderId = (String) arguments.get("orderId");
        __result.putString("orderId", orderId);
      }
      if (arguments.containsKey("amount")) {
        String amount = (String) arguments.get("amount");
        __result.putString("amount", amount);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_paymentFragment_to_googlePayFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getOrderId() {
      return (String) arguments.get("orderId");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getAmount() {
      return (String) arguments.get("amount");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionPaymentFragmentToGooglePayFragment that = (ActionPaymentFragmentToGooglePayFragment) object;
      if (arguments.containsKey("orderId") != that.arguments.containsKey("orderId")) {
        return false;
      }
      if (getOrderId() != null ? !getOrderId().equals(that.getOrderId()) : that.getOrderId() != null) {
        return false;
      }
      if (arguments.containsKey("amount") != that.arguments.containsKey("amount")) {
        return false;
      }
      if (getAmount() != null ? !getAmount().equals(that.getAmount()) : that.getAmount() != null) {
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
      result = 31 * result + (getOrderId() != null ? getOrderId().hashCode() : 0);
      result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionPaymentFragmentToGooglePayFragment(actionId=" + getActionId() + "){"
          + "orderId=" + getOrderId()
          + ", amount=" + getAmount()
          + "}";
    }
  }
}
