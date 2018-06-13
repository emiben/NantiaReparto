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
import com.nantia.repartonantia.listadeprecios.ProductoLista;

import java.util.ArrayList;

/**
 * Created by Emi on 11/6/2018.
 */

public class ProductoListaAdapter extends RecyclerView.Adapter<ProductoListaAdapter.ViewHolder> implements Filterable {

    private ArrayList<ProductoLista> mData;
    private LayoutInflater mInflater;
    private ClienteListaAdapter.ItemClickListener mClickListener;
    private ArrayList<ProductoLista> mDataOrigianl;
    ValueFilter valueFilter;



    public ProductoListaAdapter(Context context, ArrayList<ProductoLista> mData) {
        this.mData = mData;
        this.mInflater = LayoutInflater.from(context);
        mDataOrigianl = new ArrayList<>();
        mDataOrigianl.addAll((ArrayList<ProductoLista>)mData.clone());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lista_de_precio_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProductoLista productoLista = mData.get(position);
        holder.nombre.setText(productoLista.getProducto().getNombre());
        holder.presentacion.setText(productoLista.getProducto().getPresentacion());
        holder.actualizado.setText("Actualizdo:" + productoLista.getActualizado().toString());
        holder.precio.setText(String.valueOf(productoLista.getPrecio()));
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView presentacion;
        TextView actualizado;
        TextView precio;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.producto_lista_tv);
            presentacion = itemView.findViewById(R.id.presentacion_lista_tv);
            actualizado = itemView.findViewById(R.id.actualizado_lista_tv);
            precio = itemView.findViewById(R.id.precio_lista_tv);
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
                                    .contains(constraint.toString().toUpperCase()) ||
                            (mDataOrigianl.get(i).getProducto().getDescripcion().toUpperCase())
                                    .contains(constraint.toString().toUpperCase()) ||
                            String.valueOf(mDataOrigianl.get(i).getPrecio()).toUpperCase()
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
            mData.addAll((ArrayList<ProductoLista>) results.values);
            notifyDataSetChanged();
        }
    }
}
