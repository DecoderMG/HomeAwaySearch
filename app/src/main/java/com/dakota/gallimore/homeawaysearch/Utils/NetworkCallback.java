package com.dakota.gallimore.homeawaysearch.Utils;

import org.json.JSONObject;

/**
 * Created by galli_000 on 11/1/2017.
 * Interface allowing us to keep track of asynchronous network calls.
 */

public interface NetworkCallback {

    /**
     * Callback method for detecting when we've retrieved json back from OkHttp asynchronous call.
     *
     * @param jsonObject - JSONObject returned from homeaway servers
     */
    void onJsonObjectReturn(JSONObject jsonObject);
}
