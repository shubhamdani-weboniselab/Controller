package com.webonise.controller.create;


interface CreateView {

    void showToastOfFillRequiredData();

    void onSuccessfullySavingDataInRealm();

    void onErrorOfSavingDataInRealm();

    void onValidData();

    void onInValidData();

    void setDataInView(CreateModel createModel);

    void dismissDialog();

    void showDialog();
}
