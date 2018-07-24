package com.nantia.repartonantia.stock;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.nantia.repartonantia.R;
import com.nantia.repartonantia.adapters.StockAdapter;
import com.nantia.repartonantia.adapters.StockInfoPOJO;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StockFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StockFragment extends Fragment implements StockView{
    private ProgressBar progressBar;
    private RecyclerView stockRV;
    private SearchView buscarSV;
    private StockAdapter stockAdapter;
    private StockPresenter stockPresenter;


    public StockFragment() {
        // Required empty public constructor
    }

    /**
     */
    // TODO: Rename and change types and number of parameters
    public static StockFragment newInstance(String param1, String param2) {
        StockFragment fragment = new StockFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stock, container, false);
    }

    @Override
    public void setStockInfo(ArrayList<StockInfoPOJO> stock) {
        stockRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        stockAdapter = new StockAdapter(getActivity(), stock);
        stockRV.setAdapter(stockAdapter);
    }
}
