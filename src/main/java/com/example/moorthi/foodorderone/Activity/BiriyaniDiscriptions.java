package com.example.moorthi.foodorderone.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moorthi.foodorderone.Database.DatabaseHelper;
import com.example.moorthi.foodorderone.Interfaces.CommonFoodPos;
import com.example.moorthi.foodorderone.Pojo.FoodDetails;
import com.example.moorthi.foodorderone.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.math.BigDecimal;
import java.util.List;

public class BiriyaniDiscriptions extends AppCompatActivity implements View.OnClickListener{
AppCompatImageView backHomelist,productImage;
int quantity = 1;
AppCompatTextView txt_quantity,count,priceAmount,toolbartitle;
AppCompatButton addCart;
DatabaseHelper databaseHelper;
FoodDetails objFoodDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indian_discriptions);
        databaseHelper = new DatabaseHelper(this);
        backHomelist = findViewById(R.id.backHomelist);
        backHomelist.setOnClickListener(this);
        count = findViewById(R.id.count);
        productImage = findViewById(R.id.productImage);
        priceAmount = findViewById(R.id.priceAmount);
        toolbartitle = findViewById(R.id.toolbartitle);



        setMenuCount();

        Intent intent = getIntent();
        String dataTransmited=intent.getStringExtra("content");
        if (dataTransmited!=null){
            System.out.println("checkDATAAAA"+dataTransmited);
            objFoodDetails=new Gson().fromJson(dataTransmited,new TypeToken<FoodDetails>(){}.getType());
            System.out.println("CHECK_DETAILS :"+objFoodDetails.getItem_name());
        }
        Glide.with(getApplicationContext()).load(objFoodDetails.getImage_url()).into(productImage);
        BigDecimal decimal = new BigDecimal(String.valueOf(objFoodDetails.getItem_price()));
        toolbartitle.setText(objFoodDetails.getItem_name());
        String data = String.valueOf(objFoodDetails.getItem_price());
        priceAmount.setText(data);

       // intent.putExtra("result",count_add);
        //setResult(Activity.RESULT_OK,intent);
        //finish();



    }

    private void setMenuCount() {
        count.setText(String.valueOf(databaseHelper.getGrandTotal()));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backHomelist:
                finish();
                break;

        }
    }


}
