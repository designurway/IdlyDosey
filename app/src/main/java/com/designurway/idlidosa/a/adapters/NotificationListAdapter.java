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
import com.designurway.idlidosa.a.model.NotificationListResponseModel;
import com.designurway.idlidosa.model.NotificationListData;


import java.util.ArrayList;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.MyViewHolder> {

    ArrayList<NotificationListData> notificationModels;
    Context context;

    setOnFragmentTransaction setOnFragmentTransaction;

    public NotificationListAdapter(ArrayList<NotificationListData> notificationModels, Context context) {
        this.notificationModels = notificationModels;
        this.context = context;
    }

    public void sendToFragment(setOnFragmentTransaction setOnFragmentTransaction) {
        this.setOnFragmentTransaction = setOnFragmentTransaction;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_notifications, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.orderIdTv.setText(notificationModels.get(position).getOrder_id());
        holder.orderStatusTv.setText(notificationModels.get(position).getTitle());
        holder.orderMessageTv.setText(notificationModels.get(position).getMessage());
        holder.orderDayTv.setText(notificationModels.get(position).getCreated_date());
        holder.orderMessageTv.setText(notificationModels.get(position).getMessage());
        if (notificationModels.get(position).getNotification_status().equals("unread")) {
            holder.orderStatusTv.setBackgroundColor(holder.orderStatusTv.getResources().getColor(R.color.red));
        }
//


        holder.ImageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnFragmentTransaction.sendView(position, notificationModels.get(position).getOrder_id(), holder.itemView);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setOnFragmentTransaction.sendPosition(position, notificationModels.get(position).getOrder_id(), holder.itemView);
            }
        });

    }

    @Override
    public int getItemCount() {

        return notificationModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView orderIdTv;
        TextView orderStatusTv;
        TextView orderDayTv;
        TextView orderMessageTv;
        ImageView ImageDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            orderIdTv = itemView.findViewById(R.id.order_id_tv);
            orderStatusTv = itemView.findViewById(R.id.order_status_tv);
            orderDayTv = itemView.findViewById(R.id.day_tv);
            orderMessageTv = itemView.findViewById(R.id.order_message_tv);
            ImageDelete = itemView.findViewById(R.id.ImageDelete);
        }
    }

    public interface setOnFragmentTransaction {

        void sendPosition(int position, String orderId, View view);

        void sendView(int position, String orderId, View view);

    }

}
