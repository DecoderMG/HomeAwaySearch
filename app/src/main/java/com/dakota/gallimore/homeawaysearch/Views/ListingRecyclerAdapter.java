package com.dakota.gallimore.homeawaysearch.Views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.dakota.gallimore.homeawaysearch.DataClasses.SearchListing;
import com.dakota.gallimore.homeawaysearch.R;
import com.dakota.gallimore.homeawaysearch.Utils.AdapterClickListener;
import com.dakota.gallimore.homeawaysearch.Utils.GlideApp;

import java.util.ArrayList;

/**
 * Created by galli_000 on 11/7/2017.
 * Recycler Adapter responsible for handling returned search results from HomeAway.
 */

public class ListingRecyclerAdapter extends RecyclerView.Adapter<ListingRecyclerAdapter.ListingViewHolder> {

    Context mContext;
    ArrayList<SearchListing> data = new ArrayList<>();
    String accessToken;
    private AdapterClickListener clicklistener = null;

    public ListingRecyclerAdapter(Context context, ArrayList<SearchListing> data) {
        super();
        mContext = context;
        this.data = data;
        accessToken = "";
    }

    public ListingRecyclerAdapter(Context context, ArrayList<SearchListing> data, String accessToken) {
        super();
        mContext = context;
        this.data = data;
        this.accessToken = accessToken;
    }

    @Override
    public ListingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.listing_list_item, parent, false);
        ListingViewHolder holder = new ListingViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ListingViewHolder holder, final int position) {
        SearchListing searchListing = data.get(position);

        holder.headline.setText(searchListing.getHeadline());
        holder.rate.setText(searchListing.getPriceQuote().getAverageNightly() + " " + searchListing.getPriceQuote().getCurrencyUnits());

        // Make sure we have the access token and use ti to access restricted HomeAway data.
        if (accessToken != "") {
            GlideUrl glideUrl = new GlideUrl(searchListing.getThumbnailUrl(), new LazyHeaders.Builder()
                    .addHeader("Authorization", "Bearer " + accessToken).build());

            GlideApp.with(mContext).load(glideUrl).into(holder.listingImage);
        } else {
            GlideApp.with(mContext).load(searchListing.getThumbnailUrl()).into(holder.listingImage);
        }
        if (searchListing.getPriceRanges().size() > 1) {
            holder.rate.setText(searchListing.getPriceRange(0).getPeriodType());
        }
        holder.location.setText(searchListing.getLocation().getCity() + ", " + searchListing.getLocation().getState() + " " + searchListing.getLocation().getCountry());
    }

    /**
     * Sets Interface click listener for RecyclerView.
     *
     * @param listener - listener to apply to RecyclerView
     */
    public void setClickListener(AdapterClickListener listener) {
        this.clicklistener = listener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ListingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView rate;
        public TextView headline;
        public TextView location;
        public ImageView listingImage;

        public ListingViewHolder(View itemView) {
            super(itemView);

            rate = itemView.findViewById(R.id.rate);
            headline = itemView.findViewById(R.id.headline);
            location = itemView.findViewById(R.id.location);
            listingImage = itemView.findViewById(R.id.thumbnail);

            itemView.setOnClickListener(this);
        }

        /**
         * Call interface method when view is clicked.
         *
         * @param v - view that was clicked
         */
        @Override
        public void onClick(View v) {
            if (clicklistener != null) {
                clicklistener.itemClicked(v, getAdapterPosition());
            }
        }
    }
}
