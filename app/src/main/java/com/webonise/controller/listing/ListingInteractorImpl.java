package com.webonise.controller.listing;

import com.feedhenry.sdk.FHResponse;
import com.google.gson.Gson;
import com.webonise.controller.ControllerApplication;
import com.webonise.controller.WebService;
import com.webonise.controller.WebServiceBuilder;
import com.webonise.controller.exceptions.NoInternetException;
import com.webonise.controller.interfaces.ApiResponseInterface;
import com.webonise.controller.utilities.Constants;

import org.json.fh.JSONObject;

public class ListingInteractorImpl implements ListingInteractor {

    private ListingView view;

    public ListingInteractorImpl(ListingView view) {
        this.view = view;
    }

    @Override
    public void removeDataFromRealm(String id, final int position, final OnListingDeleteListener listener) {
        try {
            final WebService webService = new WebServiceBuilder(ControllerApplication.getInstance())
                    .setUrl(String.format(Constants.URL_DELETE_ITEM, id))
                    .setRequestType("DELETE")
                    .build();

            webService.makeWebRequest(new ApiResponseInterface() {
                @Override
                public void onResponse(FHResponse fhResponse) {
                    listener.onDeleteSuccess(position);
                }

                @Override
                public void onError(FHResponse fhResponse) {
                    listener.onDeleteError();
                }
            });
        } catch (NoInternetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTodo(LisitngModel.DataEntity.ListEntity listEntity, final int position, boolean isChecked, final OnUpdateResultListener listener) {
        try {
            JSONObject params = new JSONObject();
            params.put("checked", isChecked);
            params.put("name", listEntity.getFields().getName());
            final WebService webService = new WebServiceBuilder(ControllerApplication.getInstance())
                    .setUrl(String.format(Constants.URL_DELETE_ITEM, listEntity.getGuid()))
                    .setParams(params)
                    .setRequestType("PUT")
                    .build();

            webService.makeWebRequest(new ApiResponseInterface() {
                @Override
                public void onResponse(FHResponse fhResponse) {
                    listener.onSuccess(position);
                }

                @Override
                public void onError(FHResponse fhResponse) {
                    listener.onError(position);
                }
            });
        } catch (NoInternetException e) {
            e.printStackTrace();
        }
    }

    public void getAllDataFromRealm(final ListingInteractor.OnListingResultListener listingResultListener) {
        try {
            final WebService webService = new WebServiceBuilder(ControllerApplication.getInstance())
                    .setUrl(Constants.URL_GET_ITEM)
                    .setRequestType("GET")
                    .build();

            webService.makeWebRequest(new ApiResponseInterface() {
                @Override
                public void onResponse(FHResponse fhResponse) {
                    listingResultListener.onSuccess(LisitngModel.class.cast(
                            new Gson().fromJson(fhResponse.getRawResponse(),
                                    LisitngModel.class)).getData().getList());
                }

                @Override
                public void onError(FHResponse fhResponse) {
                    listingResultListener.onError(fhResponse.getErrorMessage());
                }
            });
        } catch (NoInternetException e) {
            e.printStackTrace();
        }

    }
}
