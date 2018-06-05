package com.nantia.repartonantia.login;

/**
 * Created by Emi on 6/5/2018.
 */

public interface ILoginView {
    void onClearText();
    void onSetProgressBarVisibility(int visibility);
    void navigteToMainActivity();
    void enableIngresar(boolean enable);
}
