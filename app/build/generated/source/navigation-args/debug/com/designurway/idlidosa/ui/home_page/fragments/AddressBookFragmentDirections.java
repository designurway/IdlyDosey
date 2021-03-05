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

public class AddressBookFragmentDirections {
  private AddressBookFragmentDirections() {
  }

  @NonNull
  public static ActionAddressBookFragmentToPaymentFragment actionAddressBookFragmentToPaymentFragment(
      @NonNull String name, @NonNull String address, @NonNull String amount, @NonNull String phone,
      @NonNull String comboId, @NonNull String productId, @NonNull String orderId) {
    return new ActionAddressBookFragmentToPaymentFragment(name, address, amount, phone, comboId, productId, orderId);
  }

  @NonNull
  public static ActionAddressBookFragmentToChangeAddressFragment actionAddressBookFragmentToChangeAddressFragment(
      @NonNull String Address) {
    return new ActionAddressBookFragmentToChangeAddressFragment(Address);
  }

  @NonNull
  public static ActionAddressBookFragmentToPaytmActivity actionAddressBookFragmentToPaytmActivity(
      @NonNull String name, @NonNull String address, @NonNull String amount, @NonNull String phone,
      @NonNull String comboId, @NonNull String productId, @NonNull String orderId) {
    return new ActionAddressBookFragmentToPaytmActivity(name, address, amount, phone, comboId, productId, orderId);
  }

  @NonNull
  public static ActionAddressBookFragmentToServiceNotAvailableFragment actionAddressBookFragmentToServiceNotAvailableFragment(
      @NonNull String address) {
    return new ActionAddressBookFragmentToServiceNotAvailableFragment(address);
  }

  @NonNull
  public static ActionAddressBookFragmentToLocationFragment actionAddressBookFragmentToLocationFragment(
      @NonNull String amount, @NonNull String name, @NonNull String phone,
      @NonNull String orderId) {
    return new ActionAddressBookFragmentToLocationFragment(amount, name, phone, orderId);
  }

  @NonNull
  public static NavDirections actionGlobalNotificationListFragment() {
    return HomeNavGraphDirections.actionGlobalNotificationListFragment();
  }

