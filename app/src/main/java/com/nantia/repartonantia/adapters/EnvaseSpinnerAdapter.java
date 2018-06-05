package com.nantia.repartonantia.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nantia.repartonantia.R;
import com.nantia.repartonantia.producto.Envase;

import java.util.ArrayList;

/**
 * Created by Emi on 4/6/2018.
 */

public class EnvaseSpinnerAdapter extends ArrayAdapter<Envase> {

//    private final LayoutInflater mInflater;
    private final Context mContext;
    private final ArrayList<Envase> items;

    public EnvaseSpinnerAdapter(@NonNull Context context, @NonNull ArrayList<Envase> objects) {
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
            listItem = LayoutInflater.from(mContext).inflate(R.layout.envase_spinner_row,parent,false);
        }
        Envase envase = items.get(position);
        TextView envDesc = listItem.findViewById(R.id.envase_tv);
        envDesc.setText(envase.getDescripcion());

        if(position == 0){
            envDesc.setTextColor(Color.GRAY);
        }else{
            envDesc.setTextColor(Color.BLACK);
        }
        return listItem;
    }


    //    public EnvaseSpinnerAdapter(Context mContext, ArrayList<Envase> items) {
//        this.mContext = mContext;
//        this.items = items;
//        this.mInflater = LayoutInflater.from(mContext);
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = mInflater.inflate(R.layout.envase_spinner_row, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        Envase envase = items.get(position);
//        holder.envDesc.setText(envase.getDescripcion());
//        if(position == 0){
//            holder.envDesc.setTextColor(Color.GRAY);
//        }else{
//            holder.envDesc.setTextColor(Color.BLACK);
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return items.size();
//    }

//    public class ViewHolder extends RecyclerView.ViewHolder {
//        TextView envDesc;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            envDesc = itemView.findViewById(R.id.primarioTV);
//        }
//    }

//    @Override
//    public View getDropDownView(int position, @Nullable View convertView,
//                                @NonNull ViewGroup parent) {
//        View view = createItemView(position, convertView, parent);
//
//        TextView envaseDesc = view.findViewById(R.id.envase_tv);
//
//        if(position == 0){
//            // Set the hint text color gray
//            envaseDesc.setTextColor(Color.GRAY);
//        }
//        else {
//            envaseDesc.setTextColor(Color.BLACK);
//        }
//
//        return view;
//    }



}
