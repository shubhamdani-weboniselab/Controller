package com.webonise.controller.create;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.webonise.controller.BaseActivity;
import com.webonise.controller.R;

/**
 * Created by webonise on 29/3/17.
 */
public class CreateActivity extends BaseActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_create);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setupToolBar() {
        setToolBarWithTitle(getString(R.string.create));
    }
}
