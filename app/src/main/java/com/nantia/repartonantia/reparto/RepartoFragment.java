package com.nantia.repartonantia.reparto;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.util.Base64Utils;
import com.nantia.repartonantia.R;
import com.nantia.repartonantia.cliente.ClienteActivity;
import com.nantia.repartonantia.data.DataHolder;
import com.nantia.repartonantia.map.MapActivity;

import static com.nantia.repartonantia.utils.Constantes.KEY_CLIENTE_LISTA;
import static com.nantia.repartonantia.utils.Constantes.KEY_REPARTO;

/**
 * A simple {@link Fragment} subclass.
 */
public class RepartoFragment extends Fragment implements View.OnClickListener {
    private TextView repartoTv;
    private TextView vendedor1Tv;
    private TextView vendedor2Tv;
    private TextView rutaTv;
    private TextView estadoTv;
    private View rutaLo;
    private View clientesLo;
    private Reparto reparto;
    private Button comenzarRepartoBtn;
    private Button finalizarRepartoBtn;

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
        setListeners();
        return view;
    }

    private void initializeViewObjects(View view){
        repartoTv = view.findViewById(R.id.reparto_desc_tv);
        vendedor1Tv = view.findViewById(R.id.reparto_vend_1_tv);
        vendedor2Tv = view.findViewById(R.id.reparto_vend_2_tv);
        rutaTv = view.findViewById(R.id.reparto_ruta_tv);
        rutaLo = view.findViewById(R.id.reparto_ruta_lo);
        clientesLo = view.findViewById(R.id.reparto_clientes_lo);
        estadoTv = view.findViewById(R.id.reparto_estado_tv);
        comenzarRepartoBtn = view.findViewById(R.id.comenzar_reparto_btn);
        finalizarRepartoBtn = view.findViewById(R.id.finalizar_reparto_btn);
    }

    private void setListeners(){
        rutaLo.setOnClickListener(this);
        clientesLo.setOnClickListener(this);
        comenzarRepartoBtn.setOnClickListener(this);
        finalizarRepartoBtn.setOnClickListener(this);
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
                vendedor2Tv.setText(reparto.getVendedor2().getNombreCompleto());
            if(reparto.getRuta().getNombre() != null && !reparto.getRuta().getNombre().isEmpty())
                rutaTv.setText(reparto.getRuta().getNombre());
            if(reparto.getEstado() != null && !reparto.getEstado().isEmpty())
                estadoTv.setText(reparto.getEstado());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.comenzar_reparto_btn:
                comenzarReparto();
                break;
            case R.id.venta_finalizar_btn:
                finalizarReparto();
                break;
            case R.id.reparto_ruta_lo:
                navigateToRepartoMap();
                break;
            case R.id.reparto_clientes_lo:
                navigateToRepartoClientes();
                break;
            default:
                break;
        }
    }

    private void navigateToRepartoClientes() {
        Intent i = new Intent(getActivity(), ClienteActivity.class);
        Bundle b = new Bundle();
        b.putSerializable(KEY_CLIENTE_LISTA, reparto.getRuta().getClientes());
        i.putExtra(KEY_CLIENTE_LISTA, b);
        startActivity(i);
    }

    private void navigateToRepartoMap() {
        Intent i = new Intent(getActivity(), MapActivity.class);
        Bundle b = new Bundle();
        b.putSerializable(KEY_CLIENTE_LISTA, reparto.getRuta().getClientes());
        i.putExtra(KEY_CLIENTE_LISTA, b);
        startActivity(i);
    }

    private void comenzarReparto(){
        DataHolder.getReparto().setEstado(RepartoEstado.COMENZADO.name());
        reparto.setEstado(RepartoEstado.COMENZADO.name());
        comenzarRepartoBtn.setVisibility(View.GONE);
        finalizarRepartoBtn.setVisibility(View.VISIBLE);
        estadoTv.setText(reparto.getEstado());
    }

    private void finalizarReparto(){
        DataHolder.getReparto().setEstado(RepartoEstado.FINALIZADO.name());
        reparto.setEstado(RepartoEstado.FINALIZADO.name());
        estadoTv.setText(reparto.getEstado());
    }
}
