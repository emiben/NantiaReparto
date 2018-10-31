package com.nantia.repartonantia.listadeprecios;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.nantia.repartonantia.R;
import com.nantia.repartonantia.adapters.ProductoListaAdapter;
import com.nantia.repartonantia.producto.Producto;

import java.util.ArrayList;
import java.util.Calendar;

import static com.nantia.repartonantia.utils.Constantes.KEY_LISTA_DE_PRECIOS;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaDePrecioFragment extends Fragment {

    private TextView nombreDeLista;
    private ProgressBar progressBar;
    private SearchView buscar;
    private RecyclerView listaDepreciosRV;
    private ListaDePrecio listaDePrecios;
    private ProductoListaAdapter productoListaAdapter;

    public ListaDePrecioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_de_precio, container, false);

        initializeViewObjects(view);

        if(getArguments().getSerializable(KEY_LISTA_DE_PRECIOS) != null){
            this.listaDePrecios = (ListaDePrecio) getArguments().getSerializable(KEY_LISTA_DE_PRECIOS);
            loadData();
            addListeners();
        }

        return view;
    }


    private void initializeViewObjects(View view){
        nombreDeLista = view.findViewById(R.id.nombre_lista_tv);

        progressBar = view.findViewById(R.id.lista_precio_progress);
        buscar = view.findViewById(R.id.lista_precio_buscar_sv);
        listaDepreciosRV = view.findViewById(R.id.lista_precio_rv);
    }

    private void loadData(){
        nombreDeLista.setText(listaDePrecios.getNombreLista());
        productoListaAdapter =
                new ProductoListaAdapter(getActivity(), listaDePrecios.getProductosLista());
        listaDepreciosRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        listaDepreciosRV.setAdapter(productoListaAdapter);
    }

//    //TODO: eliminar esto
//    private void cargarLista(){
//        ArrayList<ProductoLista> productosLista = new ArrayList<>();
//        for (int i=0; i < 100; i++){
//
//            Producto producto = new Producto(i, "Producto " + i, i+"L", "Descripcion " + i, true);
//            ProductoLista productoLista = new ProductoLista(producto, i*10, Calendar.getInstance().getTime());
//            productosLista.add(productoLista);
//        }
//        listaDePrecios = new ListaDePrecio(1, "Lista de Test", Calendar.getInstance().getTime().toString(), productosLista);
//    }

    private void addListeners(){
        buscar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                productoListaAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}
