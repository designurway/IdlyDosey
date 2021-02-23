// Generated by view binder compiler. Do not edit!
package com.designurway.idlidosa.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.designurway.idlidosa.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentEmergencyBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final RelativeLayout addItemsRl;

  @NonNull
  public final RecyclerView addItmsRv;

  @NonNull
  public final Button addMoreItem;

  @NonNull
  public final EditText addPrescriptionEt;

  @NonNull
  public final TextView orTv;

  @NonNull
  public final EditText prescriptionQtnEt;

  @NonNull
  public final Button sendMOrder;

  @NonNull
  public final CardView uploadImgCv;

  @NonNull
  public final RelativeLayout uploadImgRl;

  @NonNull
  public final ImageView uploadImgv;

  @NonNull
  public final RelativeLayout uploadRl;

  private FragmentEmergencyBinding(@NonNull ConstraintLayout rootView,
      @NonNull RelativeLayout addItemsRl, @NonNull RecyclerView addItmsRv,
      @NonNull Button addMoreItem, @NonNull EditText addPrescriptionEt, @NonNull TextView orTv,
      @NonNull EditText prescriptionQtnEt, @NonNull Button sendMOrder,
      @NonNull CardView uploadImgCv, @NonNull RelativeLayout uploadImgRl,
      @NonNull ImageView uploadImgv, @NonNull RelativeLayout uploadRl) {
    this.rootView = rootView;
    this.addItemsRl = addItemsRl;
    this.addItmsRv = addItmsRv;
    this.addMoreItem = addMoreItem;
    this.addPrescriptionEt = addPrescriptionEt;
    this.orTv = orTv;
    this.prescriptionQtnEt = prescriptionQtnEt;
    this.sendMOrder = sendMOrder;
    this.uploadImgCv = uploadImgCv;
    this.uploadImgRl = uploadImgRl;
    this.uploadImgv = uploadImgv;
    this.uploadRl = uploadRl;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentEmergencyBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentEmergencyBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_emergency, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentEmergencyBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.add_items_rl;
      RelativeLayout addItemsRl = rootView.findViewById(id);
      if (addItemsRl == null) {
        break missingId;
      }

      id = R.id.add_itms_rv;
      RecyclerView addItmsRv = rootView.findViewById(id);
      if (addItmsRv == null) {
        break missingId;
      }

      id = R.id.addMoreItem;
      Button addMoreItem = rootView.findViewById(id);
      if (addMoreItem == null) {
        break missingId;
      }

      id = R.id.add_prescription_et;
      EditText addPrescriptionEt = rootView.findViewById(id);
      if (addPrescriptionEt == null) {
        break missingId;
      }

      id = R.id.or_tv;
      TextView orTv = rootView.findViewById(id);
      if (orTv == null) {
        break missingId;
      }

      id = R.id.prescription_qtn_et;
      EditText prescriptionQtnEt = rootView.findViewById(id);
      if (prescriptionQtnEt == null) {
        break missingId;
      }

      id = R.id.send_m_order;
      Button sendMOrder = rootView.findViewById(id);
      if (sendMOrder == null) {
        break missingId;
      }

      id = R.id.upload_img_cv;
      CardView uploadImgCv = rootView.findViewById(id);
      if (uploadImgCv == null) {
        break missingId;
      }

      id = R.id.upload_img_rl;
      RelativeLayout uploadImgRl = rootView.findViewById(id);
      if (uploadImgRl == null) {
        break missingId;
      }

      id = R.id.upload_imgv;
      ImageView uploadImgv = rootView.findViewById(id);
      if (uploadImgv == null) {
        break missingId;
      }

      id = R.id.upload_rl;
      RelativeLayout uploadRl = rootView.findViewById(id);
      if (uploadRl == null) {
        break missingId;
      }

      return new FragmentEmergencyBinding((ConstraintLayout) rootView, addItemsRl, addItmsRv,
          addMoreItem, addPrescriptionEt, orTv, prescriptionQtnEt, sendMOrder, uploadImgCv,
          uploadImgRl, uploadImgv, uploadRl);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
