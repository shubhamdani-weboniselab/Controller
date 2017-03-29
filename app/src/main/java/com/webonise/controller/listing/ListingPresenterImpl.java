package com.webonise.controller.listing;

public class ListingPresenterImpl implements ListingPresenter {

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
}
