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

public class PaymentSucessfulFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private PaymentSucessfulFragmentArgs() {
  }

  private PaymentSucessfulFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static PaymentSucessfulFragmentArgs fromBundle(@NonNull Bundle bundle) {
    PaymentSucessfulFragmentArgs __result = new PaymentSucessfulFragmentArgs();
    bundle.setClassLoader(PaymentSucessfulFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("JsonString")) {
      String JsonString;
      JsonString = bundle.getString("JsonString");
      if (JsonString == null) {
        throw new IllegalArgumentException("Argument \"JsonString\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("JsonString", JsonString);
    } else {
      throw new IllegalArgumentException("Required argument \"JsonString\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getJsonString() {
    return (String) arguments.get("JsonString");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("JsonString")) {
      String JsonString = (String) arguments.get("JsonString");
      __result.putString("JsonString", JsonString);
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
    PaymentSucessfulFragmentArgs that = (PaymentSucessfulFragmentArgs) object;
    if (arguments.containsKey("JsonString") != that.arguments.containsKey("JsonString")) {
      return false;
    }
    if (getJsonString() != null ? !getJsonString().equals(that.getJsonString()) : that.getJsonString() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + (getJsonString() != null ? getJsonString().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "PaymentSucessfulFragmentArgs{"
        + "JsonString=" + getJsonString()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    public Builder(PaymentSucessfulFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder(@NonNull String JsonString) {
      if (JsonString == null) {
        throw new IllegalArgumentException("Argument \"JsonString\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("JsonString", JsonString);
    }

    @NonNull
    public PaymentSucessfulFragmentArgs build() {
      PaymentSucessfulFragmentArgs result = new PaymentSucessfulFragmentArgs(arguments);
      return result;
    }

    @NonNull
    public Builder setJsonString(@NonNull String JsonString) {
      if (JsonString == null) {
        throw new IllegalArgumentException("Argument \"JsonString\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("JsonString", JsonString);
      return this;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public String getJsonString() {
      return (String) arguments.get("JsonString");
    }
  }
}
