package com.nantia.repartonantia.venta;


import android.os.Bundle;
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
import com.nantia.repartonantia.adapters.VentaListaAdapter;

import java.util.List;

import static com.nantia.repartonantia.utils.Constantes.KEY_VENTA;
import static com.nantia.repartonantia.utils.Constantes.KEY_VENTA_VISTA;

/**
 * A simple {@link Fragment} subclass.
 */
public class VentaListaFragment extends Fragment implements VentaListaView, VentaListaAdapter.ItemClickListener {

    private ProgressBar progressBar;
    private RecyclerView ventasRv;
    private SearchView buscarSv;
    private List<Venta> ventas;
    private VentaListaAdapter ventaListaAdapter;
    private VentaListaPresenter ventaListaPresenter;



    public VentaListaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_venta_lista, container, false);
        ventaListaPresenter = new VentaListaPresenter(this);
        initializeViewObjects(view);
        ventaListaPresenter.getVentas();
        return view;
    }

    private void initializeViewObjects(View view) {
        progressBar = view.findViewById(R.id.venta_lista_progress);
        ventasRv = view.findViewById(R.id.venta_lista_rv);
        ventasRv = view.findViewById(R.id.venta_lista_rv);
        buscarSv = view.findViewById(R.id.buscar_sv);
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void setVentasInfo(List<Venta> ventas) {
        if(ventas != null){
            this.ventas = ventas;
            ventasRv.setLayoutManager(new LinearLayoutManager(getActivity()));
            ventaListaAdapter = new VentaListaAdapter(getActivity(), ventas);
            ventaListaAdapter.setClickListener(this);
            ventasRv.setAdapter(ventaListaAdapter);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        navigateToVenta(ventaListaAdapter.getItem(position));
    }

    @Override
    public void addListeners() {
        if(ventaListaAdapter != null){
            buscarSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    ventaListaAdapter.getFilter().filter(s);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    ventaListaAdapter.getFilter().filter(s);
                    return false;
                }
            });
        }

    }

    private void navigateToVenta(Venta venta) {
        Bundle b = new Bundle();
        b.putSerializable(KEY_VENTA, venta);
        b.putBoolean(KEY_VENTA_VISTA, true);
        VentaFragment ventaFragment = new VentaFragment();
        ventaFragment.setArguments(b);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.venta_layout, ventaFragment)
                .addToBackStack(null)
                .commit();
    }
}
