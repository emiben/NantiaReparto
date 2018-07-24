package com.nantia.repartonantia.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nantia.repartonantia.R;
import com.nantia.repartonantia.stock.Stock;

import java.util.ArrayList;

/**
 * Created by Emi on 23/7/2018.
 */

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.ViewHolder>{
    private ArrayList<StockInfoPOJO> mData;
    private LayoutInflater mInflater;

    public StockAdapter(Context context, ArrayList<StockInfoPOJO> mData) {
        this.mData = mData;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.stock_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StockInfoPOJO stockInfoPOJO = mData.get(position);
        holder.primario.setText(stockInfoPOJO.getPrimario());
        holder.cantidad.setText(String.valueOf(stockInfoPOJO.getCantidad()));
        holder.secundario.setText(stockInfoPOJO.getSecudario());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView primario;
        TextView cantidad;
        TextView secundario;

        ViewHolder(View itemView) {
            super(itemView);
            primario = itemView.findViewById(R.id.primarioTV);
            cantidad = itemView.findViewById(R.id.cantidadTV);
            secundario = itemView.findViewById(R.id.secundaroioTV);
        }
    }
}
