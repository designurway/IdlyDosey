package com.designurway.idlidosa.ui.auth.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavArgs;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class AuthProfileFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private AuthProfileFragmentArgs() {
  }

  private AuthProfileFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static AuthProfileFragmentArgs fromBundle(@NonNull Bundle bundle) {
    AuthProfileFragmentArgs __result = new AuthProfileFragmentArgs();
    bundle.setClassLoader(AuthProfileFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("address")) {
      String address;
      address = bundle.getString("address");
      if (address == null) {
        throw new IllegalArgumentException("Argument \"address\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("address", address);
    } else {
      throw new IllegalArgumentException("Required argument \"address\" is missing and does not have an android:defaultValue");
    }
    if (bundle.containsKey("pinCode")) {
      String pinCode;
      pinCode = bundle.getString("pinCode");
      if (pinCode == null) {
        throw new IllegalArgumentException("Argument \"pinCode\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("pinCode", pinCode);
    } else {
      throw new IllegalArgumentException("Required argument \"pinCode\" is missing and does not have an android:defaultValue");
    }
    if (bundle.containsKey("referralCode")) {
      String referralCode;
      referralCode = bundle.getString("referralCode");
      if (referralCode == null) {
        throw new IllegalArgumentException("Argument \"referralCode\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("referralCode", referralCode);
    } else {
      throw new IllegalArgumentException("Required argument \"referralCode\" is missing and does not have an android:defaultValue");
    }
    if (bundle.containsKey("phone")) {
      String phone;
      phone = bundle.getString("phone");
      if (phone == null) {
        throw new IllegalArgumentException("Argument \"phone\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("phone", phone);
    } else {
      throw new IllegalArgumentException("Required argument \"phone\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getAddress() {
    return (String) arguments.get("address");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getPinCode() {
    return (String) arguments.get("pinCode");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getReferralCode() {
    return (String) arguments.get("referralCode");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getPhone() {
    return (String) arguments.get("phone");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("address")) {
      String address = (String) arguments.get("address");
      __result.putString("address", address);
    }
    if (arguments.containsKey("pinCode")) {
      String pinCode = (String) arguments.get("pinCode");
      __result.putString("pinCode", pinCode);
    }
    if (arguments.containsKey("referralCode")) {
      String referralCode = (String) arguments.get("referralCode");
      __result.putString("referralCode", referralCode);
    }
    if (arguments.containsKey("phone")) {
      String phone = (String) arguments.get("phone");
      __result.putString("phone", phone);
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
    AuthProfileFragmentArgs that = (AuthProfileFragmentArgs) object;
    if (arguments.containsKey("address") != that.arguments.containsKey("address")) {
      return false;
    }
    if (getAddress() != null ? !getAddress().equals(that.getAddress()) : that.getAddress() != null) {
      return false;
    }
    if (arguments.containsKey("pinCode") != that.arguments.containsKey("pinCode")) {
      return false;
    }
    if (getPinCode() != null ? !getPinCode().equals(that.getPinCode()) : that.getPinCode() != null) {
      return false;
    }
    if (arguments.containsKey("referralCode") != that.arguments.containsKey("referralCode")) {
      return false;
    }
    if (getReferralCode() != null ? !getReferralCode().equals(that.getReferralCode()) : that.getReferralCode() != null) {
      return false;
    }
    if (arguments.containsKey("phone") != that.arguments.containsKey("phone")) {
      return false;
    }
    if (getPhone() != null ? !getPhone().equals(that.getPhone()) : that.getPhone() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
    result = 31 * result + (getPinCode() != null ? getPinCode().hashCode() : 0);
    result = 31 * result + (getReferralCode() != null ? getReferralCode().hashCode() : 0);
    result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "AuthProfileFragmentArgs{"
        + "address=" + getAddress()
        + ", pinCode=" + getPinCode()
        + ", referralCode=" + getReferralCode()
        + ", phone=" + getPhone()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    public Builder(AuthProfileFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder(@NonNull String address, @NonNull String pinCode, @NonNull String referralCode,
        @NonNull String phone) {
      if (address == null) {
        throw new IllegalArgumentException("Argument \"address\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("address", address);
      if (pinCode == null) {
        throw new IllegalArgumentException("Argument \"pinCode\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("pinCode", pinCode);
      if (referralCode == null) {
        throw new IllegalArgumentException("Argument \"referralCode\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("referralCode", referralCode);
      if (phone == null) {
        throw new IllegalArgumentException("Argument \"phone\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("phone", phone);
    }

    @NonNull
    public AuthProfileFragmentArgs build() {
      AuthProfileFragmentArgs result = new AuthProfileFragmentArgs(arguments);
      return result;
    }

    @NonNull
    public Builder setAddress(@NonNull String address) {
      if (address == null) {
        throw new IllegalArgumentException("Argument \"address\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("address", address);
      return this;
    }

    @NonNull
    public Builder setPinCode(@NonNull String pinCode) {
      if (pinCode == null) {
        throw new IllegalArgumentException("Argument \"pinCode\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("pinCode", pinCode);
      return this;
    }

    @NonNull
    public Builder setReferralCode(@NonNull String referralCode) {
      if (referralCode == null) {
        throw new IllegalArgumentException("Argument \"referralCode\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("referralCode", referralCode);
      return this;
    }

    @NonNull
    public Builder setPhone(@NonNull String phone) {
      if (phone == null) {
        throw new IllegalArgumentException("Argument \"phone\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("phone", phone);
      return this;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getAddress() {
      return (String) arguments.get("address");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getPinCode() {
      return (String) arguments.get("pinCode");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getReferralCode() {
      return (String) arguments.get("referralCode");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getPhone() {
      return (String) arguments.get("phone");
    }
  }
}
