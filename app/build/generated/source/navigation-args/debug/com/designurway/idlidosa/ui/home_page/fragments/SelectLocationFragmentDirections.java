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
  public static ActionSelectLocationFragmentToProfileFragment actionSelectLocationFragmentToProfileFragment(
      @NonNull String Address) {
    return new ActionSelectLocationFragmentToProfileFragment(Address);
  }

  public static class ActionSelectLocationFragmentToProfileFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionSelectLocationFragmentToProfileFragment(@NonNull String Address) {
      if (Address == null) {
        throw new IllegalArgumentException("Argument \"Address\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("Address", Address);
    }

    @NonNull
    public ActionSelectLocationFragmentToProfileFragment setAddress(@NonNull String Address) {
      if (Address == null) {
        throw new IllegalArgumentException("Argument \"Address\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("Address", Address);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("Address")) {
        String Address = (String) arguments.get("Address");
        __result.putString("Address", Address);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_selectLocationFragment_to_profileFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getAddress() {
      return (String) arguments.get("Address");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionSelectLocationFragmentToProfileFragment that = (ActionSelectLocationFragmentToProfileFragment) object;
      if (arguments.containsKey("Address") != that.arguments.containsKey("Address")) {
        return false;
      }
      if (getAddress() != null ? !getAddress().equals(that.getAddress()) : that.getAddress() != null) {
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
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionSelectLocationFragmentToProfileFragment(actionId=" + getActionId() + "){"
          + "Address=" + getAddress()
          + "}";
    }
  }
}
