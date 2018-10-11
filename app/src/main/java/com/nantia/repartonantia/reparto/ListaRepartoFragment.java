package com.nantia.repartonantia.reparto;


import android.arch.persistence.room.Room;
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
import android.widget.Toast;

import com.nantia.repartonantia.R;
import com.nantia.repartonantia.adapters.RepartoInfoPOJO;
import com.nantia.repartonantia.adapters.RepartoListaAdapter;
import com.nantia.repartonantia.data.AppDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.nantia.repartonantia.utils.Constantes.KEY_DB_NOMBRE;
import static com.nantia.repartonantia.utils.Constantes.KEY_REPARTO;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaRepartoFragment extends Fragment implements ListaRepartoView, RepartoListaAdapter.ItemClickListener {
    private ProgressBar progressBar;
    private RecyclerView repartoRV;
    private SearchView buscarSV;
    private RepartoListaAdapter repartoListaAdapter;
    private List<RepartoInfoPOJO> repartos;
    private ListaRepartoPresenter presenter;


    public ListaRepartoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_reparto, container, false);
        AppDatabase db = Room.databaseBuilder(getActivity(),
                AppDatabase.class, KEY_DB_NOMBRE).build();
        presenter = new ListaRepartoPresenter(this, db);
        initializeViewObjects(view);

        presenter.getRepartosInfo();

        return view;
    }

    private void initializeViewObjects(View view) {
        progressBar = view.findViewById(R.id.reparto_lista_progress);
        repartoRV = view.findViewById(R.id.reparto_lista_lista_rv);
        buscarSV = view.findViewById(R.id.reparto_lista_buscar_sv);
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void setRepartosInfo(ArrayList<RepartoInfoPOJO> repartos) {
        if(repartos != null){
            this.repartos = repartos;
            repartoRV.setLayoutManager(new LinearLayoutManager(getActivity()));
            repartoListaAdapter = new RepartoListaAdapter(getContext(), repartos);
            repartoListaAdapter.setClickListener(this);
            repartoRV.setAdapter(repartoListaAdapter);
        }

    }

    @Override
    public void addListeners() {
        if(repartoListaAdapter != null){
            repartoListaAdapter.setClickListener(this);
            buscarSV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    repartoListaAdapter.getFilter().filter(s);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    repartoListaAdapter.getFilter().filter(s);
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
    public void navigateToReparto(Reparto reparto) {
        Bundle b = new Bundle();
        b.putSerializable(KEY_REPARTO, reparto);
        RepartoFragment repartoFragment = new RepartoFragment();
        repartoFragment.setArguments(b);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.reparto_layout, repartoFragment).commit();


    }

    @Override
    public void onItemClick(View view, int position) {
        presenter.getReparto(repartoListaAdapter.getItem(position).getId());
    }


}
