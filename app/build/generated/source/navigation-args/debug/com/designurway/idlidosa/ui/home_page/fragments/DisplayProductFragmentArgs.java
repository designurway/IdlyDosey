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

public class DisplayProductFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private DisplayProductFragmentArgs() {
  }

  private DisplayProductFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static DisplayProductFragmentArgs fromBundle(@NonNull Bundle bundle) {
    DisplayProductFragmentArgs __result = new DisplayProductFragmentArgs();
    bundle.setClassLoader(DisplayProductFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("ItemType")) {
      String ItemType;
      ItemType = bundle.getString("ItemType");
      if (ItemType == null) {
        throw new IllegalArgumentException("Argument \"ItemType\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("ItemType", ItemType);
    } else {
      throw new IllegalArgumentException("Required argument \"ItemType\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getItemType() {
    return (String) arguments.get("ItemType");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("ItemType")) {
      String ItemType = (String) arguments.get("ItemType");
      __result.putString("ItemType", ItemType);
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
    DisplayProductFragmentArgs that = (DisplayProductFragmentArgs) object;
    if (arguments.containsKey("ItemType") != that.arguments.containsKey("ItemType")) {
      return false;
    }
    if (getItemType() != null ? !getItemType().equals(that.getItemType()) : that.getItemType() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getItemType() != null ? getItemType().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "DisplayProductFragmentArgs{"
        + "ItemType=" + getItemType()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    public Builder(DisplayProductFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder(@NonNull String ItemType) {
      if (ItemType == null) {
        throw new IllegalArgumentException("Argument \"ItemType\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("ItemType", ItemType);
    }

    @NonNull
    public DisplayProductFragmentArgs build() {
      DisplayProductFragmentArgs result = new DisplayProductFragmentArgs(arguments);
      return result;
    }

    @NonNull
    public Builder setItemType(@NonNull String ItemType) {
      if (ItemType == null) {
        throw new IllegalArgumentException("Argument \"ItemType\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("ItemType", ItemType);
      return this;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getItemType() {
      return (String) arguments.get("ItemType");
    }
  }
}
