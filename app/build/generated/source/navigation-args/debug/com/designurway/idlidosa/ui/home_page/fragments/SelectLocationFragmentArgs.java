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

public class SelectLocationFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private SelectLocationFragmentArgs() {
  }

  private SelectLocationFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static SelectLocationFragmentArgs fromBundle(@NonNull Bundle bundle) {
    SelectLocationFragmentArgs __result = new SelectLocationFragmentArgs();
    bundle.setClassLoader(SelectLocationFragmentArgs.class.getClassLoader());
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
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getPhone() {
    return (String) arguments.get("phone");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getReferralCode() {
    return (String) arguments.get("referralCode");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("phone")) {
      String phone = (String) arguments.get("phone");
      __result.putString("phone", phone);
    }
    if (arguments.containsKey("referralCode")) {
      String referralCode = (String) arguments.get("referralCode");
      __result.putString("referralCode", referralCode);
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
    SelectLocationFragmentArgs that = (SelectLocationFragmentArgs) object;
    if (arguments.containsKey("phone") != that.arguments.containsKey("phone")) {
      return false;
    }
    if (getPhone() != null ? !getPhone().equals(that.getPhone()) : that.getPhone() != null) {
      return false;
    }
    if (arguments.containsKey("referralCode") != that.arguments.containsKey("referralCode")) {
      return false;
    }
    if (getReferralCode() != null ? !getReferralCode().equals(that.getReferralCode()) : that.getReferralCode() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
    result = 31 * result + (getReferralCode() != null ? getReferralCode().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "SelectLocationFragmentArgs{"
        + "phone=" + getPhone()
        + ", referralCode=" + getReferralCode()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    public Builder(SelectLocationFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder(@NonNull String phone, @NonNull String referralCode) {
      if (phone == null) {
        throw new IllegalArgumentException("Argument \"phone\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("phone", phone);
      if (referralCode == null) {
        throw new IllegalArgumentException("Argument \"referralCode\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("referralCode", referralCode);
    }

    @NonNull
    public SelectLocationFragmentArgs build() {
      SelectLocationFragmentArgs result = new SelectLocationFragmentArgs(arguments);
      return result;
    }

    @NonNull
    public Builder setPhone(@NonNull String phone) {
      if (phone == null) {
        throw new IllegalArgumentException("Argument \"phone\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("phone", phone);
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

    @SuppressWarnings("unchecked")
    @NonNull
    public String getPhone() {
      return (String) arguments.get("phone");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getReferralCode() {
      return (String) arguments.get("referralCode");
    }
  }
}
