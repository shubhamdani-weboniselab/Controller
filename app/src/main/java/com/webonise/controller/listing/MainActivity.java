package com.webonise.controller.listing;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.webonise.controller.BaseActivity;
import com.webonise.controller.R;
import com.webonise.controller.create.CreateActivity;
import com.webonise.controller.create.CreateModel;

import io.realm.RealmResults;

public class MainActivity extends BaseActivity implements View.OnClickListener, ListingView {

    public static final int CREATE_CODE = 1;
    private ListingPresenter presenter;
    private RecyclerView mRlListing;
    private ListingAdapter adapter;

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
        adapter = new ListingAdapter(MainActivity.this, data, presenter);
        mRlListing.setLayoutManager(new LinearLayoutManager(this));
        mRlListing.setAdapter(adapter);
    }

    @Override
    public void showDeleteDialog(final CreateModel createModel, final int position) {
        DialogInterface.OnClickListener positiveListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.deleteItem(position);
            }
        };
        DialogInterface.OnClickListener negListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        };
        try {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Delete this item")
                    .setPositiveButton("Yes", positiveListener)
                    .setNegativeButton("No", negListener)
                    .create();
            dialog.show();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void startEditActivity(CreateModel createModel, int position) {

    }


    @Override
    public void setupToolBar() {
        setToolBarWithTitle(getString(R.string.listings));
    }
}
