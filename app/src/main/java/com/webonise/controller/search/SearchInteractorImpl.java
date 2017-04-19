package com.webonise.controller.search;


import com.feedhenry.sdk.FHResponse;
import com.webonise.controller.WebService;
import com.webonise.controller.interfaces.ApiResponseInterface;

class SearchInteractorImpl implements SearchInteractor {

    @Override
    public void search(WebService webService, final OnSearchResultListener listener) {
        webService.makeWebRequest(new ApiResponseInterface() {
            @Override
            public void onResponse(FHResponse fhResponse) {
                listener.onSearchSuccess(fhResponse.getRawResponse());
            }

            @Override
            public void onError(FHResponse fhResponse) {
                listener.onSearchFail(fhResponse.getErrorMessage());
            }
        });
    }
}
