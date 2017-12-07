package com.bdone.asus.bdvoucher.homePage.bottomSheet;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bdone.asus.bdvoucher.R;
import com.bdone.asus.bdvoucher.dialogFragments.GetCodeDilaog;
import com.bdone.asus.bdvoucher.dialogFragments.ViewCodeDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rasel on 3/12/2017.
 */


public class BottomSheetFragment extends BottomSheetDialogFragment {
    FloatingActionButton buttonFab;
    TextView offerName;
    TextView merchantName;
    TextView productDetails;
    TextView offerDetails;
    String offerNameString;
    String merchantNameString;
    TextView locationText;
    //  ImageView imageViewOffer;
    String mProductDetalisString;
    String mOfferDetilsString;
    String promotionURL;
    int imageCount;
    int companyID;
    int productID;
    private LinearLayout dotsLayout;
    private List<ImageView> dots;
    String offerCode;
    private int NUM_PAGES = 0;
    private ViewPager mViewpager;
    private String location;

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    public static BottomSheetFragment newInstance(String offerNameString, String merchantNameString,
                                                  String offerCode, int companyId, int productId,
                                                  String mProductDetalis, String mOfferDetils,
                                                  int mImageCount, String url, String locationValue) {
        BottomSheetFragment fragment = new BottomSheetFragment();
        fragment.offerNameString = offerNameString;
        fragment.merchantNameString = merchantNameString;
        fragment.offerCode = offerCode;
        fragment.companyID = companyId;
        fragment.productID = productId;
        fragment.mProductDetalisString = mProductDetalis;
        fragment.mOfferDetilsString = mOfferDetils;
        fragment.imageCount = mImageCount;
        fragment.promotionURL = url;
        fragment.location = locationValue;
        return fragment;
    }


    public static BottomSheetFragment newInstance(String offerNameString, String merchantNameString,
                                                  String offerCode, int companyId, int productId,
                                                  String mProductDetalis, String mOfferDetils,
                                                  int mImageCount, String url) {
        BottomSheetFragment fragment = new BottomSheetFragment();
        fragment.offerNameString = offerNameString;
        fragment.merchantNameString = merchantNameString;
        fragment.offerCode = offerCode;
        fragment.companyID = companyId;
        fragment.productID = productId;
        fragment.mProductDetalisString = mProductDetalis;
        fragment.mOfferDetilsString = mOfferDetils;
        fragment.imageCount = mImageCount;
        fragment.promotionURL = url;

        return fragment;
    }


    @Override
    public void setupDialog(Dialog dialog, int style) {

        View contentView = View.inflate(getContext(), R.layout.fragment_bottom_sheet, null);
        dialog.setContentView(contentView);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View contentView = View.inflate(getContext(), R.layout.fragment_bottom_sheet, null);
        offerName = (TextView) contentView.findViewById(R.id.tv_offerName);
        merchantName = (TextView) contentView.findViewById(R.id.tv_merchantName);
        // imageViewOffer = (ImageView) contentView.findViewById(R.id.image_offer);
        productDetails = (TextView) contentView.findViewById(R.id.tv_productDetails);
        offerDetails = (TextView) contentView.findViewById(R.id.tv_offerDetails);

        dotsLayout = (LinearLayout) contentView.findViewById(R.id.dots);
        productDetails.setText(mProductDetalisString);
        offerDetails.setText(mOfferDetilsString);
        offerName.setText(String.valueOf(offerNameString));
        merchantName.setText(String.valueOf(merchantNameString));
        locationText=(TextView)contentView.findViewById(R.id.tv_offerAddress) ;
        locationText.setText(String.valueOf(location));
        mViewpager = (ViewPager) contentView.findViewById(R.id.container);
        dots = new ArrayList<>();
        NUM_PAGES = imageCount;


        buttonFab = (FloatingActionButton) contentView.findViewById(R.id.fab_call);
        buttonFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {

                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "09612336699"));
                    startActivity(intent);
                    //   startActivity(intent);
                }

            }
        });


        LinearLayout linearLayoutGetCode = (LinearLayout) contentView.findViewById(R.id.linear_getCode);

        LinearLayout linearLayoutViewCode = (LinearLayout) contentView.findViewById(R.id.linear_viewCode);
        LinearLayout linearLayoutVisitSite = (LinearLayout) contentView.findViewById(R.id.linear_getWebsite);

        if (String.valueOf(promotionURL).length() > 5) {
            Log.e("myCheck", "onCreateView: " + String.valueOf(promotionURL));
            linearLayoutVisitSite.setVisibility(View.VISIBLE);
        } else {

        }

        linearLayoutVisitSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(promotionURL).trim()));

                startActivity(browse);
            }
        });

        linearLayoutGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetCodeDilaog dialogGetCode = GetCodeDilaog.newInstance(offerCode, companyID, productID);
                dialogGetCode.show(getActivity().getFragmentManager(), "dialog");

            }
        });

        linearLayoutViewCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewCodeDialog dialogViewCode = ViewCodeDialog.newInstance(offerCode);
                dialogViewCode.show(getActivity().getFragmentManager(), "dialoag");
            }
        });

        mViewpager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return ShowImageFragment.newInstance(position, productID);
            }

            @Override
            public int getCount() {
                return imageCount;
            }
        });

        addDots(contentView);
        return contentView;


    }

    public void addDots(View view) {


        try {
            dotsLayout.removeAllViews();
            for (int i = 0; i < NUM_PAGES; i++) {
                ImageView dot = new ImageView(view.getContext());
                if (getResources() != null) {

                    dot.setImageDrawable(getResources().getDrawable(R.drawable.circle_inactive));
                }


                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        15,
                        15
                );
                params.setMargins(8, 0, 8, 0);


                dotsLayout.addView(dot, params);


                dots.add(dot);
            }

            mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    selectDot(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
            selectDot(0);

        } catch (Exception e) {

            Toast.makeText(view.getContext(), String.valueOf(e), Toast.LENGTH_SHORT).show();
        }


    }

    public void selectDot(int idx) {
        Resources res = getResources();
        for (int i = 0; i < NUM_PAGES; i++) {
            int drawableId = (i == idx) ? (R.drawable.circle_active) : (R.drawable.circle_inactive);
            Drawable drawable = res.getDrawable(drawableId);
            dots.get(i).setImageDrawable(drawable);
        }
    }
}
