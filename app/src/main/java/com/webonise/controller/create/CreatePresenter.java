package com.webonise.controller.create;

public interface CreatePresenter {

    void saveDataInRealm(String s, boolean type);

    boolean validateData(String s, boolean type);
}
