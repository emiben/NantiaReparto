package com.nantia.repartonantia.venta;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.nantia.repartonantia.R;
import com.nantia.repartonantia.adapters.ListaProdVentaAdapter;
import com.nantia.repartonantia.cliente.Cliente;
import com.nantia.repartonantia.data.DataHolder;
import com.nantia.repartonantia.listadeprecios.ListaDePrecio;

import static com.nantia.repartonantia.utils.Constantes.KEY_CLIENTE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaProductoVentaFragment extends Fragment {

    private TextView cantProdCarroTV;
    private ProgressBar progressBar;
    private SearchView buscar;
    private RecyclerView prodsRV;

    private Cliente cliente;
    private Venta venta;
    private ListaDePrecio listaDePrecio;


    public ListaProductoVentaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_producto_venta, container, false);
        initializeViewObjects(view);

        venta = new Venta();

        if(getArguments().getSerializable(KEY_CLIENTE) != null){
            this.cliente = (Cliente) getArguments().getSerializable(KEY_CLIENTE);
            loadData();
        }

        return view;
    }


    private void initializeViewObjects(View view){
        cantProdCarroTV = view.findViewById(R.id.cant_prod_carro);
        progressBar = view.findViewById(R.id.lista_prod_venta_progress);
        buscar = view.findViewById(R.id.lista_prod_venta_buscar_sv);
        prodsRV = view.findViewById(R.id.lista_prod_venta_rv);
    }


    private void loadData(){
        listaDePrecio = DataHolder.getListaDePrecioById(cliente.getIdLista());
        if(listaDePrecio != null){
            ListaProdVentaAdapter listaProdVentaAdapter = new ListaProdVentaAdapter(getActivity(), listaDePrecio.getProductosLista());
            prodsRV.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            prodsRV.setAdapter(listaProdVentaAdapter);
        }
    }




}
