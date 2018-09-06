package com.nantia.repartonantia.venta;


import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import com.nantia.repartonantia.R;
import com.nantia.repartonantia.adapters.ListaProdVentaAdapter;
import com.nantia.repartonantia.adapters.ProductoVentaAdapter;
import com.nantia.repartonantia.cliente.Cliente;
import com.nantia.repartonantia.data.DataHolder;
import com.nantia.repartonantia.listadeprecios.ListaDePrecio;
import com.nantia.repartonantia.listadeprecios.ProductoLista;
import com.nantia.repartonantia.producto.Producto;
import java.util.ArrayList;
import java.util.List;

import static com.nantia.repartonantia.utils.Constantes.KEY_CLIENTE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaProductoVentaFragment extends Fragment implements ListaProdVentaAdapter.ItemClickListener {

    private TextView cantProdCarroTV;
    private ProgressBar progressBar;
    private SearchView buscar;
    private RecyclerView prodsRV;

    private ListaProdVentaAdapter listaProdVentaAdapter;
    private Cliente cliente;
    private Venta venta;
    private List<ProductoVenta> prodsVenta;
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
        prodsVenta = new ArrayList<>();

        if(getArguments().getSerializable(KEY_CLIENTE) != null){
            this.cliente = (Cliente) getArguments().getSerializable(KEY_CLIENTE);
            loadData();
            setListeners();
        }

        return view;
    }


    private void initializeViewObjects(View view){
        cantProdCarroTV = view.findViewById(R.id.cant_prod_carro);
        progressBar = view.findViewById(R.id.lista_prod_venta_progress);
        buscar = view.findViewById(R.id.lista_prod_venta_buscar_sv);
        prodsRV = view.findViewById(R.id.lista_prod_venta_rv);
    }

    private void setListeners(){
        if(listaProdVentaAdapter != null) listaProdVentaAdapter.setClickListener(this);
    }


    private void loadData(){
        listaDePrecio = DataHolder.getListaDePrecioById(cliente.getIdLista());
        if(listaDePrecio != null){
            listaProdVentaAdapter = new ListaProdVentaAdapter(getActivity(), listaDePrecio.getProductosLista());
            prodsRV.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            prodsRV.setAdapter(listaProdVentaAdapter);
        }
    }


    private void showDialog(final ProductoLista productoLista){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(productoLista.getProducto().getNombre() + " " + productoLista.getProducto().getPresentacion());
        builder.setMessage(getString(R.string.lista_prod_venta_cantidad));
        final EditText cantidad = new EditText(getActivity());
        cantidad.setInputType(InputType.TYPE_CLASS_NUMBER);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addOrUpdateArticulo(productoLista, cantidad.getText().toString());
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    private void addOrUpdateArticulo(ProductoLista productoLista, String cantidad){
        //TODO: Hacer el manejo contra Stock!!
        int cant = 0;
        if(!cantidad.isEmpty()) cant = Integer.valueOf(cantidad);
        ProductoVenta productoVenta = getProdEnLista(productoLista.getProducto().getId());
        if(cant == 0){
            if(productoVenta != null) prodsVenta.remove(productoVenta);
        }else {
            if(productoVenta == null){
                productoVenta = new ProductoVenta(0, productoLista.getProducto(), cant,
                    productoLista.getPrecio(), (cant * productoLista.getPrecio()));
            }else {
                prodsVenta.remove(productoVenta);
                productoVenta.setCantidad(cant);
                productoVenta.setProductoTotal((cant * productoLista.getPrecio()));
            }
            prodsVenta.add(productoVenta);
        }

    }

    @Override public void onItemClick(View view, int position) {
        showDialog(listaProdVentaAdapter.getItem(position));
    }

    private ProductoVenta getProdEnLista(long prodId){
        for (ProductoVenta productoVenta : prodsVenta){
            if(productoVenta.getProducto().getId() == prodId){
                return productoVenta;
            }
        }
        return null;
    }
}
