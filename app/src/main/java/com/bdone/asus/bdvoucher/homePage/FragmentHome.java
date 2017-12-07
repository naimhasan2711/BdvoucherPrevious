package com.bdone.asus.bdvoucher.homePage;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bdone.asus.bdvoucher.ActivityHome;
import com.bdone.asus.bdvoucher.Maps.ClusteringDemoActivity;
import com.bdone.asus.bdvoucher.NetworkCall.RetrofitClient;
import com.bdone.asus.bdvoucher.NetworkCall.interfaces.HomeInterfaceForNetworkCall;
import com.bdone.asus.bdvoucher.NetworkCall.interfaces.InterfaceCategoryWiseProducts;
import com.bdone.asus.bdvoucher.NetworkCall.models.BannerModel;
import com.bdone.asus.bdvoucher.NetworkCall.models.CategoryWiseSendBody;
import com.bdone.asus.bdvoucher.NetworkCall.models.HomeBlockModel;
import com.bdone.asus.bdvoucher.NetworkCall.models.RecomandedProducts;
import com.bdone.asus.bdvoucher.NetworkCall.models.TopCategoryPayLoad;
import com.bdone.asus.bdvoucher.R;
import com.bdone.asus.bdvoucher.Search.SearchFragment;
import com.bdone.asus.bdvoucher.categoryWiseProducts.FragmentcategoryWiseProduct;
import com.bdone.asus.bdvoucher.homePage.adapters.HomeBlockRecylerAdapter;
import com.bdone.asus.bdvoucher.homePage.adapters.HomeCategoryRecylerAdapter;
import com.bdone.asus.bdvoucher.homePage.adapters.HomeRecommendedRecylerAdapter;
import com.bdone.asus.bdvoucher.homePage.bottomSheet.BottomSheetFragment;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Rasel on 1/17/2017.
 */

public class FragmentHome extends Fragment {


    Retrofit retrofit;
    List<TopCategoryPayLoad> categories;
    InterfaceCategoryWiseProducts interfaceCategoryWiseProducts;
    private SliderLayout mDemoSlider;
    private TextView textViewMapComing;
    private RecyclerView recyclerViewCategory;
    // private ImageView imageViewTrophy;
    private RecyclerView recyclerViewRecommendedVoucher;
    private String TAG = "homeLogs";
    private HomeInterfaceForNetworkCall homeApiInterface;
    private RecyclerView recyclerViewHomeBlocks;
    private RecyclerView recyclerViewLatestProducts;
    private RecyclerView recyclerViewEndingSoonHome;
    private FirebaseDatabase database;
    private DatabaseReference myRef = null;
    List<BannerModel> taskDesList;

    public FragmentHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((ActivityHome) getActivity()).setSupportActionBar(toolbar);
        categories = new ArrayList<>();
        textViewMapComing = (TextView) view.findViewById(R.id.tv_more);
        recyclerViewCategory = (RecyclerView) view.findViewById(R.id.recyclerViewCategory);
        recyclerViewRecommendedVoucher = (RecyclerView) view.findViewById(R.id.recyclerViewRecomandedVoucher);
        recyclerViewHomeBlocks = (RecyclerView) view.findViewById(R.id.homeblocks);
        recyclerViewLatestProducts = (RecyclerView) view.findViewById(R.id.recylerViewEndingSoon);
        recyclerViewEndingSoonHome = (RecyclerView) view.findViewById(R.id.recyclerViewEndingSoon);
        retrofit = RetrofitClient.getInstance(getActivity());
        homeApiInterface = retrofit.create(HomeInterfaceForNetworkCall.class);
        interfaceCategoryWiseProducts = retrofit.create(InterfaceCategoryWiseProducts.class);
        setCategoryRecylerView(recyclerViewCategory);
        setRecommendedRecyclerView(recyclerViewRecommendedVoucher, 1);
        setRecommendedRecyclerView(recyclerViewLatestProducts, 0);
        setEndingSoonRecyclerView(recyclerViewEndingSoonHome, 3);
        setHomeBlockRecylerViewDatas(recyclerViewHomeBlocks);
        final ActionBar ab = ((ActivityHome) getActivity()).getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        ab.setTitle(" ");
        ab.setDisplayHomeAsUpEnabled(true);

      //  FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        database = FirebaseDatabase.getInstance();

        myRef = database.getReference("banners");


        mDemoSlider = (SliderLayout) view.findViewById(R.id.slider);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                GenericTypeIndicator<List<BannerModel>> genericTypeIndicator = new GenericTypeIndicator<List<BannerModel>>() {
                };

                taskDesList = dataSnapshot.getValue(genericTypeIndicator);

