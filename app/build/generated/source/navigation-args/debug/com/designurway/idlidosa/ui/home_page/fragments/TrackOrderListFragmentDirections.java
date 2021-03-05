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

public class TrackOrderListFragmentDirections {
  private TrackOrderListFragmentDirections() {
  }

  @NonNull
  public static ActionTrackOrderListFragmentToTrackOrderFragment actionTrackOrderListFragmentToTrackOrderFragment(
      @NonNull String orderId, @NonNull String amount) {
    return new ActionTrackOrderListFragmentToTrackOrderFragment(orderId, amount);
  }

  @NonNull
  public static NavDirections actionGlobalNotificationListFragment() {
    return HomeNavGraphDirections.actionGlobalNotificationListFragment();
  }

  public static class ActionTrackOrderListFragmentToTrackOrderFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionTrackOrderListFragmentToTrackOrderFragment(@NonNull String orderId,
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
    public ActionTrackOrderListFragmentToTrackOrderFragment setOrderId(@NonNull String orderId) {
      if (orderId == null) {
        throw new IllegalArgumentException("Argument \"orderId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("orderId", orderId);
      return this;
    }

    @NonNull
    public ActionTrackOrderListFragmentToTrackOrderFragment setAmount(@NonNull String amount) {
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
      return R.id.action_trackOrderListFragment_to_trackOrderFragment;
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
      ActionTrackOrderListFragmentToTrackOrderFragment that = (ActionTrackOrderListFragmentToTrackOrderFragment) object;
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
      return "ActionTrackOrderListFragmentToTrackOrderFragment(actionId=" + getActionId() + "){"
          + "orderId=" + getOrderId()
          + ", amount=" + getAmount()
          + "}";
    }
  }
}
