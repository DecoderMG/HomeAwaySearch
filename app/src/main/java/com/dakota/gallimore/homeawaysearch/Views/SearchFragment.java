package com.dakota.gallimore.homeawaysearch.Views;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dakota.gallimore.homeawaysearch.DataClasses.ListingSearchPaginator;
import com.dakota.gallimore.homeawaysearch.ListingActivity;
import com.dakota.gallimore.homeawaysearch.R;
import com.dakota.gallimore.homeawaysearch.Utils.AdapterClickListener;
import com.dakota.gallimore.homeawaysearch.Utils.AuthUtils;
import com.dakota.gallimore.homeawaysearch.Utils.NetworkCallback;
import com.dakota.gallimore.homeawaysearch.Utils.NetworkUtils;
import com.dakota.gallimore.homeawaysearch.di.HomeAwaySearchApplication;
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

import javax.inject.Inject;

import okhttp3.OkHttpClient;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements AdapterClickListener {

    @Inject
    Gson mGson;

    @Inject
    OkHttpClient client;

    AuthState authState;
    AuthorizationServiceConfiguration serviceConfig;
    RecyclerView mRecyclerView;
    ListingSearchPaginator searchListings;
    private OnFragmentInteractionListener mListener;

    // TODO: Update Search UI to have a better looking search bar
    // and results have a more material design... perhaps remove description from card view.

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getActivity() != null) {
            HomeAwaySearchApplication.get(getActivity()).getAppComponent().inject(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        mRecyclerView = rootView.findViewById(R.id.listing_search_results_recyclerview);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
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
        // Inflate the layout for this fragment
        return rootView;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // Set up Authorization details to access HomeAway API.
        serviceConfig = AuthUtils.getHomeAwayAuthorizationServiceConfiguration();
        AuthorizationResponse resp = AuthorizationResponse.fromIntent(getActivity().getIntent());
        AuthorizationException ex = AuthorizationException.fromIntent(getActivity().getIntent());

        // We need to check for token validity since we received a successful reply from HomeAway servers, we should process it.
        if (resp != null) {
            // authorization completed
            authState = new AuthState(serviceConfig);

            // Update out authentication status for single sign on
            authState.update(resp, ex);

            // Should probably find a better way but HomeAway requires the client key and secret to be transmitted
            // with requests in an authorization header.
            ClientAuthentication clientAuth = new ClientSecretBasic("9529fdde-76d2-4c47-a8ec-8299235f77c7");

            TokenRequest req = resp.createTokenExchangeRequest();
            final AuthorizationService authorizationService = new AuthorizationService(getActivity());

            authorizationService.performTokenRequest(req, clientAuth, new AuthorizationService.TokenResponseCallback() {
                @Override
                public void onTokenRequestCompleted(@Nullable TokenResponse response, @Nullable AuthorizationException aEx) {
                    if (response != null) {
                        authState.update(response, aEx);
                        AuthUtils.writeAuthState(authState, getContext());

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

                                NetworkUtils.getHomeAwayJsonData("https://ws.homeaway.com/public/search",
                                        authState.getAccessToken(),
                                        client,
                                        new NetworkCallback() {
                                    @Override
                                    public void onJsonObjectReturn(String jsonObject) {
                                        displayJsonListings(jsonObject);
                                    }
                                });
                            }
                        });

                    } else {
                        Log.d("Search Frag", "Access Token Failure");
                        //tv.setText("Access Token Failure");
                    }
                }
            });
        } else if (ex == null) {
            // We did not fetch a reply from HomeAway servers and did not run into an error,
            // out cached access token is still valid, proceed with requests.

            authState = AuthUtils.readAuthState(getActivity());
            //tv.setText("Authorization Code: " + authState.getLastAuthorizationResponse().authorizationCode);

            NetworkUtils.getHomeAwayJsonData("https://ws.homeaway.com/public/search",
                    authState.getAccessToken(),
                    client,
                    new NetworkCallback() {
                @Override
                public void onJsonObjectReturn(String jsonObject) {
                    displayJsonListings(jsonObject);
                }
            });
        }
        //TODO: Handle complete authentication failure flow.
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Populates RecyclerView from JSON data provided by HomeAway.
     *
     * @param jsonObject - JSONObject containing HomeAway search results
     */
    private void displayJsonListings(String jsonObject) {

        Log.d(this.getClass().getSimpleName(), jsonObject);
        searchListings = mGson.fromJson(jsonObject, ListingSearchPaginator.class);
        if (searchListings != null) {

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ListingRecyclerAdapter mAdapter = new ListingRecyclerAdapter(getContext(),
                            searchListings.getEntries(),
                            authState.getAccessToken());
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                    mRecyclerView.setLayoutManager(gridLayoutManager);
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.setClickListener(SearchFragment.this);
                }
            });

        } else {
            //TODO: Handle null reply from HomeAway
        }
    }
    /**
     * Handle OnClick from RecyclerView
     *
     * @param view     - view clicked on
     * @param position - position of clicked view in recyclerview
     */
    @Override
    public void itemClicked(View view, int position) {
        Intent intent = new Intent(getContext(), ListingActivity.class);
        intent.putExtra("SearchListing", searchListings.getEntries().get(position));
        startActivity(intent);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
