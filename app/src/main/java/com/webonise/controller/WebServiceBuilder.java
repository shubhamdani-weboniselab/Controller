package com.webonise.controller;

import android.app.ProgressDialog;
import android.content.Context;

import com.webonise.controller.exceptions.NoInternetException;

import org.json.fh.JSONObject;

import java.util.Arrays;

import cz.msebera.android.httpclient.Header;

public class WebServiceBuilder {

    public Context context;
    public String url;
    public Header[] header;
    public JSONObject params;
    public ProgressDialog progressDialog;
    public int timeOut = 15000;
    public Class<?> responseType;
    public boolean showProgressDialog;
    public String requestType;

    public WebServiceBuilder(final Context context) {
        this.context = context;
    }

    public WebServiceBuilder setUrl(final String url) {
        this.url = url;
        return this;
    }

    public WebServiceBuilder setHeader(final Header[] header) {
        this.header = null == header ? new Header[0] : Arrays.copyOf(header, header.length);
        return this;
    }

    public WebServiceBuilder setTimeOut(final int timeOut) {
        this.timeOut = timeOut;
        return this;
    }

    public WebServiceBuilder setShowProgressDialog(final ProgressDialog showProgressDialog) {
        this.progressDialog = showProgressDialog;
        showProgressDialog.setCancelable(false);
        return this;
    }

    public WebServiceBuilder setParams(final JSONObject params) {
        this.params = params;
        return this;
    }

    public WebServiceBuilder setResponseType(final Class<?> responseType) {
        this.responseType = responseType;
        return this;
    }


    public WebServiceBuilder setShowProgressDialog(final boolean isProgressDialog) {
        this.showProgressDialog = isProgressDialog;
        return this;
    }

    public WebServiceBuilder setRequestType(final String requestType) {
        this.requestType = requestType;
        return this;
    }

    public WebService build() throws NoInternetException {
        return new WebService(this, context);
    }
}
