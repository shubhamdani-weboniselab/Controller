package com.webonise.controller.interfaces;

import com.feedhenry.sdk.FHResponse;

public interface ApiResponseInterface {

    void onResponse(final FHResponse fhResponse);

    void onError(final FHResponse fhResponse);
}
