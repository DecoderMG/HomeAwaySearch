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
import com.dakota.gallimore.homeawaysearch.DataClasses.ListingAdPhoto;
import com.dakota.gallimore.homeawaysearch.R;
import com.dakota.gallimore.homeawaysearch.Utils.GlideApp;

import java.util.ArrayList;

/**
 * Created by galli_000 on 11/14/2017.
 */

public class ListingPhotosRecyclerAdapter extends RecyclerView.Adapter<ListingPhotosRecyclerAdapter.ListingPhotosViewHolder> {
    private static ArrayList<ListingAdPhoto> dataList;
    private Context mContext;
    private String accessToken;

    public ListingPhotosRecyclerAdapter(Context context, ArrayList<ListingAdPhoto> media, String accessToken) {
        this.mContext = context;
        dataList = media;
        this.accessToken = accessToken;
    }

    @Override
    public ListingPhotosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.listing_media_list_item, parent, false);
        ListingPhotosViewHolder viewHolder = new ListingPhotosViewHolder(rootView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ListingPhotosViewHolder holder, int position) {
        if (accessToken != "") {
            String imageUrl = "";
            ListingAdPhoto photoData = dataList.get(position);
            if(photoData.getLarge().getUrl() != null) {
                imageUrl = photoData.getLarge().getUrl();
            } else if (photoData.getMedium().getUrl() != null ) {
                imageUrl = photoData.getMedium().getUrl();
            } else if(photoData.getSmall().getUrl() != null){
                imageUrl = photoData.getSmall().getUrl();
            }
            GlideUrl glideUrl = new GlideUrl(imageUrl, new LazyHeaders.Builder()
                    .addHeader("Authorization", "Bearer " + accessToken)
                    .build());
            GlideApp.with(mContext).load(glideUrl).into(holder.image);
        } else {
            GlideApp.with(mContext).load(dataList.get(position).getMedium().getUrl()).into(holder.image);
        }
        holder.title.setText(dataList.get(position).getCaption());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ListingPhotosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView image;
        private TextView title;

        public ListingPhotosViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageMain);
            title = itemView.findViewById(R.id.title);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
