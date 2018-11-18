package com.example.moorthi.foodorderone.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.moorthi.foodorderone.Fragments.ChineseFood;
import com.example.moorthi.foodorderone.Fragments.IndainFood;
import com.example.moorthi.foodorderone.Fragments.ItalianFood;

/**
 * Created by moorthy on 11/18/18.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public ViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                IndainFood indainFood = new IndainFood();
                return indainFood;
            case 1:
                ChineseFood chineseFood = new ChineseFood();
                return chineseFood;
            case 2:
                ItalianFood italianFood = new ItalianFood();
                return italianFood;

                default:
                    return null;
        }
    }





    // This determines the number of tabs
    @Override
    public int getCount() {
        return 3;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return "Indian";
            case 1:
                return "Chinese";
            case 2:
                return "Italian";
            default:
                return null;
        }
    }

}

