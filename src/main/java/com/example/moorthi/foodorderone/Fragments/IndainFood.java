package com.example.moorthi.foodorderone.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moorthi.foodorderone.Activity.BiriyaniDiscriptions;
import com.example.moorthi.foodorderone.Activity.ChapatiDiscriptions;
import com.example.moorthi.foodorderone.Activity.DosaDiscriptions;
import com.example.moorthi.foodorderone.Adapter.IndianFoodAdapter;
import com.example.moorthi.foodorderone.Interfaces.CommonFoodPos;
import com.example.moorthi.foodorderone.Model.CommonModel;
import com.example.moorthi.foodorderone.R;

import java.util.ArrayList;


public class IndainFood extends Fragment implements CommonFoodPos {
    RecyclerView recylerindianfoods;
    IndianFoodAdapter adapter;
    ArrayList<CommonModel> list = new ArrayList<>();
    CommonModel indianSpecl;
    @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_indain_food, container, false);
        declare(v);
        insertData();
        return v;
        }

    private void insertData() {
        indianSpecl = new CommonModel("Chicken Biriyani","200",getResources().getDrawable(R.drawable.images));
        list.add(indianSpecl);
        indianSpecl = new CommonModel("Dosa","100",getResources().getDrawable(R.drawable.chapthi2));
        list.add(indianSpecl);
        indianSpecl = new CommonModel("Chapati","100",getResources().getDrawable(R.drawable.dosa));
        list.add(indianSpecl);

    }

    private void declare(View v) {
        recylerindianfoods = v.findViewById(R.id.recylerIndianfoods);
        adapter = new IndianFoodAdapter(getActivity(),list,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recylerindianfoods.setLayoutManager(mLayoutManager);
        recylerindianfoods.setItemAnimator(new DefaultItemAnimator());
        recylerindianfoods.setAdapter(adapter);
    }


    @Override
    public void indianfoodPos(int pos) {
        if(pos == 0){
            Intent i = new Intent(getActivity(), BiriyaniDiscriptions.class);
            startActivity(i);
        }else if(pos ==1){
            Intent i = new Intent(getActivity(), ChapatiDiscriptions.class);
            startActivity(i);
        }else {
            Intent i = new Intent(getActivity(), DosaDiscriptions.class);
            startActivity(i);
        }
    }
}

