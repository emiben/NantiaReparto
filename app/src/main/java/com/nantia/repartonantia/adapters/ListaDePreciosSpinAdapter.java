package com.nantia.repartonantia.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nantia.repartonantia.R;
import com.nantia.repartonantia.listadeprecios.ListaDePrecio;

import java.util.ArrayList;

/**
 * Created by Emi on 16/7/2018.
 */

public class ListaDePreciosSpinAdapter extends ArrayAdapter<ListaDePrecio> {

    private final Context mContext;
    private final ArrayList<ListaDePrecio> items;

    public ListaDePreciosSpinAdapter(Context mContext, ArrayList<ListaDePrecio> items) {
        super(mContext, 0, items);
        this.mContext = mContext;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemVew(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemVew(position, convertView, parent);
    }

    private View createItemVew(int position, @Nullable View convertView,
                               @NonNull ViewGroup parent){
        View listItem = convertView;
        if(listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.spinner_row,parent,false);
        }
        ListaDePrecio listaDePrecio = items.get(position);
        TextView envDesc = listItem.findViewById(R.id.spinner_tv);
        envDesc.setText(listaDePrecio.getNombreLista());

        if(listaDePrecio.getId() == 0){
            envDesc.setTextColor(Color.GRAY);
        }else{
            envDesc.setTextColor(Color.BLACK);
        }
        return listItem;
    }
}
