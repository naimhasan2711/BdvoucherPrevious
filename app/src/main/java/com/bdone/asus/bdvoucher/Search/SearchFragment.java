package com.bdone.asus.bdvoucher.Search;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;

import com.bdone.asus.bdvoucher.NetworkCall.RetrofitClient;
import com.bdone.asus.bdvoucher.NetworkCall.interfaces.SearchInterface;
import com.bdone.asus.bdvoucher.NetworkCall.models.RecomandedProducts;
import com.bdone.asus.bdvoucher.NetworkCall.models.SeachRequestBody;
import com.bdone.asus.bdvoucher.R;
import com.bdone.asus.bdvoucher.categoryWiseProducts.adapters.CategoryWiseRecylerAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ASUS on 3/30/2017.
 */

public class SearchFragment extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    private EditText mEditText;
    Retrofit retrofit;
    SearchInterface searchInterface;
    RecyclerView mRecyclerViewSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarForS);

        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();

        ab.setTitle(" ");
        ab.setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        retrofit = RetrofitClient.getInstance(this);
        searchInterface = retrofit.create(SearchInterface.class);
        mRecyclerViewSearch = (RecyclerView) findViewById(R.id.recclerViewSearch);
        mEditText = (EditText) findViewById(R.id.search_editText);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                hideProgressDialog();
                if (charSequence.length() > 1) {
                    search(String.valueOf(charSequence));


                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading..");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish();
            // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    private void search(String value) {

        showProgressDialog();

        searchInterface.getSearch(new SeachRequestBody(-1, "", -1, -1, value, 0, 45)).enqueue(new Callback<List<RecomandedProducts>>() {
            @Override
            public void onResponse(Call<List<RecomandedProducts>> call, Response<List<RecomandedProducts>> response) {
                if (response.isSuccessful()) {

                    Log.e("checckData", "onResponse: " + String.valueOf(response.body()));
                    hideProgressDialog();
                    mRecyclerViewSearch.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    CategoryWiseRecylerAdapter categorywiseRecylaeradapter = new CategoryWiseRecylerAdapter(response.body());
                    mRecyclerViewSearch.setAdapter(categorywiseRecylaeradapter);
                }
            }

            @Override
            public void onFailure(Call<List<RecomandedProducts>> call, Throwable t) {
                hideProgressDialog();
            }
        });
    }

}
