package com.webonise.controller.listing;

import android.app.ProgressDialog;
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
import com.webonise.controller.search.SearchActivity;

import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener, ListingView {

    public static final int CREATE_CODE = 1;
    private ListingPresenter presenter;
    private RecyclerView mRlListing;
    private ListingAdapter adapter;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        mRlListing = (RecyclerView) findViewById(R.id.mRlListing);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton fabSearch = (FloatingActionButton) findViewById(R.id.fabSearch);
        fab.setOnClickListener(this);
        fabSearch.setOnClickListener(this);
        presenter = new ListingPresenterImpl(this);
        presenter.showAllData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fabSearch:
                presenter.startSearchActivity();
                break;
            case R.id.fab:
                presenter.startCreateActivity();
                break;
        }
    }

    @Override
    public void gotoCreateActivity() {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivityForResult(intent, MainActivity.CREATE_CODE);
    }

    @Override
    public void showAllListing(List<LisitngModel.DataEntity.ListEntity> data) {
        adapter = new ListingAdapter(MainActivity.this, data, presenter);
        mRlListing.setLayoutManager(new LinearLayoutManager(this));
        mRlListing.setAdapter(adapter);
    }

    @Override
    public void showDeleteDialog(final String id, final int position) {
        DialogInterface.OnClickListener positiveListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.deleteItem(id, position);
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
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

    @Override
    public void gotoSearchActivity() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public void showProgressDialog() {
        progressDialog = ProgressDialog.show(this, "", "Loading...", true, false);
    }

    @Override
    public void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void reloadList(int position) {
        adapter.removeItem(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateSuccess(int position) {
        showMessage("todo updated");
        adapter.setItemChecked(position);
    }

    @Override
    public void updateFail(int position) {

    }


    @Override
    public void setupToolBar() {
        setToolBarWithTitle(getString(R.string.listings));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MainActivity.CREATE_CODE && resultCode == RESULT_OK) {
            presenter.showAllData();
        }

    }
}
