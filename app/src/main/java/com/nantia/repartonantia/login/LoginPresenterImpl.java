package com.nantia.repartonantia.login;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.nantia.repartonantia.cliente.Cliente;
import com.nantia.repartonantia.cliente.ClienteService;
import com.nantia.repartonantia.cliente.TipoDocumento;
import com.nantia.repartonantia.data.DataHolder;
import com.nantia.repartonantia.listadeprecios.ListaDePrecio;
import com.nantia.repartonantia.listadeprecios.ListaDePreciosService;
import com.nantia.repartonantia.producto.Envase;
import com.nantia.repartonantia.producto.EnvaseService;
import com.nantia.repartonantia.producto.Producto;
import com.nantia.repartonantia.producto.ProductoService;
import com.nantia.repartonantia.usuario.Usuario;
import com.nantia.repartonantia.utils.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Emi on 6/5/2018.
 */

public class LoginPresenterImpl implements ILoginPresenter{
    private final String TAG = LoginPresenterImpl.class.getName();
    ILoginView iLoginView;

    public LoginPresenterImpl(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
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
        getEnvases();
        getProductos();
        getClientes();
        getListasDePrecios();
    }

    private void getEnvases(){
        EnvaseService envaseService = RetrofitClientInstance.getRetrofitInstance().create(EnvaseService.class);
        Call<ArrayList<Envase>> call = envaseService.getEnvases();
        call.enqueue(new Callback<ArrayList<Envase>>() {
            @Override
            public void onResponse(Call<ArrayList<Envase>> call, Response<ArrayList<Envase>> response) {
                DataHolder.setEnvases(response.body());
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
            }

            @Override
            public void onFailure(Call<ArrayList<Cliente>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
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
            }

            @Override
            public void onFailure(Call<ArrayList<ListaDePrecio>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });

    }
}
