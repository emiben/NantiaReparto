package com.nantia.repartonantia.producto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.nantia.repartonantia.R;
import com.nantia.repartonantia.adapters.ProductoAdapter;

import java.util.ArrayList;

/**
 *
 */
public class ProductoListaFragment extends Fragment implements ProductoListaView {

    private SearchView buscarSV;
    private ListView productosLV;
    private ArrayList<Producto> productos;
    private ProductoAdapter productoAdapter;
    private ProgressBar progressBar;
    private ProductoListaPresenter productoListaPresenter;

    public ProductoListaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_producto_lista, container, false);
        productoListaPresenter = new ProductoListaPresenter(this);
        initializeViewObjects(view);
        productoListaPresenter.getProductos();

        return view;
    }

    private void initializeViewObjects(View view){
        buscarSV = view.findViewById(R.id.buscar_sv);
        productosLV = view.findViewById(R.id.producto_lv);
        progressBar = view.findViewById(R.id.producto_lista_progress);
    }


    @Override
    public void addListeners(){
        buscarSV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                productoAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                productoAdapter.getFilter().filter(s);
                return false;
            }
        });
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void clearSearchView() {
        this.buscarSV.setQuery("", false);
        this.buscarSV.clearFocus();
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        this.progressBar.setVisibility(visibility);
    }

    @Override
    public void navigteToProductoFragment(Producto producto) {
        Bundle b = new Bundle();
        b.putSerializable("producto", producto);
        ProductoFragment pf = new ProductoFragment();
        pf.setArguments(b);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.producto_lista_layout, pf)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void setProductosInfo(ArrayList<Producto> productos) {
        productoAdapter = new ProductoAdapter(getActivity(), productos, this);
        productosLV.setAdapter(productoAdapter);
    }
}
