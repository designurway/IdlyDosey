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

public class DisplayProductFragmentDirections {
  private DisplayProductFragmentDirections() {
  }

  @NonNull
  public static ActionDisplayProductFragmentToProductDetailfragment actionDisplayProductFragmentToProductDetailfragment(
      @NonNull String productId, @NonNull String present) {
    return new ActionDisplayProductFragmentToProductDetailfragment(productId, present);
  }

  public static class ActionDisplayProductFragmentToProductDetailfragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionDisplayProductFragmentToProductDetailfragment(@NonNull String productId,
        @NonNull String present) {
      if (productId == null) {
        throw new IllegalArgumentException("Argument \"productId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("productId", productId);
      if (present == null) {
        throw new IllegalArgumentException("Argument \"present\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("present", present);
    }

    @NonNull
    public ActionDisplayProductFragmentToProductDetailfragment setProductId(
        @NonNull String productId) {
      if (productId == null) {
        throw new IllegalArgumentException("Argument \"productId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("productId", productId);
      return this;
    }

    @NonNull
    public ActionDisplayProductFragmentToProductDetailfragment setPresent(@NonNull String present) {
      if (present == null) {
        throw new IllegalArgumentException("Argument \"present\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("present", present);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("productId")) {
        String productId = (String) arguments.get("productId");
        __result.putString("productId", productId);
      }
      if (arguments.containsKey("present")) {
        String present = (String) arguments.get("present");
        __result.putString("present", present);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_displayProductFragment_to_productDetailfragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getProductId() {
      return (String) arguments.get("productId");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getPresent() {
      return (String) arguments.get("present");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionDisplayProductFragmentToProductDetailfragment that = (ActionDisplayProductFragmentToProductDetailfragment) object;
      if (arguments.containsKey("productId") != that.arguments.containsKey("productId")) {
        return false;
      }
      if (getProductId() != null ? !getProductId().equals(that.getProductId()) : that.getProductId() != null) {
        return false;
      }
      if (arguments.containsKey("present") != that.arguments.containsKey("present")) {
        return false;
      }
      if (getPresent() != null ? !getPresent().equals(that.getPresent()) : that.getPresent() != null) {
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
      result = 31 * result + (getProductId() != null ? getProductId().hashCode() : 0);
      result = 31 * result + (getPresent() != null ? getPresent().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionDisplayProductFragmentToProductDetailfragment(actionId=" + getActionId() + "){"
          + "productId=" + getProductId()
          + ", present=" + getPresent()
          + "}";
    }
  }
}
