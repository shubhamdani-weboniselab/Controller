package com.webonise.controller.listing;

import com.webonise.controller.ControllerApplication;
import com.webonise.controller.create.CreateModel;

import io.realm.Realm;
import io.realm.RealmResults;

public class ListingInteractorImpl implements ListingInteractor {

    private ListingView view;

    public ListingInteractorImpl(ListingView view) {
        this.view = view;
    }

    @Override
    public void removeDataFromRealm(int pos, ListingInteractor.OnListingDeleteListener listener) {
        try {
            final Realm realmInstance = ControllerApplication.getRealmInstance();
            final RealmResults<CreateModel> allCreateModel = realmInstance.where(CreateModel.class).findAll();
            realmInstance.beginTransaction();
            allCreateModel.remove(pos);
            realmInstance.commitTransaction();
            listener.onDeleteSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            listener.onDeleteError();
        }
    }

    public void getAllDataFromRealm(ListingInteractor.OnListingResultListener listingResultListener) {
        try {
            final Realm realmInstance = ControllerApplication.getRealmInstance();
            realmInstance.beginTransaction();
            final RealmResults<CreateModel> createModels = realmInstance.allObjects(CreateModel.class);
            realmInstance.commitTransaction();
            listingResultListener.onSuccess(createModels);
        } catch (Exception exp) {
            exp.printStackTrace();
            listingResultListener.onError();
        }
    }
}
