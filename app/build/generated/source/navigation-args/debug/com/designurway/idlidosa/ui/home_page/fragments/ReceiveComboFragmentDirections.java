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

public class ReceiveComboFragmentDirections {
  private ReceiveComboFragmentDirections() {
  }

  @NonNull
  public static ActionReceiveComboFragmentToReciveComboAddressFragment actionReceiveComboFragmentToReciveComboAddressFragment(
      @NonNull String comboId, @NonNull String productId) {
    return new ActionReceiveComboFragmentToReciveComboAddressFragment(comboId, productId);
  }

  public static class ActionReceiveComboFragmentToReciveComboAddressFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionReceiveComboFragmentToReciveComboAddressFragment(@NonNull String comboId,
        @NonNull String productId) {
      if (comboId == null) {
        throw new IllegalArgumentException("Argument \"comboId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("comboId", comboId);
      if (productId == null) {
        throw new IllegalArgumentException("Argument \"productId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("productId", productId);
    }

    @NonNull
    public ActionReceiveComboFragmentToReciveComboAddressFragment setComboId(
        @NonNull String comboId) {
      if (comboId == null) {
        throw new IllegalArgumentException("Argument \"comboId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("comboId", comboId);
      return this;
    }

    @NonNull
    public ActionReceiveComboFragmentToReciveComboAddressFragment setProductId(
        @NonNull String productId) {
      if (productId == null) {
        throw new IllegalArgumentException("Argument \"productId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("productId", productId);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("comboId")) {
        String comboId = (String) arguments.get("comboId");
        __result.putString("comboId", comboId);
      }
      if (arguments.containsKey("productId")) {
        String productId = (String) arguments.get("productId");
        __result.putString("productId", productId);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_receiveComboFragment_to_reciveComboAddressFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getComboId() {
      return (String) arguments.get("comboId");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getProductId() {
      return (String) arguments.get("productId");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionReceiveComboFragmentToReciveComboAddressFragment that = (ActionReceiveComboFragmentToReciveComboAddressFragment) object;
      if (arguments.containsKey("comboId") != that.arguments.containsKey("comboId")) {
        return false;
      }
      if (getComboId() != null ? !getComboId().equals(that.getComboId()) : that.getComboId() != null) {
        return false;
      }
      if (arguments.containsKey("productId") != that.arguments.containsKey("productId")) {
        return false;
      }
      if (getProductId() != null ? !getProductId().equals(that.getProductId()) : that.getProductId() != null) {
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
      result = 31 * result + (getComboId() != null ? getComboId().hashCode() : 0);
      result = 31 * result + (getProductId() != null ? getProductId().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionReceiveComboFragmentToReciveComboAddressFragment(actionId=" + getActionId() + "){"
          + "comboId=" + getComboId()
          + ", productId=" + getProductId()
          + "}";
    }
  }
}
