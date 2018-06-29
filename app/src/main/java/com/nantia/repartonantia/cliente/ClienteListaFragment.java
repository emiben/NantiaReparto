package com.nantia.repartonantia.cliente;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.nantia.repartonantia.R;
import com.nantia.repartonantia.adapters.ClienteListaAdapter;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 */
public class ClienteListaFragment extends Fragment implements ClienteListaAdapter.ItemClickListener,
        ClienteListaView {
    private ProgressBar progressBar;
    private RecyclerView clientesRV;
    private SearchView buscarSV;
    private ArrayList<Cliente> clientes;
    private ClienteListaAdapter clienteListaAdapter;
    private ClienteListaPresenter clienteListaPresenter;
    private FloatingActionButton clientesFAB;


    public ClienteListaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cliente_lista, container, false);
        clienteListaPresenter = new ClienteListaPresenter(this);
        initializeViewObjects(view);
        clienteListaPresenter.getClientes();

        return view;
    }

    private void initializeViewObjects(View view) {
        progressBar = view.findViewById(R.id.cliente_lista_progress);
        clientesRV = view.findViewById(R.id.cliente_lista_rv);
        buscarSV = view.findViewById(R.id.cliente_buscar_sv);
        clientesFAB = view.findViewById(R.id.cliente_fab);
    }

    private void navigateToClienteFragment(Cliente cliente) {
        Bundle b = new Bundle();
        b.putSerializable("cliente", cliente);
        ClienteFragment clienteFragmentf = new ClienteFragment();
        clienteFragmentf.setArguments(b);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.cliente_lista_layout, clienteFragmentf)
                .addToBackStack(null)
                .commit();
    }

    private void navigateToClienteNuevoFragment() {
        ClienteNuevoFragment clienteNuevoFragment = new ClienteNuevoFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.cliente_lista_layout, clienteNuevoFragment, "clienteNuevoFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onItemClick(View view, int position) {
        navigateToClienteFragment(clienteListaAdapter.getItem(position));
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }


    @Override
    public void setClienteInfo(ArrayList<Cliente> clientes) {
        if (clientes != null){
            this.clientes = clientes;
            clientesRV.setLayoutManager(new LinearLayoutManager(getActivity()));
            clienteListaAdapter = new ClienteListaAdapter(getActivity(), clientes);
            clienteListaAdapter.setClickListener(this);
            clientesRV.setAdapter(clienteListaAdapter);
        }
    }

    @Override
    public void addListeners() {
        if (clienteListaAdapter != null){
            buscarSV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    clienteListaAdapter.getFilter().filter(s);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    clienteListaAdapter.getFilter().filter(s);
                    return false;
                }
            });
        }

        clientesFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToClienteNuevoFragment();
            }
        });
    }

    @Override
    public void clearSearchView() {
        this.buscarSV.setQuery("", false);
        this.buscarSV.clearFocus();
    }

    @Override
    public void showError(String error) {

    }
}
