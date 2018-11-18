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

import com.example.moorthi.foodorderone.Activity.DumplingsDiscriptions;
import com.example.moorthi.foodorderone.Activity.FriedDiscriptions;
import com.example.moorthi.foodorderone.Activity.MargheritaPizzaDiscriptions;
import com.example.moorthi.foodorderone.Activity.PanzenellaDicriptions;
import com.example.moorthi.foodorderone.Activity.PastaDiscriptions;
import com.example.moorthi.foodorderone.Activity.SpanichNoodelsDiscriptions;
import com.example.moorthi.foodorderone.Adapter.ChineaseFoodAdapter;
import com.example.moorthi.foodorderone.Adapter.ItalianFoodAdapter;
import com.example.moorthi.foodorderone.Interfaces.CommonFoodPos;
import com.example.moorthi.foodorderone.Model.CommonModel;
import com.example.moorthi.foodorderone.R;

import java.util.ArrayList;

/**
 * Created by moorthy on 11/18/18.
 */

public class ItalianFood extends Fragment implements CommonFoodPos{
    RecyclerView recylerindianfoods;
    ItalianFoodAdapter adapter;
    ArrayList<CommonModel> list = new ArrayList<>();
    CommonModel indianSpecl;
    public ItalianFood() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_italian_food, container, false);
        declare(v);
        insertData();
        return v;
    }

    private void insertData() {
        indianSpecl = new CommonModel("Panzenella","200",getResources().getDrawable(R.drawable.paniaitaly1));
        list.add(indianSpecl);
        indianSpecl = new CommonModel("Pasta","100",getResources().getDrawable(R.drawable.pasta2));
        list.add(indianSpecl);
        indianSpecl = new CommonModel("Margherita Pizza","200",getResources().getDrawable(R.drawable.pizaita1));
        list.add(indianSpecl);

    }

    private void declare(View v) {
        recylerindianfoods = v.findViewById(R.id.recylerIndianfoods);
        adapter = new ItalianFoodAdapter(getActivity(),list,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recylerindianfoods.setLayoutManager(mLayoutManager);
        recylerindianfoods.setItemAnimator(new DefaultItemAnimator());
        recylerindianfoods.setAdapter(adapter);
    }


    @Override
    public void indianfoodPos(int pos) {
        if(pos == 0){
            Intent i = new Intent(getActivity(), PanzenellaDicriptions.class);
            startActivity(i);
        }else if(pos ==1){
            Intent i = new Intent(getActivity(), PastaDiscriptions.class);
            startActivity(i);
        }else {
            Intent i = new Intent(getActivity(), MargheritaPizzaDiscriptions.class);
            startActivity(i);
        }
    }
}
