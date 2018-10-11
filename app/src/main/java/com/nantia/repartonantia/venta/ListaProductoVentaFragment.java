package com.nantia.repartonantia.venta;


import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.nantia.repartonantia.R;
import com.nantia.repartonantia.adapters.ListaProdVentaAdapter;
import com.nantia.repartonantia.adapters.ProductoVentaAdapter;
import com.nantia.repartonantia.cliente.Cliente;
import com.nantia.repartonantia.data.DataHolder;
import com.nantia.repartonantia.listadeprecios.ListaDePrecio;
import com.nantia.repartonantia.listadeprecios.ProductoLista;
import com.nantia.repartonantia.producto.Producto;
import com.nantia.repartonantia.stock.ProductoStock;
import com.nantia.repartonantia.stock.Stock;

import java.util.ArrayList;
import java.util.List;

import static com.nantia.repartonantia.utils.Constantes.KEY_CLIENTE;
import static com.nantia.repartonantia.utils.Constantes.KEY_VENTA;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaProductoVentaFragment extends Fragment
        implements ListaProductoVentaView, ListaProdVentaAdapter.ItemClickListener {

    private TextView cantProdCarroTV;
    private ProgressBar progressBar;
    private SearchView buscar;
    private RecyclerView prodsRV;
    private ConstraintLayout articulosCarroLO;
    private ListaProdVentaAdapter listaProdVentaAdapter;
    private ListaProductoVentaPresenter presenter;
    private boolean refrescar = true;


    public ListaProductoVentaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_producto_venta, container, false);
        initializeViewObjects(view);

        if(getArguments().getSerializable(KEY_CLIENTE) != null && presenter == null){
            refrescar = false;
            Cliente cliente = (Cliente) getArguments().getSerializable(KEY_CLIENTE);
            presenter = new ListaProductoVentaPresenter(this, cliente);
            presenter.getData();
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(refrescar){
            updateCantidadCarro(presenter.getCantArticulos());
            loadData(presenter.getProdsLista());
            setListeners();
        }else {
            refrescar = true;
        }
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void showStockError(float cant) {
        Toast.makeText(getActivity(), getString(R.string.lista_prod_error_stock) + String.valueOf(cant), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showStockNoAsociadoError() {
        Toast.makeText(getActivity(), R.string.lista_prod_error_stock_no_asociado, Toast.LENGTH_LONG).show();
    }

    @Override
    public void finishActivity(){
        getActivity().finish();
    }


    private void initializeViewObjects(View view){
        cantProdCarroTV = view.findViewById(R.id.cant_prod_carro);
        progressBar = view.findViewById(R.id.lista_prod_venta_progress);
        buscar = view.findViewById(R.id.lista_prod_venta_buscar_sv);
        prodsRV = view.findViewById(R.id.lista_prod_venta_rv);
        articulosCarroLO = view.findViewById(R.id.articulos_carro_lo);
    }

    @Override
    public void setListeners(){
        if(listaProdVentaAdapter != null) listaProdVentaAdapter.setClickListener(this);
        articulosCarroLO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToVenta();
            }
        });
        buscar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                listaProdVentaAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                listaProdVentaAdapter.getFilter().filter(s);
                return false;
            }
        });
    }


    @Override
    public void updateCantidadCarro(int cant) {
        cantProdCarroTV.setText(String.valueOf(cant));
    }

    @Override
    public void loadData(List<ProductoLista> prodsLista){
        listaProdVentaAdapter = new ListaProdVentaAdapter(getActivity(), prodsLista);
        prodsRV.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        prodsRV.setAdapter(listaProdVentaAdapter);
    }

    @Override public void onItemClick(View view, int position) {
        showDialog(listaProdVentaAdapter.getItem(position));
    }

    private void navigateToVenta() {
        Bundle b = new Bundle();
        b.putSerializable(KEY_VENTA, presenter.getVenta());
        VentaFragment ventaFragment = new VentaFragment();
        ventaFragment.setArguments(b);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.venta_layout, ventaFragment)
                .addToBackStack(null)
                .commit();
    }

    private void showDialog(final ProductoLista productoLista){
        if(DataHolder.getReparto() != null && DataHolder.getReparto().getStock() != null){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(productoLista.getProducto().getNombre() + " " + productoLista.getProducto().getPresentacion());
            builder.setMessage(getString(R.string.lista_prod_venta_cantidad));
            final EditText cantidad = new EditText(getActivity());
            cantidad.setInputType(InputType.TYPE_CLASS_NUMBER);

            builder.setView(cantidad);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    presenter.addOrUpdateArticulo(productoLista, cantidad.getText().toString());
                }
            });

            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        }else {
            showStockNoAsociadoError();
        }

    }

}
