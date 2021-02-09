package com.designurway.idlidosa.ui.home_page.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavArgs;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class PaymentFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private PaymentFragmentArgs() {
  }

  private PaymentFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static PaymentFragmentArgs fromBundle(@NonNull Bundle bundle) {
    PaymentFragmentArgs __result = new PaymentFragmentArgs();
    bundle.setClassLoader(PaymentFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("name")) {
      String name;
      name = bundle.getString("name");
      if (name == null) {
        throw new IllegalArgumentException("Argument \"name\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("name", name);
    } else {
      throw new IllegalArgumentException("Required argument \"name\" is missing and does not have an android:defaultValue");
    }
    if (bundle.containsKey("address")) {
      String address;
      address = bundle.getString("address");
      if (address == null) {
        throw new IllegalArgumentException("Argument \"address\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("address", address);
    } else {
      throw new IllegalArgumentException("Required argument \"address\" is missing and does not have an android:defaultValue");
    }
    if (bundle.containsKey("amount")) {
      String amount;
      amount = bundle.getString("amount");
      if (amount == null) {
        throw new IllegalArgumentException("Argument \"amount\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("amount", amount);
    } else {
      throw new IllegalArgumentException("Required argument \"amount\" is missing and does not have an android:defaultValue");
    }
    if (bundle.containsKey("phone")) {
      String phone;
      phone = bundle.getString("phone");
      if (phone == null) {
        throw new IllegalArgumentException("Argument \"phone\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("phone", phone);
    } else {
      throw new IllegalArgumentException("Required argument \"phone\" is missing and does not have an android:defaultValue");
    }
    if (bundle.containsKey("comboId")) {
      String comboId;
      comboId = bundle.getString("comboId");
      if (comboId == null) {
        throw new IllegalArgumentException("Argument \"comboId\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("comboId", comboId);
    } else {
      throw new IllegalArgumentException("Required argument \"comboId\" is missing and does not have an android:defaultValue");
    }
    if (bundle.containsKey("productId")) {
      String productId;
      productId = bundle.getString("productId");
      if (productId == null) {
        throw new IllegalArgumentException("Argument \"productId\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("productId", productId);
    } else {
      throw new IllegalArgumentException("Required argument \"productId\" is missing and does not have an android:defaultValue");
    }
    if (bundle.containsKey("orderId")) {
      String orderId;
      orderId = bundle.getString("orderId");
      if (orderId == null) {
        throw new IllegalArgumentException("Argument \"orderId\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("orderId", orderId);
    } else {
      throw new IllegalArgumentException("Required argument \"orderId\" is missing and does not have an android:defaultValue");
    }
    return __result;
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

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
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
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    PaymentFragmentArgs that = (PaymentFragmentArgs) object;
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
    return result;
  }

  @Override
  public String toString() {
    return "PaymentFragmentArgs{"
        + "name=" + getName()
        + ", address=" + getAddress()
        + ", amount=" + getAmount()
        + ", phone=" + getPhone()
        + ", comboId=" + getComboId()
        + ", productId=" + getProductId()
        + ", orderId=" + getOrderId()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    public Builder(PaymentFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder(@NonNull String name, @NonNull String address, @NonNull String amount,
        @NonNull String phone, @NonNull String comboId, @NonNull String productId,
        @NonNull String orderId) {
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
    public PaymentFragmentArgs build() {
      PaymentFragmentArgs result = new PaymentFragmentArgs(arguments);
      return result;
    }

    @NonNull
    public Builder setName(@NonNull String name) {
      if (name == null) {
        throw new IllegalArgumentException("Argument \"name\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("name", name);
      return this;
    }

    @NonNull
    public Builder setAddress(@NonNull String address) {
      if (address == null) {
        throw new IllegalArgumentException("Argument \"address\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("address", address);
      return this;
    }

    @NonNull
    public Builder setAmount(@NonNull String amount) {
      if (amount == null) {
        throw new IllegalArgumentException("Argument \"amount\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("amount", amount);
      return this;
    }

    @NonNull
    public Builder setPhone(@NonNull String phone) {
      if (phone == null) {
        throw new IllegalArgumentException("Argument \"phone\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("phone", phone);
      return this;
    }

    @NonNull
    public Builder setComboId(@NonNull String comboId) {
      if (comboId == null) {
        throw new IllegalArgumentException("Argument \"comboId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("comboId", comboId);
      return this;
    }

    @NonNull
    public Builder setProductId(@NonNull String productId) {
      if (productId == null) {
        throw new IllegalArgumentException("Argument \"productId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("productId", productId);
      return this;
    }

    @NonNull
    public Builder setOrderId(@NonNull String orderId) {
      if (orderId == null) {
        throw new IllegalArgumentException("Argument \"orderId\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("orderId", orderId);
      return this;
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
  }
}
