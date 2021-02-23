package com.designurway.idlidosa.a.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.model.GetorderHistoryModel;


import java.util.ArrayList;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.MyViewHolder> {
    ArrayList<GetorderHistoryModel> orderHistoryModels;
    Context context;

    public OrderHistoryAdapter(  ArrayList<GetorderHistoryModel> orderHistoryModels, Context context) {
        this.orderHistoryModels = orderHistoryModels;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_history, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.orderIdTv.setText("#"+orderHistoryModels.get(position).getOrder_id());
        holder.amountTv.setText("\u20A8"+" "+orderHistoryModels.get(position).getPrice());
        holder.dateTv.setText(orderHistoryModels.get(position).getCreated_date());
        holder.statusTv.setText(orderHistoryModels.get(position).getOrder_status());

        holder.amountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,orderHistoryModels.get(position).getOrder_status() , Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return orderHistoryModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView orderIdTv;
        TextView amountTv;
        TextView dateTv;
        TextView statusTv;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            orderIdTv= itemView.findViewById(R.id.order_id_tv);
            amountTv= itemView.findViewById(R.id.amount_tv);
            dateTv= itemView.findViewById(R.id.ordered_date_tv);
            statusTv= itemView.findViewById(R.id.order_status_tv);

        }
    }


}
