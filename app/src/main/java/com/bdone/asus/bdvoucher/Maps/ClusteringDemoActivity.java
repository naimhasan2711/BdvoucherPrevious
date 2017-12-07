/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bdone.asus.bdvoucher.Maps;

import com.bdone.asus.bdvoucher.NetworkCall.models.MyItemForMap;
import com.bdone.asus.bdvoucher.homePage.bottomSheet.BottomSheetFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple activity demonstrating ClusterManager.
 */
public class ClusteringDemoActivity extends BaseDemoActivity {
    private ClusterManager<MyItem> mClusterManager;
    private FirebaseDatabase database;
    private DatabaseReference myRef = null;

    @Override
    protected void startDemo() {
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(23.8103, 90.4125), 12));

        mClusterManager = new ClusterManager<MyItem>(this, getMap());
        getMap().setOnCameraIdleListener(mClusterManager);


        mClusterManager.setOnClusterItemInfoWindowClickListener(new ClusterManager.OnClusterItemInfoWindowClickListener<MyItem>() {
            @Override
            public void onClusterItemInfoWindowClick(MyItem myItem) {

/*
*
*     fragment.offerNameString = offerNameString;
        fragment.merchantNameString = merchantNameString;
        fragment.offerCode = offerCode;
        fragment.companyID = companyId;
        fragment.productID = productId;
        fragment.mProductDetalisString = mProductDetalis;
        fragment.mOfferDetilsString = mOfferDetils;
        fragment.imageCount = mImageCount;
        fragment.promotionURL = url;
* */
                BottomSheetFragment bottomSheetDialogFragment = BottomSheetFragment.newInstance(myItem.getTitle(),
                        myItem.getCompanyName(),
                        myItem.getProductCode(),
                        myItem.getCompanyId(),
                        myItem.getId(),
                        myItem.getProductDetails(),
                        myItem.getOfferDetails(),
                        myItem.getImageCount(),
                        myItem.getPromotionalUrl());
                bottomSheetDialogFragment.show(getSupportFragmentManager(), "tag");
            }
        });
        getMap().setOnInfoWindowClickListener(mClusterManager);

        //    FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        database = FirebaseDatabase.getInstance();

        myRef = database.getReference("locations");

        myRef.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {


                                            GenericTypeIndicator<List<MyItemForMap>> genericTypeIndicator = new GenericTypeIndicator<List<MyItemForMap>>() {
                                            };

                                            List<MyItemForMap> items = dataSnapshot.getValue(genericTypeIndicator);
                                            List<MyItem> myItems = new ArrayList<MyItem>();
                                            for (MyItemForMap oka : items
                                                    ) {

                                               /*
                                               * int CompanyId,
                  String CompanyName,
                  int Id,
                  int ImageCount,
                  String OfferDetails,
                  String ProductCode,
                  String ProductDetails,
                  String PromotionalUrl*/
                                                myItems.add(new MyItem(oka.getLat(), oka.getLng(), oka.getTitle(), oka.getSnippet(), oka.getCompanyId(), oka.getCompanyName()
                                                        , oka.getId(), oka.getImageCount(), oka.getOfferDetails(), oka.getProductCode(), oka.getProductDetails()
                                                        , oka.getPromotionalUrl()));

                                            }
                                            mClusterManager.addItems(myItems);

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    }
        );


    }

   /* private void readItems() throws JSONException {
        InputStream inputStream = getResources().openRawResource(R.raw.radar_search);
        List<MyItem> items = new MyItemReader().read(inputStream);
        mClusterManager.addItems(items);
    }*/
}