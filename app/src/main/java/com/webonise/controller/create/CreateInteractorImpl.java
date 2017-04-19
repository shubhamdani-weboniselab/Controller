package com.webonise.controller.create;


import android.text.TextUtils;

import com.feedhenry.sdk.FHResponse;
import com.webonise.controller.ControllerApplication;
import com.webonise.controller.WebService;
import com.webonise.controller.WebServiceBuilder;
import com.webonise.controller.exceptions.NoInternetException;
import com.webonise.controller.interfaces.ApiResponseInterface;
import com.webonise.controller.utilities.Constants;

import org.json.fh.JSONObject;

import io.realm.Realm;

class CreateInteractorImpl implements CreateInteractor {

    @Override
    public void validateData(String s, CreateInteractor.OnValidDataListener listener) {

        if (TextUtils.isEmpty(s)) {
            listener.onInValidData();
        } else {
            listener.onValidData();
        }
    }

    @Override
    public void getDataFromRealm(int position, CreateInteractor.OnItemFetchListener listener) {
        try {
            final Realm realmInstance = ControllerApplication.getRealmInstance();
            final CreateModel createModel = realmInstance.where(CreateModel.class).findAll().get(position);
            listener.onSuccess(createModel);
        } catch (Exception e) {
            e.printStackTrace();
            listener.onError();
        }
    }

    @Override
    public void saveDataInLocalDB(String title, boolean type, final CreateInteractor.OnCreationFinishListener listener) {
        try {
            JSONObject params = new JSONObject();
            params.put("name", title);
            params.put("checked", type);
            final WebService webService = new WebServiceBuilder(ControllerApplication.getInstance())
                    .setParams(params)
                    .setUrl(Constants.URL_POST_ITEM)
                    .setRequestType("POST")
                    .build();

            webService.makeWebRequest(new ApiResponseInterface() {
                @Override
                public void onResponse(FHResponse fhResponse) {
                    listener.onCreateSuccess(fhResponse.getRawResponse());
                }

                @Override
                public void onError(FHResponse fhResponse) {
                    listener.onCreateError(fhResponse.getErrorMessage());
                }
            });
        } catch (NoInternetException e) {
            e.printStackTrace();
        }

    }

}
