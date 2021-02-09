package com.designurway.idlidosa.ui.home_page.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.designurway.idlidosa.R;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class HomeFragmentDirections {
  private HomeFragmentDirections() {
  }

  @NonNull
  public static ActionHomeFragmentToDisplayProductFragment actionHomeFragmentToDisplayProductFragment(
      @NonNull String ItemType) {
    return new ActionHomeFragmentToDisplayProductFragment(ItemType);
  }

  @NonNull
  public static NavDirections actionHomeFragmentToEmergencyFragment() {
    return new ActionOnlyNavDirections(R.id.action_homeFragment_to_emergencyFragment);
  }

  public static class ActionHomeFragmentToDisplayProductFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionHomeFragmentToDisplayProductFragment(@NonNull String ItemType) {
      if (ItemType == null) {
        throw new IllegalArgumentException("Argument \"ItemType\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("ItemType", ItemType);
    }

    @NonNull
    public ActionHomeFragmentToDisplayProductFragment setItemType(@NonNull String ItemType) {
      if (ItemType == null) {
        throw new IllegalArgumentException("Argument \"ItemType\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("ItemType", ItemType);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("ItemType")) {
        String ItemType = (String) arguments.get("ItemType");
        __result.putString("ItemType", ItemType);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_homeFragment_to_displayProductFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getItemType() {
      return (String) arguments.get("ItemType");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionHomeFragmentToDisplayProductFragment that = (ActionHomeFragmentToDisplayProductFragment) object;
      if (arguments.containsKey("ItemType") != that.arguments.containsKey("ItemType")) {
        return false;
      }
      if (getItemType() != null ? !getItemType().equals(that.getItemType()) : that.getItemType() != null) {
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
      result = 31 * result + (getItemType() != null ? getItemType().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionHomeFragmentToDisplayProductFragment(actionId=" + getActionId() + "){"
          + "ItemType=" + getItemType()
          + "}";
    }
  }
}
