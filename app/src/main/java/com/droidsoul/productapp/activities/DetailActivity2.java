package com.droidsoul.productapp.activities;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

import com.droidsoul.productapp.R;
import com.droidsoul.productapp.adapters.DetailAdapter;
import com.droidsoul.productapp.models.Product;

import org.parceler.Parcels;

import java.util.List;

import static java.security.AccessController.getContext;

public class DetailActivity2 extends AppCompatActivity {

    List<Product> list;
    int position;
    RecyclerView rvDetail;
    DetailAdapter detailAdapter;
    LinearLayoutManager linearLayoutManager;
    public final static String LIST_STATE_KEY = "recycler_list_state";
    Parcelable listState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);
        getSupportActionBar().setTitle("Product Detail");
        list = Parcels.unwrap(getIntent().getParcelableExtra("list"));
        position = getIntent().getIntExtra("position", 0);
        rvDetail = findViewById(R.id.rvDetail);
        detailAdapter = new DetailAdapter(this, list);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rvDetail);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvDetail.setAdapter(detailAdapter);
        rvDetail.setLayoutManager(linearLayoutManager);
        rvDetail.scrollToPosition(position);
    }
    /*
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

    @Override
    protected void onResume() {
        super.onResume();
        if (listState != null) {
            linearLayoutManager.onRestoreInstanceState(listState);
        }
    }*/
    @Override
    public void onBackPressed() {
        finishAfterTransition();
    }
}
