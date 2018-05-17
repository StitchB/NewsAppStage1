package com.example.android.newsappstage1;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper methods related to requesting and receiving news data from USGS.
 */
public final class QueryUtils {

    /** Context */
    private static Context mContext;

    /** Loader Manager */
    private static LoaderManager mLoaderManager;

    /** Image */
    private static String image = "http://via.placeholder.com/500x500";

    /** Tag for the log messages */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Constant value for the news loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int IMAGE_LOADER_ID = 1;

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Query the USGS dataset and return a list of {@link News} objects.
     */
    public static List<News> fetchNewsData(Context context, LoaderManager loaderManager, String requestUrl) {

        //Context
        mContext = context;

        //Context
        mLoaderManager = loaderManager;

        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link News}
        List<News> newsList = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link News}
        return newsList;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the news JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return a list of {@link News} objects that has been built up from
     * parsing the given JSON response.
     */
    private static List<News> extractFeatureFromJson(String newsJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding news to
        List<News> newsList = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Get a reference to the ConnectivityManager to check state of network connectivity
            ConnectivityManager connMgr = (ConnectivityManager)
                    mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

            // Get details on the currently active default data network
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(newsJSON);

            // Extract the JSONArray associated with the key called "results",
            // which represents a list of results (or news).
            JSONArray newsArray = baseJsonResponse.getJSONObject("response").getJSONArray("results");

            // For each news in the newsArray, create an {@link News} object
            for (int i = 0; i < newsArray.length(); i++) {

                // Get a single news at position i within the list of news
                JSONObject currentNews = newsArray.getJSONObject(i);

                // Extract the value for the key called "webTitle"
                String title = currentNews.getString("webTitle");

                // Extract the value for the key called "sectionName"
                String sectionName = currentNews.getString("sectionName");

                // Extract the value for the key called "webPublicationDate"
                String publicationDate = currentNews.getString("webPublicationDate");

                // Extract the value for the key called "url"
                String url = currentNews.getString("webUrl");

                //News image loader
                LoaderManager.LoaderCallbacks<String> imageLoader
                        = new LoaderManager.LoaderCallbacks<String>() {
                    @Override
                    public Loader<String> onCreateLoader(int i, Bundle bundle) {
                        // Create a new loader for the given website URL
                        return new ImageLoader(mContext, "https://www.theguardian.com/books/2018/may/13/connect-julian-gough-review"); //TODO pass correct url
                    }

                    @Override
                    public void onLoadFinished(Loader<String> loader, String imageUrl) {
                        // Use temporary image TODO
                        //mEmptyStateTextView.setText(R.string.no_news);

                        // Clear the adapter of previous news data
                        //mAdapter.clear();

                        // Update to correct image
                        if (imageUrl != null && !imageUrl.isEmpty()) {
                            image = imageUrl;
                        }
                    }

                    @Override
                    public void onLoaderReset(Loader<String> loader) {

                    }
                };

                // If there is a network connection, fetch data
                if (networkInfo != null && networkInfo.isConnected()) {
                    // Get a reference to the LoaderManager, in order to interact with loaders.
                    LoaderManager loaderManager = mLoaderManager;

                    // Initialize the loaders. Pass in the int ID constant defined above and pass in null for
                    // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
                    // because this activity implements the LoaderCallbacks interface).
                    loaderManager.initLoader(IMAGE_LOADER_ID, null, imageLoader);

                    loaderManager.destroyLoader(IMAGE_LOADER_ID);
                } else {
                    // Otherwise, display error
                    // First, hide loading indicator so error message will be visible
                    /*View loadingIndicator = findViewById(R.id.loading_indicator);
                    loadingIndicator.setVisibility(View.GONE);

                    // Update empty state with no connection error message
                    mEmptyStateTextView.setText(R.string.no_internet_connection);*/ //TODO ?
                }

                // Create a new {@link News} object with the title, section name, publication date,
                // and url from the JSON response.
                News news = new News(title, sectionName, publicationDate, url, image);

                // Add the new {@link News} to the list of news.
                newsList.add(news);
            }
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the news JSON results", e);
        }

        // Return the list of news
        return newsList;
    }

}