package com.bdone.asus.bdvoucher.homePage.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bdone.asus.bdvoucher.NetworkCall.models.HomeBlockModel;
import com.bdone.asus.bdvoucher.R;
import com.bdone.asus.bdvoucher.categoryWiseProducts.FragmentcategoryWiseProduct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 3/25/2017.
 */

public class HomeBlockRecylerAdapter extends RecyclerView.Adapter<HomeBlockRecylerAdapter.HomeBlockViewHolder> {


    List<HomeBlockModel> homeBlockModels = new ArrayList<>();

    public HomeBlockRecylerAdapter(List<HomeBlockModel> homeBlockModels) {
        this.homeBlockModels = homeBlockModels;
    }

    @Override
    public HomeBlockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeBlockViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recylerview_home_block, parent, false));
    }

    @Override
    public void onBindViewHolder(final HomeBlockViewHolder holder, final int position) {
        holder.textView.setText(homeBlockModels.get(position).getCategoryNameEng());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));

        holder.recyclerView.setAdapter(new HomeBlockChildRecylerAdapter(homeBlockModels.get(position).getProductData()));
        holder.textViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = FragmentcategoryWiseProduct.newInstance(homeBlockModels.get(position).getCategoryNameEng(), homeBlockModels.get(position).getCategoryId());
                FragmentManager fm =((FragmentActivity)holder.itemView.getContext()).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.containerHome, fragment);
                ft.addToBackStack("TAGREVOVE");
                ft.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return homeBlockModels.size();
    }

    class HomeBlockViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textViewMore;
        RecyclerView recyclerView;

        public HomeBlockViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.textHomeBlock);
            textViewMore = (TextView) itemView.findViewById(R.id.textMore);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerViewHomeBlockChild);
        }
    }
}
