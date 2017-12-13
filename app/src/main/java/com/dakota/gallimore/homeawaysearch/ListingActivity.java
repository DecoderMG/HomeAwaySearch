package com.dakota.gallimore.homeawaysearch;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dakota.gallimore.homeawaysearch.DataClasses.Listing;
import com.dakota.gallimore.homeawaysearch.DataClasses.ListingAdPhoto;
import com.dakota.gallimore.homeawaysearch.DataClasses.ListingLocation;
import com.dakota.gallimore.homeawaysearch.DataClasses.ListingSearchHit;
import com.dakota.gallimore.homeawaysearch.Utils.AuthUtils;
import com.dakota.gallimore.homeawaysearch.Utils.GlideApp;
import com.dakota.gallimore.homeawaysearch.Utils.NetworkCallback;
import com.dakota.gallimore.homeawaysearch.Utils.NetworkUtils;
import com.dakota.gallimore.homeawaysearch.Views.ListingPhotosRecyclerAdapter;
import com.google.gson.Gson;

import net.openid.appauth.AuthState;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.ClientAuthentication;
import net.openid.appauth.ClientSecretBasic;
import net.openid.appauth.TokenRequest;
import net.openid.appauth.TokenResponse;

import java.util.ArrayList;
import java.util.Locale;

public class ListingActivity extends AppCompatActivity {

    TextView headline;
    TextView location;
    TextView description;
    TextView bathrooms;
    TextView bedrooms;
    TextView imageCounter;
    ImageView featuredImage;

    RecyclerView photosRecyclerView;

    AuthState authState;
    AuthorizationServiceConfiguration serviceConfig;
    Listing listing;
    String listingUrl;

    Context mContext;

    //TODO: Finish Hooking up UI elements to JSON information.
    //TODO: Implement Loader to keep data on rotations so we don't send out a new JSON request.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        mContext = this;

        headline = findViewById(R.id.listing_headline_textview);
        location = findViewById(R.id.listing_location_textview);
        description = findViewById(R.id.listing_details_textview);

        bathrooms = findViewById(R.id.bathroomTabCount);
        bedrooms = findViewById(R.id.bedroomTabCount);

        imageCounter = findViewById(R.id.listing_photos_counter);

        featuredImage = findViewById(R.id.listing_main_imageview);

        photosRecyclerView = findViewById(R.id.photos_recyclerview_landscape);

        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        photosRecyclerView.setLayoutManager(linearLayoutManager);

        photosRecyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        if (getIntent() != null && getIntent().hasExtra("SearchListing")) {
            ListingSearchHit searchListing = (ListingSearchHit) getIntent().getSerializableExtra("SearchListing");
            ListingLocation listingLocation = searchListing.getLocation();

            headline.setText(searchListing.getHeadline());
            this.location.setText(listingLocation.getCity() + ", " + listingLocation.getState() + ", " +
                    listingLocation.getCountry());
            description.setText(searchListing.getDescription());
            listingUrl = searchListing.getDetailsUrl().toString();

            Glide.with(this).load(searchListing.getThumbnail().getUri()).into(featuredImage);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        serviceConfig = AuthUtils.getHomeAwayAuthorizationServiceConfiguration();
        AuthorizationResponse resp = AuthorizationResponse.fromIntent(getIntent());
        AuthorizationException ex = AuthorizationException.fromIntent(getIntent());

        if (resp != null) {
            // authorization completed
            authState = new AuthState(serviceConfig);

            authState.update(resp, ex);
            ClientAuthentication clientAuth = new ClientSecretBasic("9529fdde-76d2-4c47-a8ec-8299235f77c7");

            TokenRequest req = resp.createTokenExchangeRequest();
            final AuthorizationService authorizationService = new AuthorizationService(this);

            authorizationService.performTokenRequest(req, clientAuth, new AuthorizationService.TokenResponseCallback() {
                @Override
                public void onTokenRequestCompleted(@Nullable TokenResponse response, @Nullable AuthorizationException aEx) {
                    if (response != null) {
                        //tv.setText("Access Token: " + response.accessToken);
                        authState.update(response, aEx);
                        AuthUtils.writeAuthState(authState, getBaseContext());

                        authState.performActionWithFreshTokens(authorizationService, new AuthState.AuthStateAction() {
                            @Override
                            public void execute(
                                    String accessToken,
                                    String idToken,
                                    AuthorizationException ex) {
                                if (ex != null) {
                                    // negotiation for fresh tokens failed, check ex for more details
                                    return;
                                }
                                if (checkListingUrl(listingUrl)) {
                                    NetworkUtils.getHomeAwayJsonData(listingUrl, authState.getAccessToken(), new NetworkCallback() {
                                        @Override
                                        public void onJsonObjectReturn(String jsonObject) {
                                            displayJsonListingWithGson(jsonObject);
                                        }
                                    });
                                }

                            }
                        });

                    } else {
                        Log.d("Search Frag", "Access Token Failure");
                    }
                }
            });
        } else if (ex == null) {

            authState = AuthUtils.readAuthState(this);

            if (checkListingUrl(listingUrl)) {
                Log.d("Listing Activity: ", listingUrl);
                StringBuilder urlBuilder = new StringBuilder(listingUrl);
                urlBuilder.append("&q=DETAILS");
                urlBuilder.append("&q=LOCATION");
                urlBuilder.append("&q=PHOTOS");
                Log.d("Listing Activity: ", urlBuilder.toString());
                NetworkUtils.getHomeAwayJsonData(urlBuilder.toString(), authState.getAccessToken(), new NetworkCallback() {
                    @Override
                    public void onJsonObjectReturn(String jsonObject) {
                        displayJsonListingWithGson(jsonObject);
                    }
                });
            }
        }
    }

    private boolean checkListingUrl(String url) {
        return !(url.isEmpty() || url == null || url.equals(""));
    }

    private void displayJsonListingWithGson(String data) {
        listing = new Gson().fromJson(data, Listing.class);
        GlideApp.get(this).clearDiskCache();
        runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Glide.get(mContext).clearMemory();
                    GlideUrl glideUrl = new GlideUrl(listing.getPhotos().getPhotos().get(0).getLarge().getUrl(), new LazyHeaders.Builder()
                            .addHeader("Authorization", "Bearer " + authState.getAccessToken()).build());

                    Glide.with(mContext).load(glideUrl).listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            Log.d("GLIDE", String.format(Locale.ROOT,
                                    "onException(%s, %s, %s, %s)", e, model, target, isFirstResource), e);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            Log.d("GLIDE", String.format(Locale.ROOT,
                                    "onResourceReady(%s, %s, %s, %s)", resource, model, target, isFirstResource));

                            return false;
                        }
                    }).into(featuredImage);

                    Log.d("Glide Debug", glideUrl.toStringUrl());
                    ArrayList<ListingAdPhoto> photos = new ArrayList<>();
                    for (int i = 0; i < listing.getPhotos().getPhotos().size(); i++) {
                        photos.add(listing.getPhotos().getPhotos().get(i));
                    }

                    description.setText(listing.getAdContent().getDescription());
                    imageCounter.setText(listing.getPhotos().getPhotos().size() + " Photos");
                    ListingPhotosRecyclerAdapter photosRecyclerAdapter = new
                            ListingPhotosRecyclerAdapter(mContext, photos, authState.getAccessToken());
                    photosRecyclerView.setAdapter(photosRecyclerAdapter);
                }
            });
    }
}
