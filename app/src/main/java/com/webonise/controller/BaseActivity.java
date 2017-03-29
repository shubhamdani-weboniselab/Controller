package com.webonise.controller;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by webonise on 29/3/17.
 */
public abstract class BaseActivity extends Activity {

    public abstract void setupToolBar();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupToolBar();
    }

    public void setToolBarWithTitle(String title) {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        toolbar.setNavigationOnClickListener(getNavigationClickListener());
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
    }

    public View.OnClickListener getNavigationClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };
    }
}
