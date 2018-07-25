package com.nantia.repartonantia.stock;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nantia.repartonantia.R;

public class StockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        StockFragment stockFragment = new StockFragment();
        fragmentTransaction.add(R.id.stock_layout, stockFragment).commit();
    }
}
