package com.designurway.idlidosa.ui.auth.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavArgs;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class ProfileFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private ProfileFragmentArgs() {
  }

  private ProfileFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static ProfileFragmentArgs fromBundle(@NonNull Bundle bundle) {
    ProfileFragmentArgs __result = new ProfileFragmentArgs();
    bundle.setClassLoader(ProfileFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("Address")) {
      String Address;
      Address = bundle.getString("Address");
      if (Address == null) {
        throw new IllegalArgumentException("Argument \"Address\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("Address", Address);
    } else {
      throw new IllegalArgumentException("Required argument \"Address\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getAddress() {
    return (String) arguments.get("Address");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("Address")) {
      String Address = (String) arguments.get("Address");
      __result.putString("Address", Address);
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
    ProfileFragmentArgs that = (ProfileFragmentArgs) object;
    if (arguments.containsKey("Address") != that.arguments.containsKey("Address")) {
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
    return "ProfileFragmentArgs{"
        + "Address=" + getAddress()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    public Builder(ProfileFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder(@NonNull String Address) {
      if (Address == null) {
        throw new IllegalArgumentException("Argument \"Address\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("Address", Address);
    }

    @NonNull
    public ProfileFragmentArgs build() {
      ProfileFragmentArgs result = new ProfileFragmentArgs(arguments);
      return result;
    }

    @NonNull
    public Builder setAddress(@NonNull String Address) {
      if (Address == null) {
        throw new IllegalArgumentException("Argument \"Address\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("Address", Address);
      return this;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getAddress() {
      return (String) arguments.get("Address");
    }
  }
}