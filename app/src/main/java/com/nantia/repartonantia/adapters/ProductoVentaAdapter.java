package com.nantia.repartonantia.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nantia.repartonantia.R;
import com.nantia.repartonantia.venta.ProductoVenta;

import java.util.ArrayList;
import java.util.List;

public class ProductoVentaAdapter extends RecyclerView.Adapter<ProductoVentaAdapter.ViewHolder> {

    private List<ProductoVenta> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;


    public ProductoVentaAdapter(Context context, List<ProductoVenta> mData) {
        this.mData = mData;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.venta_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProductoVenta productoVenta = mData.get(position);
        if(productoVenta.getProducto().getNombre() != null){
            holder.producto.setText(productoVenta.getProducto().getNombre());
        }
        holder.precioUnitario.setText(String.valueOf(productoVenta.getPrecioUnitario()));
        holder.cantidad.setText(String.valueOf(productoVenta.getCantidad()));
        holder.total.setText(String.valueOf(productoVenta.getProductoTotal()));

    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView producto;
        TextView precioUnitario;
        TextView cantidad;
        TextView total;

        ViewHolder(View itemView) {
            super(itemView);
            producto = itemView.findViewById(R.id.venta_articulo_tv);
            precioUnitario = itemView.findViewById(R.id.venta_pu_tv);
            cantidad = itemView.findViewById(R.id.venta_cantidad_tv);
            total = itemView.findViewById(R.id.venta_total_tv);
        }

        @Override public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
