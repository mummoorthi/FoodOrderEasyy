package com.example.moorthi.foodorderone.Activity;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.moorthi.foodorderone.Adapter.CartAdapter;
import com.example.moorthi.foodorderone.Adapter.IndianFoodAdapter;
import com.example.moorthi.foodorderone.Database.DatabaseHelper;
import com.example.moorthi.foodorderone.Interfaces.CommonFoodPos;
import com.example.moorthi.foodorderone.Pojo.FoodDetails;
import com.example.moorthi.foodorderone.Pojo.cartItem;
import com.example.moorthi.foodorderone.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CartDetails extends AppCompatActivity implements CommonFoodPos,View.OnClickListener{
    RecyclerView recyclerView;
public  DatabaseHelper databaseHelper;
List<cartItem> foodListt;
    private List<FoodDetails> foodList;
AppCompatTextView total,count,title;
CartAdapter adapter;
AppCompatImageView ic_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_details);

        databaseHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.cartRecylerview);
        count = findViewById(R.id.count);
        ic_back = findViewById(R.id.ic_back);
        title = findViewById(R.id.title);
        ic_back.setOnClickListener(this);
        LinearLayoutManager objLinearManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(objLinearManager);
        getcheckoutFromDb();
        getDetailsFromDbb();
        System.out.println("CHECK_CHECKOUT_PAGE foodListt : "+new Gson().toJson(foodListt));
        System.out.println("CHECK_CHECKOUT_PAGE foodList : "+new Gson().toJson(foodList));
        total = findViewById(R.id.Total);
        if(foodListt!=null){
            for(int i=0; i<foodListt.size(); i++){
                total.setText(foodListt.get(i).getGranttoall());
            }
        }
        setMenuCount();

    }


    private void setMenuCount(){
        count.setText(String.valueOf(databaseHelper.getGrandTotal()));
    }

    private void getcheckoutFromDb() {
        foodListt=new ArrayList<>();
        foodListt=databaseHelper.getCheckout();
        System.out.println("bchbdjcbdv"+foodListt);
        if (foodListt!=null){
            if (foodListt.size()>0){
                adapter=new CartAdapter(this,foodListt,foodList,this);
                recyclerView.setAdapter(adapter);
            }
        }
    }
    private void getDetailsFromDbb() {
        System.out.println("hjuih"+foodList);
        foodList=new ArrayList<>();
        foodList=databaseHelper.getAllFoods();
        if (foodList!=null){
            if (foodList.size()>0){
                adapter=new CartAdapter(this,foodListt,foodList,this);
                recyclerView.setAdapter(adapter);
            }
        }
    }

    @Override
    public void indianfoodPos(int pos, String type) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ic_back:
                finish();
                break;
        }
    }
}
