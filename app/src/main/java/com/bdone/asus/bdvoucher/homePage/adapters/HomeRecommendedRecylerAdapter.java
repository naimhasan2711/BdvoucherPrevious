package com.bdone.asus.bdvoucher.homePage.adapters;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bdone.asus.bdvoucher.NetworkCall.models.RecomandedProducts;
import com.bdone.asus.bdvoucher.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ASUS on 3/10/2017.
 */

public class HomeRecommendedRecylerAdapter extends RecyclerView.Adapter<HomeRecommendedRecylerAdapter.RecommendedVoucherViewHolder> {

    List<RecomandedProducts> recommendedVoucherModelsList = new ArrayList<>();
    ClickListenerDataPassInterface clickListenerDataPassInterface;
    int trackID;


    public HomeRecommendedRecylerAdapter(List<RecomandedProducts> recommendedVoucherModelsList, int trackID) {

        this.recommendedVoucherModelsList = recommendedVoucherModelsList;
        this.trackID = trackID;

    }

    @Override
    public RecommendedVoucherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recylerview_recommended_singel, parent, false);
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.recylerview_latest_products, parent, false);
        View view3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.recylerview_ending_soon, parent, false);

        if (trackID == 1) {
            return new RecommendedVoucherViewHolder(view);
        } else if (trackID == 3) {

            return new RecommendedVoucherViewHolder(view3);
        } else {
            return new RecommendedVoucherViewHolder(view2);

        }
    }

    @Override
    public void onBindViewHolder(final RecommendedVoucherViewHolder holder, int position) {


        if (recommendedVoucherModelsList.get(position).getProductTitle().length() > 10) {
            holder.recommendedText.setText(recommendedVoucherModelsList.get(position).getProductTitle().substring(0, 11) + "...");
        } else {
            holder.recommendedText.setText(recommendedVoucherModelsList.get(position).getProductTitle());
        }
        if (trackID == 3) {

            Random r = new Random();
            int red = r.nextInt(255 - 0 + 1) + 0;
            int green = r.nextInt(255 - 0 + 1) + 0;
            int blue = r.nextInt(255 - 0 + 1) + 0;

            GradientDrawable draw = new GradientDrawable();
            // draw.setShape(GradientDrawable.OVAL);
            draw.setColor(Color.rgb(red, green, blue));
            holder.recommendedText.setBackground(draw);

        }

        Glide
                .with(holder.itemView.getContext())
                .load(R.drawable.badge)
                .override(300, 200)
                .thumbnail(0.1f)
                .into(holder.imageViewBadge);
        holder.textViewPercent.setText(recommendedVoucherModelsList.get(position).getOfferDiscount() + "%");

        Glide.with(holder.itemView.getContext())
                .load("http://bdvoucher.com/Images/products/" + String.valueOf(recommendedVoucherModelsList.get(position).getId()) + "/1_360.jpg")
                .thumbnail(0.1f)
                .into(holder.recommendedImage);
        Log.e("tag", "onBindViewHolder:      " + "http://bdvoucher.com/Images/products/" + String.valueOf(recommendedVoucherModelsList.get(position).getId()) + "/1_1024.jpg");
    /*    holder.recommendedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.itemView.getContext(),"working",Toast.LENGTH_LONG).show();
            }
        });*/


    }

    @Override
    public int getItemCount() {
        return recommendedVoucherModelsList.size();
    }

    public void onClick(ClickListenerDataPassInterface clickListenerDataPassInterface) {
        this.clickListenerDataPassInterface = clickListenerDataPassInterface;
    }

    public interface ClickListenerDataPassInterface {

        void clickListenerMethod(View view, int position);
    }

    class RecommendedVoucherViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView recommendedImage;
        TextView recommendedText;
        ImageView imageViewBadge;
        TextView textViewPercent;

        public RecommendedVoucherViewHolder(View itemView) {
            super(itemView);
            recommendedImage = (ImageView) itemView.findViewById(R.id.image_rec_recommended);
            recommendedText = (TextView) itemView.findViewById(R.id.textView_rec_recommended_text);
            imageViewBadge = (ImageView) itemView.findViewById(R.id.image_badge);
            textViewPercent = (TextView) itemView.findViewById(R.id.textPercent);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListenerDataPassInterface.clickListenerMethod(view, getAdapterPosition());
        }
    }
}
