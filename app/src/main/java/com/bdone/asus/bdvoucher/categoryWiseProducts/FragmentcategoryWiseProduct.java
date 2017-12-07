package com.bdone.asus.bdvoucher.categoryWiseProducts;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bdone.asus.bdvoucher.ActivityHome;
import com.bdone.asus.bdvoucher.NetworkCall.RetrofitClient;
import com.bdone.asus.bdvoucher.NetworkCall.interfaces.InterfaceCategoryWiseProducts;
import com.bdone.asus.bdvoucher.NetworkCall.models.CategoryWiseSendBody;
import com.bdone.asus.bdvoucher.NetworkCall.models.RecomandedProducts;
import com.bdone.asus.bdvoucher.R;
import com.bdone.asus.bdvoucher.Search.SearchFragment;
import com.bdone.asus.bdvoucher.categoryWiseProducts.adapters.CategoryWiseRecylerAdapter;

import java.util.List;

import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ASUS on 3/25/2017.
 */

public class FragmentcategoryWiseProduct extends Fragment {
    TextView textViewCategoryName;
    String categoryName;
    int categoryId;
    ImageView mSeImageView;
    RecyclerView mRecylerViewCategoryProducts;
    Retrofit retrofit;
    InterfaceCategoryWiseProducts interfaceCategoryWiseProducts;
    ProgressDialog progress;

    public static FragmentcategoryWiseProduct newInstance(String categoryName, int categoryId) {
        FragmentcategoryWiseProduct fragment = new FragmentcategoryWiseProduct();
        fragment.categoryName = categoryName;
        fragment.categoryId = categoryId;

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_wise_product, container, false);
        mRecylerViewCategoryProducts = (RecyclerView) view.findViewById(R.id.recylerViewCategoryProducts);

         progress = new ProgressDialog(getActivity());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
     //   progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
// To dismiss the dialog

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((ActivityHome) getActivity()).setSupportActionBar(toolbar);
        final ActionBar ab = ((ActivityHome) getActivity()).getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        ab.setTitle(" ");
        ab.setDisplayHomeAsUpEnabled(true);

        textViewCategoryName = (TextView) view.findViewById(R.id.tv_categoryName);
        textViewCategoryName.setText(categoryName);

        retrofit = RetrofitClient.getInstance(getActivity());
        interfaceCategoryWiseProducts = retrofit.create(InterfaceCategoryWiseProducts.class);
        getCategorywiseProducts(mRecylerViewCategoryProducts);
        mSeImageView= (ImageView) view.findViewById(R.id.searchFromCategory);
        mSeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchFragment.class));
            }
        });
        return view;
    }

    private void getCategorywiseProducts(final RecyclerView mRecyclerView) {

        interfaceCategoryWiseProducts.getCategoryWiseProduct(new CategoryWiseSendBody(-1, "", categoryId, -1, 0, 60)).enqueue(new Callback<List<RecomandedProducts>>() {
            @Override
            public void onResponse(Call<List<RecomandedProducts>> call, Response<List<RecomandedProducts>> response) {
                if (response.isSuccessful()) {
                    Log.e("myTag", "onResponse: " + String.valueOf(response.body()));
                    mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    CategoryWiseRecylerAdapter categorywiseRecylaeradapter = new CategoryWiseRecylerAdapter(response.body());
                    mRecyclerView.setAdapter(categorywiseRecylaeradapter);
                    mRecyclerView.setAdapter(new ScaleInAnimationAdapter(categorywiseRecylaeradapter));
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<RecomandedProducts>> call, Throwable t) {

            }
        });

    }
}