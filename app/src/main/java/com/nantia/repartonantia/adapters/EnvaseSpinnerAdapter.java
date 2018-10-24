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
import com.nantia.repartonantia.producto.Envase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emi on 4/6/2018.
 */

public class EnvaseSpinnerAdapter extends ArrayAdapter<Envase> {

//    private final LayoutInflater mInflater;
    private final Context mContext;
    private final List<Envase> items;

    public EnvaseSpinnerAdapter(@NonNull Context context, @NonNull List<Envase> objects) {
        super(context, 0, objects);
        this.mContext = context;
        this.items = objects;
//        mInflater = LayoutInflater.from(context);
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
        Envase envase = items.get(position);
        TextView envDesc = listItem.findViewById(R.id.spinner_tv);
        envDesc.setText(envase.getDescripcion());

        if(envase.getId() == 0){
            envDesc.setTextColor(Color.GRAY);
        }else{
            envDesc.setTextColor(Color.BLACK);
        }
        return listItem;
    }
}
