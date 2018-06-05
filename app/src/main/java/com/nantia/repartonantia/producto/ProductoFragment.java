package com.nantia.repartonantia.producto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nantia.repartonantia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductoFragment extends Fragment {
    private Producto producto;

    public ProductoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_producto, container, false);

        if(getArguments().getSerializable("producto")!=null){
            producto = (Producto) getArguments().getSerializable("producto");
            initializeViewObjects(view, producto);
        }

        return view;
    }

    private void initializeViewObjects(View view, Producto producto){
        ((TextView) view.findViewById(R.id.producto_nombre_tv)).setText(producto.getNombre());
        ((TextView) view.findViewById(R.id.producto_presentacion_tv)).setText(producto.getPresentacion());
        ((TextView) view.findViewById(R.id.producto_descripcion_tv)).setText(producto.getDescripcion());
        if(producto.isRetornable()){
            ((TextView) view.findViewById(R.id.producto_retornable_tv)).setText(getString(R.string.producto_retornable));
        }else {
            ((TextView) view.findViewById(R.id.producto_retornable_tv)).setText(getString(R.string.producto_no_retornable));
        }
    }

}
