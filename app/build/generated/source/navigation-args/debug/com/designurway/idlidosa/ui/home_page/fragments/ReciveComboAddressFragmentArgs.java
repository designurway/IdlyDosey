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

public class ReciveComboAddressFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private ReciveComboAddressFragmentArgs() {
  }

  private ReciveComboAddressFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static ReciveComboAddressFragmentArgs fromBundle(@NonNull Bundle bundle) {
    ReciveComboAddressFragmentArgs __result = new ReciveComboAddressFragmentArgs();
    bundle.setClassLoader(ReciveComboAddressFragmentArgs.class.getClassLoader());
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
    return __result;
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
  public Bundle toBundle() {
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
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    ReciveComboAddressFragmentArgs that = (ReciveComboAddressFragmentArgs) object;
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
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getComboId() != null ? getComboId().hashCode() : 0);
    result = 31 * result + (getProductId() != null ? getProductId().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ReciveComboAddressFragmentArgs{"
        + "comboId=" + getComboId()
        + ", productId=" + getProductId()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    public Builder(ReciveComboAddressFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder(@NonNull String comboId, @NonNull String productId) {
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
    public ReciveComboAddressFragmentArgs build() {
      ReciveComboAddressFragmentArgs result = new ReciveComboAddressFragmentArgs(arguments);
      return result;
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
  }
}
