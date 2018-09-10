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
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import android.widget.Toast;
import com.nantia.repartonantia.R;
import com.nantia.repartonantia.adapters.ProductoVentaAdapter;

import static com.nantia.repartonantia.utils.Constantes.KEY_VENTA;

/**
 * A simple {@link Fragment} subclass.
 */
public class VentaFragment  extends Fragment implements VentaView, View.OnClickListener {
    private ProgressBar progressBar;
    private RecyclerView articulosRV;
    private TextView ivaTV;
    private TextView totalTV;
    private EditText descuentoET;
    private EditText entregaET;
    private TextView saldoTV;
    private RadioButton vendedor1RB;
    private RadioButton vendedor2RB;
    private VentaPresenter ventaPresenter;
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
            Venta venta = (Venta)getArguments().getSerializable(KEY_VENTA);
            ventaPresenter = new VentaPresenter(this, venta);
            setListeners(view);
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
        progressBar = view.findViewById(R.id.venta_pb);
    }


    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void loadData(Venta venta) {
        productoVentaAdapter = new ProductoVentaAdapter(getActivity(), venta.getProductosVenta());
        articulosRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        articulosRV.setAdapter(productoVentaAdapter);
        ivaTV.setText(String.valueOf(venta.getIvaTotal()));
        totalTV.setText(String.valueOf(venta.getTotalVenta()));
        descuentoET.setText(String.valueOf(venta.getDescuento()));
        entregaET.setText(String.valueOf(venta.getPagoTotal()));
        saldoTV.setText(String.valueOf(venta.calcularSaldo()));
    }

    @Override public boolean isVendedor1Checked() {
        return vendedor1RB.isChecked();
    }

    private void setListeners(View view){
        descuentoET.setOnClickListener(this);
        entregaET.setOnClickListener(this);
        view.findViewById(R.id.venta_cancelar_btn).setOnClickListener(this);
        view.findViewById(R.id.venta_finalizar_btn).setOnClickListener(this);
        ((RadioGroup) view.findViewById(R.id.venta_rg)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.venta_vendedor_1_rb:
                        vendedor2RB.setChecked(false);
                        break;
                    case R.id.venta_vendedor_2_rb:
                        vendedor1RB.setChecked(false);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.venta_descuento_et:
                ventaPresenter.setData(descuentoET.getText().toString(), entregaET.getText().toString());
                break;
            case R.id.venta_entrega_et:
                ventaPresenter.setData(descuentoET.getText().toString(), entregaET.getText().toString());
                break;
            case R.id.venta_finalizar_btn:
                finalizarVenta();
                break;
            case R.id.venta_cancelar_btn:
                getActivity().finish();
                break;
            default:
                break;
        }
    }

    private void showError(String error){
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();

    }

    private void finalizarVenta(){
        if(vendedor1RB.isChecked() || vendedor2RB.isChecked()){
            ventaPresenter.finalizarVenta();
        }else {
            showError(getString(R.string.venta_error_vendedor_no_selec));
        }
    }
}
