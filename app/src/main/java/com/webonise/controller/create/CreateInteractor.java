package com.webonise.controller.create;

interface CreateInteractor {

    interface OnCreationFinishListener {
        void onSuccess();

        void onError();
    }

    void saveDataInLocalDB(String title, boolean type, CreateInteractor.OnCreationFinishListener listener);
}
