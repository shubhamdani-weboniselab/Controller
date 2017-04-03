package com.webonise.controller.listing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.webonise.controller.BaseActivity;
import com.webonise.controller.R;
import com.webonise.controller.create.CreateActivity;
import com.webonise.controller.create.CreateModel;

import io.realm.RealmResults;

public class MainActivity extends BaseActivity implements View.OnClickListener, ListingView {

    public static final int CREATE_CODE = 1;
    private ListingPresenter presenter;
    private RecyclerView mRlListing;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        mRlListing = (RecyclerView) findViewById(R.id.mRlListing);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        presenter = new ListingPresenterImpl(this);
        presenter.showAllData();
    }

    @Override
    public void onClick(View view) {
        presenter.startCreateActivity();
    }

    @Override
    public void gotoCreateActivity() {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivityForResult(intent, MainActivity.CREATE_CODE);
    }

    @Override
    public void showAllListing(RealmResults<CreateModel> data) {
        ListingAdapter adapter = new ListingAdapter(MainActivity.this, data, this);
        mRlListing.setLayoutManager(new LinearLayoutManager(this));
        mRlListing.setAdapter(adapter);
    }

    @Override
    public void setupToolBar() {
        setToolBarWithTitle(getString(R.string.listings));
    }
}
