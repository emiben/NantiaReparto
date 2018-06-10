package com.nantia.repartonantia.cliente;

import android.content.DialogInterface;
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
import com.nantia.repartonantia.producto.Envase;

import java.util.ArrayList;


public class ClienteFragment extends Fragment implements ClienteView {
    private Cliente cliente;
    private TextView nombre;
    private RecyclerView clienteInfo;
    private FloatingActionButton editFAB;
    private ClienteAdapter clienteAdapter;
    private ClientePresenter clientePresenter;

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
            cliente = (Cliente) getArguments().getSerializable("cliente");
        }
        clientePresenter = new ClientePresenter(this, getActivity());
        initializeViewObjects(view);
        clientePresenter.cargarInfo();

        editFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToActualizarCliente();
            }
        });

        return view;
    }

    private void navigateToActualizarCliente() {
        Bundle b = new Bundle();
        b.putSerializable("cliente", cliente);
        ClienteNuevoFragment clienteNuevoFragment = new ClienteNuevoFragment();
        clienteNuevoFragment.setArguments(b);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.cliente_lista_layout, clienteNuevoFragment, "clienteNuevoFragment")
                .addToBackStack(null)
                .commit();
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

    private void initializeViewObjects(View view){
        nombre = view.findViewById(R.id.cliente_info_nombre_tv);
        clienteInfo = view.findViewById(R.id.cliente_info_rv);
        editFAB = view.findViewById(R.id.cliente_info_fab);

    }

}
