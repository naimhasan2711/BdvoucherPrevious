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

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class MyItem implements ClusterItem {
    private final LatLng mPosition;
    private String mTitle;
    private String mSnippet;

    int CompanyId;
    String CompanyName;
    int Id;
    int ImageCount;
    String OfferDetails;
    String ProductCode;
    String ProductDetails;
    String PromotionalUrl;


    public MyItem(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
        mTitle = null;
        mSnippet = null;
    }

    public MyItem(double lat, double lng, String title, String snippet, int CompanyIda,
                  String CompanyNamee,
                  int Idd,
                  int ImageCountr,
                  String OfferDetailss,
                  String ProductCodee,
                  String ProductDetailss,
                  String PromotionalUrll) {
        mPosition = new LatLng(lat, lng);
        mTitle = title;
        mSnippet = snippet;
        CompanyId = CompanyIda;
        CompanyName = CompanyNamee;
        Id = Idd;

        ImageCount = ImageCountr;
        OfferDetails = OfferDetailss;
        ProductCode = ProductCodee;
        ProductDetails = ProductDetailss;
        PromotionalUrl = PromotionalUrll;


    }

    public int getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(int companyId) {
        CompanyId = companyId;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getImageCount() {
        return ImageCount;
    }

    public void setImageCount(int imageCount) {
        ImageCount = imageCount;
    }

    public String getOfferDetails() {
        return OfferDetails;
    }

    public void setOfferDetails(String offerDetails) {
        OfferDetails = offerDetails;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getProductDetails() {
        return ProductDetails;
    }

    public void setProductDetails(String productDetails) {
        ProductDetails = productDetails;
    }

    public String getPromotionalUrl() {
        return PromotionalUrl;
    }

    public void setPromotionalUrl(String promotionalUrl) {
        PromotionalUrl = promotionalUrl;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getSnippet() {
        return mSnippet;
    }

    /**
     * Set the title of the marker
     *
     * @param title string to be set as title
     */
    public void setTitle(String title) {
        mTitle = title;
    }

    /**
     * Set the description of the marker
     *
     * @param snippet string to be set as snippet
     */
    public void setSnippet(String snippet) {
        mSnippet = snippet;
    }
}
