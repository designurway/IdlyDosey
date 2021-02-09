package com.designurway.idlidosa.a.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.model.PrescriptionModel;

import java.util.ArrayList;

public class PrescriptionAdapter extends RecyclerView.Adapter<PrescriptionAdapter.vholder> {

    ArrayList<PrescriptionModel> prescriptionModels;
    Context context;

    public PrescriptionAdapter(ArrayList<PrescriptionModel> prescriptionModels, Context context) {
        this.prescriptionModels = prescriptionModels;
        this.context = context;
    }

    @NonNull
    @Override
    public vholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_prescription,parent,false);
        return new vholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull vholder holder, int position) {

        holder.prescriptionName.setText(prescriptionModels.get(position).getPrescriptionName());
        holder.prescriptionQuantity.setText(prescriptionModels.get(position).getPrescriptionQunatity());

    }

    @Override
    public int getItemCount() {
        return prescriptionModels.size();
    }

    public class vholder extends RecyclerView.ViewHolder {

        public TextView prescriptionName;
        public TextView prescriptionQuantity;

        public vholder(@NonNull View itemView) {
            super(itemView);

            this.prescriptionName= itemView.findViewById(R.id.pres_name_tv);
            this.prescriptionQuantity= itemView.findViewById(R.id.pres_quantity_tv);
        }
    }
}
