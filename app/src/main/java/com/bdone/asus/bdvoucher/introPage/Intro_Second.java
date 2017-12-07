package com.bdone.asus.bdvoucher.introPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bdone.asus.bdvoucher.R;
import com.bumptech.glide.Glide;

/**
 * Created by Rasel on 1/10/2017.
 */

public class Intro_Second extends Fragment_base{

    private DemoParallaxAdapter mCatsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_second_intro, container, false);
        final ImageView image = (ImageView) v.findViewById(R.id.image);



        Glide.with(getActivity())
                .load(getArguments().getInt("image"))
                .thumbnail(0.1f)
                .into(image);



        return v;
    }

    public void setAdapter(DemoParallaxAdapter catsAdapter) {
        mCatsAdapter = catsAdapter;
    }
}
