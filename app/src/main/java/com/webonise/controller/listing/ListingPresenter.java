package com.webonise.controller.listing;

public interface ListingPresenter {

    void startCreateActivity();

    void showAllData();

    void onItemClick(LisitngModel.DataEntity.ListEntity createModel, int position, boolean isChecked);

    void onLongClick(String id, int position);

    void deleteItem(String id, int position);

    void startSearchActivity();
}
