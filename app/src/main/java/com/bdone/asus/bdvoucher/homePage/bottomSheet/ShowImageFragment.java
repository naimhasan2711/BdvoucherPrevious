package com.bdone.asus.bdvoucher.homePage.bottomSheet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bdone.asus.bdvoucher.R;
import com.bumptech.glide.Glide;

public class ShowImageFragment extends Fragment {


    int position;
    String folderName;
    int totalCount;
    int id;

    public ShowImageFragment() {
        // Required empty public constructor
    }


    public static ShowImageFragment newInstance(int postion, int id) {


        ShowImageFragment fragment = new ShowImageFragment();
        fragment.position = postion;
        fragment.id = id;
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.single_image_fragment, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView_android);

        final int imageCount = position + 1;


        Glide
                .with(getActivity())
                .load("http://bdvoucher.com/Images/products/" + String.valueOf(id) + "/" + imageCount + "_360.jpg")
                .into(imageView);

        return view;
    }


}
