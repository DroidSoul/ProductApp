package com.droidsoul.productapp.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Parcel;
import android.os.Parcelable;

import com.loopj.android.http.RequestParams;
import java.util.ArrayList;

/**
 * Created by bear&bear on 3/30/2018.
 */

public class Product implements Parcelable{

    private String productID;
    private String productName;
    private String shortDescription = "";
    private String longDescription = "";
    private String price;
    private double reviewRating;
    private int reviewCount;
    private boolean inStock;
    private String imagePath;

    public static Product fromJSON(JSONObject jsonObject) {
        Product product = new Product();
        try {
            product.setProductID(jsonObject.getString("productId"));
            product.setProductName(jsonObject.getString("productName"));
            if (jsonObject.getString("shortDescription") != null) {
                product.setShortDescription(jsonObject.getString("shortDescription"));
            }
            if (jsonObject.getString("longDescription") != null) {
                product.setLongDescription(jsonObject.getString("longDescription"));
            }
            product.setPrice(jsonObject.getString("price"));
            product.setImagePath(jsonObject.getString("productImage"));
            product.setReviewRating(jsonObject.getDouble("reviewRating"));
            product.setReviewCount(jsonObject.getInt("reviewCount"));
            product.setInStock(jsonObject.getBoolean("inStock"));
        } catch(JSONException e) {
            e.printStackTrace();
        }
        return product;
    }

    public static ArrayList<Product> fromJSONArray(JSONArray jsonArray) {
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                products.add(Product.fromJSON(jsonArray.getJSONObject(i)));
            } catch(JSONException e) {
                e.printStackTrace();
            }
        }
        return products;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        if (shortDescription != null) {this.shortDescription = shortDescription;}
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        if (longDescription != null) {this.longDescription = longDescription;}
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public double getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(double reviewRating) {
        this.reviewRating = reviewRating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.productID);
        dest.writeString(this.productName);
        dest.writeString(this.shortDescription);
        dest.writeString(this.longDescription);
        dest.writeString(this.price);
        dest.writeDouble(this.reviewRating);
        dest.writeInt(this.reviewCount);
        dest.writeByte(this.inStock ? (byte) 1 : (byte) 0);
        dest.writeString(this.imagePath);
    }

    public Product() {
    }

    protected Product(Parcel in) {
        this.productID = in.readString();
        this.productName = in.readString();
        this.shortDescription = in.readString();
        this.longDescription = in.readString();
        this.price = in.readString();
        this.reviewRating = in.readDouble();
        this.reviewCount = in.readInt();
        this.inStock = in.readByte() != 0;
        this.imagePath = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
