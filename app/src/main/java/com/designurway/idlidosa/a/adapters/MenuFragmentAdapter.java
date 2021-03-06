package com.designurway.idlidosa.a.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.model.Menumodel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuFragmentAdapter extends RecyclerView.Adapter<MenuFragmentAdapter.Vholder> {
    ArrayList<Menumodel> modellist;
    Context context;
    setFragmentTransaction setFragmentTransaction;

    public MenuFragmentAdapter(ArrayList<Menumodel> modellist, Context context) {
        this.modellist = modellist;
        this.context = context;
    }

    public void sendToFragment(setFragmentTransaction setOnFragmentTransaction){
        this.setFragmentTransaction=setOnFragmentTransaction;
    }

    @NonNull
    @Override
    public Vholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_featured_item, parent, false);

        return new Vholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vholder holder, int position) {

        holder.menuName.setText(modellist.get(position).getProduct_name());
        holder.menu_description_tv.setText(modellist.get(position).getDescription());
        holder.menu_price_tv.setText("\u20B9 "+modellist.get(position).getPrice());

        setFragmentTransaction.sendView(position,holder.itemView);
        holder.cardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragmentTransaction.sendPosition(position,modellist.get(position).getProduct_id());
//                Toast.makeText(context, String.valueOf(modellist.get(position).getId()),
//                        Toast.LENGTH_SHORT).show();
            }
        });

        if (!modellist.get(position).getImage().isEmpty() && !modellist.get(position).getImage().isEmpty()){
            Picasso.get().load(modellist.get(position).getImage()).into(holder.menuImage);
        }

    }

    @Override
    public int getItemCount() {
        return modellist.size();
    }

    public class Vholder extends RecyclerView.ViewHolder {
        ImageView menuImage;
        TextView menuName,menu_description_tv,menu_price_tv;
        CardView  cardLayout;
        ProgressBar progress_bulk;

        public Vholder(@NonNull View itemView) {
            super(itemView);
            menuImage= itemView.findViewById(R.id.menu_img_iv);
            menuName= itemView.findViewById(R.id.menu_name_tv);
            menu_price_tv= itemView.findViewById(R.id.menu_price_tv);
            menu_description_tv= itemView.findViewById(R.id.menu_description_tv);
            cardLayout= itemView.findViewById(R.id.menu_overall_layout_cv);
            progress_bulk= itemView.findViewById(R.id.progress_bulk);
        }
    }

    public interface setFragmentTransaction {
        void sendPosition(int position,String id);
        void sendView(int position,View view);
    }
}
