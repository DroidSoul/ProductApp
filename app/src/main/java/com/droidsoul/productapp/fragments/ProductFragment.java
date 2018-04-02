package com.droidsoul.productapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.droidsoul.productapp.R;
import com.droidsoul.productapp.models.Product;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by bear&bear on 3/31/2018.
 */

public class ProductFragment extends Fragment {

    private String title;
    private Product product;

    // newInstance constructor for creating fragment with arguments
    public static ProductFragment newInstance(Product product, String title) {
        ProductFragment fragmentFirst = new ProductFragment();
        Bundle args = new Bundle();
        args.putParcelable("product", Parcels.wrap(product));
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        product = Parcels.unwrap(getArguments().getParcelable("product"));
        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        TextView tvPrice = (TextView) view.findViewById(R.id.tvPrice);
        ImageView ivProductImage = view.findViewById(R.id.ivProductImage);
        tvName.setText(product.getProductName());
        tvPrice.setText(product.getPrice());
        Picasso.with(getContext()).load(product.getImagePath()).transform(new RoundedCornersTransformation(10, 10)).into(ivProductImage);
        return view;
    }


}
