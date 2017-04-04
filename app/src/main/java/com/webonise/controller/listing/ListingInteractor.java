package com.webonise.controller.listing;

import com.webonise.controller.create.CreateModel;

import io.realm.RealmResults;

public interface ListingInteractor {

    void removeDataFromRealm(int pos, ListingInteractor.OnListingDeleteListener listener);

    interface OnListingResultListener {
        void onSuccess(RealmResults<CreateModel> data);

        void onError();
    }

    interface OnListingDeleteListener {
        void onDeleteSuccess();

        void onDeleteError();
    }

    void getAllDataFromRealm(ListingInteractor.OnListingResultListener listingResultListener);
}
