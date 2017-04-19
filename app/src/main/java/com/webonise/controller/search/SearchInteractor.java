package com.webonise.controller.search;


import com.webonise.controller.WebService;

interface SearchInteractor {

    void search(WebService webService, OnSearchResultListener listener);

    interface OnSearchResultListener {
        void onSearchSuccess(String rawResponse);
        void onSearchFail(String errorMessage);
    }
}
