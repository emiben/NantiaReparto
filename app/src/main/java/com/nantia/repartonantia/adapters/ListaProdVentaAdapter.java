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
import com.nantia.repartonantia.listadeprecios.ProductoLista;

import java.util.ArrayList;
import java.util.List;

public class ListaProdVentaAdapter extends RecyclerView.Adapter<ListaProdVentaAdapter.ViewHolder> implements Filterable {

    private List<ProductoLista> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private List<ProductoLista> mDataOrigianl;
    ValueFilter valueFilter;

    public ListaProdVentaAdapter(Context context, List<ProductoLista> mData) {
        this.mData = mData;
        this.mInflater = LayoutInflater.from(context);
        mDataOrigianl = new ArrayList<>();
        mDataOrigianl.addAll(new ArrayList<>(mData));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lista_prod_venta_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProductoLista productoLista = mData.get(position);
        if(productoLista.getProducto().getNombre() != null)
            holder.nombre.setText(productoLista.getProducto().getNombre());
        if(productoLista.getProducto().getPresentacion() != null)
            holder.presentacion.setText(productoLista.getProducto().getPresentacion());
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

    public ProductoLista getItem(int position){
        return mData.get(position);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nombre;
        TextView presentacion;

        ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.prod_nombre);
            presentacion = itemView.findViewById(R.id.prod_desc);
            itemView.setOnClickListener(this);
        }

        @Override public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    private class ValueFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint != null && constraint.length() > 0){
                ArrayList<ProductoLista> filterList = new ArrayList<>();
                for (int i = 0; i < mDataOrigianl.size(); i++) {
                    if((mDataOrigianl.get(i).getProducto().getNombre().toUpperCase())
                            .contains(constraint.toString().toUpperCase()) ||
                            (mDataOrigianl.get(i).getProducto().getPresentacion().toUpperCase())
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
            mData.addAll((List<ProductoLista>) results.values);
            notifyDataSetChanged();
        }
    }
}
