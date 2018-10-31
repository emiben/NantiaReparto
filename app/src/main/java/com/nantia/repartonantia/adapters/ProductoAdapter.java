package com.nantia.repartonantia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nantia.repartonantia.producto.Producto;
import com.nantia.repartonantia.producto.ProductoListaView;
import com.nantia.repartonantia.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emi on 20/5/2018.
 */

public class ProductoAdapter extends ArrayAdapter<Producto> implements Filterable {

    private ProductoListaView productoListaView;
    private Context context;
    private List<Producto> productos;
    private LayoutInflater inflater;
    List<Producto> productosOriginal;
    ValueFilter valueFilter;

    private static class ViewHolder {
        LinearLayout layout;
        TextView nombre;
        TextView presentacion;
        TextView retornable;
    }

    public ProductoAdapter(Context context, ArrayList<Producto> productos, ProductoListaView productoListaView) {
        super(context, R.layout.product_row, productos);
        this.productoListaView = productoListaView;
        this.context = context;
        this.productos = productos;
        productosOriginal = new ArrayList<>();
        productosOriginal.addAll((ArrayList<Producto>)productos.clone());
    }


    private void goToProducto(int position) {
        Object object= getItem(position);
        Producto producto =(Producto) object;

        productoListaView.navigteToProductoFragment(producto);
        productoListaView.clearSearchView();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Producto producto = getItem(position);
        ViewHolder viewHolder;
        final View result;

        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.product_row, parent, false);
            viewHolder.layout = convertView.findViewById(R.id.row_layout);
            viewHolder.nombre = convertView.findViewById(R.id.tv_nombre);
            viewHolder.presentacion = convertView.findViewById(R.id.tv_presentacion);
            viewHolder.retornable = convertView.findViewById(R.id.tv_retornable);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.nombre.setText(producto.getNombre());
        viewHolder.presentacion.setText(producto.getPresentacion());
        if(producto.isRetornable()){
            viewHolder.retornable.setText(context.getString(R.string.producto_retornable));
        }else {
            viewHolder.retornable.setText(context.getString(R.string.producto_no_retornable));
        }

        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToProducto(position);
            }
        });

        return result;
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
                ArrayList<Producto> filterList = new ArrayList<>();
                for (int i = 0; i < productosOriginal.size(); i++) {
                    if((productosOriginal.get(i).getNombre().toUpperCase())
                            .contains(constraint.toString().toUpperCase()) ||
                        (productosOriginal.get(i).getPresentacion().toUpperCase())
                                .contains(constraint.toString().toUpperCase())){
                        filterList.add(productosOriginal.get(i));
                    }
                }
                filterResults.count = filterList.size();
                filterResults.values = filterList;
            } else {
                filterResults.count = productosOriginal.size();
                filterResults.values = productosOriginal;
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            productos.clear();
            productos.addAll((ArrayList<Producto>) filterResults.values);
            notifyDataSetChanged();
        }
    }
}
