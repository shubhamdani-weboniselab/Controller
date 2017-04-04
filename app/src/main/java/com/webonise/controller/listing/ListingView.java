package com.webonise.controller.listing;

import com.webonise.controller.create.CreateModel;

import io.realm.RealmResults;

public interface ListingView {
    void gotoCreateActivity();

    void showAllListing(RealmResults<CreateModel> data);

    void showDeleteDialog(CreateModel createModel, int position);

    void showMessage(String msg);

    void startEditActivity(CreateModel createModel, int position);

}
