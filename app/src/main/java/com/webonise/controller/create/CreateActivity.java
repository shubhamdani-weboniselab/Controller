package com.webonise.controller.create;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.webonise.controller.BaseActivity;
import com.webonise.controller.R;

public class CreateActivity extends BaseActivity implements View.OnClickListener, CreateView {

    private Button mBtnSave;
    private RadioGroup mRGType;
    private EditText mEdtTitle;
    private CreatePresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_create);
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {
        mBtnSave = (Button) findViewById(R.id.mBtnSave);
        mRGType = (RadioGroup) findViewById(R.id.mRgType);
        mEdtTitle = (EditText) findViewById(R.id.mEdtTitle);
        mBtnSave.setOnClickListener(this);
        presenter = new CreatePresenterImpl(this);
    }

    @Override
    public void setupToolBar() {
        setToolBarWithTitle(getString(R.string.create));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == mBtnSave.getId()) {
            presenter.saveDataInRealm(mEdtTitle.getText().toString(), getType());
        }
    }

    public boolean getType() {
        return mRGType.getCheckedRadioButtonId() == R.id.rbShould;
    }

    @Override
    public void showToastOfFillRequiredData() {
        Toast.makeText(this, "Fill All Data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessfullySavingDataInRealm() {
        Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onErrorOfSavingDataInRealm() {
        Toast.makeText(this, "SWW", Toast.LENGTH_SHORT).show();
        finish();
    }
}
