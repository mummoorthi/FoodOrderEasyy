package com.example.moorthi.foodorderone.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.moorthi.foodorderone.Interfaces.CommonFoodPos;
import com.example.moorthi.foodorderone.Model.CommonModel;
import com.example.moorthi.foodorderone.Pojo.FoodDetails;
import com.example.moorthi.foodorderone.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moorthy on 11/18/18.
 */

public class IndianFoodAdapter extends RecyclerView.Adapter<IndianFoodAdapter.MyviewHolder>{

    Context activity;
List<FoodDetails> indianSpeclslist;
    CommonFoodPos pos;

    public IndianFoodAdapter(Activity activityy, List<FoodDetails> list, CommonFoodPos foodpos) {
        this.activity = activityy;
        this.indianSpeclslist = list;
        this.pos = foodpos;
    }

    @NonNull
    @Override
    public IndianFoodAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.indian_foodfood_item,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IndianFoodAdapter.MyviewHolder holder, int position) {

        Glide.with(activity).load(indianSpeclslist.get(position).getImage_url())
                .into(holder.foodimage);

        holder.title.setText(indianSpeclslist.get(position).getItem_name());
        holder.price.setText(indianSpeclslist.get(position).getItem_price().toString());
        holder.countname.setText(String.valueOf(indianSpeclslist.get(position).getCount()));
        holder.rateTitle.setText(String.valueOf(indianSpeclslist.get(position).getAverage_rating()));

    }

    @Override
    public int getItemCount() {
        return indianSpeclslist.size();
    }

    class MyviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        AppCompatImageView foodimage,priceimage;
        AppCompatTextView title,price;
AppCompatImageButton add,sub;
AppCompatTextView countname,rateTitle;
        MyviewHolder(View itemView) {
            super(itemView);
            foodimage = itemView.findViewById(R.id.imageIndianFoods);
            foodimage.setOnClickListener(this);
            priceimage = itemView.findViewById(R.id.imagePrice);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.priceAmount);
            rateTitle = itemView.findViewById(R.id.rateTitle);
            add = itemView.findViewById(R.id.add);
            sub = itemView.findViewById(R.id.sub);
            countname = itemView.findViewById(R.id.text);
            add.setOnClickListener(this);
            sub.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.imageIndianFoods:
                    pos.indianfoodPos(getAdapterPosition(),"click");
                    break;
                case R.id.add:
                    pos.indianfoodPos(getAdapterPosition(),"add");
                    break;
                case R.id.sub:
                    pos.indianfoodPos(getAdapterPosition(),"remove");
                    break;
            }
        }
    }
}
