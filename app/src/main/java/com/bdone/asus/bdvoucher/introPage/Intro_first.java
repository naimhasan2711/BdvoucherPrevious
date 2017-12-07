package com.bdone.asus.bdvoucher.introPage;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bdone.asus.bdvoucher.R;
import com.bumptech.glide.Glide;


public class Intro_first extends Fragment_base {

    private DemoParallaxAdapter mCatsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_first_intro, container, false);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/sareeka.ttf");
        Typeface tf2 = Typeface.createFromAsset(getActivity().getAssets(),
                "fonts/white.otf");





        final ImageView image = (ImageView) v.findViewById(R.id.image);



        Glide.with(getActivity())
                .load(getArguments().getInt("image"))
                .into(image);



        return v;
    }

    public void setAdapter(DemoParallaxAdapter catsAdapter) {
        mCatsAdapter = catsAdapter;
    }
}
