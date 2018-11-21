package com.example.moorthi.foodorderone.Pojo;

import java.math.BigDecimal;

/**
 * Created by moorthy on 11/20/18.
 */

public class cartItem {

    private int count;
    private String granttotal;
    private String granttoall;
private String itemname;

    public cartItem(int count, String granttotal, String granttoall, String itemname) {
        this.count = count;
        this.granttotal = granttotal;
        this.granttoall = granttoall;
        this.itemname = itemname;
    }

    public cartItem(){

    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getGranttotal() {
        return granttotal;
    }

    public void setGranttotal(String granttotal) {
        this.granttotal = granttotal;
    }

    public String getGranttoall() {
        return granttoall;
    }

    public void setGranttoall(String granttoall) {
        this.granttoall = granttoall;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }
}
