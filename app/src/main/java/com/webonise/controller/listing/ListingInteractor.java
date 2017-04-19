package com.webonise.controller.listing;

import java.util.List;

public interface ListingInteractor {

    void removeDataFromRealm(String id, int position, OnListingDeleteListener listener);

    void updateTodo(LisitngModel.DataEntity.ListEntity id, int position, boolean isChecked, OnUpdateResultListener listener);

    interface OnListingResultListener {
        void onSuccess(List<LisitngModel.DataEntity.ListEntity> data);

        void onError(String errorMessage);
    }

    interface OnUpdateResultListener {
        void onSuccess(int position);

        void onError(int position);
    }

    interface OnListingDeleteListener {
        void onDeleteSuccess(int position);

        void onDeleteError();
    }

    void getAllDataFromRealm(ListingInteractor.OnListingResultListener listingResultListener);
}
