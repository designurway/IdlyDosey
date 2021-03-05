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

public class ChangeAddressFragmentDirections {
  private ChangeAddressFragmentDirections() {
  }

  @NonNull
  public static ActionChangeAddressFragmentToAddressBookFragment actionChangeAddressFragmentToAddressBookFragment(
      @NonNull String amount, @NonNull String FromSetting, @NonNull String orderId) {
    return new ActionChangeAddressFragmentToAddressBookFragment(amount, FromSetting, orderId);
  }

  @NonNull
  public static NavDirections actionGlobalNotificationListFragment() {
    return HomeNavGraphDirections.actionGlobalNotificationListFragment();
  }

  public static class ActionChangeAddressFragmentToAddressBookFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionChangeAddressFragmentToAddressBookFragment(@NonNull String amount,
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
    public ActionChangeAddressFragmentToAddressBookFragment setAmount(@NonNull String amount) {
      if (amount == null) {
        throw new IllegalArgumentException("Argument \"amount\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("amount", amount);
      return this;
    }

    @NonNull
    public ActionChangeAddressFragmentToAddressBookFragment setFromSetting(
        @NonNull String FromSetting) {
      if (FromSetting == null) {
        throw new IllegalArgumentException("Argument \"FromSetting\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("FromSetting", FromSetting);
      return this;
    }

    @NonNull
    public ActionChangeAddressFragmentToAddressBookFragment setOrderId(@NonNull String orderId) {
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
      return R.id.action_changeAddressFragment_to_addressBookFragment;
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
      ActionChangeAddressFragmentToAddressBookFragment that = (ActionChangeAddressFragmentToAddressBookFragment) object;
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
      return "ActionChangeAddressFragmentToAddressBookFragment(actionId=" + getActionId() + "){"
          + "amount=" + getAmount()
          + ", FromSetting=" + getFromSetting()
          + ", orderId=" + getOrderId()
          + "}";
    }
  }
}
