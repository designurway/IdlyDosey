package com.designurway.idlidosa.a.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.model.TrackOrderListData;

import java.util.List;

public class TrackOrderListAdapter extends RecyclerView.Adapter<TrackOrderListAdapter.TracOrderList_VH> {

    List<TrackOrderListData> list;
    Context context;
    TrackOrderClickListner trackOrderClickListner;

    public void setTrackOrderClickListner(TrackOrderClickListner trackOrderClickListner) {
        this.trackOrderClickListner = trackOrderClickListner;
    }

    public TrackOrderListAdapter(List<TrackOrderListData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public TracOrderList_VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_order_list_item, parent, false);
        return  new TracOrderList_VH(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TracOrderList_VH holder, int position) {
        TrackOrderListData data = list.get(position);
        holder.orderIdTxt.setText("#"+data.getOrder_id());
        holder.orderPrice.setText("\u20A8"+" "+data.getAmount());
        holder.trackOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trackOrderClickListner.onTrackOrderBtnClicked(position,holder.itemView);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TracOrderList_VH extends  RecyclerView.ViewHolder{

        TextView orderIdTxt;
        TextView orderPrice;
       Button trackOrderBtn;

        public TracOrderList_VH(@NonNull View itemView) {
            super(itemView);

            orderIdTxt= itemView.findViewById(R.id.orderIdTxt);
            orderPrice= itemView.findViewById(R.id.orderPrice);
            trackOrderBtn=itemView.findViewById(R.id.trackOrderBtn);

        }
    }

    public  interface TrackOrderClickListner{
        void onTrackOrderBtnClicked(int position, View itemView);
    }
}
