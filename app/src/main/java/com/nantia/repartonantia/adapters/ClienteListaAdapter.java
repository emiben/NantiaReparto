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
import com.nantia.repartonantia.cliente.Cliente;

import java.util.ArrayList;

/**
 * Created by Emi on 28/5/2018.
 */

public class ClienteListaAdapter extends RecyclerView.Adapter<ClienteListaAdapter.ViewHolder> implements Filterable{

    private ArrayList<Cliente> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private ArrayList<Cliente> mDataOrigianl;
    ValueFilter valueFilter;

    public ClienteListaAdapter(Context context, ArrayList<Cliente> mData) {
        this.mData = mData;
        this.mInflater = LayoutInflater.from(context);
        mDataOrigianl = new ArrayList<>();
        mDataOrigianl.addAll((ArrayList<Cliente>)mData.clone());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cliente_lista_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cliente cliente = mData.get(position);
        holder.nombreTV.setText(cliente.getNombre1() + " " + cliente.getNombre2());
        holder.direccionTV.setText("Dir.: " + cliente.getDireccion().getDireccion());
        holder.telefonoTV.setText("Tel.: " + cliente.getCelular());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nombreTV;
        TextView direccionTV;
        TextView telefonoTV;

        ViewHolder(View itemView) {
            super(itemView);
            nombreTV = itemView.findViewById(R.id.cliente_nombre_tv);
            direccionTV = itemView.findViewById(R.id.cliente_direccion);
            telefonoTV = itemView.findViewById(R.id.cliente_telefono);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public Cliente getItem(int position) {
        return mData.get(position);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
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
                ArrayList<Cliente> filterList = new ArrayList<>();
                for (int i = 0; i < mDataOrigianl.size(); i++) {
                    if((mDataOrigianl.get(i).getNombre1().toUpperCase())
                            .contains(constraint.toString().toUpperCase()) ||
                            (mDataOrigianl.get(i).getNombre2().toUpperCase())
                                    .contains(constraint.toString().toUpperCase()) ||
                            (mDataOrigianl.get(i).getDireccion().getDireccion().toUpperCase())
                                    .contains(constraint.toString().toUpperCase()) ||
                            String.valueOf(mDataOrigianl.get(i).getDireccion().getTelefono()).toUpperCase()
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
            mData.addAll((ArrayList<Cliente>) results.values);
            notifyDataSetChanged();
        }
    }
}
