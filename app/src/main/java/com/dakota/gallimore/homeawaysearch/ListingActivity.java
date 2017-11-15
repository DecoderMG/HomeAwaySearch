package com.dakota.gallimore.homeawaysearch;

import android.content.Context;
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
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.dakota.gallimore.homeawaysearch.DataClasses.Listing;
import com.dakota.gallimore.homeawaysearch.DataClasses.ListingMedia;
import com.dakota.gallimore.homeawaysearch.DataClasses.Location;
import com.dakota.gallimore.homeawaysearch.DataClasses.SearchListing;
import com.dakota.gallimore.homeawaysearch.Utils.AuthUtils;
import com.dakota.gallimore.homeawaysearch.Utils.JsonUtils;
import com.dakota.gallimore.homeawaysearch.Utils.NetworkCallback;
import com.dakota.gallimore.homeawaysearch.Utils.NetworkUtils;
import com.dakota.gallimore.homeawaysearch.Views.ListingPhotosRecyclerAdapter;

import net.openid.appauth.AuthState;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.ClientAuthentication;
import net.openid.appauth.ClientSecretBasic;
import net.openid.appauth.TokenRequest;
import net.openid.appauth.TokenResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
            SearchListing searchListing = (SearchListing) getIntent().getSerializableExtra("SearchListing");
            Location location = searchListing.getLocation();

            headline.setText(searchListing.getHeadline());
            this.location.setText(location.getCity() + ", " + location.getState() + ", " +
                    location.getCountry());
            description.setText(searchListing.getDescription());
            listingUrl = searchListing.getDetailsUrl().toString();

            Glide.with(this).load(searchListing.getThumbnailUrl()).into(featuredImage);
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
                                        public void onJsonObjectReturn(JSONObject jsonObject) {
                                            displayJsonListing(jsonObject);
                                        }
                                    });
                                }

                            }
                        });

                    } else {
                        Log.d("Search Frag", "Access Token Failure");
                        //tv.setText("Access Token Failure");
                    }
                }
            });
        } else if (ex == null) {

            authState = AuthUtils.readAuthState(this);
            //tv.setText("Authorization Code: " + authState.getLastAuthorizationResponse().authorizationCode);

            if (checkListingUrl(listingUrl)) {
                Log.d("Listing Activity: ", listingUrl);
                StringBuilder urlBuilder = new StringBuilder(listingUrl);
                urlBuilder.append("&q=DETAILS");
                urlBuilder.append("&q=LOCATION");
                urlBuilder.append("&q=PHOTOS");
                Log.d("Listing Activity: ", urlBuilder.toString());
                NetworkUtils.getHomeAwayJsonData(urlBuilder.toString(), authState.getAccessToken(), new NetworkCallback() {
                    @Override
                    public void onJsonObjectReturn(JSONObject jsonObject) {
                        displayJsonListing(jsonObject);
                    }
                });
            }
        }
    }

    private boolean checkListingUrl(String url) {
        return !(url.isEmpty() || url == null || url.equals(""));
    }

    private void displayJsonListing(JSONObject jsonObject) {
        try {
            listing = JsonUtils.parseListingJson(jsonObject);

            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    GlideUrl glideUrl = new GlideUrl(listing.getPhoto(0).getUri(), new LazyHeaders.Builder()
                            .addHeader("Authorization", "Bearer " + authState.getAccessToken()).build());

                    Log.d("Glide Debug", glideUrl.toStringUrl());
                    //Glide.with(ListingActivity.this).load("https://imagesus.homeaway.com/mda01/83f65519-5dfc-402a-8fea-d5e74bf0d19e.1.10").into(featuredImage);
                    ArrayList<ListingMedia> photos = new ArrayList<>();
                    for (int i = 0; i < listing.getPhotos().size(); i++) {
                        if (listing.getPhoto(i).getImageType().equals("photo")) {
                            photos.add(listing.getPhoto(i));
                        } else {
                            break;
                        }
                    }

                    description.setText(listing.getDescription());
                    imageCounter.setText(listing.getPhotos().size() + " Photos");
                    ListingPhotosRecyclerAdapter photosRecyclerAdapter = new
                            ListingPhotosRecyclerAdapter(mContext, photos, authState.getAccessToken());
                    photosRecyclerView.setAdapter(photosRecyclerAdapter);
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
