package com.designurway.idlidosa.a.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.model.NotificationListData;

import java.util.List;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.Notification_VH> {

    List<NotificationListData> listData;
    Context context;

    NotificationClickedListner notificationClickedListner;

    public NotificationListAdapter(NotificationClickedListner notificationClickedListner) {
        this.notificationClickedListner = notificationClickedListner;
    }

    public NotificationListAdapter(List<NotificationListData> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public Notification_VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item_list,parent,false);
        return new Notification_VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Notification_VH holder, int position) {
            NotificationListData data = listData.get(position);
            holder.notificationListTitle.setText(data.getTitle());
            holder.notificationListMessage.setText(data.getMessage());
            holder.notificationListDate.setText(data.getCreated_date());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class Notification_VH extends RecyclerView.ViewHolder{

        TextView notificationListTitle,notificationListMessage,notificationListDate;

        public Notification_VH(@NonNull View itemView) {
            super(itemView);

            notificationListTitle = itemView.findViewById(R.id.notificationListTitle);
            notificationListMessage = itemView.findViewById(R.id.notificationListMessage);
            notificationListDate = itemView.findViewById(R.id.notificationListDate);
        }
    }


    public interface NotificationClickedListner{

        void onNotificationClicked(int postion, View itemView);
    }
}
