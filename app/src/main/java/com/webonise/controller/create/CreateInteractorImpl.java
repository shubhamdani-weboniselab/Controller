package com.webonise.controller.create;


import android.text.TextUtils;

import com.webonise.controller.ControllerApplication;

import io.realm.Realm;

class CreateInteractorImpl implements CreateInteractor {

    @Override
    public boolean validateData(String s) {
        return TextUtils.isEmpty(s);
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
