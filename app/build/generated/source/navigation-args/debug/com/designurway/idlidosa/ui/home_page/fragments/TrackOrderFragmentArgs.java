package com.designurway.idlidosa.ui.home_page.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavArgs;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class TrackOrderFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private TrackOrderFragmentArgs() {
  }

  private TrackOrderFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static TrackOrderFragmentArgs fromBundle(@NonNull Bundle bundle) {
    TrackOrderFragmentArgs __result = new TrackOrderFragmentArgs();
    bundle.setClassLoader(TrackOrderFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("orderId")) {
      String orderId;
      orderId = bundle.getString("orderId");
      if (orderId == null) {
        throw new IllegalArgumentException("Argument \"orderId\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("orderId", orderId);
    } else {
      throw new IllegalArgumentException("Required argument \"orderId\" is missing and does not have an android:defaultValue");
    }
    if (bundle.containsKey("amount")) {
      String amount;
      amount = bundle.getString("amount");
      if (amount == null) {
        throw new IllegalArgumentException("Argument \"amount\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("amount", amount);
    } else {
      throw new IllegalArgumentException("Required argument \"amount\" is missing and does not have an android:defaultValue");
    }
    return __result;
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

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
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
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    TrackOrderFragmentArgs that = (TrackOrderFragmentArgs) object;
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
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getOrderId() != null ? getOrderId().hashCode() : 0);
    result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "TrackOrderFragmentArgs{"
        + "orderId=" + getOrderId()
        + ", amount=" + getAmount()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    public Builder(TrackOrderFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder(@NonNull String orderId, @NonNull String amount) {
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
    public TrackOrderFragmentArgs build() {
      TrackOrderFragmentArgs result = new TrackOrderFragmentArgs(arguments);
      return result;
    }

    @NonNull
    public Builder setOrderId(@NonNull String orderId) {
      if (orderId == null) {
        throw new IllegalArgumentException("Argument \"orderId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("orderId", orderId);
      return this;
    }

    @NonNull
    public Builder setAmount(@NonNull String amount) {
      if (amount == null) {
        throw new IllegalArgumentException("Argument \"amount\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("amount", amount);
      return this;
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
  }
}
