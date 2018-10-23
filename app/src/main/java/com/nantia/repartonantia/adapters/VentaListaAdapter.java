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
import com.nantia.repartonantia.venta.Venta;

import java.util.ArrayList;
import java.util.List;

public class VentaListaAdapter extends RecyclerView.Adapter<VentaListaAdapter.ViewHolder> implements Filterable {

    private List<Venta> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private List<Venta> mDataOrigianl;
    ValueFilter valueFilter;

    public VentaListaAdapter(Context context, List<Venta> mData) {
        this.mData = mData;
        this.mInflater = LayoutInflater.from(context);
        mDataOrigianl = new ArrayList<Venta>();
        mDataOrigianl.addAll(new ArrayList<>(mData));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.venta_lista_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Venta venta = mData.get(position);
        if(venta.getCliente() != null)
            holder.clienteNombre.setText(venta.getCliente().getNombreCompleto());
        if(venta.getFecha() != null)
            holder.fecha.setText(venta.getFecha());
        holder.total.setText("$ " + venta.getTotalVenta());
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

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        TextView clienteNombre;
        TextView fecha;
        TextView total;

        ViewHolder(View itemView) {
            super(itemView);
            clienteNombre = itemView.findViewById(R.id.venta_lista_cliente_tv);
            fecha = itemView.findViewById(R.id.venta_lista_fecha_tv);
            total = itemView.findViewById(R.id.venta_lista_total_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }

    }

    public Venta getItem(int position){
        return mData.get(position);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    private class ValueFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                ArrayList<Venta> filterList = new ArrayList<>();
                for (int i = 0; i < mDataOrigianl.size(); i++) {
                    if ((mDataOrigianl.get(i).getCliente().getNombreCompleto().toUpperCase())
                            .contains(constraint.toString().toUpperCase()) ||
                            (mDataOrigianl.get(i).getFecha().toUpperCase())
                                    .contains(constraint.toString().toUpperCase())) {
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
            mData.addAll((List<Venta>) results.values);
            notifyDataSetChanged();
        }
    }
}
