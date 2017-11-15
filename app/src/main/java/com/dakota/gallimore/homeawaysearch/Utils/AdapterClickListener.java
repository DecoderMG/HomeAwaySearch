package com.dakota.gallimore.homeawaysearch.Utils;

import android.view.View;

/**
 * Created by galli_000 on 11/15/2017.
 * Interface responsible to aiding in handling click events within a RecyclerView.
 */

public interface AdapterClickListener {
    void itemClicked(View view, int position);
}
