package com.webonise.controller.search;

import com.webonise.controller.WebService;

class SearchPresenterImpl implements SearchPresenter, SearchInteractor.OnSearchResultListener {
    private final SearchInteractor interactor;
    private SearchView view;

    public SearchPresenterImpl(SearchView view) {
        this.view = view;
        interactor = new SearchInteractorImpl();
    }

    @Override
    public void search(WebService webService) {
        interactor.search(webService, this);
    }


    @Override
    public void onSearchSuccess(String rawResponse) {
        view.showSearchResult(rawResponse);
    }

    @Override
    public void onSearchFail(String errorMessage) {
        view.showErrorMessage(errorMessage);
    }
}
