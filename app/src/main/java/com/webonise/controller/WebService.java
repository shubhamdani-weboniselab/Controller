package com.webonise.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.feedhenry.sdk.FH;
import com.feedhenry.sdk.FHActCallback;
import com.feedhenry.sdk.FHHttpClient;
import com.feedhenry.sdk.FHResponse;
import com.feedhenry.sdk.api.FHCloudRequest;
import com.feedhenry.sdk.exceptions.FHNotReadyException;
import com.webonise.controller.exceptions.NoInternetException;
import com.webonise.controller.interfaces.ApiResponseInterface;

import org.json.fh.JSONObject;

import cz.msebera.android.httpclient.Header;

public class WebService {

    private final String TAG = getClass().getSimpleName();
    private String url;
    private String requestType;
    private Header[] header;
    private JSONObject params;
    private ProgressDialog progressDialog;
    private boolean isProgressDialog;
    private final int TIME_OUT = 30000;
    private Context mContext;


    public WebService(final WebServiceBuilder webServiceBuilder, Context context) throws
            NoInternetException {
        this.mContext = context;
        if (null != webServiceBuilder) {
            if (!isConnectedToInternet(webServiceBuilder.context)) {
                throw new NoInternetException();
            }
            this.mContext = webServiceBuilder.context;
            this.header = webServiceBuilder.header;
            this.params = webServiceBuilder.params;
            this.progressDialog = webServiceBuilder.progressDialog;
            this.isProgressDialog = webServiceBuilder.showProgressDialog;
            this.url = webServiceBuilder.url;
            this.requestType = webServiceBuilder.requestType;
        }
    }

    public void FHInitialization(final Context context, final ApiResponseInterface
            responseInterface) {

        FH.init(context, new FHActCallback() {
            @Override
            public void success(final FHResponse resp) {
                responseInterface.onResponse(resp);
            }

            @Override
            public void fail(final FHResponse arg0) {
                responseInterface.onError(arg0);
            }
        });
    }

    public void makeWebRequest(final ApiResponseInterface responseInterface) {

        if (progressDialog != null && isProgressDialog) {
            progressDialog.show();
        }
        FHHttpClient.setTimeout(TIME_OUT);

        if (FH.isReady()) {
            makeRequest(responseInterface);
        } else {
            FHInitialization(mContext, new ApiResponseInterface() {
                @Override
                public void onResponse(final FHResponse response) {
                    makeRequest(responseInterface);
                }

                @Override
                public void onError(FHResponse fhResponse) {
                    responseInterface.onError(fhResponse);
                }
            });
        }
    }

    private void makeRequest(final ApiResponseInterface responseInterface) {
        try {
            Log.d("Host", " " + FH.getCloudHost());
        } catch (FHNotReadyException e) {
            e.printStackTrace();
        }
        try {
            final FHCloudRequest request = FH.buildCloudRequest(url, requestType, header, params);

            Log.d(TAG, url);
            Log.d(TAG, null == params ? "" : params.toString());
            request.executeAsync(new FHActCallback() {
                @Override
                public void success(final FHResponse fhResponse) {
                    handleSuccessResponse(fhResponse, responseInterface);
                }

                @Override
                public void fail(final FHResponse fhResponse) {
                    dismissDialog();
                    if (fhResponse != null && fhResponse.getErrorMessage() != null) {
                        Log.e("onCreateError >>>>>>>>", fhResponse.getErrorMessage());
                        handleFailResponse(fhResponse, responseInterface);
                    }
                }
            });
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private void handleFailResponse(final FHResponse fhResponse, final ApiResponseInterface
            responseInterface) {
        responseInterface.onError(fhResponse);
    }

    private void handleSuccessResponse(final FHResponse fhResponse, final ApiResponseInterface
            responseInterface) {
        if (null != fhResponse) {
            Log.d("Response >>>>", fhResponse.getRawResponse());
            responseInterface.onResponse(fhResponse);
        } else {
            responseInterface.onError(fhResponse);
        }
        dismissDialog();
    }

    public void dismissDialog() {
        if (null != progressDialog && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public static boolean isConnectedToInternet(final Context context) {
        final ConnectivityManager connectivity =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivity) {
            final NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && NetworkInfo.State.CONNECTED == info.getState()) {
                return true;
            }
        }
        return false;
    }
}
