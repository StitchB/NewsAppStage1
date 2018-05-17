package com.example.android.newsappstage1;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
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
 * Helper methods related to requesting and receiving image url from Guardian.
 */
public final class ImageUtils {

    /** Tag for the log messages */
    private static final String LOG_TAG = ImageUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link ImageUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private ImageUtils() {
    }

    /**
     * Query the Guardian news website and return an image url.
     */
    public static String fetchImageUrl(String requestUrl) {

        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a HTML response back
        String htmlResponse = null;
        try {
            htmlResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract image url from the news website html
        String newsImage = extractImageFromHtml(htmlResponse);

        // Return the image
        return newsImage;
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
        String result = "";

        // If the URL is null, then return early.
        if (url == null) {
            return result;
        }

        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();

            int code = urlConnection.getResponseCode();

            if(code==200){
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                if (in != null) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                    String line = "";

                    while ((line = bufferedReader.readLine()) != null)
                        result += line;
                }
                in.close();
            }

            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }

        return result;
    }

    /**
     * Return a news image url
     */
    private static String extractImageFromHtml(String newsHtml) {
        // If the html string is empty or null, then return early.
        if (TextUtils.isEmpty(newsHtml)) {
            return null;
        }
        String test = newsHtml;
        String imageUrl = "https://i.guim.co.uk/img/media/cd50f0b5cf318e708752e20f756a5486763b8d71/0_31_4106_2464/master/4106.jpg?w=620&q=55&auto=format&usm=12&fit=max&s=541481a31ee97d13d1293cb983b2def6";

        // Return the news image URL
        return imageUrl;
    }

}