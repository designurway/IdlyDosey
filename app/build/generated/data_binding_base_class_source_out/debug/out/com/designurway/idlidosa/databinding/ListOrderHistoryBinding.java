// Generated by view binder compiler. Do not edit!
package com.designurway.idlidosa.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.designurway.idlidosa.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ListOrderHistoryBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView amountTv;

  @NonNull
  public final ImageView dateIcon;

  @NonNull
  public final ImageView dot;

  @NonNull
  public final TextView orderIdTv;

  @NonNull
  public final TextView orderStatusTv;

  @NonNull
  public final TextView orderTxt;

  @NonNull
  public final TextView orderedDateTv;

  private ListOrderHistoryBinding(@NonNull RelativeLayout rootView, @NonNull TextView amountTv,
      @NonNull ImageView dateIcon, @NonNull ImageView dot, @NonNull TextView orderIdTv,
      @NonNull TextView orderStatusTv, @NonNull TextView orderTxt,
      @NonNull TextView orderedDateTv) {
    this.rootView = rootView;
    this.amountTv = amountTv;
    this.dateIcon = dateIcon;
    this.dot = dot;
    this.orderIdTv = orderIdTv;
    this.orderStatusTv = orderStatusTv;
    this.orderTxt = orderTxt;
    this.orderedDateTv = orderedDateTv;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ListOrderHistoryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ListOrderHistoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.list_order_history, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ListOrderHistoryBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.amount_tv;
      TextView amountTv = rootView.findViewById(id);
      if (amountTv == null) {
        break missingId;
      }

      id = R.id.dateIcon;
      ImageView dateIcon = rootView.findViewById(id);
      if (dateIcon == null) {
        break missingId;
      }

      id = R.id.dot;
      ImageView dot = rootView.findViewById(id);
      if (dot == null) {
        break missingId;
      }

      id = R.id.order_id_tv;
      TextView orderIdTv = rootView.findViewById(id);
      if (orderIdTv == null) {
        break missingId;
      }

      id = R.id.order_status_tv;
      TextView orderStatusTv = rootView.findViewById(id);
      if (orderStatusTv == null) {
        break missingId;
      }

      id = R.id.orderTxt;
      TextView orderTxt = rootView.findViewById(id);
      if (orderTxt == null) {
        break missingId;
      }

      id = R.id.ordered_date_tv;
      TextView orderedDateTv = rootView.findViewById(id);
      if (orderedDateTv == null) {
        break missingId;
      }

      return new ListOrderHistoryBinding((RelativeLayout) rootView, amountTv, dateIcon, dot,
          orderIdTv, orderStatusTv, orderTxt, orderedDateTv);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
