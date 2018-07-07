package com.nantia.repartonantia.listadeprecios;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.nantia.repartonantia.R;
import com.nantia.repartonantia.adapters.ListaDePreciosAdapter;

import java.util.ArrayList;

import static com.nantia.repartonantia.utils.Constantes.KEY_LISTA_DE_PRECIOS;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaDeListasDePrecioFragment extends Fragment
        implements ListaDeListaDePreciosView, ListaDePreciosAdapter.ItemClickListener {
    private ListaDeListasDePrecioPresenter presenter;
    private ProgressBar progressBar;
    private RecyclerView listasRV;
    private SearchView buscarSV;
    private ArrayList<ListaDePrecio> listasDePrecios;
    private ListaDePreciosAdapter listaDePreciosAdapter;


    public ListaDeListasDePrecioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_de_listas_de_precio, container, false);
        presenter = new ListaDeListasDePrecioPresenter(this);
        initializeViewObjects(view);
        presenter.getListasDePrecios();
        return view;
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void setListasDePrecioInfo(ArrayList<ListaDePrecio> listasDePrecios) {
        if(listasDePrecios != null){
            this.listasDePrecios = listasDePrecios;
            listasRV.setLayoutManager(new LinearLayoutManager(getActivity()));
            listaDePreciosAdapter = new ListaDePreciosAdapter(getActivity(), listasDePrecios);
            listaDePreciosAdapter.setClickListener(this);
            listasRV.setAdapter(listaDePreciosAdapter);
        }
    }

    @Override
    public void addListeners() {
        if(listaDePreciosAdapter != null){
            buscarSV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    listaDePreciosAdapter.getFilter().filter(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    listaDePreciosAdapter.getFilter().filter(newText);
                    return false;
                }
            });
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(View view, int position) {
        navigateToListaDePrecio(listaDePreciosAdapter.getItem(position));
    }

    private void initializeViewObjects(View view){
        progressBar = view.findViewById(R.id.lista_lista_precio_progress);
        listasRV = view.findViewById(R.id.lista_lista_precio_rv);
        buscarSV = view.findViewById(R.id.lista_lista_precio_buscar_sv);
    }

    void navigateToListaDePrecio(ListaDePrecio listaDePrecio){
        Bundle b = new Bundle();
        b.putSerializable(KEY_LISTA_DE_PRECIOS, listaDePrecio);
        ListaDePrecioFragment listaDePrecioFragment = new ListaDePrecioFragment();
        listaDePrecioFragment.setArguments(b);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.lista_precios_layout, listaDePrecioFragment)
                .addToBackStack(null)
                .commit();
    }
}
