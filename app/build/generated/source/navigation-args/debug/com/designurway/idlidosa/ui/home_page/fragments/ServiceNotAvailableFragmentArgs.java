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

public class ServiceNotAvailableFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private ServiceNotAvailableFragmentArgs() {
  }

  private ServiceNotAvailableFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static ServiceNotAvailableFragmentArgs fromBundle(@NonNull Bundle bundle) {
    ServiceNotAvailableFragmentArgs __result = new ServiceNotAvailableFragmentArgs();
    bundle.setClassLoader(ServiceNotAvailableFragmentArgs.class.getClassLoader());
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
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getAddress() {
    return (String) arguments.get("address");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("address")) {
      String address = (String) arguments.get("address");
      __result.putString("address", address);
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
    ServiceNotAvailableFragmentArgs that = (ServiceNotAvailableFragmentArgs) object;
    if (arguments.containsKey("address") != that.arguments.containsKey("address")) {
      return false;
    }
    if (getAddress() != null ? !getAddress().equals(that.getAddress()) : that.getAddress() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ServiceNotAvailableFragmentArgs{"
        + "address=" + getAddress()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    public Builder(ServiceNotAvailableFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder(@NonNull String address) {
      if (address == null) {
        throw new IllegalArgumentException("Argument \"address\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("address", address);
    }

    @NonNull
    public ServiceNotAvailableFragmentArgs build() {
      ServiceNotAvailableFragmentArgs result = new ServiceNotAvailableFragmentArgs(arguments);
      return result;
    }

    @NonNull
    public Builder setAddress(@NonNull String address) {
      if (address == null) {
        throw new IllegalArgumentException("Argument \"address\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("address", address);
      return this;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getAddress() {
      return (String) arguments.get("address");
    }
  }
}
