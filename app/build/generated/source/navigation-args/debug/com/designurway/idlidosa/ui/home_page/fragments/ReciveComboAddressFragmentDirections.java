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

public class ReciveComboAddressFragmentDirections {
  private ReciveComboAddressFragmentDirections() {
  }

  @NonNull
  public static ActionReciveComboAddressFragmentToPaymentFragment actionReciveComboAddressFragmentToPaymentFragment(
      @NonNull String name, @NonNull String address, @NonNull String amount, @NonNull String phone,
      @NonNull String comboId, @NonNull String productId, @NonNull String orderId) {
    return new ActionReciveComboAddressFragmentToPaymentFragment(name, address, amount, phone, comboId, productId, orderId);
  }

  @NonNull
  public static ActionReciveComboAddressFragmentToPaytmActivity actionReciveComboAddressFragmentToPaytmActivity(
      @NonNull String name, @NonNull String address, @NonNull String amount, @NonNull String phone,
      @NonNull String comboId, @NonNull String productId, @NonNull String orderId) {
    return new ActionReciveComboAddressFragmentToPaytmActivity(name, address, amount, phone, comboId, productId, orderId);
  }

  public static class ActionReciveComboAddressFragmentToPaymentFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionReciveComboAddressFragmentToPaymentFragment(@NonNull String name,
        @NonNull String address, @NonNull String amount, @NonNull String phone,
        @NonNull String comboId, @NonNull String productId, @NonNull String orderId) {
      if (name == null) {
        throw new IllegalArgumentException("Argument \"name\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("name", name);
      if (address == null) {
        throw new IllegalArgumentException("Argument \"address\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("address", address);
      if (amount == null) {
        throw new IllegalArgumentException("Argument \"amount\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("amount", amount);
      if (phone == null) {
        throw new IllegalArgumentException("Argument \"phone\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("phone", phone);
      if (comboId == null) {
        throw new IllegalArgumentException("Argument \"comboId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("comboId", comboId);
      if (productId == null) {
        throw new IllegalArgumentException("Argument \"productId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("productId", productId);
      if (orderId == null) {
        throw new IllegalArgumentException("Argument \"orderId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("orderId", orderId);
    }

    @NonNull
    public ActionReciveComboAddressFragmentToPaymentFragment setName(@NonNull String name) {
      if (name == null) {
        throw new IllegalArgumentException("Argument \"name\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("name", name);
      return this;
    }

    @NonNull
    public ActionReciveComboAddressFragmentToPaymentFragment setAddress(@NonNull String address) {
      if (address == null) {
        throw new IllegalArgumentException("Argument \"address\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("address", address);
      return this;
    }

    @NonNull
    public ActionReciveComboAddressFragmentToPaymentFragment setAmount(@NonNull String amount) {
      if (amount == null) {
        throw new IllegalArgumentException("Argument \"amount\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("amount", amount);
      return this;
    }

    @NonNull
    public ActionReciveComboAddressFragmentToPaymentFragment setPhone(@NonNull String phone) {
      if (phone == null) {
        throw new IllegalArgumentException("Argument \"phone\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("phone", phone);
      return this;
    }

    @NonNull
    public ActionReciveComboAddressFragmentToPaymentFragment setComboId(@NonNull String comboId) {
      if (comboId == null) {
        throw new IllegalArgumentException("Argument \"comboId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("comboId", comboId);
      return this;
    }

    @NonNull
    public ActionReciveComboAddressFragmentToPaymentFragment setProductId(
        @NonNull String productId) {
      if (productId == null) {
        throw new IllegalArgumentException("Argument \"productId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("productId", productId);
      return this;
    }

    @NonNull
    public ActionReciveComboAddressFragmentToPaymentFragment setOrderId(@NonNull String orderId) {
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
      if (arguments.containsKey("name")) {
        String name = (String) arguments.get("name");
        __result.putString("name", name);
      }
      if (arguments.containsKey("address")) {
        String address = (String) arguments.get("address");
        __result.putString("address", address);
      }
      if (arguments.containsKey("amount")) {
        String amount = (String) arguments.get("amount");
        __result.putString("amount", amount);
      }
      if (arguments.containsKey("phone")) {
        String phone = (String) arguments.get("phone");
        __result.putString("phone", phone);
      }
      if (arguments.containsKey("comboId")) {
        String comboId = (String) arguments.get("comboId");
        __result.putString("comboId", comboId);
      }
      if (arguments.containsKey("productId")) {
        String productId = (String) arguments.get("productId");
        __result.putString("productId", productId);
      }
      if (arguments.containsKey("orderId")) {
        String orderId = (String) arguments.get("orderId");
        __result.putString("orderId", orderId);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_reciveComboAddressFragment_to_paymentFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getName() {
      return (String) arguments.get("name");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getAddress() {
      return (String) arguments.get("address");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getAmount() {
      return (String) arguments.get("amount");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getPhone() {
      return (String) arguments.get("phone");
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
      ActionReciveComboAddressFragmentToPaymentFragment that = (ActionReciveComboAddressFragmentToPaymentFragment) object;
      if (arguments.containsKey("name") != that.arguments.containsKey("name")) {
        return false;
      }
      if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) {
        return false;
      }
      if (arguments.containsKey("address") != that.arguments.containsKey("address")) {
        return false;
      }
      if (getAddress() != null ? !getAddress().equals(that.getAddress()) : that.getAddress() != null) {
        return false;
      }
      if (arguments.containsKey("amount") != that.arguments.containsKey("amount")) {
        return false;
      }
      if (getAmount() != null ? !getAmount().equals(that.getAmount()) : that.getAmount() != null) {
        return false;
      }
      if (arguments.containsKey("phone") != that.arguments.containsKey("phone")) {
        return false;
      }
      if (getPhone() != null ? !getPhone().equals(that.getPhone()) : that.getPhone() != null) {
        return false;
      }
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
      result = 31 * result + (getName() != null ? getName().hashCode() : 0);
      result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
      result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
      result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
      result = 31 * result + (getComboId() != null ? getComboId().hashCode() : 0);
      result = 31 * result + (getProductId() != null ? getProductId().hashCode() : 0);
      result = 31 * result + (getOrderId() != null ? getOrderId().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionReciveComboAddressFragmentToPaymentFragment(actionId=" + getActionId() + "){"
          + "name=" + getName()
          + ", address=" + getAddress()
          + ", amount=" + getAmount()
          + ", phone=" + getPhone()
          + ", comboId=" + getComboId()
          + ", productId=" + getProductId()
          + ", orderId=" + getOrderId()
          + "}";
    }
  }

  public static class ActionReciveComboAddressFragmentToPaytmActivity implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionReciveComboAddressFragmentToPaytmActivity(@NonNull String name,
        @NonNull String address, @NonNull String amount, @NonNull String phone,
        @NonNull String comboId, @NonNull String productId, @NonNull String orderId) {
      if (name == null) {
        throw new IllegalArgumentException("Argument \"name\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("name", name);
      if (address == null) {
        throw new IllegalArgumentException("Argument \"address\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("address", address);
      if (amount == null) {
        throw new IllegalArgumentException("Argument \"amount\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("amount", amount);
      if (phone == null) {
        throw new IllegalArgumentException("Argument \"phone\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("phone", phone);
      if (comboId == null) {
        throw new IllegalArgumentException("Argument \"comboId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("comboId", comboId);
      if (productId == null) {
        throw new IllegalArgumentException("Argument \"productId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("productId", productId);
      if (orderId == null) {
        throw new IllegalArgumentException("Argument \"orderId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("orderId", orderId);
    }

    @NonNull
    public ActionReciveComboAddressFragmentToPaytmActivity setName(@NonNull String name) {
      if (name == null) {
        throw new IllegalArgumentException("Argument \"name\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("name", name);
      return this;
    }

    @NonNull
    public ActionReciveComboAddressFragmentToPaytmActivity setAddress(@NonNull String address) {
      if (address == null) {
        throw new IllegalArgumentException("Argument \"address\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("address", address);
      return this;
    }

    @NonNull
    public ActionReciveComboAddressFragmentToPaytmActivity setAmount(@NonNull String amount) {
      if (amount == null) {
        throw new IllegalArgumentException("Argument \"amount\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("amount", amount);
      return this;
    }

    @NonNull
    public ActionReciveComboAddressFragmentToPaytmActivity setPhone(@NonNull String phone) {
      if (phone == null) {
        throw new IllegalArgumentException("Argument \"phone\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("phone", phone);
      return this;
    }

    @NonNull
    public ActionReciveComboAddressFragmentToPaytmActivity setComboId(@NonNull String comboId) {
      if (comboId == null) {
        throw new IllegalArgumentException("Argument \"comboId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("comboId", comboId);
      return this;
    }

    @NonNull
    public ActionReciveComboAddressFragmentToPaytmActivity setProductId(@NonNull String productId) {
      if (productId == null) {
        throw new IllegalArgumentException("Argument \"productId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("productId", productId);
      return this;
    }

    @NonNull
    public ActionReciveComboAddressFragmentToPaytmActivity setOrderId(@NonNull String orderId) {
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
      if (arguments.containsKey("name")) {
        String name = (String) arguments.get("name");
        __result.putString("name", name);
      }
      if (arguments.containsKey("address")) {
        String address = (String) arguments.get("address");
        __result.putString("address", address);
      }
      if (arguments.containsKey("amount")) {
        String amount = (String) arguments.get("amount");
        __result.putString("amount", amount);
      }
      if (arguments.containsKey("phone")) {
        String phone = (String) arguments.get("phone");
        __result.putString("phone", phone);
      }
      if (arguments.containsKey("comboId")) {
        String comboId = (String) arguments.get("comboId");
        __result.putString("comboId", comboId);
      }
      if (arguments.containsKey("productId")) {
        String productId = (String) arguments.get("productId");
        __result.putString("productId", productId);
      }
      if (arguments.containsKey("orderId")) {
        String orderId = (String) arguments.get("orderId");
        __result.putString("orderId", orderId);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_reciveComboAddressFragment_to_paytmActivity;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getName() {
      return (String) arguments.get("name");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getAddress() {
      return (String) arguments.get("address");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getAmount() {
      return (String) arguments.get("amount");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getPhone() {
      return (String) arguments.get("phone");
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
      ActionReciveComboAddressFragmentToPaytmActivity that = (ActionReciveComboAddressFragmentToPaytmActivity) object;
      if (arguments.containsKey("name") != that.arguments.containsKey("name")) {
        return false;
      }
      if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) {
        return false;
      }
      if (arguments.containsKey("address") != that.arguments.containsKey("address")) {
        return false;
      }
      if (getAddress() != null ? !getAddress().equals(that.getAddress()) : that.getAddress() != null) {
        return false;
      }
      if (arguments.containsKey("amount") != that.arguments.containsKey("amount")) {
        return false;
      }
      if (getAmount() != null ? !getAmount().equals(that.getAmount()) : that.getAmount() != null) {
        return false;
      }
      if (arguments.containsKey("phone") != that.arguments.containsKey("phone")) {
        return false;
      }
      if (getPhone() != null ? !getPhone().equals(that.getPhone()) : that.getPhone() != null) {
        return false;
      }
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
      result = 31 * result + (getName() != null ? getName().hashCode() : 0);
      result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
      result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
      result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
      result = 31 * result + (getComboId() != null ? getComboId().hashCode() : 0);
      result = 31 * result + (getProductId() != null ? getProductId().hashCode() : 0);
      result = 31 * result + (getOrderId() != null ? getOrderId().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionReciveComboAddressFragmentToPaytmActivity(actionId=" + getActionId() + "){"
          + "name=" + getName()
          + ", address=" + getAddress()
          + ", amount=" + getAmount()
          + ", phone=" + getPhone()
          + ", comboId=" + getComboId()
          + ", productId=" + getProductId()
          + ", orderId=" + getOrderId()
          + "}";
    }
  }
}
