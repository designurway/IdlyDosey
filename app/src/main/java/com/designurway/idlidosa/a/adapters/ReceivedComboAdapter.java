package com.designurway.idlidosa.a.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.model.ErrorMessageModel;
import com.designurway.idlidosa.a.model.GetNotificationResponse;
import com.designurway.idlidosa.a.model.ReceivedComboModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.AndroidUtils;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReceivedComboAdapter extends RecyclerView.Adapter<ReceivedComboAdapter.MyViewHolder> {
    private static final String TAG ="ReceivedComboAdapter" ;
    ArrayList<ReceivedComboModel> comboModels;
    Context context;
    setFragmentTransaction setFragmentTransaction;

    public ReceivedComboAdapter(ArrayList<ReceivedComboModel> comboModels,Context context){
        this.comboModels=comboModels;
        this.context=context;
    }

    public void sendToFragment(setFragmentTransaction setOnFragmentTransaction){
        this.setFragmentTransaction=setOnFragmentTransaction;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_received_combo, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(comboModels.get(position).getComboImage()).into(holder.comboImageIv);
        holder.comboTitleTv.setText(comboModels.get(position).getComboName());
//        holder.comboDescriptionTv.setText(comboModels.get(position).getComboDescription());

        holder.comboRedeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragmentTransaction.sendPosition(position,comboModels.get(position).getComboId(),comboModels.get(position).getProdId());

//                setAletDialog(position);
            }
        });

    }

    private void postItems(String ProdId,String ComboId) {
        String orderId=AndroidUtils.randomName(10);
        RetrofitApi retrofitApi= BaseClient.getClient().create(RetrofitApi.class);
        Call<ErrorMessageModel> call=retrofitApi.postComboWonDetails(PreferenceManager.getCustomerId(),
                orderId,
                ProdId,
                ComboId);
        Log.d(TAG,"customerId"+PreferenceManager.getCustomerId());
        Log.d(TAG,"order_id"+orderId);
        Log.d(TAG,"ProdId"+ProdId);
        Log.d(TAG,"ComboId"+ComboId);
        call.enqueue(new Callback<ErrorMessageModel>() {
            @Override
            public void onResponse(Call<ErrorMessageModel> call, Response<ErrorMessageModel> response) {
                if (response.isSuccessful()){
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();

                    getNotification(orderId);

                }else{
                    Toast.makeText(context, "failure", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ErrorMessageModel> call, Throwable t){
                Log.d(TAG,"onFailure"+t.getMessage());

            }
        });
    }

    @Override
    public int getItemCount() {
        return comboModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView comboImageIv;
        TextView comboTitleTv;
        TextView comboDescriptionTv;
        Button comboRedeem;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            comboImageIv= itemView.findViewById(R.id.reward_image_cv);
            comboTitleTv= itemView.findViewById(R.id.reward_title_tv);
            comboDescriptionTv= itemView.findViewById(R.id.combo_descp_tv);
            comboRedeem= itemView.findViewById(R.id.redeem_combo_btn);
        }
    }

    public interface setFragmentTransaction {
        void sendPosition(int position,String comboId,String productid);
    }

    public void getNotification(String order_id) {
        RetrofitApi api = BaseClient.getClient().create(RetrofitApi.class);
        Call<GetNotificationResponse> call = api.getNotification( order_id, "Free Order");
        call.enqueue(new Callback<GetNotificationResponse>() {
            @Override
            public void onResponse(Call<GetNotificationResponse> call, Response<GetNotificationResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
                } else {

                }
            }

            @Override
            public void onFailure(Call<GetNotificationResponse> call, Throwable t) {
                Toast.makeText(context, "Onfail", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void setAletDialog(int position) {

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Are you sure!! Redeem Combo");
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                postItems(comboModels.get(position).getProdId(),comboModels.get(position).getComboId());
                comboModels.remove(position);
                notifyDataSetChanged();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.setCancelable(true);
        AlertDialog alertDialog = alert.create();


        alertDialog.show();
    }


}
