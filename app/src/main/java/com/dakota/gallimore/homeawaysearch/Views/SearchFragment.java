package com.dakota.gallimore.homeawaysearch.Views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dakota.gallimore.homeawaysearch.DataClasses.SearchListing;
import com.dakota.gallimore.homeawaysearch.DataClasses.User;
import com.dakota.gallimore.homeawaysearch.R;
import com.dakota.gallimore.homeawaysearch.Utils.AuthUtils;
import com.dakota.gallimore.homeawaysearch.Utils.JsonUtils;
import com.dakota.gallimore.homeawaysearch.Utils.NetworkCallback;
import com.dakota.gallimore.homeawaysearch.Utils.NetworkUtils;

import net.openid.appauth.AuthState;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.ClientAuthentication;
import net.openid.appauth.ClientSecretBasic;
import net.openid.appauth.TokenRequest;
import net.openid.appauth.TokenResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match,
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    // May not need additional parameters upon completion.
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    AuthState authState;
    AuthorizationServiceConfiguration serviceConfig;
    JSONObject jsonReply;
    EditText ed;
    RecyclerView mRecyclerView;
    // TODO: Rename and change types of parameters, may not need parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

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
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        ed = rootView.findViewById(R.id.search);
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

        serviceConfig = AuthUtils.getHomeAwayAuthorizationServiceConfiguration();
        AuthorizationResponse resp = AuthorizationResponse.fromIntent(getActivity().getIntent());
        AuthorizationException ex = AuthorizationException.fromIntent(getActivity().getIntent());

        if (resp != null) {
            // authorization completed
            authState = new AuthState(serviceConfig);

            authState.update(resp, ex);
            //tv.setText("Authorization Code: " + authState.getLastAuthorizationResponse().authorizationCode);
            ClientAuthentication clientAuth = new ClientSecretBasic("9529fdde-76d2-4c47-a8ec-8299235f77c7");

            TokenRequest req = resp.createTokenExchangeRequest();//new TokenRequest.Builder(serviceConfig, "b7159595-c72d-4c9b-8c53-37d649a7d2b8")
            //.setGrantType("").build();
            final AuthorizationService authorizationService = new AuthorizationService(getActivity());

            authorizationService.performTokenRequest(req, clientAuth, new AuthorizationService.TokenResponseCallback() {
                @Override
                public void onTokenRequestCompleted(@Nullable TokenResponse response, @Nullable AuthorizationException aEx) {
                    if (response != null) {
                        //tv.setText("Access Token: " + response.accessToken);
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

                                NetworkUtils.getHomeAwayJsonData("https://ws.homeaway.com/public/search?q=austin&minPrice=100.0&availabilityEnd=2016-05-04&availabilityStart=2016-04-22&pageSize=1&locale=en&refine=Sleeps:1", authState.getAccessToken(), new NetworkCallback() {
                                    @Override
                                    public void onJsonObjectReturn(JSONObject jsonObject) {
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

            authState = AuthUtils.readAuthState(getActivity());
            //tv.setText("Authorization Code: " + authState.getLastAuthorizationResponse().authorizationCode);

            NetworkUtils.getHomeAwayJsonData("https://ws.homeaway.com/public/search", authState.getAccessToken(), new NetworkCallback() {
                @Override
                public void onJsonObjectReturn(JSONObject jsonObject) {
                    displayJsonListings(jsonObject);
                }
            });


            /*
                    OkHttpClient client = new OkHttpClient();




                    Request request = new Request.Builder()
                            .url("https://ws.homeaway.com/public/myListings")
                            .addHeader("Authorization", "Bearer " + authState.getAccessToken())
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, final IOException e) {
                            final String exception = e.getMessage();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tv.setText(exception);
                                }
                            });

                        }

                        @Override
                        public void onResponse(Call call, final Response response) throws IOException {
                            if(!response.isSuccessful()) {
                                throw new IOException("Unexpected code " + response);
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            tv.setText(response.body().string());
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        }
                    }); */
        }
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

    private void displayJsonListings(JSONObject jsonObject) {
        jsonReply = jsonObject;
        if (jsonReply != null) {

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    User user = null;
                    try {
                        ArrayList<SearchListing> searchListings = new ArrayList<>();
                        JSONArray entries = jsonReply.getJSONArray("entries");
                        for (int i = 0; i < entries.length(); i++) {
                            searchListings.add(JsonUtils.parseSearchListingJson(entries.getJSONObject(i)));
                        }
                        ListingRecyclerAdapter mAdapter = new ListingRecyclerAdapter(getContext(), searchListings, authState.getAccessToken());
                        LinearLayoutManager layoutManager =
                                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setAdapter(mAdapter);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    //StringBuilder userBuffer = new StringBuilder();
                    //userBuffer.append("User Id: " + user.getId() + "\nName: " + user.getFirstName() + " " + user.getLastName() + "\nEmail: " + user.getEmail()
                    //        + "\nAccounts: ");
                    //for (String account : user.getAccountTypes()) {
                    //    userBuffer.append(account);
                    //    userBuffer.append(" | ");
                    //}

                    //userBuffer.append("\n\n" + jsonReply.toString());
                    //Log.d("Search Frag: ", user.getEmail());
                    //tv.setText(accountsBuffer.toString());
                }
            });

        } else {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //tv.setText("empty json returned");
                }
            });
        }
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
