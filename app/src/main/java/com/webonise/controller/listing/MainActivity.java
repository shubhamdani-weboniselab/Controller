package com.webonise.controller.listing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.webonise.controller.BaseActivity;
import com.webonise.controller.R;
import com.webonise.controller.create.CreateActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener, ListingView {

    private ListingPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        presenter = new ListingPresenterImpl(this);
    }

    @Override
    public void onClick(View view) {
        presenter.startCreateActivity();
    }

    @Override
    public void gotoCreateActivity() {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

    @Override
    public void setupToolBar() {
        setToolBarWithTitle(getString(R.string.listings));
    }
}
