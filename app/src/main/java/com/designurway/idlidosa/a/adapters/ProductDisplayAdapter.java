package com.designurway.idlidosa.a.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.designurway.idlidosa.R;
import com.designurway.idlidosa.a.model.ErrorMessageModel;
import com.designurway.idlidosa.a.model.Menumodel;
import com.designurway.idlidosa.a.model.StatusAndMessageModel;
import com.designurway.idlidosa.a.retrofit.BaseClient;
import com.designurway.idlidosa.a.retrofit.RetrofitApi;
import com.designurway.idlidosa.a.utils.AndroidUtils;
import com.designurway.idlidosa.a.utils.PreferenceManager;
import com.designurway.idlidosa.a.utils.SharedPrefManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDisplayAdapter extends RecyclerView.Adapter<ProductDisplayAdapter.Vholder> {
    private static final String TAG = "ProductDisplayAdapter";
    ArrayList<Menumodel> menudtailsmodel;
    Context context;
    int amount = 0;
    String orderid;
    int qnty = 0;

    setFragmentTransaction setFragmentTransaction;

    public void sendToFragment(setFragmentTransaction setOnFragmentTransaction) {
        this.setFragmentTransaction = setOnFragmentTransaction;
    }

    public ProductDisplayAdapter(ArrayList<Menumodel> menudtailsmodel, Context context) {
        this.menudtailsmodel = menudtailsmodel;
        this.context = context;
    }

    @NonNull
    @Override
    public Vholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.layout_display_product_item, parent, false);
        return new Vholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vholder holder, int position) {

        holder.title_txt.setText(menudtailsmodel.get(position).getProduct_name());
        holder.txt_rupees.setText(menudtailsmodel.get(position).getPrice());
        holder.item_qty_tv.setText(String.valueOf(menudtailsmodel.get(position).getOrder_qty()));
//        holder.btn_addTocart.setText(menudtailsmodel.get(position).);
        qnty = Integer.valueOf(menudtailsmodel.get(position).getOrder_qty());
        menudtailsmodel.get(position).setQty((Integer.valueOf(menudtailsmodel.get(position).getOrder_qty())));

        holder.image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragmentTransaction.sendPosition(position, menudtailsmodel.get(position).getProduct_id(), holder.itemView,menudtailsmodel.get(position).getProduct_present());
            }
        });
        if (menudtailsmodel.get(position).getProduct_present().contains("yes")) {
            holder.btn_addTocart.setText("Already In Cart");
            holder.linear_button.setVisibility(View.GONE);

        }

        Picasso.get().load(menudtailsmodel.get(position).getImage()).into(holder.image_view);

        holder.plus_img_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int num = Integer.valueOf(menudtailsmodel.get(position).getQty());

                num++;
                holder.item_qty_tv.setText(String.valueOf(num));
                menudtailsmodel.get(position).setQty((num));
                amount = Integer.valueOf(menudtailsmodel.get(position).getPrice());
                postCartQuantity(String.valueOf(num), menudtailsmodel.get(position).getProduct_id(), String.valueOf(amount * num));
                holder.txt_rupees.setText(String.valueOf(amount * num));


            }
        });

        holder.minus_img_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.valueOf(menudtailsmodel.get(position).getQty());
                if (num > Integer.valueOf(menudtailsmodel.get(position).getOrder_qty())) {
                    num--;
                    holder.item_qty_tv.setText(String.valueOf(num));
                    menudtailsmodel.get(position).setQty(num);
                    amount = Integer.valueOf(menudtailsmodel.get(position).getPrice());
                    postCartQuantity(String.valueOf(num), menudtailsmodel.get(position).getProduct_id(), String.valueOf(amount * num));
                    holder.txt_rupees.setText(String.valueOf(amount * num));
                }


            }
        });

        holder.btn_addTocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menudtailsmodel.get(position).getProduct_present().contains("yes")) {
                    holder.btn_addTocart.setText("Already In Cart");
                    holder.linear_button.setVisibility(View.GONE);

                }else{
                    amount = Integer.valueOf(menudtailsmodel.get(position).getPrice());
                    holder.linear_button.setVisibility(View.VISIBLE);
                    AddToCart(String.valueOf(amount), String.valueOf(1), menudtailsmodel.get(position).getProduct_id(), holder.btn_addTocart);
                    holder.btn_addTocart.setVisibility(View.GONE);
                    setFragmentTransaction.sendView(position,holder.itemView);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return menudtailsmodel.size();
    }

    public class Vholder extends RecyclerView.ViewHolder {
        TextView title_txt, sub_title, txt_rupees, item_qty_tv;
        ImageView image_view, minus_img_iv, plus_img_iv;
        Button btn_addTocart;
        LinearLayout linear_button;

        public Vholder(@NonNull View itemView) {
            super(itemView);

            title_txt = itemView.findViewById(R.id.title_txt);
            sub_title = itemView.findViewById(R.id.sub_title);
            image_view = itemView.findViewById(R.id.image_view);
            minus_img_iv = itemView.findViewById(R.id.minus_img_iv);
            plus_img_iv = itemView.findViewById(R.id.plus_img_iv);
            txt_rupees = itemView.findViewById(R.id.txt_rupees);
            item_qty_tv = itemView.findViewById(R.id.item_qty_tv);
            btn_addTocart = itemView.findViewById(R.id.btn_addTocart);
            linear_button = itemView.findViewById(R.id.linear_button);

        }
    }

    public void postCartQuantity(String qnty, String pro_id, String amount) {


        RetrofitApi api = BaseClient.getClient().create(RetrofitApi.class);
        Call<ErrorMessageModel> call = api.postCartQuantity(PreferenceManager.getCustomerId(),
                pro_id, orderid, amount, qnty);
        Log.d(TAG, "orderId" + orderid);

        call.enqueue(new Callback<ErrorMessageModel>() {
            @Override
            public void onResponse(Call<ErrorMessageModel> call, Response<ErrorMessageModel> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, "Already in cart",
                            Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ErrorMessageModel> call, Throwable t) {

                Toast.makeText(context, "Onfailure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void AddToCart(String amount, String quantity, String pro_id, Button btn) {
        int count = SharedPrefManager.loadFrompref(context);
        count++;
        SharedPrefManager.SaveTotalKey(context, count);
        String order_id = AndroidUtils.randomName(5);
        Log.d("order_id", order_id);
        orderid = order_id;
        RetrofitApi api = BaseClient.getClient().create(RetrofitApi.class);
        Call<StatusAndMessageModel> call = api.AddtoCart(PreferenceManager.getCustomerId(), pro_id,
                amount, order_id, quantity);
        Log.d(TAG, "customer_id" + PreferenceManager.getCustomerId());
        Log.d(TAG, "pro_id" + pro_id);
        Log.d(TAG, "amount" + amount);
        Log.d(TAG, "order_id" + order_id);
        Log.d(TAG, "quantity" + quantity);

        call.enqueue(new Callback<StatusAndMessageModel>() {
            @Override
            public void onResponse(Call<StatusAndMessageModel> call, Response<StatusAndMessageModel> response) {
                if (response.isSuccessful() && response.body().getMessage().contains("Inserted")) {
//                    we will get the order id in this api

                    Log.d("order_id", "orderid");

                } else if (response.body().getMessage().contains("Already in cart")) {
                    Toast.makeText(context, "Already in cart", Toast.LENGTH_SHORT).show();
                    btn.setText("Already in cart");
                }
            }

            @Override
            public void onFailure(Call<StatusAndMessageModel> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.getMessage());
            }
        });

    }

    public interface setFragmentTransaction {
        void sendPosition(int position, String id, View view,String present);

        void sendView(int position,View view);
    }


}
