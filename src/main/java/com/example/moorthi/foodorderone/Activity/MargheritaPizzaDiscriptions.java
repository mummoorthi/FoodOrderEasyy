package com.example.moorthi.foodorderone.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.example.moorthi.foodorderone.R;

public class MargheritaPizzaDiscriptions extends AppCompatActivity implements View.OnClickListener{
    AppCompatImageView backHomelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_margherita_pizza_discriptions);

        backHomelist = findViewById(R.id.backHomelist);
        backHomelist.setOnClickListener(this);
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
