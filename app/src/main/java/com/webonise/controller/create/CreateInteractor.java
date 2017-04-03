package com.webonise.controller.create;

interface CreateInteractor {

    void validateData(String s, CreateInteractor.OnValidDataListener listener);

    interface OnCreationFinishListener {
        void onSuccess();

        void onError();
    }

    interface OnValidDataListener {
        void onValidData();

        void onInValidData();
    }

    void saveDataInLocalDB(String title, boolean type, CreateInteractor.OnCreationFinishListener listener);
}
