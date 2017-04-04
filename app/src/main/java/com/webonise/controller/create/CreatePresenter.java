package com.webonise.controller.create;

public interface CreatePresenter {

    void saveDataInRealm(String s, boolean type);

    void validateData(String s);

    void populateData(int position);
}
