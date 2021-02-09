package com.designurway.idlidosa.a.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.model.MenuDetailsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PraductRrecyclerAdaprter extends RecyclerView.Adapter<PraductRrecyclerAdaprter.vholder> {
    ArrayList<MenuDetailsModel> modellist;
    Context context;

    public PraductRrecyclerAdaprter(ArrayList<MenuDetailsModel> modellist, Context context) {
        this.modellist = modellist;
        this.context = context;
    }

    @NonNull
    @Override
    public vholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_item,
                parent, false);

        return new vholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull vholder holder, int position) {
        holder.productNameTv.setText(modellist.get(position).getName());
        holder.productAmountTv.setText(modellist.get(position).getPrice());
        Picasso.get().load(modellist.get(position).getImage()).into(holder.productImageIv);

    }

    @Override
    public int getItemCount() {
        return modellist.size();
    }

    public class vholder extends RecyclerView.ViewHolder{
        TextView productNameTv;
        TextView productAmountTv;
        ImageView productImageIv;

        public vholder(@NonNull View itemView) {
            super(itemView);
            productNameTv= itemView.findViewById(R.id.item_name_tv);
            productAmountTv= itemView.findViewById(R.id.txt_price);
            productImageIv= itemView.findViewById(R.id.item_img_civ);
        }
    }
}
