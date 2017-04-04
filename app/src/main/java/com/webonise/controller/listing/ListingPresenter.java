package com.webonise.controller.listing;

import com.webonise.controller.create.CreateModel;

public interface ListingPresenter {

    void startCreateActivity();

    void showAllData();

    void onItemClick(CreateModel createModel, int position);

    void onLongClick(CreateModel createModel, int position);

    void deleteItem(int position);
}
