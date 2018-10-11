package com.nantia.repartonantia.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nantia.repartonantia.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emi on 28/5/2018.
 */

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolder> {
    private List<ClienteInfoPOJO> mData;
    private LayoutInflater mInflater;

    public ClienteAdapter(Context context, ArrayList<ClienteInfoPOJO> mData) {
        this.mData = mData;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cliente_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ClienteInfoPOJO clienteInfoPOJO = mData.get(position);
        holder.icono.setImageDrawable(clienteInfoPOJO.getImage());
        holder.primario.setText(clienteInfoPOJO.getPrimario());
        holder.secundario.setText(clienteInfoPOJO.getSecundario());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icono;
        TextView primario;
        TextView secundario;

        ViewHolder(View itemView) {
            super(itemView);
            icono = itemView.findViewById(R.id.cliente_icono);
            primario = itemView.findViewById(R.id.primarioTV);
            secundario = itemView.findViewById(R.id.secundaroioTV);
        }
    }
}
