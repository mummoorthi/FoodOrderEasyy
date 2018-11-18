package com.example.moorthi.foodorderone.Model;

import android.graphics.drawable.Drawable;

/**
 * Created by moorthy on 11/18/18.
 */

public class IndianSpecl {

    String title,price;
    Drawable Image;


    public IndianSpecl(String title, String price,Drawable img) {
        this.title = title;
        this.price = price;
        this.Image = img;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Drawable getImage() {
        return Image;
    }

    public void setImage(Drawable image) {
        Image = image;
    }
}
