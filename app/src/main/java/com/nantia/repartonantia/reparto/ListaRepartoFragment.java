package com.nantia.repartonantia.reparto;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.nantia.repartonantia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaRepartoFragment extends Fragment {
    private ProgressBar progressBar;
    private RecyclerView repartoRV;
    private SearchView buscarSV;


    public ListaRepartoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_reparto, container, false);
    }

}
