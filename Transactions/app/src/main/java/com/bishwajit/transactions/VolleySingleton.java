package com.bishwajit.transactions;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by bishwajit on 4/14/2016.
 */
public class VolleySingleton {

    private static VolleySingleton sInstance = null;
    private RequestQueue mRequestQueue;

    //constructor
    private VolleySingleton() {
        mRequestQueue = Volley.newRequestQueue(MyApplication.getContext());
    }

    public static VolleySingleton getsInstance() {
        if (sInstance == null)
            sInstance = new VolleySingleton();
        return sInstance;

    }

    public RequestQueue getmRequestQueue() {
        return mRequestQueue;
    }
}