  public static class ActionAddressBookFragmentToPaymentFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionAddressBookFragmentToPaymentFragment(@NonNull String name,
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
    public ActionAddressBookFragmentToPaymentFragment setName(@NonNull String name) {
      if (name == null) {
        throw new IllegalArgumentException("Argument \"name\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("name", name);
      return this;
    }

    @NonNull
    public ActionAddressBookFragmentToPaymentFragment setAddress(@NonNull String address) {
      if (address == null) {
        throw new IllegalArgumentException("Argument \"address\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("address", address);
      return this;
    }

    @NonNull
    public ActionAddressBookFragmentToPaymentFragment setAmount(@NonNull String amount) {
      if (amount == null) {
        throw new IllegalArgumentException("Argument \"amount\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("amount", amount);
      return this;
    }

    @NonNull
    public ActionAddressBookFragmentToPaymentFragment setPhone(@NonNull String phone) {
      if (phone == null) {
        throw new IllegalArgumentException("Argument \"phone\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("phone", phone);
      return this;
    }

    @NonNull
    public ActionAddressBookFragmentToPaymentFragment setComboId(@NonNull String comboId) {
      if (comboId == null) {
        throw new IllegalArgumentException("Argument \"comboId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("comboId", comboId);
      return this;
    }

    @NonNull
    public ActionAddressBookFragmentToPaymentFragment setProductId(@NonNull String productId) {
      if (productId == null) {
        throw new IllegalArgumentException("Argument \"productId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("productId", productId);
      return this;
    }

    @NonNull
    public ActionAddressBookFragmentToPaymentFragment setOrderId(@NonNull String orderId) {
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
      return R.id.action_addressBookFragment_to_paymentFragment;
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
      ActionAddressBookFragmentToPaymentFragment that = (ActionAddressBookFragmentToPaymentFragment) object;
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
      return "ActionAddressBookFragmentToPaymentFragment(actionId=" + getActionId() + "){"
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

  public static class ActionAddressBookFragmentToChangeAddressFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionAddressBookFragmentToChangeAddressFragment(@NonNull String Address) {
      if (Address == null) {
        throw new IllegalArgumentException("Argument \"Address\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("Address", Address);
    }

    @NonNull
    public ActionAddressBookFragmentToChangeAddressFragment setAddress(@NonNull String Address) {
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
      return R.id.action_addressBookFragment_to_changeAddressFragment;
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
      ActionAddressBookFragmentToChangeAddressFragment that = (ActionAddressBookFragmentToChangeAddressFragment) object;
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
      return "ActionAddressBookFragmentToChangeAddressFragment(actionId=" + getActionId() + "){"
          + "Address=" + getAddress()
          + "}";
    }
  }

  public static class ActionAddressBookFragmentToPaytmActivity implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionAddressBookFragmentToPaytmActivity(@NonNull String name, @NonNull String address,
        @NonNull String amount, @NonNull String phone, @NonNull String comboId,
        @NonNull String productId, @NonNull String orderId) {
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
    public ActionAddressBookFragmentToPaytmActivity setName(@NonNull String name) {
      if (name == null) {
        throw new IllegalArgumentException("Argument \"name\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("name", name);
      return this;
    }

    @NonNull
    public ActionAddressBookFragmentToPaytmActivity setAddress(@NonNull String address) {
      if (address == null) {
        throw new IllegalArgumentException("Argument \"address\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("address", address);
      return this;
    }

    @NonNull
    public ActionAddressBookFragmentToPaytmActivity setAmount(@NonNull String amount) {
      if (amount == null) {
        throw new IllegalArgumentException("Argument \"amount\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("amount", amount);
      return this;
    }

    @NonNull
    public ActionAddressBookFragmentToPaytmActivity setPhone(@NonNull String phone) {
      if (phone == null) {
        throw new IllegalArgumentException("Argument \"phone\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("phone", phone);
      return this;
    }

    @NonNull
    public ActionAddressBookFragmentToPaytmActivity setComboId(@NonNull String comboId) {
      if (comboId == null) {
        throw new IllegalArgumentException("Argument \"comboId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("comboId", comboId);
      return this;
    }

    @NonNull
    public ActionAddressBookFragmentToPaytmActivity setProductId(@NonNull String productId) {
      if (productId == null) {
        throw new IllegalArgumentException("Argument \"productId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("productId", productId);
      return this;
    }

    @NonNull
    public ActionAddressBookFragmentToPaytmActivity setOrderId(@NonNull String orderId) {
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
      return R.id.action_addressBookFragment_to_paytmActivity;
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
      ActionAddressBookFragmentToPaytmActivity that = (ActionAddressBookFragmentToPaytmActivity) object;
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
      return "ActionAddressBookFragmentToPaytmActivity(actionId=" + getActionId() + "){"
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

  public static class ActionAddressBookFragmentToServiceNotAvailableFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionAddressBookFragmentToServiceNotAvailableFragment(@NonNull String address) {
      if (address == null) {
        throw new IllegalArgumentException("Argument \"address\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("address", address);
    }

    @NonNull
    public ActionAddressBookFragmentToServiceNotAvailableFragment setAddress(
        @NonNull String address) {
      if (address == null) {
        throw new IllegalArgumentException("Argument \"address\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("address", address);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("address")) {
        String address = (String) arguments.get("address");
        __result.putString("address", address);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_addressBookFragment_to_serviceNotAvailableFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getAddress() {
      return (String) arguments.get("address");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionAddressBookFragmentToServiceNotAvailableFragment that = (ActionAddressBookFragmentToServiceNotAvailableFragment) object;
      if (arguments.containsKey("address") != that.arguments.containsKey("address")) {
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
      return "ActionAddressBookFragmentToServiceNotAvailableFragment(actionId=" + getActionId() + "){"
          + "address=" + getAddress()
          + "}";
    }
  }

  public static class ActionAddressBookFragmentToLocationFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionAddressBookFragmentToLocationFragment(@NonNull String amount,
        @NonNull String name, @NonNull String phone, @NonNull String orderId) {
      if (amount == null) {
        throw new IllegalArgumentException("Argument \"amount\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("amount", amount);
      if (name == null) {
        throw new IllegalArgumentException("Argument \"name\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("name", name);
      if (phone == null) {
        throw new IllegalArgumentException("Argument \"phone\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("phone", phone);
      if (orderId == null) {
        throw new IllegalArgumentException("Argument \"orderId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("orderId", orderId);
    }

    @NonNull
    public ActionAddressBookFragmentToLocationFragment setAmount(@NonNull String amount) {
      if (amount == null) {
        throw new IllegalArgumentException("Argument \"amount\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("amount", amount);
      return this;
    }

    @NonNull
    public ActionAddressBookFragmentToLocationFragment setName(@NonNull String name) {
      if (name == null) {
        throw new IllegalArgumentException("Argument \"name\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("name", name);
      return this;
    }

    @NonNull
    public ActionAddressBookFragmentToLocationFragment setPhone(@NonNull String phone) {
      if (phone == null) {
        throw new IllegalArgumentException("Argument \"phone\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("phone", phone);
      return this;
    }

    @NonNull
    public ActionAddressBookFragmentToLocationFragment setOrderId(@NonNull String orderId) {
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
      if (arguments.containsKey("name")) {
        String name = (String) arguments.get("name");
        __result.putString("name", name);
      }
      if (arguments.containsKey("phone")) {
        String phone = (String) arguments.get("phone");
        __result.putString("phone", phone);
      }
      if (arguments.containsKey("orderId")) {
        String orderId = (String) arguments.get("orderId");
        __result.putString("orderId", orderId);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_addressBookFragment_to_locationFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getAmount() {
      return (String) arguments.get("amount");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getName() {
      return (String) arguments.get("name");
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getPhone() {
      return (String) arguments.get("phone");
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
      ActionAddressBookFragmentToLocationFragment that = (ActionAddressBookFragmentToLocationFragment) object;
      if (arguments.containsKey("amount") != that.arguments.containsKey("amount")) {
        return false;
      }
      if (getAmount() != null ? !getAmount().equals(that.getAmount()) : that.getAmount() != null) {
        return false;
      }
      if (arguments.containsKey("name") != that.arguments.containsKey("name")) {
        return false;
      }
      if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) {
        return false;
      }
      if (arguments.containsKey("phone") != that.arguments.containsKey("phone")) {
        return false;
      }
      if (getPhone() != null ? !getPhone().equals(that.getPhone()) : that.getPhone() != null) {
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
      result = 31 * result + (getName() != null ? getName().hashCode() : 0);
      result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
      result = 31 * result + (getOrderId() != null ? getOrderId().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionAddressBookFragmentToLocationFragment(actionId=" + getActionId() + "){"
          + "amount=" + getAmount()
          + ", name=" + getName()
          + ", phone=" + getPhone()
          + ", orderId=" + getOrderId()
          + "}";
    }
  }
}
