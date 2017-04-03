package com.webonise.controller.create;

class CreatePresenterImpl implements CreatePresenter, CreateInteractor.OnCreationFinishListener {

    private final CreateInteractorImpl interactor;
    private CreateView createView;

    public CreatePresenterImpl(CreateView createView) {
        this.createView = createView;
        this.interactor = new CreateInteractorImpl();
    }

    @Override
    public void saveDataInRealm(String title, boolean type) {
        interactor.saveDataInLocalDB(title, type, this);
    }

    @Override
    public void onSuccess() {
        createView.onSuccessfullySavingDataInRealm();
    }

    @Override
    public void onError() {
        createView.onErrorOfSavingDataInRealm();
    }
}
