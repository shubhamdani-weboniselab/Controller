package com.webonise.controller.listing;

import android.util.Log;

import com.webonise.controller.create.CreateModel;

import io.realm.RealmResults;

public class ListingPresenterImpl implements ListingPresenter, ListingInteractor.OnListingResultListener {

    private final ListingInteractorImpl listingInteractor;
    private ListingView view;

    public ListingPresenterImpl(ListingView view) {
        this.view = view;
        listingInteractor = new ListingInteractorImpl(view);
    }

    @Override
    public void startCreateActivity() {
        view.gotoCreateActivity();
    }

    @Override
    public void showAllData() {
        listingInteractor.getAllDataFromRealm(this);
    }

    @Override
    public void onSuccess(RealmResults<CreateModel> data) {
        Log.d("@@", "fetched success");
        view.showAllListing(data);
    }

    @Override
    public void onError() {
        Log.d("@@", "fetched failuer");
    }
}
