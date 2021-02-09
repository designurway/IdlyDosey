package com.designurway.idlidosa.a.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.model.DashComboModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ComboAdapter extends RecyclerView.Adapter<ComboAdapter.vholder> {
    ArrayList<DashComboModel> dashComboModels;
    Context context;
    setFragmentTransaction setFragmentTransaction;

    public ComboAdapter(ArrayList<DashComboModel> dashComboModels, Context context) {
        this.dashComboModels = dashComboModels;
        this.context = context;
    }

    public void sendToFragment(setFragmentTransaction setOnFragmentTransaction){
        this.setFragmentTransaction=setOnFragmentTransaction;
    }

    @NonNull
    @Override
    public vholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.layout_featured_item,parent,false);

        return new vholder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull vholder holder, int position) {

        holder.itemName.setText(dashComboModels.get(position).getComboName());
        Picasso.get().load(dashComboModels.get(position).getComboImage()).into(holder.itemimage);
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragmentTransaction.sendPosition(position,dashComboModels.get(position).getComboId());
            }
        });


    }

    @Override
    public int getItemCount() {
        return dashComboModels.size();
    }

    public class vholder extends RecyclerView.ViewHolder {

        TextView itemName;
        ImageView itemimage;
        CardView itemLayout;


        public vholder(@NonNull View itemView) {
            super(itemView);
           itemName= itemView.findViewById(R.id.menu_name_tv);
            itemimage= itemView.findViewById(R.id.menu_img_iv);
            itemLayout= itemView.findViewById(R.id.menu_overall_layout_cv);
        }
    }

    public interface setFragmentTransaction {
        void sendPosition(int position,String id);
    }
}
