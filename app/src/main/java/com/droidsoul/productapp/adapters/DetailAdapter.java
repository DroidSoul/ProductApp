package com.droidsoul.productapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.droidsoul.productapp.R;
import com.droidsoul.productapp.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

import static android.R.attr.category;
import static android.R.attr.orientation;

/**
 * Created by bear&bear on 4/1/2018.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder>{

    private List<Product> mProducts;
    private Context mContext;
    private int orientation;

    public DetailAdapter(Context context, List<Product> products) {
        this.mProducts = products;
        mContext = context;
        this.orientation = context.getResources().getConfiguration().orientation;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvPrice;
        ImageView ivProductImage;
        RatingBar ratingBar;
        TextView tvReviewCount;
        TextView tvInstock;
        TextView tvDescription;


        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            ivProductImage = itemView.findViewById(R.id.ivProductImage);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            tvReviewCount = itemView.findViewById(R.id.tvReviewCount);
            tvInstock = itemView.findViewById(R.id.tvInStock);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }
    }

    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View detailView = inflater.inflate(R.layout.itemdetail_product, parent, false);

        DetailAdapter.ViewHolder viewHolder = new DetailAdapter.ViewHolder(detailView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DetailAdapter.ViewHolder holder, int position) {
        Product product = mProducts.get(position);
        holder.tvName.setText(product.getProductName());
        holder.tvPrice.setText(product.getPrice());
        holder.tvReviewCount.setText(product.getReviewCount() + " reviews");
        holder.tvInstock.setText(product.isInStock() ? "In Stock" : "Out Of Stock");
        holder.tvDescription.setText(product.getLongDescription());
        holder.ratingBar.setRating((float)product.getReviewRating());
/*        if (orientation == 1) {
            Picasso.with(getContext()).load(product.getImagePath()).transform(new RoundedCornersTransformation(10, 10)).into(holder.ivProductImage);
        }
        else {
            Picasso.with(getContext()).load(product.getImagePath()).transform(new RoundedCornersTransformation(10, 10)).into(holder.ivProductImage);
        }*/
        Picasso.with(getContext()).load(product.getImagePath()).placeholder(R.drawable.large_movie_poster).transform(new RoundedCornersTransformation(10, 10)).into(holder.ivProductImage);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }
    private Context getContext() {
        return mContext;
    }
}