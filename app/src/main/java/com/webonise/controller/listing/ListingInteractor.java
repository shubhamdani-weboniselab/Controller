package com.webonise.controller.listing;

import com.webonise.controller.create.CreateModel;

import io.realm.RealmResults;

public interface ListingInteractor {

    interface OnListingResultListener {
        void onSuccess(RealmResults<CreateModel> data);

        void onError();
    }

    void getAllDataFromRealm(ListingInteractor.OnListingResultListener listingResultListener);
}
