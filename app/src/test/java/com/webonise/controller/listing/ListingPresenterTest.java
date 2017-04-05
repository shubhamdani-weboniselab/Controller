package com.webonise.controller.listing;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
}