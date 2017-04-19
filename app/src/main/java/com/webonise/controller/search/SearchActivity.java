package com.webonise.controller.search;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.webonise.controller.BaseActivity;
import com.webonise.controller.R;
import com.webonise.controller.WebService;
import com.webonise.controller.WebServiceBuilder;
import com.webonise.controller.exceptions.NoInternetException;
import com.webonise.controller.utilities.Constants;

import org.json.fh.JSONObject;

import java.util.List;

public class SearchActivity extends BaseActivity implements View.OnClickListener, SearchView {

    private EditText edtSearch;
    private Button btnSearch;
    private RecyclerView rvSearch;
    private SearchPresenter presenter;

    @Override
    public void setupToolBar() {
        setToolBarWithTitle("Search");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search);
        super.onCreate(savedInstanceState);
        edtSearch = (EditText) findViewById(R.id.edtSearch);
        rvSearch = (RecyclerView) findViewById(R.id.rvSearch);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);
        presenter = new SearchPresenterImpl(this);
    }

    private WebService getWebService() {
        try {
            ProgressDialog dialog = new ProgressDialog(this);
            dialog.setMessage("Loading...");
            JSONObject params = new JSONObject();
            params.put("term", getSearchText());
            return new WebServiceBuilder(this)
                    .setUrl(Constants.URL)
                    .setRequestType("GET")
                    .setParams(params)
                    .setShowProgressDialog(true)
                    .setShowProgressDialog(dialog)
                    .build();
        } catch (NoInternetException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSearch:
                if (!TextUtils.isEmpty(getSearchText())) {
                    presenter.search(getWebService());
                }
                break;
        }
    }

    @Override
    public void showSearchResult(String rawResponse) {
        rvSearch.setLayoutManager(new LinearLayoutManager(this));
        List<UserModel.DataEntity.UsersEntity> list = getUserList(rawResponse);
        if (list != null) {
            SearchAdapter adapter = new SearchAdapter(this, list);
            rvSearch.setAdapter(adapter);
        } else {
            showErrorMessage("SWW");
        }
    }

    private List<UserModel.DataEntity.UsersEntity> getUserList(String rawResponse) {
        final UserModel userModel = UserModel.class.cast(new Gson().fromJson(rawResponse, UserModel.class));
        if (userModel != null) {
            final UserModel.DataEntity data = userModel.getData();
            if (data != null) {
                return data.getUsers();
            }
        }
        return null;
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this, "" + errorMessage, Toast.LENGTH_SHORT).show();
    }

    public String getSearchText() {
        return edtSearch.getText().toString().trim();
    }
}
