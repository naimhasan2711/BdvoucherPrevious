package com.bdone.asus.bdvoucher;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bdone.asus.bdvoucher.NetworkCall.RetrofitClient;
import com.bdone.asus.bdvoucher.NetworkCall.interfaces.HomeInterfaceForNetworkCall;
import com.bdone.asus.bdvoucher.NetworkCall.models.TopCategoryPayLoad;
import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoadingActivity extends AppCompatActivity {

    private static final String TAG = "LoadingActivity";
    ProgressBar progressBar;
    private Retrofit retrofit;
    private HomeInterfaceForNetworkCall homeApiInterface;
    private ImageView imageViewLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_loading);
        imageViewLogo = (ImageView) findViewById(R.id.image);
        retrofit = RetrofitClient.getInstance(this);
        homeApiInterface = retrofit.create(HomeInterfaceForNetworkCall.class);


        Glide
                .with(this)
                .load(R.drawable.logo)

                .thumbnail(0.1f)
                .into(imageViewLogo);


      /*  Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts/sareeka.ttf");
        Typeface tf2 = Typeface.createFromAsset(this.getAssets(),
                "fonts/white.otf");


        TextView tv = (TextView) findViewById(R.id.welcome_tv);
        tv.setTypeface(tf);*/
      /*  TextView tv2 = (TextView)findViewById(R.id.welcome_tv2);
        tv2.setTypeface(tf2);*/


        //addLogoAnimation();
        //    int secondsDelayed = 1;
        /*new Handler().postDelayed(new Runnable() {
            public void run() {
                getAllNetworkData();
                finish();
            }
        }, secondsDelayed * 1000);*/

      /*  setStatusBarBackgroundWhite();*/
        getNavMenu();

    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    Log.e(TAG, "instance initializer: ", exception);
                } finally {
                    Intent intent = new Intent(getApplicationContext(), ActivityHome.class);
                    startActivity(intent);
                }
            }
        }.start();

    }


    public void getNavMenu() {


        homeApiInterface.getCategories().enqueue(new Callback<List<TopCategoryPayLoad>>() {
            @Override
            public void onResponse(Call<List<TopCategoryPayLoad>> call, Response<List<TopCategoryPayLoad>> response) {

                if (response.isSuccessful()) {


                } else {


                }
            }

            @Override
            public void onFailure(Call<List<TopCategoryPayLoad>> call, Throwable t) {

            }
        });

    }

  /*  private void setStatusBarBackgroundWhite() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window currentWindow = getWindow();
            currentWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            currentWindow.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            currentWindow.setStatusBarColor(ContextCompat.getColor(this, R.color.colorWhite));
        }
    }*/

}