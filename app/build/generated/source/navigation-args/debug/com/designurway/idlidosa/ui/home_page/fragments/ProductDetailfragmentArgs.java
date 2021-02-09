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

public class ProductDetailfragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private ProductDetailfragmentArgs() {
  }

  private ProductDetailfragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static ProductDetailfragmentArgs fromBundle(@NonNull Bundle bundle) {
    ProductDetailfragmentArgs __result = new ProductDetailfragmentArgs();
    bundle.setClassLoader(ProductDetailfragmentArgs.class.getClassLoader());
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
    if (bundle.containsKey("present")) {
      String present;
      present = bundle.getString("present");
      if (present == null) {
        throw new IllegalArgumentException("Argument \"present\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("present", present);
    } else {
      throw new IllegalArgumentException("Required argument \"present\" is missing and does not have an android:defaultValue");
    }
    return __result;
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

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
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
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    ProductDetailfragmentArgs that = (ProductDetailfragmentArgs) object;
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
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getProductId() != null ? getProductId().hashCode() : 0);
    result = 31 * result + (getPresent() != null ? getPresent().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ProductDetailfragmentArgs{"
        + "productId=" + getProductId()
        + ", present=" + getPresent()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    public Builder(ProductDetailfragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder(@NonNull String productId, @NonNull String present) {
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
    public ProductDetailfragmentArgs build() {
      ProductDetailfragmentArgs result = new ProductDetailfragmentArgs(arguments);
      return result;
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
    public Builder setPresent(@NonNull String present) {
      if (present == null) {
        throw new IllegalArgumentException("Argument \"present\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("present", present);
      return this;
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
  }
}
