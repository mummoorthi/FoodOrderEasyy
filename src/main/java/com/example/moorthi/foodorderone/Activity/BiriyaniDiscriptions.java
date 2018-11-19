package com.example.moorthi.foodorderone.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.example.moorthi.foodorderone.R;

public class BiriyaniDiscriptions extends AppCompatActivity implements View.OnClickListener{
AppCompatImageView backHomelist;
int quantity = 1;
AppCompatImageButton sub,add;
AppCompatTextView txt_quantity;
AppCompatButton addCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indian_discriptions);
        backHomelist = findViewById(R.id.backHomelist);
        backHomelist.setOnClickListener(this);
        sub = findViewById(R.id.sub);
        sub.setOnClickListener(this);
        add = findViewById(R.id.add);
        add.setOnClickListener(this);
        txt_quantity = findViewById(R.id.text);
        addCart = findViewById(R.id.addCart);
addCart.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backHomelist:
                finish();
                break;

            case R.id.add:
                quantity = quantity + 1;
                System.out.println("quatity is " + quantity);
                txt_quantity.setText(String.valueOf(quantity));

                break;
            case R.id.sub:
                if (quantity > 1) {
                    quantity = quantity - 1;
                    txt_quantity.setText(String.valueOf(quantity));
                }
                break;
            case R.id.addCart:
                Toast.makeText(getApplicationContext(),"Add Cart",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
