package com.webonise.controller.listing;


import android.content.Context;

import com.webonise.controller.ControllerApplication;
import com.webonise.controller.create.CreateModel;
import com.webonise.controller.utilities.Constants;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import io.realm.Realm;
import io.realm.RealmConfiguration;

@RunWith(MockitoJUnitRunner.class)
public class ListingInteractorTest {

    @Mock
    ListingView view;
    @Mock
    ListingInteractor.OnListingDeleteListener listener;
    ListingInteractorImpl interactor;


    @Before
    public void setUp() throws Exception {
        interactor = new ListingInteractorImpl(view);
    }

    @Test
    public void testRemoveDataFromRealm() {
        final Context mock = Mockito.mock(Context.class);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(mock)
                .deleteRealmIfMigrationNeeded()
                .name("sfs")
                .schemaVersion(Constants.SCHEMA_VERSION)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
        final Realm realmInstance = Realm.getInstance(mock);
        realmInstance.beginTransaction();
        final CreateModel createModel = new CreateModel();
        createModel.setTitle("bla");
        createModel.setType(false);
        realmInstance.copyToRealm(createModel);


        interactor.removeDataFromRealm(ControllerApplication.getRealmInstance(), 0, listener);
        Mockito.verify(listener, Mockito.atLeastOnce()).onDeleteSuccess();
    }
}