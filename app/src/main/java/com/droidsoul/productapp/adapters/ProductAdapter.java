package com.droidsoul.productapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.droidsoul.productapp.R;
import com.droidsoul.productapp.activities.DetailActivity;
import com.droidsoul.productapp.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by bear&bear on 3/30/2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> mProducts;
    private Context mContext;
    private int orientation;

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ProductAdapter(Context context, List<Product> products) {
        this.mProducts = products;
        this.mContext = context;
        this.orientation = context.getResources().getConfiguration().orientation;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvPrice;
        ImageView ivProductImage;

        ViewHolder(final View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            ivProductImage = itemView.findViewById(R.id.ivProductImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Triggers click upwards to the adapter on click
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View productView = inflater.inflate(R.layout.item_product, parent, false);

        ProductAdapter.ViewHolder viewHolder = new ProductAdapter.ViewHolder(productView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = mProducts.get(position);
        holder.tvName.setText(product.getProductName());
        holder.tvPrice.setText(product.getPrice());
/*        if (orientation == 1) {
            Picasso.with(getContext()).load(product.getImagePath()).transform(new RoundedCornersTransformation(10, 10)).into(holder.ivProductImage);
        }
        else {
            Picasso.with(getContext()).load(product.getImagePath()).transform(new RoundedCornersTransformation(10, 10)).into(holder.ivProductImage);
        }*/
        Picasso.with(getContext()).load(product.getImagePath()).placeholder(R.drawable.small_movie_poster).transform(new RoundedCornersTransformation(10, 10)).into(holder.ivProductImage);
//        Picasso.with(getContext()).load(product.getImagePath()).into(holder.ivProductImage);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    private Context getContext() {
        return mContext;
    }

}
