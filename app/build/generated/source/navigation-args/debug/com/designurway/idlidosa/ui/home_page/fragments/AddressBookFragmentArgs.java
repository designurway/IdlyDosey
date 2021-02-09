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

public class AddressBookFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private AddressBookFragmentArgs() {
  }

  private AddressBookFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static AddressBookFragmentArgs fromBundle(@NonNull Bundle bundle) {
    AddressBookFragmentArgs __result = new AddressBookFragmentArgs();
    bundle.setClassLoader(AddressBookFragmentArgs.class.getClassLoader());
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
    if (bundle.containsKey("FromSetting")) {
      String FromSetting;
      FromSetting = bundle.getString("FromSetting");
      if (FromSetting == null) {
        throw new IllegalArgumentException("Argument \"FromSetting\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("FromSetting", FromSetting);
    } else {
      throw new IllegalArgumentException("Required argument \"FromSetting\" is missing and does not have an android:defaultValue");
    }
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
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getAmount() {
    return (String) arguments.get("amount");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getFromSetting() {
    return (String) arguments.get("FromSetting");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getOrderId() {
    return (String) arguments.get("orderId");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("amount")) {
      String amount = (String) arguments.get("amount");
      __result.putString("amount", amount);
    }
    if (arguments.containsKey("FromSetting")) {
      String FromSetting = (String) arguments.get("FromSetting");
      __result.putString("FromSetting", FromSetting);
    }
    if (arguments.containsKey("orderId")) {
      String orderId = (String) arguments.get("orderId");
      __result.putString("orderId", orderId);
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
    AddressBookFragmentArgs that = (AddressBookFragmentArgs) object;
    if (arguments.containsKey("amount") != that.arguments.containsKey("amount")) {
      return false;
    }
    if (getAmount() != null ? !getAmount().equals(that.getAmount()) : that.getAmount() != null) {
      return false;
    }
    if (arguments.containsKey("FromSetting") != that.arguments.containsKey("FromSetting")) {
      return false;
    }
    if (getFromSetting() != null ? !getFromSetting().equals(that.getFromSetting()) : that.getFromSetting() != null) {
      return false;
    }
    if (arguments.containsKey("orderId") != that.arguments.containsKey("orderId")) {
      return false;
    }
    if (getOrderId() != null ? !getOrderId().equals(that.getOrderId()) : that.getOrderId() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
    result = 31 * result + (getFromSetting() != null ? getFromSetting().hashCode() : 0);
    result = 31 * result + (getOrderId() != null ? getOrderId().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "AddressBookFragmentArgs{"
        + "amount=" + getAmount()
        + ", FromSetting=" + getFromSetting()
        + ", orderId=" + getOrderId()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    public Builder(AddressBookFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder(@NonNull String amount, @NonNull String FromSetting, @NonNull String orderId) {
      if (amount == null) {
        throw new IllegalArgumentException("Argument \"amount\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("amount", amount);
      if (FromSetting == null) {
        throw new IllegalArgumentException("Argument \"FromSetting\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("FromSetting", FromSetting);
      if (orderId == null) {
        throw new IllegalArgumentException("Argument \"orderId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("orderId", orderId);
    }

    @NonNull
    public AddressBookFragmentArgs build() {
      AddressBookFragmentArgs result = new AddressBookFragmentArgs(arguments);
      return result;
    }

    @NonNull
    public Builder setAmount(@NonNull String amount) {
      if (amount == null) {
        throw new IllegalArgumentException("Argument \"amount\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("amount", amount);
      return this;
    }

    @NonNull
    public Builder setFromSetting(@NonNull String FromSetting) {
      if (FromSetting == null) {
        throw new IllegalArgumentException("Argument \"FromSetting\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("FromSetting", FromSetting);
      return this;
    }

    @NonNull
    public Builder setOrderId(@NonNull String orderId) {
      if (orderId == null) {
        throw new IllegalArgumentException("Argument \"orderId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("orderId", orderId);
      return this;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getAmount() {
      return (String) arguments.get("amount");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getFromSetting() {
      return (String) arguments.get("FromSetting");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getOrderId() {
      return (String) arguments.get("orderId");
    }
  }
}
