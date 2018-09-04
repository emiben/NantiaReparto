package com.nantia.repartonantia.venta;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.nantia.repartonantia.R;
import com.nantia.repartonantia.adapters.ProductoVentaAdapter;

import static com.nantia.repartonantia.utils.Constantes.KEY_VENTA;

/**
 * A simple {@link Fragment} subclass.
 */
public class VentaFragment extends Fragment {

    private RecyclerView articulosRV;
    private TextView ivaTV;
    private TextView totalTV;
    private EditText descuentoET;
    private EditText entregaET;
    private TextView saldoTV;
    private RadioButton vendedor1RB;
    private RadioButton vendedor2RB;
    private Button cancelarBtn;
    private Button finalizarBtn;

    private Venta venta;
    private ProductoVentaAdapter productoVentaAdapter;

    public VentaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_venta, container, false);

        initializeViewObjects(view);

        if(getArguments().getSerializable(KEY_VENTA) != null){
            this.venta = (Venta)getArguments().getSerializable(KEY_VENTA);
            loadData();
        }

        return view;
    }


    private void initializeViewObjects(View view){
        articulosRV = view.findViewById(R.id.venta_articulos_rv);
        ivaTV = view.findViewById(R.id.venta_iva_tv);
        totalTV = view.findViewById(R.id.venta_total_tv);
        descuentoET = view.findViewById(R.id.venta_descuento_et);
        entregaET = view.findViewById(R.id.venta_entrega_et);
        saldoTV = view.findViewById(R.id.venta_saldo_tv);
        vendedor1RB = view.findViewById(R.id.venta_vendedor_1_rb);
        vendedor2RB = view.findViewById(R.id.venta_vendedor_2_rb);
        cancelarBtn = view.findViewById(R.id.venta_cancelar_btn);
        finalizarBtn = view.findViewById(R.id.venta_finalizar_btn);
    }


    private void loadData(){
        productoVentaAdapter = new ProductoVentaAdapter(getActivity(), venta.getProductosVenta());
        articulosRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        articulosRV.setAdapter(productoVentaAdapter);
        ivaTV.setText(String.valueOf(venta.getIvaTotal()));
        totalTV.setText(String.valueOf(venta.getTotalVenta()));
        descuentoET.setText(String.valueOf(venta.getDescuento()));
        entregaET.setText(String.valueOf(venta.getPagoTotal()));
        saldoTV.setText(String.valueOf(venta.calcularSaldo()));
    }

}
