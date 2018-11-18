package com.example.moorthi.foodorderone.Adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;
import com.example.moorthi.foodorderone.Fragments.IndainFood;
import com.example.moorthi.foodorderone.Interfaces.IndianFoodPos;
import com.example.moorthi.foodorderone.Model.IndianSpecl;
import com.example.moorthi.foodorderone.R;

import java.util.ArrayList;

/**
 * Created by moorthy on 11/18/18.
 */

public class IndianFoodAdapter extends RecyclerView.Adapter<IndianFoodAdapter.MyviewHolder>{

    Context activity;
ArrayList<IndianSpecl> indianSpeclslist;
    IndianFoodPos pos;

    public IndianFoodAdapter(Activity activityy, ArrayList<IndianSpecl> list,IndianFoodPos foodpos) {
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

    class MyviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        AppCompatImageView foodimage,priceimage;
        AppCompatTextView title,price;

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
            switch (view.getId()){
                case R.id.imageIndianFoods:
                    int poss = getAdapterPosition();
                    pos.indianfoodPos(poss);
                    break;
            }
        }
    }
}
