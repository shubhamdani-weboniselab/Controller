package com.webonise.controller.create;

/**
 * Created by webonise on 30/3/17.
 */

interface CreateView {

    void showToastOfFillRequiredData();

    void onSuccessfullySavingDataInRealm();

    void onErrorOfSavingDataInRealm();

    void onValidData();

    void onInValidData();
}
