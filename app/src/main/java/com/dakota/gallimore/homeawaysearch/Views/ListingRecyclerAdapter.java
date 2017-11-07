package com.dakota.gallimore.homeawaysearch.Views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dakota.gallimore.homeawaysearch.DataClasses.SearchListing;
import com.dakota.gallimore.homeawaysearch.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by galli_000 on 11/7/2017.
 */

public class ListingRecyclerAdapter extends RecyclerView.Adapter<ListingRecyclerAdapter.ListingViewHolder> {

    Context mContext;
    List<SearchListing> data = Collections.emptyList();

    public ListingRecyclerAdapter(Context context, List<SearchListing> data) {
        super();
        mContext = context;
        this.data = data;
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
        holder.listingImage.setImageBitmap(searchListing.getThumbnail());
        holder.roomRate.setText(searchListing.getPriceQuote().getAverageNightly() + " " + searchListing.getPriceQuote().getCurrencyUnits());
        holder.roomRatePeriod.setText(searchListing.getPriceRangePeriodType());
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
