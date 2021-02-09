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

public class MobileVerificationFragmentDirections {
  private MobileVerificationFragmentDirections() {
  }

  @NonNull
  public static ActionMobileVerificationFragment2ToOtpVerficationFragment actionMobileVerificationFragment2ToOtpVerficationFragment(
      @NonNull String phone) {
    return new ActionMobileVerificationFragment2ToOtpVerficationFragment(phone);
  }

  @NonNull
  public static ActionMobileVerificationFragment2ToReferalCodeFragment actionMobileVerificationFragment2ToReferalCodeFragment(
      @NonNull String phone) {
    return new ActionMobileVerificationFragment2ToReferalCodeFragment(phone);
  }

  public static class ActionMobileVerificationFragment2ToOtpVerficationFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionMobileVerificationFragment2ToOtpVerficationFragment(@NonNull String phone) {
      if (phone == null) {
        throw new IllegalArgumentException("Argument \"phone\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("phone", phone);
    }

    @NonNull
    public ActionMobileVerificationFragment2ToOtpVerficationFragment setPhone(
        @NonNull String phone) {
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
      if (arguments.containsKey("phone")) {
        String phone = (String) arguments.get("phone");
        __result.putString("phone", phone);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_mobileVerificationFragment2_to_otpVerficationFragment;
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
      ActionMobileVerificationFragment2ToOtpVerficationFragment that = (ActionMobileVerificationFragment2ToOtpVerficationFragment) object;
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
      result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionMobileVerificationFragment2ToOtpVerficationFragment(actionId=" + getActionId() + "){"
          + "phone=" + getPhone()
          + "}";
    }
  }

  public static class ActionMobileVerificationFragment2ToReferalCodeFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionMobileVerificationFragment2ToReferalCodeFragment(@NonNull String phone) {
      if (phone == null) {
        throw new IllegalArgumentException("Argument \"phone\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("phone", phone);
    }

    @NonNull
    public ActionMobileVerificationFragment2ToReferalCodeFragment setPhone(@NonNull String phone) {
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
      if (arguments.containsKey("phone")) {
        String phone = (String) arguments.get("phone");
        __result.putString("phone", phone);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_mobileVerificationFragment2_to_referalCodeFragment;
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
      ActionMobileVerificationFragment2ToReferalCodeFragment that = (ActionMobileVerificationFragment2ToReferalCodeFragment) object;
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
      result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionMobileVerificationFragment2ToReferalCodeFragment(actionId=" + getActionId() + "){"
          + "phone=" + getPhone()
          + "}";
    }
  }
}
