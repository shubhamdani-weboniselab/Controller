package com.webonise.controller.listing;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ListingPresenterTest {

    @Mock
    ListingView view;
    @Mock
    ListingInteractor interactor;

    private ListingPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new ListingPresenterImpl(view);
    }

    @Test
    public void testStartActivity() {
        presenter.startCreateActivity();
        Mockito.verify(view, Mockito.atLeastOnce()).gotoCreateActivity();
    }
}