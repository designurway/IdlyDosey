// Generated by view binder compiler. Do not edit!
package com.designurway.idlidosa.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.designurway.idlidosa.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class NotificationItemListBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView notificationListDate;

  @NonNull
  public final TextView notificationListMessage;

  @NonNull
  public final TextView notificationListTitle;

  @NonNull
  public final CircleImageView notifyImage;

  private NotificationItemListBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView notificationListDate, @NonNull TextView notificationListMessage,
      @NonNull TextView notificationListTitle, @NonNull CircleImageView notifyImage) {
    this.rootView = rootView;
    this.notificationListDate = notificationListDate;
    this.notificationListMessage = notificationListMessage;
    this.notificationListTitle = notificationListTitle;
    this.notifyImage = notifyImage;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static NotificationItemListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static NotificationItemListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.notification_item_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static NotificationItemListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.notificationListDate;
      TextView notificationListDate = rootView.findViewById(id);
      if (notificationListDate == null) {
        break missingId;
      }

      id = R.id.notificationListMessage;
      TextView notificationListMessage = rootView.findViewById(id);
      if (notificationListMessage == null) {
        break missingId;
      }

      id = R.id.notificationListTitle;
      TextView notificationListTitle = rootView.findViewById(id);
      if (notificationListTitle == null) {
        break missingId;
      }

      id = R.id.notify_image;
      CircleImageView notifyImage = rootView.findViewById(id);
      if (notifyImage == null) {
        break missingId;
      }

      return new NotificationItemListBinding((ConstraintLayout) rootView, notificationListDate,
          notificationListMessage, notificationListTitle, notifyImage);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
