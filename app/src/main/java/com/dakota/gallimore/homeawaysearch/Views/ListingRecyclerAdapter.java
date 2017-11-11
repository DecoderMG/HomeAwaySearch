package com.dakota.gallimore.homeawaysearch.Views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.dakota.gallimore.homeawaysearch.DataClasses.SearchListing;
import com.dakota.gallimore.homeawaysearch.R;

import java.util.ArrayList;

/**
 * Created by galli_000 on 11/7/2017.
 * Recycler Adapter responsible for handling returned search results from HomeAway.
 */

public class ListingRecyclerAdapter extends RecyclerView.Adapter<ListingRecyclerAdapter.ListingViewHolder> {

    Context mContext;
    ArrayList<SearchListing> data = new ArrayList<>();
    String accessToken;

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
    public void onBindViewHolder(ListingViewHolder holder, int position) {
        SearchListing searchListing = data.get(position);

        holder.headline.setText(searchListing.getHeadline());
        holder.description.setText(searchListing.getDescription());
        holder.bathrooms.setText(searchListing.getBathrooms() + " Bathroom(s)");
        holder.bedrooms.setText(searchListing.getBedrooms() + " Bedroom(s)");
        holder.roomRate.setText(searchListing.getPriceQuote().getAverageNightly() + " " + searchListing.getPriceQuote().getCurrencyUnits());

        if (accessToken != "") {
            GlideUrl glideUrl = new GlideUrl(searchListing.getThumbnailUrl(), new LazyHeaders.Builder()
                    .addHeader("Authorization", "Bearer " + accessToken).build());

            Glide.with(mContext).load(glideUrl).into(holder.listingImage);
        } else {
            Glide.with(mContext).load(searchListing.getThumbnailUrl()).into(holder.listingImage);
        }
        holder.roomRatePeriod.setText(searchListing.getPriceRange(0).getPeriodType());
        holder.location.setText(searchListing.getLocation().getCity() + ", " + searchListing.getLocation().getState() + " " + searchListing.getLocation().getCountry());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ListingViewHolder extends RecyclerView.ViewHolder {

        public TextView headline;
        public TextView description;
        public TextView roomRate;
        public TextView roomRatePeriod;
        public TextView bedrooms;
        public TextView bathrooms;
        public TextView location;
        public ImageView listingImage;

        public ListingViewHolder(View itemView) {
            super(itemView);

            headline = itemView.findViewById(R.id.listing_headline_list_item);
            description = itemView.findViewById(R.id.description_textview_list_item);
            roomRate = itemView.findViewById(R.id.room_rate_textview_list_item);
            roomRatePeriod = itemView.findViewById(R.id.payment_term_textview_list_item);
            bedrooms = itemView.findViewById(R.id.bedrooms_textview_list_item);
            bathrooms = itemView.findViewById(R.id.bathrooms_textview_list_item);
            location = itemView.findViewById(R.id.location_textview_list_item);
            listingImage = itemView.findViewById(R.id.listing_card_imageview);
        }
    }
}
