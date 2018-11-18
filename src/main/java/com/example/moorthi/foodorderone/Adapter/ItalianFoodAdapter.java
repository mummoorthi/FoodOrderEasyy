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
import com.example.moorthi.foodorderone.Interfaces.CommonFoodPos;
import com.example.moorthi.foodorderone.Model.CommonModel;
import com.example.moorthi.foodorderone.R;

import java.util.ArrayList;

/**
 * Created by moorthy on 11/18/18.
 */

public class ItalianFoodAdapter extends RecyclerView.Adapter<ItalianFoodAdapter.MyviewHolder> {

    Context activity;
    ArrayList<CommonModel> indianSpeclslist;
    CommonFoodPos pos;

    public ItalianFoodAdapter(Activity activityy, ArrayList<CommonModel> list, CommonFoodPos foodpos) {
        this.activity = activityy;
        this.indianSpeclslist = list;
        this.pos = foodpos;
    }

    @NonNull
    @Override
    public ItalianFoodAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chinease_food_item, parent, false);
        return new ItalianFoodAdapter.MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItalianFoodAdapter.MyviewHolder holder, int position) {

        //  String imageUrl="https://image.tmdb.org/t/p/w500/"+data.get(position).getPosterPath();
        //Glide.with(activity).load(imageUrl).into(holder.movieimage);

        Glide.with(activity).load(indianSpeclslist.get(position).getImage())
                .into(holder.foodimage);
        holder.title.setText(indianSpeclslist.get(position).getTitle());
        holder.price.setText(indianSpeclslist.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return indianSpeclslist.size();
    }

    class MyviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        AppCompatImageView foodimage, priceimage;
        AppCompatTextView title, price;

        public MyviewHolder(View itemView) {
            super(itemView);
            foodimage = itemView.findViewById(R.id.imageIndianFoods);
            foodimage.setOnClickListener(this);
            priceimage = itemView.findViewById(R.id.imagePrice);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.priceAmount);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imageIndianFoods:
                    int poss = getAdapterPosition();
                    pos.indianfoodPos(poss);
                    break;
            }
        }
    }
}