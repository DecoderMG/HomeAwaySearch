package com.dakota.gallimore.homeawaysearch;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.dakota.gallimore.homeawaysearch.Views.SearchFragment;

public class MainActivity extends AppCompatActivity implements SearchFragment.OnFragmentInteractionListener {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SearchFragment searchFragment = SearchFragment.newInstance(null, null);
        fragmentTransaction.add(R.id.fragment_holder, searchFragment);
        fragmentTransaction.commit();

        //tv = (TextView) findViewById(R.id.textview_server_reply);


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
