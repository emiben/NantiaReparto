package com.nantia.repartonantia.login;

import android.content.Context;
import android.view.View;
import android.widget.Toast;


import com.nantia.repartonantia.usuario.Usuario;
import com.nantia.repartonantia.utils.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Emi on 6/5/2018.
 */

public class LoginPresenterImpl implements ILoginPresenter{
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
}
