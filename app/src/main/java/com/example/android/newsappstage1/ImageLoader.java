package com.example.android.newsappstage1;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.v4.content.Loader;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Get the image url from the news website by using an AsyncTask to perform the
 * network request to the given URL.
 */
public class ImageLoader extends AsyncTaskLoader<String> {

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link ImageLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public ImageLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread. (based on https://stackoverflow.com/questions/32964827/get-text-from-a-url-using-android-httpurlconnection)
     */
    @Override
    public String loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a news image url.
        String imageUrl = ImageUtils.fetchImageUrl(mUrl);
        return imageUrl;
    }

}