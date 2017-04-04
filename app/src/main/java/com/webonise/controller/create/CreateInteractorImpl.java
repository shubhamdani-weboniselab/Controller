package com.webonise.controller.create;


import android.text.TextUtils;

import com.webonise.controller.ControllerApplication;

import io.realm.Realm;

class CreateInteractorImpl implements CreateInteractor {

    @Override
    public void validateData(String s, CreateInteractor.OnValidDataListener listener) {

        if (TextUtils.isEmpty(s)) {
            listener.onInValidData();
        } else {
            listener.onValidData();
        }
    }

    @Override
    public void getDataFromRealm(int position, CreateInteractor.OnItemFetchListener listener) {
        try {
            final Realm realmInstance = ControllerApplication.getRealmInstance();
            final CreateModel createModel = realmInstance.where(CreateModel.class).findAll().get(position);
            listener.onSuccess(createModel);
        } catch (Exception e) {
            e.printStackTrace();
            listener.onError();
        }
    }

    @Override
    public void saveDataInLocalDB(String title, boolean type, CreateInteractor.OnCreationFinishListener listener) {
        try {
            final Realm realmInstance = ControllerApplication.getRealmInstance();
            realmInstance.beginTransaction();
            realmInstance.copyToRealm(new CreateModel(title, type));
            realmInstance.commitTransaction();
            listener.onSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            listener.onError();
        }

    }

}
