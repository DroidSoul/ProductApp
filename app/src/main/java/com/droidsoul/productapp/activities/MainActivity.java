package com.droidsoul.productapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.droidsoul.productapp.R;
import com.droidsoul.productapp.adapters.ProductAdapter;
import com.droidsoul.productapp.models.Product;
import com.droidsoul.productapp.utils.EndlessRecyclerViewScrollListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Product> products;
    private RecyclerView rvProducts;
    private ProductAdapter productAdapter;
    private LinearLayoutManager linearLayoutManager;
    private String oriURL = "https://walmartlabs-test.appspot.com/_ah/api/walmart/v1/walmartproducts/f6c443b0-25d4-4620-9fa4-1759353f9ed8/";
    final static int pageSize = 30;
    AsyncHttpClient client;
    public final static String LIST_STATE_KEY = "recycler_list_state";
    Parcelable listState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
    }

    private void setupView() {
        client = new AsyncHttpClient();
        products = new ArrayList<>();
        rvProducts = findViewById(R.id.rvProducts);
        productAdapter = new ProductAdapter(this, products);
        productAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
/*                Product product = products.get(position);
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("previous", Parcels.wrap(products.get(position - 1)));
                i.putExtra("current", Parcels.wrap(product));
                i.putExtra("next", Parcels.wrap(products.get(position + 1)));
                startActivity(i);*/
                Intent i = new Intent(MainActivity.this, DetailActivity2.class);
                i.putExtra("list", Parcels.wrap(products));
                i.putExtra("position", position);
                //shared elements transition animation
                Pair<View, String> p1 = Pair.create(view.findViewById(R.id.ivProductImage), "profile");
                Pair<View, String> p2 = Pair.create(view.findViewById(R.id.tvPrice), "price");
                Pair<View, String> p3 = Pair.create(view.findViewById(R.id.tvName), "name");
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(MainActivity.this, p1, p2, p3);
                startActivity(i, options.toBundle());
            }
        });
        linearLayoutManager = new LinearLayoutManager(this);
        rvProducts.setLayoutManager(linearLayoutManager);
        rvProducts.setAdapter(productAdapter);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rvProducts.addItemDecoration(itemDecoration);
        getDataFromApi(1);
    }

    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        // Save list state
        listState = linearLayoutManager.onSaveInstanceState();
        state.putParcelable(LIST_STATE_KEY, listState);
    }

    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        // Retrieve list state and list/item positions
        if(state != null)
            listState = state.getParcelable(LIST_STATE_KEY);

    }

    private void getDataFromApi(int page) {
//        RequestParams params = new RequestParams();
//        params.put("apiKey", api);
//        params.put("pageNumber", page);
 //       params.put("pageSize", 30);
        String url = oriURL + page + "/" + pageSize;
        if (page == 1) {
            rvProducts.clearOnScrollListeners();
            rvProducts.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                @Override
                public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                    getDataFromApi(page);
                }
            });
            client.get(url, new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    JSONArray productJSONResults = null;
                    try {
                        productJSONResults = response.getJSONArray("products");
                        products.clear();
                        products.addAll(Product.fromJSONArray(productJSONResults));
                        productAdapter.notifyDataSetChanged();
                        if (listState != null) {
                            linearLayoutManager.onRestoreInstanceState(listState);
 //                           rvProducts.scrollToPosition(lastPos);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                }
            });
        }
        //load more pages here
        else {
            client.get(url, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    JSONArray productJSONResults = null;
                    try {
                        productJSONResults = response.getJSONArray("products");
                        products.addAll(Product.fromJSONArray(productJSONResults));
                        int curSize = productAdapter.getItemCount();
                        productAdapter.notifyItemRangeInserted(curSize, products.size() - 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                }
            });
        }
    }

}
