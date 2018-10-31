package com.nantia.repartonantia.venta;


import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
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
import com.nantia.repartonantia.cliente.ClienteActivity;
import com.nantia.repartonantia.data.AppDatabase;
import com.nantia.repartonantia.data.DataHolder;
import com.nantia.repartonantia.listadeprecios.ProductoLista;

import static com.nantia.repartonantia.utils.Constantes.KEY_DB_NOMBRE;
import static com.nantia.repartonantia.utils.Constantes.KEY_VENTA;
import static com.nantia.repartonantia.utils.Constantes.KEY_VENTA_VISTA;

/**
 * A simple {@link Fragment} subclass.
 */
public class VentaFragment  extends Fragment implements VentaView, View.OnClickListener {
    private ProgressBar progressBar;
    private RecyclerView articulosRV;
    private TextView ivaTV;
    private TextView totalTV;
    private TextView descuentoTV;
    private TextView entregaTV;
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

        vendedor1RB.setText(DataHolder.getReparto().getVendedor1().getNombreCompleto());
        vendedor2RB.setText(DataHolder.getReparto().getVendedor2().getNombreCompleto());

        if(getArguments().getSerializable(KEY_VENTA) != null){
            Venta venta = (Venta)getArguments().getSerializable(KEY_VENTA);
            AppDatabase db = Room.databaseBuilder(getContext(),
                    AppDatabase.class, KEY_DB_NOMBRE).build();
            ventaPresenter = new VentaPresenter(this, venta, db);
            loadData(venta);
            setListeners(view);
        }

        if(getArguments().getBoolean(KEY_VENTA_VISTA)){
            ocultarBotones(view);
        }

        return view;
    }


    private void initializeViewObjects(View view){
        articulosRV = view.findViewById(R.id.venta_articulos_rv);
        ivaTV = view.findViewById(R.id.venta_iva_tv);
        totalTV = view.findViewById(R.id.venta_total_tv);
        descuentoTV = view.findViewById(R.id.venta_descuento_tv);
        entregaTV = view.findViewById(R.id.venta_entrega_tv);
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
    public void updateData(Venta venta) {
        ivaTV.setText(String.valueOf(venta.getIvaTotal()));
        totalTV.setText(String.valueOf(venta.getTotalVenta()));
        descuentoTV.setText(String.valueOf(venta.getDescuento()));
        entregaTV.setText(String.valueOf(venta.getPagoTotal()));
        saldoTV.setText(String.valueOf(venta.calcularSaldo()));
    }

    @Override public boolean isVendedor1Checked() {
        return vendedor1RB.isChecked();
    }

    @Override
    public void finishActivities() {
        getActivity().finish();
    }

    private void loadData(Venta venta){
        productoVentaAdapter = new ProductoVentaAdapter(getActivity(), venta.getProductosVenta());
        articulosRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        articulosRV.setAdapter(productoVentaAdapter);
        ventaPresenter.setData("0", "0");
    }

    private void setListeners(View view){
        descuentoTV.setOnClickListener(this);
        entregaTV.setOnClickListener(this);
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

    private void ocultarBotones(View view){
        descuentoTV.setClickable(false);
        entregaTV.setClickable(false);
        vendedor1RB.setClickable(false);
        vendedor2RB.setClickable(false);
        view.findViewById(R.id.venta_cancelar_btn).setVisibility(View.GONE);
        view.findViewById(R.id.venta_finalizar_btn).setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.venta_descuento_tv:
                showDialog(false);
                break;
            case R.id.venta_entrega_tv:
                showDialog(true);
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


    private void showDialog(final Boolean entrega){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if(entrega){
            builder.setTitle("Entrega");
            builder.setMessage("Ingrese el monto de entrega");
        }else {
            builder.setTitle("Descuento");
            builder.setMessage("Ingrese el descuento");
        }

        final EditText dinero = new EditText(getActivity());
        dinero.setInputType(InputType.TYPE_CLASS_NUMBER);

        builder.setView(dinero);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(entrega){
                    entregaTV.setText(dinero.getText());
                }else {
                    descuentoTV.setText(dinero.getText());
                }
                ventaPresenter.setData(descuentoTV.getText().toString(), entregaTV.getText().toString());
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }
}
