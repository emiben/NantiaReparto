package com.nantia.repartonantia.reparto;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nantia.repartonantia.R;

import static com.nantia.repartonantia.utils.Constantes.KEY_REPARTO;

/**
 * A simple {@link Fragment} subclass.
 */
public class RepartoFragment extends Fragment {
    private TextView repartoTv;
    private TextView vendedor1Tv;
    private TextView vendedor2Tv;
    private TextView rutaTv;
    private View rutaLo;
    private Reparto reparto;

    public RepartoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reparto, container, false);

        if(getArguments().getSerializable(KEY_REPARTO) != null){
            reparto = (Reparto) getArguments().getSerializable(KEY_REPARTO);
        }

        initializeViewObjects(view);
        loadRepartoData(reparto);
        return view;
    }


    private void initializeViewObjects(View view){
        repartoTv = view.findViewById(R.id.reparto_desc_tv);
        vendedor1Tv = view.findViewById(R.id.reparto_vend_1_tv);
        vendedor2Tv = view.findViewById(R.id.reparto_vend_2_tv);
        rutaTv = view.findViewById(R.id.reparto_ruta_tv);
        rutaLo = view.findViewById(R.id.reparto_ruta_lo);
    }

    private void loadRepartoData(Reparto reparto){
        if(reparto != null){
            if(reparto.getDescripcion() != null && !reparto.getDescripcion().isEmpty())
                repartoTv.setText(reparto.getDescripcion());
            if(reparto.getVendedor1().getNombreCompleto() != null &&
                    !reparto.getVendedor1().getNombreCompleto().isEmpty())
                vendedor1Tv.setText(reparto.getVendedor1().getNombreCompleto());
            if(reparto.getVendedor2().getNombreCompleto() != null &&
                    !reparto.getVendedor1().getNombreCompleto().isEmpty())
                vendedor1Tv.setText(reparto.getVendedor2().getNombreCompleto());
            if(reparto.getRuta().getNombre() != null && !reparto.getRuta().getNombre().isEmpty())
                rutaTv.setText(reparto.getRuta().getNombre());
        }
    }
}
