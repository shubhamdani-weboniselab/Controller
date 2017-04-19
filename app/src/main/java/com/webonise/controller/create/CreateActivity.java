package com.webonise.controller.create;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.webonise.controller.BaseActivity;
import com.webonise.controller.R;
import com.webonise.controller.listing.MainActivity;

public class CreateActivity extends BaseActivity implements View.OnClickListener, CreateView {

    private RadioGroup mRGType;
    private EditText mEdtTitle;
    private CreatePresenter presenter;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_create);
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {
        Button mBtnSave = (Button) findViewById(R.id.mBtnSave);
        mRGType = (RadioGroup) findViewById(R.id.mRgType);
        mEdtTitle = (EditText) findViewById(R.id.mEdtTitle);
        mBtnSave.setOnClickListener(this);

        presenter = new CreatePresenterImpl(this);
        final int position = getIntent().getIntExtra("position", -1);
        if (position >= 0) {
            presenter.populateData(position);
        }
    }

    @Override
    public void setupToolBar() {
        setToolBarWithTitle(getString(R.string.create));
    }

    @Override
    public void onClick(View view) {
        presenter.validateData(mEdtTitle.getText().toString());
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
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onErrorOfSavingDataInRealm() {
        Toast.makeText(this, "SWW", Toast.LENGTH_SHORT).show();
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void onValidData() {
        presenter.saveDataInRealm(mEdtTitle.getText().toString(), getType());
    }

    @Override
    public void onInValidData() {
        Toast.makeText(this, "Enter Valid Data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setDataInView(CreateModel createModel) {
        mEdtTitle.setText(createModel.getTitle());
    }

    @Override
    public void dismissDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showDialog() {
        progressDialog = ProgressDialog.show(this, "", "Loading...", true, false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MainActivity.CREATE_CODE:
                break;
        }
    }
}
