package com.bdone.asus.bdvoucher;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.bdone.asus.bdvoucher.NetworkCall.RetrofitClient;
import com.bdone.asus.bdvoucher.NetworkCall.interfaces.HomeInterfaceForNetworkCall;
import com.bdone.asus.bdvoucher.NetworkCall.models.TopCategoryPayLoad;
import com.bdone.asus.bdvoucher.categoryWiseProducts.FragmentcategoryWiseProduct;
import com.bdone.asus.bdvoucher.homePage.FragmentHome;
import com.bdone.asus.bdvoucher.userAuth.SessionManager;
import com.bdone.asus.bdvoucher.userAuth.SignInActivity;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ActivityHome extends AppCompatActivity {

    List<TopCategoryPayLoad> categoryes = new ArrayList<>();
    boolean doubleBackToExitPressedOnce = false;
    TextView mUserName;
    TextView mEmail;
    private DrawerLayout mDrawerLayout;
    private Target[] mNavigationDrawerLogoTargets;
    private Retrofit retrofit;
    private HomeInterfaceForNetworkCall homeApiInterface;
    private SessionManager session;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        retrofit = RetrofitClient.getInstance(this);
        homeApiInterface = retrofit.create(HomeInterfaceForNetworkCall.class);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        session = new SessionManager(getApplicationContext());
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        //Toast.makeText(this,refreshedToken,Toast.LENGTH_LONG).show();
        FirebaseMessaging.getInstance().subscribeToTopic("bdVoucher");
        Log.e("token", "this: " + refreshedToken);
        // FirebaseCrash.report(new Exception("My first Android non-fatal error"));
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //  Window w = getWindow(); // in Activity's onCreate() for instance
            //  w.setFlags(WindowManager.LayoutParams.flag, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }
        ;
        View headerview = navigationView.getHeaderView(0);


        mUserName = (TextView) headerview.findViewById(R.id.profileName);
        mEmail = (TextView) headerview.findViewById(R.id.email);

        navigationView.setItemIconTintList(null);

        addHomeFragmentMain();
        addMenuOnNavigationView(navigationView.getMenu());

    }

    private void addMenuOnNavigationView(final Menu menu) {

        homeApiInterface.getCategories().enqueue(new Callback<List<TopCategoryPayLoad>>() {
            @Override
            public void onResponse(Call<List<TopCategoryPayLoad>> call, Response<List<TopCategoryPayLoad>> response) {

                if (response.isSuccessful()) {
                    for (TopCategoryPayLoad navigationDrawerCategoriesModel :
                            response.body()) {
                        // Log.d(TAG, "onResponse: " + navigationDrawerCategoriesModel);
                    }
                    categoryes = response.body();

                    final Menu navigationDrawerCategoriesMenus =
                            menu.addSubMenu("Category's");

                    final List<TopCategoryPayLoad> navigationDrawerCategoriesData =
                            response.body();
                    mNavigationDrawerLogoTargets = new Target[response.body().size()];
                    int index = 0;

                    for (final TopCategoryPayLoad eachNavigationDrawerCategoryData :
                            navigationDrawerCategoriesData) {
                        final int finalIndex = index;
                        mNavigationDrawerLogoTargets[index] = new Target() {
                            @Override
                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                //   Log.d(TAG, "onBitmapLoaded: " + from.name());
                                navigationDrawerCategoriesMenus.add(Menu.NONE, finalIndex,
                                        eachNavigationDrawerCategoryData.getCategoryId(),
                                        eachNavigationDrawerCategoryData.getCategoryNameEng())
                                        .setIcon(new BitmapDrawable(getResources(), bitmap));
                                MenuItem popularCategoryMenuItem = menu.getItem(menu.size() - 1);
                                popularCategoryMenuItem.setTitle(popularCategoryMenuItem.getTitle());
                            }

                            @Override
                            public void onBitmapFailed(Drawable errorDrawable) {
                                //  Log.e(TAG, "onBitmapFailed: ");
                            }

                            @Override
                            public void onPrepareLoad(Drawable placeHolderDrawable) {
                                //   Log.d(TAG, "onPrepareLoad: ");
                            }
                        };
                        // Log.e("checkIndex", "onResponse: "+String );
                        Picasso.with(ActivityHome.this)
                                .load("http://api.bdvoucher.com/images/CatIconNav/" + String.valueOf(eachNavigationDrawerCategoryData.getCategoryId()) + ".png")
                                .into(mNavigationDrawerLogoTargets[index++]);


                    }


                } else {


                }
            }

            @Override
            public void onFailure(Call<List<TopCategoryPayLoad>> call, Throwable t) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);


        this.menu = menu;
        Log.e("MenuCheck", "onCreateOptionsMenu: " + String.valueOf(session.isLoggedIn()));
        if (session.isLoggedIn()) {
            menu.findItem(R.id.loginFragment).setTitle("Log out");

        } else {

/*            Log.d("login - > ", "logout: ");
            String stringtoset = "লগইন করুন / রেজিস্ট্রেশন করুন";
            navHeaderText.setText(stringtoset);
            setTitleifLogout();
            imageView.setImageBitmap(null);*/
        }

        return true;

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
/*        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM:
             //   menu.findItem(R.id.menu_night_mode_system).setChecked(true);
                break;
            case AppCompatDelegate.MODE_NIGHT_AUTO:
               // menu.findItem(R.id.menu_night_mode_auto).setChecked(true);
                break;
            case AppCompatDelegate.MODE_NIGHT_YES:
             //   menu.findItem(R.id.menu_night_mode_night).setChecked(true);
                break;
            case AppCompatDelegate.MODE_NIGHT_NO:
             //   menu.findItem(R.id.menu_night_mode_day).setChecked(true);
                break;
        }*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;


        }


        int id = item.getItemId();
        if (id == R.id.about) {
            startActivity(new Intent(this, About.class));
        }
        if (id == R.id.share) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "https://play.google.com/store/apps/details?id=com.bdone.asus.bdvoucher";
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Here is Bdvoucher Android Apps Download Link");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share Bdvoucher"));
        }
        if (id == R.id.loginFragment) {


            Log.e("MenuCheckClickkkkk", "onCreateOptionsMenu: " + String.valueOf(session.isLoggedIn()));
            if (session.isLoggedIn()) {
                Log.d("login1", "onOptionsItemSelected: loggedin");
                session.logoutUser(getApplicationContext());

                Toast.makeText(getBaseContext(), "Successfully Log Out", Toast.LENGTH_SHORT).show();
                menu.findItem(R.id.loginFragment).setTitle("Log In");
                mUserName.setText("User Name");
                mEmail.setText("User Mail Address");

                /*String stringtoset = "লগইন করুন / রেজিস্ট্রেশন করুন";
                navHeaderText.setText(stringtoset);*/


            } else {

                /*Intent intent=new Intent(HomeActivity.this,SignInActivity.class);
                startActivity(intent);*/
                Log.e("login2", "onOptionsItemSelected: not log in");
                Intent intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                //     startActivity(new Intent(ActivityHome.this, SignInActivity.class));

            }

        }

        return super.onOptionsItemSelected(item);
    }

    private void setNightMode(@AppCompatDelegate.NightMode int nightMode) {
        AppCompatDelegate.setDefaultNightMode(nightMode);

        if (Build.VERSION.SDK_INT >= 11) {
            recreate();
        }
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {


                        if (String.valueOf(menuItem.getTitle()).equals("Home")) {
                     /*       Bundle extras = getIntent().getExtras();

                            getIntent().removeExtra("notificationTyp");
                            getIntent().removeExtra("isOrder");
*/
                            Fragment fragment = new FragmentHome();
                            FragmentManager fm = getSupportFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.replace(R.id.containerHome, fragment);
                            fm.popBackStackImmediate();

                            ft.commit();


                        } else {

                            Fragment fragment = FragmentcategoryWiseProduct.newInstance(categoryes.get(menuItem.getItemId()).getCategoryNameEng(), categoryes.get(menuItem.getItemId()).getCategoryId());
                            FragmentManager fm = getSupportFragmentManager();
                            fm.popBackStackImmediate();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.addToBackStack("hlw");
                            ft.replace(R.id.containerHome, fragment);
                            ft.commit();
                        }
                        menuItem.setChecked(false);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }


    public void addHomeFragmentMain() {

        Fragment fragment = new FragmentHome();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.containerHome, fragment);
        ft.commit();


    }


    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT) || mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {

            closeLeftDrawer();
        } else {
            if (getFragmentManager().getBackStackEntryCount() > 0) {
                getFragmentManager().popBackStack();

                View view = getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    //   Toast.makeText(getApplicationContext(),"Back Pressed for Fragment",Toast.LENGTH_LONG).show();
                }

            } else {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.containerHome);
                if (fragment instanceof FragmentHome) {
                    if (doubleBackToExitPressedOnce) {
                        super.onBackPressed();
                        return;
                    }

                    this.doubleBackToExitPressedOnce = true;
                    Toast.makeText(this, "Press again to Exit", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleBackToExitPressedOnce = false;
                        }
                    }, 2000);
                } else {
                    super.onBackPressed();
                }
            }
        }
    }

    public void closeLeftDrawer() {
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (session.isLoggedIn()) {
            //  setUserImage();
            mUserName.setText(session.getUserDetails().get("user"));
            mEmail.setText(session.getUserDetails().get("email"));
        }

        invalidateOptionsMenu();
    }


}
