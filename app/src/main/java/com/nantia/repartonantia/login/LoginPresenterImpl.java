package com.nantia.repartonantia.login;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.nantia.repartonantia.cliente.Cliente;
import com.nantia.repartonantia.cliente.ClienteService;
import com.nantia.repartonantia.data.AppDatabase;
import com.nantia.repartonantia.data.DataHolder;
import com.nantia.repartonantia.listadeprecios.ListaDePrecio;
import com.nantia.repartonantia.listadeprecios.ListaDePreciosService;
import com.nantia.repartonantia.producto.Envase;
import com.nantia.repartonantia.producto.EnvaseService;
import com.nantia.repartonantia.producto.Producto;
import com.nantia.repartonantia.producto.ProductoService;
import com.nantia.repartonantia.reparto.Reparto;
import com.nantia.repartonantia.usuario.Usuario;
import com.nantia.repartonantia.utils.RetrofitClientInstance;
import com.nantia.repartonantia.venta.Venta;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Emi on 6/5/2018.
 */

public class LoginPresenterImpl implements ILoginPresenter{
    private final String TAG = LoginPresenterImpl.class.getName();
    ILoginView iLoginView;
    AppDatabase db;

    public LoginPresenterImpl(ILoginView iLoginView, AppDatabase db) {
        this.iLoginView = iLoginView;
        this.db = db;
    }

    @Override
    public void clear() {
        iLoginView.onClearText();
    }

    @Override
    public void doLogin(String name, String passwd) {
        iLoginView.onSetProgressBarVisibility(View.VISIBLE);
        iLoginView.enableIngresar(false);
        Login login = new Login(name, passwd);
        LoginService loginService = RetrofitClientInstance.getRetrofitInstance().create(LoginService.class);

        Call<Usuario> call = loginService.login(login);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                iLoginView.onSetProgressBarVisibility(View.INVISIBLE);
                if(response.code() == 200){
                    Usuario usr = response.body();
                    usr.setActualizado(true);
                    DataHolder.setUsuario(usr);
                    guardarUsuraio(usr);
                    getData();
                    iLoginView.navigteToMainActivity();
                }else if(response.code() == 401){
                    iLoginView.enableIngresar(true);
                    Toast.makeText((Context)iLoginView, "Usuario y contraseña no coinciden", Toast.LENGTH_SHORT).show();
                }else {
                    iLoginView.enableIngresar(true);
                    Toast.makeText((Context)iLoginView, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                iLoginView.onSetProgressBarVisibility(View.INVISIBLE);
                iLoginView.enableIngresar(true);
                Toast.makeText((Context)iLoginView, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void setProgressBarVisiblity(int visiblity) {
        iLoginView.onSetProgressBarVisibility(visiblity);
    }

    @Override
    public void getData() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                List<Reparto> repartos = db.repartoDao().getAll();
                if(repartos.size() > 0){
                    getDataFromDb(repartos.get(0));
                }else {
                    getDataFromServer();
                }
            }
        });
    }

    private void getDataFromServer(){
        getEnvases();
        getProductos();
        getClientes();
        getListasDePrecios();
    }

    private void getDataFromDb(final Reparto reparto){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                reparto.setVehiculo(db.vehiculoDao().getAll().get(0));
                reparto.setStock(db.stockDao().getAll().get(0));
                reparto.setVendedor1(db.usuarioDao().getAll().get(0));
                reparto.setVendedor2(db.usuarioDao().getAll().get(1));
                reparto.setRuta(db.rutaDao().getAll().get(0));
                DataHolder.setReparto(reparto);
                DataHolder.setEnvases(db.envaseDao().getAll());
                DataHolder.setProductos(db.productoDao().getAll());
                DataHolder.setClientes(db.clienteDao().getAll());
                DataHolder.setListasDePrecios(db.listaDePrecioDao().getAll());
                if(db.ventaDao().getAll() != null){
                    DataHolder.setVentas(db.ventaDao().getAll());
                }else{
                    DataHolder.setVentas(new ArrayList<Venta>());
                }
            }
        });
    }

    private void guardarUsuraio(final Usuario usuario){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.usuarioDao().insertAll(usuario);
                Log.i(TAG, "Usuario Insertado");
            }
        });
    }

    private void getEnvases(){
        EnvaseService envaseService = RetrofitClientInstance.getRetrofitInstance().create(EnvaseService.class);
        Call<ArrayList<Envase>> call = envaseService.getEnvases();
        call.enqueue(new Callback<ArrayList<Envase>>() {
            @Override
            public void onResponse(Call<ArrayList<Envase>> call, Response<ArrayList<Envase>> response) {
                DataHolder.setEnvases(response.body());
                guardarEnvases(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Envase>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void getProductos(){
        ProductoService productoService = RetrofitClientInstance.getRetrofitInstance().create(ProductoService.class);
        Call<ArrayList<Producto>> call = productoService.getProductos();
        call.enqueue(new Callback<ArrayList<Producto>>() {
            @Override
            public void onResponse(Call<ArrayList<Producto>> call, Response<ArrayList<Producto>> response) {
                DataHolder.setProductos(response.body());
                guardarProductos(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Producto>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void getClientes(){
        ClienteService clienteService = RetrofitClientInstance.getRetrofitInstance().create(ClienteService.class);
        Call<ArrayList<Cliente>> call = clienteService.getClientes();
        call.enqueue(new Callback<ArrayList<Cliente>>() {
            @Override
            public void onResponse(Call<ArrayList<Cliente>> call, Response<ArrayList<Cliente>> response) {
                DataHolder.setClientes(response.body());
                guardarClientes(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Cliente>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void guardarClientes(final List<Cliente> clientes){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.clienteDao().nukeTable();
                for (int i = 0; i < clientes.size(); i++){
                    clientes.get(i).setActualizado(true);
                    db.clienteDao().insertAll(clientes.get(i));
                }
            }
        });
    }

    private void guardarEnvases(final List<Envase> envases){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.envaseDao().nukeTable();
                for (Envase envase : envases){
                    db.envaseDao().insertAll(envase);
                }
            }
        });
    }

    private void guardarProductos(final List<Producto> productos){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.productoDao().nukeTable();
                for (Producto producto : productos){
                    db.productoDao().insertAll(producto);
                }
            }
        });
    }

    private void getListasDePrecios(){
        ListaDePreciosService listaDePreciosService =
                RetrofitClientInstance.getRetrofitInstance().create(ListaDePreciosService.class);
        Call<ArrayList<ListaDePrecio>> call = listaDePreciosService.getListasDePrecios();
        call.enqueue(new Callback<ArrayList<ListaDePrecio>>() {
            @Override
            public void onResponse(Call<ArrayList<ListaDePrecio>> call, Response<ArrayList<ListaDePrecio>> response) {
                DataHolder.setListasDePrecios(response.body());
                guardarListasDePrecio(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<ListaDePrecio>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });

    }

    private void guardarListasDePrecio(final List<ListaDePrecio> listasDePrecios){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.listaDePrecioDao().nukeTable();
                for (ListaDePrecio listaDePrecio : listasDePrecios){
                    db.listaDePrecioDao().insertAll(listaDePrecio);
                }
            }
        });
    }
}
