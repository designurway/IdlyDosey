package com.designurway.idlidosa.a.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.model.ErrorMessageModel;
import com.designurway.idlidosa.a.model.GetTotalAmountModel;
import com.designurway.idlidosa.a.model.ViewCartModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static easypay.manager.PaytmAssist.getContext;

public class ViewCartAdapter extends RecyclerView.Adapter
        <ViewCartAdapter.MyViewHolder> {
    ArrayList<ViewCartModel> viewCartModels;
    Context context;
    int amount = 0;
    int amt = 0;
    String orderId;
    setFragmentTransaction setFragmentTransaction;


    public ViewCartAdapter(Context context, ArrayList<ViewCartModel> viewCartModels) {
        this.context = context;
        this.viewCartModels = viewCartModels;
    }

    public void sendToFragment(setFragmentTransaction setOnFragmentTransaction) {
        this.setFragmentTransaction = setOnFragmentTransaction;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_cart_items, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (viewCartModels.get(position).getProduct_name()!=null ){
            holder.itemName.setText(viewCartModels.get(position).getProduct_name());
        }else {
            holder.itemName.setText(viewCartModels.get(position).getMedicine_name());
        }


        holder.itemQty.setText(viewCartModels.get(position).getQuantity());

        holder.itemPrice.setText(String.valueOf(viewCartModels.get(position).getAmount()));


        if (viewCartModels.get(position).getImage()!=null && !viewCartModels.get(position).getImage().isEmpty()){
            Picasso.get().load(viewCartModels.get(position).getImage()).into(holder.prodImage);
            holder.itemName.setText(viewCartModels.get(position).getProduct_name());
        }else if (viewCartModels.get(position).getMedicine_image()!=null && !viewCartModels.get(position).getMedicine_image().isEmpty()){
            Picasso.get().load(viewCartModels.get(position).getMedicine_image()).into(holder.prodImage);
            holder.itemName.setText("Medicine");
        }else {
            Toast.makeText(context, "No Image", Toast.LENGTH_SHORT).show();
        }

        orderId = viewCartModels.get(position).getOrder_id();

        holder.prodDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                deleteFromCart(viewCartModels.get(position).getOrder_id(), viewCartModels.get(position).getProduct_id());
//                Log.d("cartAdapter", "prod" + viewCartModels.get(position).getProduct_id());
//                viewCartModels.remove(position);

                setFragmentTransaction.sendPosition(viewCartModels.get(position).getProduct_id(),viewCartModels.get(position).getOrder_id(),holder.itemView);
                notifyDataSetChanged();
            }
        });
        holder.plusIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int amt = 0;
                int num = Integer.valueOf(viewCartModels.get(position).getQuantity());

                num++;
                holder.itemQty.setText(String.valueOf(num));
                viewCartModels.get(position).setQuantity(String.valueOf(num));
                amount = Integer.valueOf(viewCartModels.get(position).getPrice());
                amt = Integer.valueOf(holder.itemPrice.getText().toString());

                holder.itemQty.setText(String.valueOf(num));
                postCartQuantity(String.valueOf(num), viewCartModels.get(position).getProduct_id(),
                        viewCartModels.get(position).getOrder_id(), String.valueOf(amount * num));
                holder.itemPrice.setText(String.valueOf(amount * num));
//                setFragmentTransaction.sendPosition(position,  GetAmount(5));



            }
        });

        holder.minusIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int amt = 0;
                int num = Integer.valueOf(viewCartModels.get(position).getQuantity());
                if (num>1){
                    num--;
                    holder.itemQty.setText(String.valueOf(num));
                    viewCartModels.get(position).setQuantity(String.valueOf(num));
                    amount = Integer.valueOf(viewCartModels.get(position).getPrice());
                    amt = Integer.valueOf(holder.itemPrice.getText().toString());

                    holder.itemQty.setText(String.valueOf(num));
                    postCartQuantity(String.valueOf(num), viewCartModels.get(position).getProduct_id(),
                            viewCartModels.get(position).getOrder_id(), String.valueOf(amount * num));
                    holder.itemPrice.setText(String.valueOf(amount * num));
                }


//                setFragmentTransaction.sendPosition(position,  GetAmount(5));




            }
        });


    }



    @Override
    public int getItemCount() {
        return viewCartModels.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView itemDescp;
        TextView itemQty;
        TextView itemPrice;
        ImageView minusIv;
        ImageView plusIv;
        ImageView prodImage;
        ImageView prodDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name_tv);
//            itemDescp=(TextView)itemView.findViewById(R.id.item_descp_tv);
            itemQty = itemView.findViewById(R.id.item_qty_tv);
            itemPrice = itemView.findViewById(R.id.item_price_tv);
            minusIv = itemView.findViewById(R.id.minus_img_iv);
            plusIv = itemView.findViewById(R.id.plus_img_iv);
            prodImage = itemView.findViewById(R.id.prod_img_iv);
            prodDelete = itemView.findViewById(R.id.item_delete_tv);
        }
    }

    public void postCartQuantity(String qnty, String pro_id, String orderId, String amount) {


        RetrofitApi api = BaseClient.getClient().create(RetrofitApi.class);
        Call<ErrorMessageModel> call = api.postCartQuantity(PreferenceManager.getCustomerId(),
                pro_id, orderId, amount, qnty);

        call.enqueue(new Callback<ErrorMessageModel>() {
            @Override
            public void onResponse(Call<ErrorMessageModel> call, Response<ErrorMessageModel> response) {
                if (response.isSuccessful()) {
                    GetAmount();


                } else {
                    Toast.makeText(context, "fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ErrorMessageModel> call, Throwable t) {

                Log.d("onfailure","failure"+t.getMessage());

            }
        });
    }


    public interface setFragmentTransaction {
        void sendPosition(String id,String OrderId,View view);
        void sendAmount(int amount);
    }

    public int GetAmount() {

        RetrofitApi api = BaseClient.getClient().create(RetrofitApi.class);

        Call<GetTotalAmountModel> call = api.getAmount(PreferenceManager.getCustomerId());
        call.enqueue(new Callback<GetTotalAmountModel>() {
            @Override
            public void onResponse(Call<GetTotalAmountModel> call, Response<GetTotalAmountModel> response) {
                if (response.isSuccessful()) {
//                    txt_rupee.setText(response.body().getTotal_amount());
                    if (response.body().getTotal_amount()!=null){
                        if (Integer.valueOf(response.body().getTotal_amount())>0){
                            amt = Integer.valueOf(response.body().getTotal_amount());
//                            Toast.makeText(context, "Total"+amt, Toast.LENGTH_SHORT).show();
                            setFragmentTransaction.sendAmount(amt);
                        }else{
                            amt=0;
                        }
                    }

//                    Toast.makeText(context, String.valueOf(response.body().getTotal_amount()), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetTotalAmountModel> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return amt;
    }


}
