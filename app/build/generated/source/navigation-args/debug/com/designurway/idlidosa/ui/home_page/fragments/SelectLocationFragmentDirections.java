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

public class SelectLocationFragmentDirections {
  private SelectLocationFragmentDirections() {
  }

  @NonNull
  public static ActionSelectLocationFragmentToAuthProfileFragment actionSelectLocationFragmentToAuthProfileFragment(
      @NonNull String address, @NonNull String pinCode, @NonNull String referralCode,
      @NonNull String phone) {
    return new ActionSelectLocationFragmentToAuthProfileFragment(address, pinCode, referralCode, phone);
  }

  public static class ActionSelectLocationFragmentToAuthProfileFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionSelectLocationFragmentToAuthProfileFragment(@NonNull String address,
        @NonNull String pinCode, @NonNull String referralCode, @NonNull String phone) {
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
    public ActionSelectLocationFragmentToAuthProfileFragment setAddress(@NonNull String address) {
      if (address == null) {
        throw new IllegalArgumentException("Argument \"address\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("address", address);
      return this;
    }

    @NonNull
    public ActionSelectLocationFragmentToAuthProfileFragment setPinCode(@NonNull String pinCode) {
      if (pinCode == null) {
        throw new IllegalArgumentException("Argument \"pinCode\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("pinCode", pinCode);
      return this;
    }

    @NonNull
    public ActionSelectLocationFragmentToAuthProfileFragment setReferralCode(
        @NonNull String referralCode) {
      if (referralCode == null) {
        throw new IllegalArgumentException("Argument \"referralCode\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("referralCode", referralCode);
      return this;
    }

    @NonNull
    public ActionSelectLocationFragmentToAuthProfileFragment setPhone(@NonNull String phone) {
      if (phone == null) {
        throw new IllegalArgumentException("Argument \"phone\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("phone", phone);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
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
    public int getActionId() {
      return R.id.action_selectLocationFragment_to_authProfileFragment;
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

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionSelectLocationFragmentToAuthProfileFragment that = (ActionSelectLocationFragmentToAuthProfileFragment) object;
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
      if (getActionId() != that.getActionId()) {
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
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionSelectLocationFragmentToAuthProfileFragment(actionId=" + getActionId() + "){"
          + "address=" + getAddress()
          + ", pinCode=" + getPinCode()
          + ", referralCode=" + getReferralCode()
          + ", phone=" + getPhone()
          + "}";
    }
  }
}
