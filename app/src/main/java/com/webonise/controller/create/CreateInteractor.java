package com.webonise.controller.create;

interface CreateInteractor {

    boolean validateData(String s);

    interface OnCreationFinishListener {
        void onSuccess();

        void onError();
    }

    void saveDataInLocalDB(String title, boolean type, CreateInteractor.OnCreationFinishListener listener);
}
