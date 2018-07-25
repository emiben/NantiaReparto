package com.nantia.repartonantia.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.nantia.repartonantia.R;
import java.util.ArrayList;

/**
 * Created by Emi on 23/7/2018.
 */

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.ViewHolder> implements Filterable {
    private ArrayList<StockInfoPOJO> mData;
    private LayoutInflater mInflater;
    private ArrayList<StockInfoPOJO> mDataOrigianl;
    ValueFilter valueFilter;

    public StockAdapter(Context context, ArrayList<StockInfoPOJO> mData) {
        this.mData = mData;
        this.mInflater = LayoutInflater.from(context);
        mDataOrigianl = new ArrayList<>();
        mDataOrigianl.addAll((ArrayList<StockInfoPOJO>)mData.clone());
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


    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }


    private class ValueFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint != null && constraint.length() > 0){
                ArrayList<StockInfoPOJO> filterList = new ArrayList<>();
                for (int i = 0; i < mDataOrigianl.size(); i++) {
                    if((mDataOrigianl.get(i).getPrimario().toUpperCase()).contains(constraint.toString().toUpperCase())){
                        filterList.add(mDataOrigianl.get(i));
                    }
                }
                filterResults.count = filterList.size();
                filterResults.values = filterList;
            } else {
                filterResults.count = mDataOrigianl.size();
                filterResults.values = mDataOrigianl;
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mData.clear();
            mData.addAll((ArrayList<StockInfoPOJO>) results.values);
            notifyDataSetChanged();
        }
    }
}
