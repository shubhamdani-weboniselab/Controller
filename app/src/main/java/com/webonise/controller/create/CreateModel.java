package com.webonise.controller.create;

import io.realm.RealmObject;


public class CreateModel extends RealmObject {

    private String title;
    private boolean type;

    public CreateModel() {
    }

    public CreateModel(String title, boolean type) {

        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public boolean isType() {
        return type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}
