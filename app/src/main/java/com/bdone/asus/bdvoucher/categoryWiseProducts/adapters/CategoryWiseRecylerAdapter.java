package com.bdone.asus.bdvoucher.categoryWiseProducts.adapters;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bdone.asus.bdvoucher.NetworkCall.models.RecomandedProducts;
import com.bdone.asus.bdvoucher.R;
import com.bdone.asus.bdvoucher.homePage.bottomSheet.BottomSheetFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 3/25/2017.
 */

public class CategoryWiseRecylerAdapter extends RecyclerView.Adapter<CategoryWiseRecylerAdapter.CategoryViewHolder> {

    List<RecomandedProducts> cateogrywisePayloadModels = new ArrayList<>();


    public CategoryWiseRecylerAdapter(List<RecomandedProducts> cateogrywisePayloadModels) {
        this.cateogrywisePayloadModels = cateogrywisePayloadModels;
    }


    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recylerviewcategorywise, parent, false));
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, final int position) {
        holder.textViewName.setText(cateogrywisePayloadModels.get(position).getProductTitle());
        Glide
                .with(holder.itemView.getContext())
                .load(R.drawable.badge)
                .override(300, 200)
                .thumbnail(0.1f)
                .into(holder.imageViewBadge);
        holder.textViewPercent.setText(cateogrywisePayloadModels.get(position).getOfferDiscount() + "%");

        /*1024*/
        Glide.with(holder.itemView.getContext())
                .load("http://bdvoucher.com/Images/products/" + String.valueOf(cateogrywisePayloadModels.get(position).getId()) + "/1_360.jpg")
                .placeholder(R.drawable.default_iamge)
                .thumbnail(0.1f)
                .into(holder.imageViewOffer);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetFragment bottomSheetDialogFragment =BottomSheetFragment.newInstance(cateogrywisePayloadModels.get(position).getProductTitle(),cateogrywisePayloadModels.get(position).getCompanyName(),cateogrywisePayloadModels.get(position).getProductCode(),cateogrywisePayloadModels.get(position).getCompanyId(),cateogrywisePayloadModels.get(position).getId(),cateogrywisePayloadModels.get(position).getProductDetails(),cateogrywisePayloadModels.get(position).getOfferDetails(),cateogrywisePayloadModels.get(position).getImageCount(),cateogrywisePayloadModels.get(position).getPromotionalUrl(),cateogrywisePayloadModels.get(position).getOfferAddress());
                bottomSheetDialogFragment.show(((FragmentActivity)holder.itemView.getContext()).getSupportFragmentManager(), "tag");
            }
        });

    }

    @Override
    public int getItemCount() {
        return cateogrywisePayloadModels.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        ImageView imageViewOffer;
        ImageView imageViewBadge;
        TextView textViewPercent;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            imageViewOffer = (ImageView) itemView.findViewById(R.id.imageOffer);
            imageViewBadge = (ImageView) itemView.findViewById(R.id.image_badge);
            textViewPercent = (TextView) itemView.findViewById(R.id.textPercent);
        }
    }
}
