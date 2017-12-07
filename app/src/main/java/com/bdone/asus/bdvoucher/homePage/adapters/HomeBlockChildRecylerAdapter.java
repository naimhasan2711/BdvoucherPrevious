package com.bdone.asus.bdvoucher.homePage.adapters;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bdone.asus.bdvoucher.NetworkCall.models.HomeBlockSingleIteam;
import com.bdone.asus.bdvoucher.R;
import com.bdone.asus.bdvoucher.homePage.bottomSheet.BottomSheetFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 3/25/2017.
 */

public class HomeBlockChildRecylerAdapter extends RecyclerView.Adapter<HomeBlockChildRecylerAdapter.ChildViewHolderHomeBlock> {

    List<HomeBlockSingleIteam> homeBlockSingleIteams = new ArrayList<>();

    public HomeBlockChildRecylerAdapter(List<HomeBlockSingleIteam> homeBlockSingleIteams) {
        this.homeBlockSingleIteams = homeBlockSingleIteams;

    }

    @Override
    public ChildViewHolderHomeBlock onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChildViewHolderHomeBlock(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_home_block_child, parent, false));
    }

    @Override
    public void onBindViewHolder(final ChildViewHolderHomeBlock holder, final int position) {


        if (homeBlockSingleIteams.get(position).getProductTitle().length() > 14) {
            holder.textView.setText(homeBlockSingleIteams.get(position).getProductTitle().substring(0, 13) + "...");
        } else {
            holder.textView.setText(homeBlockSingleIteams.get(position).getProductTitle());
        }


        Glide.with(holder.itemView.getContext())
                .load("http://bdvoucher.com/Images/products/" + String.valueOf(homeBlockSingleIteams.get(position).getId()) + "/1_360.jpg")
                .thumbnail(0.1f)
                .into(holder.imageView);


        Glide
                .with(holder.itemView.getContext())
                .load(R.drawable.badge)
                .override(300, 200)
                .thumbnail(0.1f)
                .into(holder.imageViewBadge);
        holder.textViewPercent.setText(homeBlockSingleIteams.get(position).getOfferDiscount() + "%");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetFragment bottomSheetDialogFragment = BottomSheetFragment.newInstance(homeBlockSingleIteams.get(position).getProductTitle(), homeBlockSingleIteams.get(position).getCompanyName(), homeBlockSingleIteams.get(position).getProductCode(), homeBlockSingleIteams.get(position).getCompanyId(), homeBlockSingleIteams.get(position).getId(), homeBlockSingleIteams.get(position).getProductDetails(), homeBlockSingleIteams.get(position).getOfferDetails(), homeBlockSingleIteams.get(position).getImageCount(),homeBlockSingleIteams.get(position).getPromotionalUrl(),homeBlockSingleIteams.get(position).getOfferAddress());
                bottomSheetDialogFragment.show(((FragmentActivity) holder.itemView.getContext()).getSupportFragmentManager(), "tag");
                //  Log.e("myclick", "onClick: "+"##############" );

            }
        });

    }

    @Override
    public int getItemCount() {
        return homeBlockSingleIteams.size();
    }

    class ChildViewHolderHomeBlock extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        ImageView imageViewBadge;
        TextView textViewPercent;

        public ChildViewHolderHomeBlock(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.ChildName);
            imageView = (ImageView) itemView.findViewById(R.id.image_child);
            imageViewBadge = (ImageView) itemView.findViewById(R.id.image_badge);
            textViewPercent = (TextView) itemView.findViewById(R.id.textPercent);

        }
    }
}
