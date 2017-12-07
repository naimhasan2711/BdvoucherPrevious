package com.bdone.asus.bdvoucher.introPage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bdone.asus.bdvoucher.ActivityHome;
import com.bdone.asus.bdvoucher.R;
import com.bumptech.glide.Glide;


/**
 * Created by Rasel on 1/10/2017.
 */

public class Intro_four extends Fragment_base {

    private DemoParallaxAdapter mCatsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_four_intro, container, false);

        FloatingActionButton buttonNext = (FloatingActionButton) v.findViewById(R.id.bt_start_now);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ActivityHome.class);
                startActivity(intent);
                SharedPreferences sharedPreferencesForFirstTracking =
                        getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editorForFirstTracking = sharedPreferencesForFirstTracking.edit();
                editorForFirstTracking.putInt("isFirstOpen", 1);
                editorForFirstTracking.apply();


            }
        });

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
