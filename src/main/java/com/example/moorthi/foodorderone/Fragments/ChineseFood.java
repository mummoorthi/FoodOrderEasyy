package com.example.moorthi.foodorderone.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moorthi.foodorderone.R;

/**
 * Created by moorthy on 11/18/18.
 */

public class ChineseFood extends Fragment {

    public ChineseFood() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chinese_food, container, false);
        return v;
    }

}

