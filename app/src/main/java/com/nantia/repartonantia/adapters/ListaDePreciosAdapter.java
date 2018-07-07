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
import com.nantia.repartonantia.listadeprecios.ListaDePrecio;

import java.util.ArrayList;

/**
 * Created by Emi on 6/7/2018.
 */

public class ListaDePreciosAdapter extends RecyclerView.Adapter<ListaDePreciosAdapter.ViewHolder> implements Filterable {

    private ArrayList<ListaDePrecio> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private ArrayList<ListaDePrecio> mDataOrigianl;
    ValueFilter valueFilter;

    public ListaDePreciosAdapter(Context context, ArrayList<ListaDePrecio> mData) {
        this.mData = mData;
        this.mInflater = LayoutInflater.from(context);
        mDataOrigianl = new ArrayList<>();
        mDataOrigianl.addAll((ArrayList<ListaDePrecio>)mData.clone());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lista_de_lista_precio_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListaDePrecio listaDePrecio = mData.get(position);
        holder.nombre.setText(listaDePrecio.getNombreLista());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    public ListaDePrecio getItem(int position){
        return mData.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nombre;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.lista_lista_nombre_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) mClickListener.onItemClick(v, getAdapterPosition());
        }
    }


    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    private class ValueFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint != null && constraint.length() > 0){
                ArrayList<ListaDePrecio> filterList = new ArrayList<>();
                for (int i = 0; i < mDataOrigianl.size(); i++) {
                    if((mDataOrigianl.get(i).getNombreLista().toUpperCase())
                            .contains(constraint.toString().toUpperCase())){
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
            mData.addAll((ArrayList<ListaDePrecio>) results.values);
            notifyDataSetChanged();
        }
    }
}
