package com.nantia.repartonantia.login;

/**
 * Created by Emi on 6/5/2018.
 */

public interface ILoginPresenter {
    void clear();
    void doLogin(String name, String passwd);
    void setProgressBarVisiblity(int visiblity);
}
