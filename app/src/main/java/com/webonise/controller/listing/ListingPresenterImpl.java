package com.webonise.controller.listing;

import android.util.Log;

import java.util.List;

public class ListingPresenterImpl implements ListingPresenter, ListingInteractor.OnListingResultListener, ListingInteractor.OnListingDeleteListener, ListingInteractor.OnUpdateResultListener {

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
        view.showProgressDialog();
        listingInteractor.getAllDataFromRealm(this);
    }

    @Override
    public void onItemClick(LisitngModel.DataEntity.ListEntity listEntity, int position, boolean isChecked) {
        listingInteractor.updateTodo(listEntity, position,isChecked, this);
    }

    @Override
    public void onLongClick(String id, int position) {
        view.showDeleteDialog(id, position);
    }

    @Override
    public void deleteItem(String id, int position) {
        listingInteractor.removeDataFromRealm(id, position, this);
    }

    @Override
    public void startSearchActivity() {
        view.gotoSearchActivity();
    }

    @Override
    public void onSuccess(List<LisitngModel.DataEntity.ListEntity> data) {
        Log.d("@@", "fetched success");
        view.dismissProgressDialog();
        view.showAllListing(data);
    }

    @Override
    public void onError(String errorMessage) {
        Log.d("@@", "fetched fail");
    }

    @Override
    public void onDeleteSuccess(int position) {
        view.showMessage("Successfully Deleted");
        view.reloadList(position);
    }

    @Override
    public void onDeleteError() {
        view.showMessage("Delete failed");
    }

    @Override
    public void onSuccess(int position) {
        view.updateSuccess(position);
    }

    @Override
    public void onError(int position) {
        view.updateFail(position);
    }
}
