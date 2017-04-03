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
