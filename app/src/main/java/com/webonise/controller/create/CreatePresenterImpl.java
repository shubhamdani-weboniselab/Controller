package com.webonise.controller.create;

class CreatePresenterImpl implements CreatePresenter, CreateInteractor.OnCreationFinishListener, CreateInteractor.OnValidDataListener, CreateInteractor.OnItemFetchListener {

    private final CreateInteractor interactor;
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
    public void validateData(String s) {
        interactor.validateData(s, this);
    }

    @Override
    public void populateData(int position) {
        interactor.getDataFromRealm(position, this);
    }

    @Override
    public void onSuccess() {
        createView.onSuccessfullySavingDataInRealm();
    }

    @Override
    public void onSuccess(CreateModel createModel) {
        createView.setDataInView(createModel);
    }

    @Override
    public void onError() {
        createView.onErrorOfSavingDataInRealm();
    }

    @Override
    public void onValidData() {
        createView.onValidData();
    }

    @Override
    public void onInValidData() {
        createView.onInValidData();
    }
}
