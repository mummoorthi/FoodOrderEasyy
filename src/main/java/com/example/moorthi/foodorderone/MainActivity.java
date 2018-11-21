package com.example.moorthi.foodorderone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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
import android.widget.Toast;

import com.example.moorthi.foodorderone.Activity.BiriyaniDiscriptions;
import com.example.moorthi.foodorderone.Activity.CartDetails;
import com.example.moorthi.foodorderone.Adapter.IndianFoodAdapter;
import com.example.moorthi.foodorderone.Database.DatabaseHelper;
import com.example.moorthi.foodorderone.Interfaces.CommonFoodPos;
import com.example.moorthi.foodorderone.Interfaces.RetroInterface;
import com.example.moorthi.foodorderone.Pojo.FoodDetails;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CommonFoodPos,View.OnClickListener {

    public DatabaseHelper databaseHelper;
    IndianFoodAdapter objAdapter;
    private List<FoodDetails> foodList;
    RecyclerView objRecylerFoodlist;
    Toolbar toolbar;
    MenuItem item;
AppCompatTextView count;
AppCompatImageView cartImage;
ConstraintLayout iconParent;
Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        objRecylerFoodlist = findViewById(R.id.Id_recyler_foodLList);
        LinearLayoutManager objLinearManager = new LinearLayoutManager(MainActivity.this);
        objRecylerFoodlist.setLayoutManager(objLinearManager);
                getFoodDetails();

        count = findViewById(R.id.count);
        cartImage = findViewById(R.id.cartImage);
        iconParent = findViewById(R.id.iconParent);
        iconParent.setOnClickListener(this);
        cartImage.setOnClickListener(this);
        getDetailsFromDb();
        setMenuCount();

    }

    private void getDetailsFromDb() {
        foodList = new ArrayList<>();
        foodList = databaseHelper.getAllFoods();
        if (foodList != null) {
            if (foodList.size() > 0) {
                objAdapter = new IndianFoodAdapter(this, foodList, this);
                objRecylerFoodlist.setAdapter(objAdapter);
            }
        }
    }



    private void setMenuCount() {
            count.setText(String.valueOf(databaseHelper.getGrandTotal()));

    }

    private void getFoodDetails() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://android-full-time-task.firebaseio.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetroInterface service = retrofit.create(RetroInterface.class);

        service.getFoodDetails().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                ResponseBody objBody = response.body();
                if (objBody != null) {
                    try {
                        JSONArray objMainArrray = new JSONArray(objBody.string());
                        if (objMainArrray != null) {
                            System.out.println("CHECK_DATA RESPONSE ARRAY : " + objMainArrray.toString());
                            if (objMainArrray.length() > 0) {
                                parseFoodDetails(objMainArrray);
                            }
                        }
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {

            }
        });
    }

    private void parseFoodDetails(JSONArray objMainArrray) {
        for (int i = 0; i <= objMainArrray.length(); i++) {
            try {
                JSONObject objContent = objMainArrray.getJSONObject(i);
                if (objContent != null) {
                    BigDecimal decimal = new BigDecimal(String.valueOf(objContent.get("item_price")));
                    FoodDetails objDetials = new FoodDetails(objContent.getDouble("average_rating"), objContent.getString("image_url"), objContent.getString("item_name"), decimal, 0);
                    if (databaseHelper != null) {
                        databaseHelper.addFoodDetail(objDetials);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        getDetailsFromDb();
    }

    @Override
    public void indianfoodPos(int pos, String type) {
        FoodDetails food = foodList.get(pos);
        switch (type) {
            case "add":
                Integer count_add = databaseHelper.addOrder(food);
                System.out.println("CHECK_DATA CLICK ADD COUNT : " + count_add);
                foodList.get(pos).setCount(count_add);
                objAdapter.notifyDataSetChanged();
                setMenuCount();
                break;
            case "remove":
                Integer count_sub = databaseHelper.removeOrder(food);
                System.out.println("CHECK_DATA CLICK SUB COUNT : " + count_sub);
                foodList.get(pos).setCount(count_sub);
                setMenuCount();
                objAdapter.notifyDataSetChanged();
                break;
            default:
                System.out.println("CHECK_DATA CLICK");
                Intent objIntent = new Intent(this, BiriyaniDiscriptions.class);
                objIntent.putExtra("content", new Gson().toJson(food));
                startActivityForResult(objIntent, 11);
                break;
        }

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iconParent:
                Intent i = new Intent(getApplicationContext(), CartDetails.class);
                startActivity(i);
                break;
        }
    }

    public boolean isnetworkAvailable(Activity activity) {
        this.activity=activity;
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}


