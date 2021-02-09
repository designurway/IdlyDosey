package com.designurway.idlidosa.ui.auth.fragment;

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

public class ReferalCodeFragmentDirections {
  private ReferalCodeFragmentDirections() {
  }

  @NonNull
  public static ActionReferalCodeFragmentToRegisterFragment actionReferalCodeFragmentToRegisterFragment(
      @NonNull String phone, @NonNull String referralCode) {
    return new ActionReferalCodeFragmentToRegisterFragment(phone, referralCode);
  }

  public static class ActionReferalCodeFragmentToRegisterFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionReferalCodeFragmentToRegisterFragment(@NonNull String phone,
        @NonNull String referralCode) {
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
    public ActionReferalCodeFragmentToRegisterFragment setPhone(@NonNull String phone) {
      if (phone == null) {
        throw new IllegalArgumentException("Argument \"phone\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("phone", phone);
      return this;
    }

    @NonNull
    public ActionReferalCodeFragmentToRegisterFragment setReferralCode(
        @NonNull String referralCode) {
      if (referralCode == null) {
        throw new IllegalArgumentException("Argument \"referralCode\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("referralCode", referralCode);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
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
    public int getActionId() {
      return R.id.action_referalCodeFragment_to_registerFragment;
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

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionReferalCodeFragmentToRegisterFragment that = (ActionReferalCodeFragmentToRegisterFragment) object;
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
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
      result = 31 * result + (getReferralCode() != null ? getReferralCode().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionReferalCodeFragmentToRegisterFragment(actionId=" + getActionId() + "){"
          + "phone=" + getPhone()
          + ", referralCode=" + getReferralCode()
          + "}";
    }
  }
}
