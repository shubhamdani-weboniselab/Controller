package com.webonise.controller.listing;

import com.webonise.controller.create.CreateModel;

import java.util.List;

public interface ListingView {
    void gotoCreateActivity();

    void showAllListing(List<LisitngModel.DataEntity.ListEntity> data);

    void showDeleteDialog(String id, int position);

    void showMessage(String msg);

    void startEditActivity(CreateModel createModel, int position);

    void gotoSearchActivity();

    void showProgressDialog();

    void dismissProgressDialog();

    void reloadList(int position);

    void updateSuccess(int position);

    void updateFail(int position);
}