                for (final BannerModel bannerModel : taskDesList
                        ) {

                    DefaultSliderView textSliderView = new DefaultSliderView(getActivity());
                    // initialize a SliderLayout
                    textSliderView
                            .description("des")
                            .image(bannerModel.getBannerImageLink())
                            .setScaleType(BaseSliderView.ScaleType.Fit);
                    textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {
                            Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(bannerModel.getBannerClickLink()).trim()));

                            startActivity(browse);
                        }
                    });

                    mDemoSlider.addSlider(textSliderView);


                }




              /*  for(int i=0;i<taskDesList.size();i++){
                    Log.e(TAG, "onDataChange: ", );

                    Toast.makeText(getActivity(),"TaskTitle = "+taskDesList.get(i).getTaskTitle(),Toast.LENGTH_LONG).show();
                }*/

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        textViewMapComing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent openMap = new Intent(getActivity(), ClusteringDemoActivity.class);
                startActivity(openMap);
            }
        });

        ImageView imageView = (ImageView) view.findViewById(R.id.search);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchFragment.class));
            }
        });
        return view;

    }


    private void setCategoryRecylerView(final RecyclerView categoryRecylerView) {


        homeApiInterface.getCategories().enqueue(new Callback<List<TopCategoryPayLoad>>() {
            @Override
            public void onResponse(Call<List<TopCategoryPayLoad>> call, Response<List<TopCategoryPayLoad>> response) {
                if (response.isSuccessful()) {
                    Log.e(TAG, "onResponse: " + String.valueOf(response.body()));
                    categories = response.body();

                    HomeCategoryRecylerAdapter homeCategoryRecylerAdapter = new HomeCategoryRecylerAdapter(categories);
                    homeCategoryRecylerAdapter.getClickInterface(new HomeCategoryRecylerAdapter.RvClickInterface() {
                        @Override
                        public void clickListener(int postion, View view) {
                            //    Toast.makeText(getActivity(), String.valueOf(categories.get(postion).getCategoryNameEng()), Toast.LENGTH_LONG).show();
                            Fragment fragment = FragmentcategoryWiseProduct.newInstance(categories.get(postion).getCategoryNameEng(), categories.get(postion).getCategoryId());
                            FragmentManager fm = getActivity().getSupportFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.addToBackStack("hlw");
                            ft.replace(R.id.containerHome, fragment);
                            ft.commit();
                        }
                    });
                    categoryRecylerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

                    categoryRecylerView.setAdapter(homeCategoryRecylerAdapter);
                    categoryRecylerView.setAdapter(new ScaleInAnimationAdapter(homeCategoryRecylerAdapter));
                    categoryRecylerView.setNestedScrollingEnabled(false);
                }
            }

            @Override
            public void onFailure(Call<List<TopCategoryPayLoad>> call, Throwable t) {

                if (getView()!=null){

                    Snackbar.make(getView(), "Cannot connect to the server",Snackbar.LENGTH_INDEFINITE)
                            .setAction("CLOSE", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {


                                }
                            })
                            .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))

                            .show();


                }

            }
        });

        Log.e(TAG, "setCategoryRecylerView: " + String.valueOf(categories));

    }


    private void setRecommendedRecyclerView(final RecyclerView recommendedRecyclerView, final int trackID) {
        homeApiInterface.getRecomandedProducts(trackID).enqueue(new Callback<List<RecomandedProducts>>() {
            @Override
            public void onResponse(Call<List<RecomandedProducts>> call, final Response<List<RecomandedProducts>> response) {


                if (response.isSuccessful()) {
                    HomeRecommendedRecylerAdapter homeRecommendedRecylerAdapter = new HomeRecommendedRecylerAdapter(response.body(), trackID);
                    homeRecommendedRecylerAdapter.onClick(new HomeRecommendedRecylerAdapter.ClickListenerDataPassInterface() {
                        @Override
                        public void clickListenerMethod(View view, int position) {
                            //  Toast.makeText(view.getContext(), String.valueOf(getRecommendedVoucherList().get(position).getSortDetails()), Toast.LENGTH_LONG).show();

                            BottomSheetFragment bottomSheetDialogFragment = BottomSheetFragment.newInstance(response.body().get(position).getProductTitle(), response.body().get(position).getCompanyName(), response.body().get(position).getProductCode(), response.body().get(position).getCompanyId(), response.body().get(position).getId(), response.body().get(position).getProductDetails(), response.body().get(position).getOfferDetails(), response.body().get(position).getImageCount(), response.body().get(position).getPromotionalUrl(),response.body().get(position).getOfferAddress());
                            bottomSheetDialogFragment.show(getChildFragmentManager(), "tag");

                        }
                    });

                    recommendedRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                    recommendedRecyclerView.setAdapter(homeRecommendedRecylerAdapter);
                    recommendedRecyclerView.setAdapter(new ScaleInAnimationAdapter(homeRecommendedRecylerAdapter));
                    recommendedRecyclerView.setNestedScrollingEnabled(false);
                }
            }

            @Override
            public void onFailure(Call<List<RecomandedProducts>> call, Throwable t) {

            }
        });


    }


    private void setEndingSoonRecyclerView(final RecyclerView recommendedRecyclerView, final int trackID) {



   /*     homeApiInterface.getRecomandedProducts(trackID).enqueue(new Callback<List<RecomandedProducts>>() {
            @Override
            public void onResponse(Call<List<RecomandedProducts>> call, final Response<List<RecomandedProducts>> response) {


                if (response.isSuccessful()) {
                    HomeRecommendedRecylerAdapter homeRecommendedRecylerAdapter = new HomeRecommendedRecylerAdapter(response.body(), trackID);
                    homeRecommendedRecylerAdapter.onClick(new HomeRecommendedRecylerAdapter.ClickListenerDataPassInterface() {
                        @Override
                        public void clickListenerMethod(View view, int position) {
                            //  Toast.makeText(view.getContext(), String.valueOf(getRecommendedVoucherList().get(position).getSortDetails()), Toast.LENGTH_LONG).show();

                            BottomSheetFragment bottomSheetDialogFragment = BottomSheetFragment.newInstance(response.body().get(position).getProductTitle(), response.body().get(position).getCompanyName(), response.body().get(position).getId());
                            bottomSheetDialogFragment.show(getChildFragmentManager(), "tag");

                        }
                    });

                    recommendedRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                    recommendedRecyclerView.setAdapter(homeRecommendedRecylerAdapter);
                    recommendedRecyclerView.setNestedScrollingEnabled(false);
                }
            }

            @Override
            public void onFailure(Call<List<RecomandedProducts>> call, Throwable t) {

            }dd
        });*/
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String formattedDate = formatter.format(date);

        interfaceCategoryWiseProducts.getCategoryWiseProduct(new CategoryWiseSendBody(-1, formattedDate, -1, -1, 0, 60)).enqueue(new Callback<List<RecomandedProducts>>() {
            @Override
            public void onResponse(Call<List<RecomandedProducts>> call, final Response<List<RecomandedProducts>> response) {

                HomeRecommendedRecylerAdapter homeRecommendedRecylerAdapter = new HomeRecommendedRecylerAdapter(response.body(), trackID);
                homeRecommendedRecylerAdapter.onClick(new HomeRecommendedRecylerAdapter.ClickListenerDataPassInterface() {
                    @Override
                    public void clickListenerMethod(View view, int position) {
                        //  Toast.makeText(view.getContext(), String.valueOf(getRecommendedVoucherList().get(position).getSortDetails()), Toast.LENGTH_LONG).show();

                        BottomSheetFragment bottomSheetDialogFragment = BottomSheetFragment.newInstance(response.body().get(position).getProductTitle(), response.body().get(position).getCompanyName(), response.body().get(position).getProductCode(), response.body().get(position).getCompanyId(), response.body().get(position).getId(), response.body().get(position).getProductDetails(), response.body().get(position).getOfferDetails(), response.body().get(position).getImageCount(), response.body().get(position).getPromotionalUrl(),response.body().get(position).getOfferAddress()  );
                        bottomSheetDialogFragment.show(getChildFragmentManager(), "tag");

                    }
                });

                recommendedRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                recommendedRecyclerView.setAdapter(homeRecommendedRecylerAdapter);
                recommendedRecyclerView.setAdapter(new ScaleInAnimationAdapter(homeRecommendedRecylerAdapter));
                recommendedRecyclerView.setNestedScrollingEnabled(false);

            }

            @Override
            public void onFailure(Call<List<RecomandedProducts>> call, Throwable t) {

            }
        });


    }


    private void setHomeBlockRecylerViewDatas(final RecyclerView homeBlockRecylerView) {


        homeApiInterface.getHomeBlocks().enqueue(new Callback<List<HomeBlockModel>>() {
            @Override
            public void onResponse(Call<List<HomeBlockModel>> call, Response<List<HomeBlockModel>> response) {

                if (response.isSuccessful()) {

                    Log.e(TAG, "homeBlocks: " + String.valueOf(response.body()));
                    homeBlockRecylerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    HomeBlockRecylerAdapter homeBlockRecylerAdapter = new HomeBlockRecylerAdapter(response.body());
                    homeBlockRecylerView.setAdapter(homeBlockRecylerAdapter);
                    homeBlockRecylerView.setAdapter(new ScaleInAnimationAdapter(homeBlockRecylerAdapter));
                    homeBlockRecylerView.setNestedScrollingEnabled(false);

                }


            }

            @Override
            public void onFailure(Call<List<HomeBlockModel>> call, Throwable t) {

            }
        });

    }

}




