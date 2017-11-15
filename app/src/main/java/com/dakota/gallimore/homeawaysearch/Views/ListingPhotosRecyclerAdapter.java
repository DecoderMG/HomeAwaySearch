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
import com.dakota.gallimore.homeawaysearch.DataClasses.ListingMedia;
import com.dakota.gallimore.homeawaysearch.R;

import java.util.ArrayList;

/**
 * Created by galli_000 on 11/14/2017.
 */

public class ListingPhotosRecyclerAdapter extends RecyclerView.Adapter<ListingPhotosRecyclerAdapter.ListingPhotosViewHolder> {
    private static ArrayList<ListingMedia> dataList;
    private Context mContext;
    private String accessToken;

    public ListingPhotosRecyclerAdapter(Context context, ArrayList<ListingMedia> media, String accessToken) {
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
            GlideUrl glideUrl = new GlideUrl(dataList.get(position).getUri().toString(), new LazyHeaders.Builder()
                    .addHeader("Authorization", "Bearer " + accessToken).build());
            Glide.with(mContext).load(glideUrl).into(holder.image);
        } else {
            //Glide.with(mContext).load(dataList.get(position).getUri()).into(holder.image);
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
