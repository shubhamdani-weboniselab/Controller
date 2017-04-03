package com.webonise.controller;

import android.app.Application;

import com.webonise.controller.utilities.Constants;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by webonise on 3/4/17.
 */

public class ControllerApplication extends Application {

    private static ControllerApplication instance;
    private static Realm realm;

    public static Realm getRealmInstance() {
        return realm;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        realm = Realm.getInstance(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .name(getString(R.string.app_name))
                .schemaVersion(Constants.SCHEMA_VERSION)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }

    public static ControllerApplication getInstance() {
        return instance;
    }
}
