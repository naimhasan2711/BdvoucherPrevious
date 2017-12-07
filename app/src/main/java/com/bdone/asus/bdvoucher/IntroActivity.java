package com.bdone.asus.bdvoucher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bdone.asus.bdvoucher.introPage.DemoParallaxAdapter;
import com.bdone.asus.bdvoucher.introPage.Intro_Second;
import com.bdone.asus.bdvoucher.introPage.Intro_Third;
import com.bdone.asus.bdvoucher.introPage.Intro_first;
import com.bdone.asus.bdvoucher.introPage.Intro_four;
import com.xgc1986.parallaxPagerTransformer.ParallaxPagerTransformer;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    ViewPager mPager;
    DemoParallaxAdapter mAdapter;
    private LinearLayout dotsLayout;
    private List<ImageView> dots;

    /*appy geek*/

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dotsLayout = (LinearLayout) findViewById(R.id.dots);
        dots = new ArrayList<>();

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);

       // Toast.makeText(this, String.valueOf(sharedPreferences.getInt("isFirstOpen", 0)), Toast.LENGTH_LONG).show();
        int trackingFlag = sharedPreferences.getInt("isFirstOpen", 0);
        if (trackingFlag == 1) {
            Intent intent = new Intent(this, LoadingActivity.class);
            startActivity(intent);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        mPager = (ViewPager) findViewById(R.id.intro_viewpager);
        mPager.setBackgroundColor(0xFF000000);

        ParallaxPagerTransformer pt = new ParallaxPagerTransformer((R.id.image));


        pt.setSpeed(0.40f);
        mPager.setPageTransformer(true, pt);

        mAdapter = new DemoParallaxAdapter(getSupportFragmentManager());
        mAdapter.setPager(mPager); //only for this transformer

        Bundle bNina = new Bundle();
        bNina.putInt("image", R.drawable.one);
        bNina.putString("name", "Nina");
        Intro_first pfNina = new Intro_first();
        pfNina.setArguments(bNina);

        Bundle bNiju = new Bundle();
        bNiju.putInt("image", R.drawable.two);
        bNiju.putString("name", "Ninu Junior");
        Intro_Second pfNiju = new Intro_Second();
        pfNiju.setArguments(bNiju);

        Bundle bYuki = new Bundle();
        bYuki.putInt("image", R.drawable.three);
        bYuki.putString("name", "Yuki");
        Intro_Third pfYuki = new Intro_Third();
        pfYuki.setArguments(bYuki);

        Bundle bKero = new Bundle();
        bKero.putInt("image", R.drawable.four);
        bKero.putString("name", "Kero");
Intro_four pfKero = new Intro_four();
        pfKero.setArguments(bKero);

        mAdapter.add(pfNina);
        mAdapter.add(pfNiju);
        mAdapter.add(pfYuki);
        mAdapter.add(pfKero);
        mPager.setAdapter(mAdapter);

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().show();
        }
        addDots(this);
    }


    public void addDots(Context mContext) {


        try {
            dotsLayout.removeAllViews();
            for (int i = 0; i < 4; i++) {
                ImageView dot = new ImageView(mContext);
                if (getResources() != null) {
                 /*   ContextCompat.getDrawable(this, R.drawable.your_drawable)*/
                    dot.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.circle_inactive));
                }


                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        15,
                        15
                );
                params.setMargins(8, 0, 8, 0);


                dotsLayout.addView(dot, params);


                dots.add(dot);
            }

            mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

            Toast.makeText(mContext, "be careful on your activity", Toast.LENGTH_LONG).show();
        }


    }

    public void selectDot(int idx) {
        Resources res = getResources();
        for (int i = 0; i < 4; i++) {
            int drawableId = (i == idx) ? (R.drawable.circle_active) : (R.drawable.circle_inactive);
            Drawable drawable = res.getDrawable(drawableId);
            dots.get(i).setImageDrawable(drawable);
        }
    }

}
