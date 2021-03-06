
package com.nantia.repartonantia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nantia.repartonantia.cliente.ClienteActivity;
import com.nantia.repartonantia.listadeprecios.ListaDePreciosActivity;
import com.nantia.repartonantia.map.MapActivity;
import com.nantia.repartonantia.producto.ProductoActivity;
import com.nantia.repartonantia.reparto.RepartoActivity;
import com.nantia.repartonantia.stock.StockActivity;
import com.nantia.repartonantia.usuario.VendedoresActivity;
import com.nantia.repartonantia.venta.VentaActivity;

import static com.nantia.repartonantia.utils.Constantes.KEY_VENTA_LISTA;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListeners();
    }


    private void addListeners(){
        findViewById(R.id.menu_clientes_cv).setOnClickListener(this);
        findViewById(R.id.menu_reparto_cv).setOnClickListener(this);
        findViewById(R.id.menu_productos_cv).setOnClickListener(this);
        findViewById(R.id.menu_listas_de_precios_cv).setOnClickListener(this);
        findViewById(R.id.menu_stock_cv).setOnClickListener(this);
        findViewById(R.id.menu_mapa_cv).setOnClickListener(this);
        findViewById(R.id.menu_ventas_cv).setOnClickListener(this);
        findViewById(R.id.menu_vendedores_cv).setOnClickListener(this);
    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_clientes_cv:
                startActivity(ClienteActivity.class);
                break;
            case R.id.menu_reparto_cv:
                startActivity(RepartoActivity.class);
                break;
            case R.id.menu_productos_cv:
                startActivity(ProductoActivity.class);
                break;
            case R.id.menu_listas_de_precios_cv:
                startActivity(ListaDePreciosActivity.class);
                break;
            case R.id.menu_stock_cv:
                startActivity(StockActivity.class);
                break;
            case R.id.menu_mapa_cv:
                startActivity(MapActivity.class);
                break;
            case R.id.menu_ventas_cv:
                navigateToVentas();
                break;
            case R.id.menu_vendedores_cv:
                startActivity(VendedoresActivity.class);
                break;
            default:
                break;
        }
    }

    private void startActivity(Class<?> cls){
        Intent i = new Intent(this, cls);
        startActivity(i);
    }

    private void navigateToVentas(){
        Intent i = new Intent(this, VentaActivity.class);
        i.putExtra(KEY_VENTA_LISTA, true);
        startActivity(i);
    }

}
