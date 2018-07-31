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

import android.widget.Toast;
import com.nantia.repartonantia.R;
import com.nantia.repartonantia.adapters.StockAdapter;
import com.nantia.repartonantia.adapters.StockInfoPOJO;

import java.util.ArrayList;

/**
 *

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stock, container, false);
        stockPresenter = new StockPresenter(this);
        initializeViewObjects(view);
        stockPresenter.getStock();

        return view;
    }

    @Override
    public void setStockInfo(ArrayList<StockInfoPOJO> stock) {
        stockRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        stockAdapter = new StockAdapter(getActivity(), stock);
        stockRV.setAdapter(stockAdapter);
    }

    @Override public void addListeners() {
        if(stockAdapter != null){
            buscarSV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    stockAdapter.getFilter().filter(s);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    stockAdapter.getFilter().filter(s);
                    return false;
                }
            });
        }
    }

    @Override public void onSetProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    private void initializeViewObjects(View view){
        progressBar = view.findViewById(R.id.stock_progress);
        stockRV = view.findViewById(R.id.stock_lista_rv);
        buscarSV = view.findViewById(R.id.stock_buscar_sv);
    }
}
