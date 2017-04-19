package com.webonise.controller.search;

interface SearchView {
    void showSearchResult(String rawResponse);

    void showErrorMessage(String errorMessage);
}
