
package com.nantia.repartonantia.login;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nantia.repartonantia.MainActivity;
import com.nantia.repartonantia.R;
import com.nantia.repartonantia.data.AppDatabase;

import static com.nantia.repartonantia.utils.Constantes.KEY_DB_NOMBRE;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener{

    private TextView nombreUsuario;
    private TextView contrasenia;
    private Button btnIngresar;
    private ProgressBar progressBar;
    ILoginPresenter loginPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getViewElements();

        btnIngresar.setOnClickListener(this);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
            AppDatabase.class, KEY_DB_NOMBRE).build();
        loginPresenter = new LoginPresenterImpl(this, db);
        loginPresenter.setProgressBarVisiblity(View.INVISIBLE);

    }

    private void getViewElements(){
        nombreUsuario = (TextView) findViewById(R.id.nombre_usuario);
        contrasenia = (TextView) findViewById(R.id.contrasenia);
        btnIngresar = (Button) findViewById(R.id.boton_ingresar);
        progressBar = (ProgressBar) findViewById(R.id.login_progress);

        //TODO: Remove this
        nombreUsuario.setText("usuario1");
        contrasenia.setText("usuario1");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.boton_ingresar:
                loginPresenter.doLogin(nombreUsuario.getText().toString(),
                        contrasenia.getText().toString());
                break;
        }
    }

    @Override
    public void onClearText() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void navigteToMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void enableIngresar(boolean enable) {
        btnIngresar.setEnabled(enable);
    }
}

