package com.designurway.idlidosa.ui.home_page.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.designurway.idlidosa.HomeNavGraphDirections;
import com.designurway.idlidosa.R;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class SettingsFragmentDirections {
  private SettingsFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionSettingsFragmentToOrderHistoryFragment() {
    return new ActionOnlyNavDirections(R.id.action_settingsFragment_to_orderHistoryFragment);
  }

  @NonNull
  public static ActionSettingsFragmentToAddressBookFragment actionSettingsFragmentToAddressBookFragment(
      @NonNull String amount, @NonNull String FromSetting, @NonNull String orderId) {
    return new ActionSettingsFragmentToAddressBookFragment(amount, FromSetting, orderId);
  }

  @NonNull
  public static NavDirections actionSettingsFragmentToReceiveComboFragment() {
    return new ActionOnlyNavDirections(R.id.action_settingsFragment_to_receiveComboFragment);
  }

  @NonNull
  public static NavDirections actionSettingsFragmentToReferFriendFragment() {
    return new ActionOnlyNavDirections(R.id.action_settingsFragment_to_referFriendFragment);
  }

  @NonNull
  public static NavDirections actionSettingsFragmentToSupportFragment() {
    return new ActionOnlyNavDirections(R.id.action_settingsFragment_to_supportFragment);
  }

  @NonNull
  public static NavDirections actionSettingsFragmentToTrackOrderListFragment() {
    return new ActionOnlyNavDirections(R.id.action_settingsFragment_to_trackOrderListFragment);
  }

  @NonNull
  public static NavDirections actionGlobalNotificationListFragment() {
    return HomeNavGraphDirections.actionGlobalNotificationListFragment();
  }

  public static class ActionSettingsFragmentToAddressBookFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionSettingsFragmentToAddressBookFragment(@NonNull String amount,
        @NonNull String FromSetting, @NonNull String orderId) {
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
    public ActionSettingsFragmentToAddressBookFragment setAmount(@NonNull String amount) {
      if (amount == null) {
        throw new IllegalArgumentException("Argument \"amount\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("amount", amount);
      return this;
    }

    @NonNull
    public ActionSettingsFragmentToAddressBookFragment setFromSetting(@NonNull String FromSetting) {
      if (FromSetting == null) {
        throw new IllegalArgumentException("Argument \"FromSetting\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("FromSetting", FromSetting);
      return this;
    }

    @NonNull
    public ActionSettingsFragmentToAddressBookFragment setOrderId(@NonNull String orderId) {
      if (orderId == null) {
        throw new IllegalArgumentException("Argument \"orderId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("orderId", orderId);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
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
    public int getActionId() {
      return R.id.action_settingsFragment_to_addressBookFragment;
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

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionSettingsFragmentToAddressBookFragment that = (ActionSettingsFragmentToAddressBookFragment) object;
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
      if (getActionId() != that.getActionId()) {
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
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionSettingsFragmentToAddressBookFragment(actionId=" + getActionId() + "){"
          + "amount=" + getAmount()
          + ", FromSetting=" + getFromSetting()
          + ", orderId=" + getOrderId()
          + "}";
    }
  }
}
