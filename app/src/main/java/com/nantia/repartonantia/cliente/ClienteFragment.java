package com.nantia.repartonantia.cliente;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nantia.repartonantia.R;
import com.nantia.repartonantia.adapters.ClienteAdapter;
import com.nantia.repartonantia.adapters.ClienteInfoPOJO;
import com.nantia.repartonantia.data.DataHolder;
import com.nantia.repartonantia.producto.Envase;
import com.nantia.repartonantia.venta.Venta;
import com.nantia.repartonantia.venta.VentaActivity;

import java.util.ArrayList;
import java.util.Calendar;

import javax.crypto.Cipher;

import static com.nantia.repartonantia.utils.Constantes.KEY_CLIENTE;


public class ClienteFragment extends Fragment implements ClienteView {
    private Cliente cliente;
    private TextView nombre;
    private RecyclerView clienteInfo;
    private ClienteAdapter clienteAdapter;
    private ClientePresenter clientePresenter;
    DatePickerDialog.OnDateSetListener date;
    Calendar calendar = Calendar.getInstance();

    public ClienteFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cliente, container, false);
        if(getArguments() != null){
            cliente = (Cliente) getArguments().getSerializable(KEY_CLIENTE);
        }
        clientePresenter = new ClientePresenter(this, getActivity());
        initializeViewObjects(view);
        if(cliente != null) clientePresenter.cargarInfo();

        addListeners(view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(cliente != null){
            if(DataHolder.getClienteById(cliente.getId()) != null){
                cliente = DataHolder.getClienteById(cliente.getId());
            }
            clientePresenter.cargarInfo();
        }

    }

    @Override
    public Cliente getCliente() {
        return this.cliente;
    }

    @Override
    public void setClienteInfo(ArrayList<ClienteInfoPOJO> cliente, String nombre) {
        this.nombre.setText(nombre);
        clienteInfo.setLayoutManager(new LinearLayoutManager(getActivity()));
        clienteAdapter = new ClienteAdapter(getActivity(), cliente);
        clienteInfo.setAdapter(clienteAdapter);
    }

    private void addListeners(View view) {
        view.findViewById(R.id.cliente_info_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToActualizarCliente();
            }
        });
        view.findViewById(R.id.nueva_venta_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToVenta();
            }
        });
        view.findViewById(R.id.visitado_boton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marcarComoVisitado();
            }
        });
    }

    private void marcarComoVisitado(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Desea marcar el cliente como visitado?");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cliente.setVisitado(true);
                DataHolder.updateClienteEnReparto(cliente);
                DataHolder.removeClienteById(cliente.getId());
                DataHolder.getClientes().add(cliente);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void navigateToVenta() {
        Intent i = new Intent(getActivity(), VentaActivity.class);
        Bundle b = new Bundle();
        b.putSerializable(KEY_CLIENTE, cliente);
        i.putExtra(KEY_CLIENTE, b);
        startActivity(i);
    }

    private void navigateToActualizarCliente() {
        Bundle b = new Bundle();
        b.putSerializable(KEY_CLIENTE, cliente);
        ClienteNuevoFragment clienteNuevoFragment = new ClienteNuevoFragment();
        clienteNuevoFragment.setArguments(b);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.cliente_lista_layout, clienteNuevoFragment, "clienteNuevoFragment")
                .addToBackStack(null)
                .commit();
    }

    private void initializeViewObjects(View view){
        nombre = view.findViewById(R.id.cliente_info_nombre_tv);
        clienteInfo = view.findViewById(R.id.cliente_info_rv);
    }

}
