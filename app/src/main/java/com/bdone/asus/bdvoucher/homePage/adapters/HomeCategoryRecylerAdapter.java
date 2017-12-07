package com.bdone.asus.bdvoucher.homePage.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bdone.asus.bdvoucher.NetworkCall.models.TopCategoryPayLoad;
import com.bdone.asus.bdvoucher.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rasel on 3/5/2017.
 */

public class HomeCategoryRecylerAdapter extends RecyclerView.Adapter<HomeCategoryRecylerAdapter.CustomRecylerViewHolder> {

    List<TopCategoryPayLoad> catgoryList = new ArrayList<>();
    RvClickInterface rvClickInterface;

    public HomeCategoryRecylerAdapter(List<TopCategoryPayLoad> value) {
        this.catgoryList = value;
    }

    @Override
    public CustomRecylerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recylerview_category_single, parent, false);
        CustomRecylerViewHolder customRecylerViewHolder = new CustomRecylerViewHolder(view);

        return customRecylerViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomRecylerViewHolder holder, int position) {

        holder.categoryName.setText(catgoryList.get(position).getCategoryNameEng());

        Glide
                .with(holder.itemView.getContext())
                .load("http://bdvoucher.com/images/CatIconHome/"+catgoryList.get(position).getCategoryId()+".png")
                .override(300, 200)
                .thumbnail(0.1f)
                .into(holder.categoryIcon);

    }

    @Override
    public int getItemCount() {
        return catgoryList.size();
    }

    public void getClickInterface(RvClickInterface rvClickInterface) {
        this.rvClickInterface = rvClickInterface;

    }

    public interface RvClickInterface {
        void clickListener(int postion, View view);
    }

    class CustomRecylerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView categoryIcon;
        public TextView categoryName;

        public CustomRecylerViewHolder(View itemView) {

            super(itemView);
            categoryIcon = (ImageView) itemView.findViewById(R.id.image_cat_icon);
            categoryName = (TextView) itemView.findViewById(R.id.tv_cat_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            rvClickInterface.clickListener(getAdapterPosition(), view);

        }
    }


}
