package com.example.moorthi.foodorderone.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.moorthi.foodorderone.Database.DatabaseHelper;
import com.example.moorthi.foodorderone.Interfaces.CommonFoodPos;
import com.example.moorthi.foodorderone.Pojo.FoodDetails;
import com.example.moorthi.foodorderone.Pojo.cartItem;
import com.example.moorthi.foodorderone.R;

import java.util.List;

/**
 * Created by moorthy on 11/20/18.
 */

public class CartAdapter  extends RecyclerView.Adapter<CartAdapter.MyviewHolder>{

    Context activity;
    List<cartItem> indianSpeclslist;
    List<FoodDetails> indianSpeclslistt;
    CommonFoodPos pos;
DatabaseHelper databaseHelper;

    public CartAdapter(Activity activityy, List<cartItem> foodList,List<FoodDetails> foodListt, CommonFoodPos foodpos) {
        this.activity = activityy;
        this.indianSpeclslist = foodList;
        this.indianSpeclslistt = foodListt;
        this.pos = foodpos;
    }



    @NonNull
    @Override
    public CartAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        return new CartAdapter.MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyviewHolder holder, int position) {

        Glide.with(activity).load(indianSpeclslistt.get(position).getImage_url())
                .into(holder.cartImageView);

        holder.carttitle.setText(indianSpeclslistt.get(position).getItem_name());
        holder.cartprice.setText(indianSpeclslistt.get(position).getItem_price().toString());
        holder.grantTotal.setText(indianSpeclslist.get(position).getGranttotal());
        String data = String.valueOf(indianSpeclslistt.get(position).getCount());
        holder.Id_txt_menu_count.setText(data);

    }

    @Override
    public int getItemCount() {
        return indianSpeclslist.size();
    }

    class MyviewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView cartImageView;
        AppCompatTextView carttitle,cartprice,grantTotal,Id_txt_menu_count,grantTotall;
        public MyviewHolder(View itemView) {
            super(itemView);
            cartImageView = itemView.findViewById(R.id.cartitemImage);
            carttitle = itemView.findViewById(R.id.cartTitle);
            cartprice = itemView.findViewById(R.id.cartPrice);
            grantTotal = itemView.findViewById(R.id.grantTotal);
            Id_txt_menu_count = itemView.findViewById(R.id.Id_txt_count);
          //  grantTotall = itemView.findViewById(R.id.grantTotall);

        }
    }
}
