
package com.nantia.repartonantia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.nantia.repartonantia.listadeprecios.ListaDePreciosActivity;
import com.nantia.repartonantia.producto.ProductoActivity;
import com.nantia.repartonantia.adapters.CustomGridViewAdapter;
import com.nantia.repartonantia.cliente.ClienteActivity;
import com.nantia.repartonantia.reparto.RepartoActivity;
import com.nantia.repartonantia.stock.StockActivity;

import static com.nantia.repartonantia.utils.Constantes.CLIENTES;
import static com.nantia.repartonantia.utils.Constantes.LISTA_DE_PRECIOS;
import static com.nantia.repartonantia.utils.Constantes.MAPA;
import static com.nantia.repartonantia.utils.Constantes.PRODUCTOS;
import static com.nantia.repartonantia.utils.Constantes.REPARTO;
import static com.nantia.repartonantia.utils.Constantes.STOCK;

public class MainActivity extends AppCompatActivity {

    private GridView menuGridView;

    String[] menuTitles = {"Clientes", "Reparto", "Productos", "Listas de Precio", "Stock", "Mapa"};
    int[] menuImagesIDs = {R.drawable.cliente_icono, R.drawable.camion_icono, R.drawable.producto_icono,
                            R.drawable.lista_icono, R.drawable.stock_icono, R.drawable.mapa_icono};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomGridViewAdapter adapterViewAndroid = new CustomGridViewAdapter(MainActivity.this, menuTitles, menuImagesIDs);
        menuGridView=(GridView)findViewById(R.id.menu_gridview);
        menuGridView.setAdapter(adapterViewAndroid);
        menuGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
//                Toast.makeText(MainActivity.this, "GridView Item: " + menuTitles[+i], Toast.LENGTH_LONG).show();
                onClickNavigateTo(i);
            }
        });
    }

    private void onClickNavigateTo(int position){
        switch (position) {
            case CLIENTES:
                startActivity(ClienteActivity.class);
                break;
            case REPARTO:
                startActivity(RepartoActivity.class);
                //Toast.makeText(MainActivity.this, "GridView Item: " + menuTitles[+position], Toast.LENGTH_LONG).show();
                break;
            case PRODUCTOS:
                startActivity(ProductoActivity.class);
                break;
            case LISTA_DE_PRECIOS:
                startActivity(ListaDePreciosActivity.class);
                break;
            case STOCK:
                startActivity(StockActivity.class);
                break;
            case MAPA:
                Toast.makeText(MainActivity.this, "GridView Item: " + menuTitles[+position], Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    private void startActivity(Class<?> cls){
        Intent i = new Intent(this, cls);
        startActivity(i);
    }
}
