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
import java.util.List;

/**
 * Created by Emi on 29/7/2018.
 */

public class RepartoListaAdapter extends RecyclerView.Adapter<RepartoListaAdapter.ViewHolder> implements Filterable{
    private List<RepartoInfoPOJO> mData;
    private LayoutInflater mInflater;
    private List<RepartoInfoPOJO> mDataOrigianl;
    private ItemClickListener mClickListener;
    ValueFilter valueFilter;

    public RepartoListaAdapter(Context context, ArrayList<RepartoInfoPOJO> mData) {
        this.mData = mData;
        this.mInflater = LayoutInflater.from(context);
        mDataOrigianl = new ArrayList<>();
        mDataOrigianl.addAll((ArrayList<RepartoInfoPOJO>)mData.clone());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lista_reparto_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RepartoInfoPOJO repartoInfoPOJO = mData.get(position);
        holder.descripcion.setText(repartoInfoPOJO.getDescripcion());
        holder.vehiculo.setText(repartoInfoPOJO.getVehiculo());
        holder.dia.setText(repartoInfoPOJO.getDia());
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

    public RepartoInfoPOJO getItem(int position) {
        return mData.get(position);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView descripcion;
        TextView vehiculo;
        TextView dia;

        ViewHolder(View itemView) {
            super(itemView);
            descripcion = itemView.findViewById(R.id.reparto_lista_desc_tv);
            vehiculo = itemView.findViewById(R.id.reparto_lista_vehiculo_tv);
            dia = itemView.findViewById(R.id.reparto_lista_dia_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    private class ValueFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint != null && constraint.length() > 0){
                ArrayList<RepartoInfoPOJO> filterList = new ArrayList<>();
                for (int i = 0; i < mDataOrigianl.size(); i++) {
                    if((mDataOrigianl.get(i).getDescripcion().toUpperCase()).contains(constraint.toString().toUpperCase()) ||
                            (mDataOrigianl.get(i).getVehiculo().toUpperCase()).contains(constraint.toString().toUpperCase()) ||
                            (mDataOrigianl.get(i).getDia().toUpperCase()).contains(constraint.toString().toUpperCase())){
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
            mData.addAll((ArrayList<RepartoInfoPOJO>) results.values);
            notifyDataSetChanged();
        }
    }
}
