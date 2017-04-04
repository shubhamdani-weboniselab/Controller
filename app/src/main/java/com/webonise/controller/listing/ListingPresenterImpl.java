package com.webonise.controller.listing;

import android.util.Log;

import com.webonise.controller.create.CreateModel;

import io.realm.RealmResults;

public class ListingPresenterImpl implements ListingPresenter, ListingInteractor.OnListingResultListener, ListingInteractor.OnListingDeleteListener {

    private final ListingInteractor listingInteractor;
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
    public void onItemClick(CreateModel createModel, int position) {
        view.startEditActivity(createModel, position);
    }

    @Override
    public void onLongClick(CreateModel createModel, int position) {
        view.showDeleteDialog(createModel, position);
    }

    @Override
    public void deleteItem(int position) {
        listingInteractor.removeDataFromRealm(position, this);
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

    @Override
    public void onDeleteSuccess() {
        view.showMessage("Successfully Deleted");
    }

    @Override
    public void onDeleteError() {
        view.showMessage("Delete failed");
    }
}
