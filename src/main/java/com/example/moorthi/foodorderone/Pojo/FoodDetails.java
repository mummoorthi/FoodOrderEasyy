package com.example.moorthi.foodorderone.Pojo;

import java.math.BigDecimal;

/**
 * Created by moorthy on 11/20/18.
 */

public class FoodDetails {
    private Double average_rating;
    private String image_url;
    private String item_name;
    private BigDecimal item_price;
    private int count;

    public FoodDetails(Double average_rating, String image_url, String item_name, BigDecimal item_price,int count) {
        this.average_rating = average_rating;
        this.image_url = image_url;
        this.item_name = item_name;
        this.item_price = item_price;
        this.count=count;
    }

    public FoodDetails(){

    }

    public Double getAverage_rating() {
        return average_rating;
    }

    public void setAverage_rating(Double average_rating) {
        this.average_rating = average_rating;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public BigDecimal getItem_price() {
        return item_price;
    }

    public void setItem_price(BigDecimal item_price) {
        this.item_price = item_price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
