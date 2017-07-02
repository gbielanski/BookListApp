package com.booklist.booklistapp;

import android.net.Uri;
import android.util.Log;

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

public class QueryUtils {
    public static final String BOOK_API_URL = "https://www.googleapis.com/books/v1/volumes";

    public static URL getApiURL(String apiKey) {
        URL url = null;

        try {
            Uri uri = Uri.parse(BOOK_API_URL).buildUpon()
                    .appendQueryParameter("q", "intitle:tadeusz")
                    .appendQueryParameter("key", apiKey)
                    .build();
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static List<Book> fetchDataFromServer(URL url) {
        String jsonData = getBooksJsonData(url);
        ArrayList<Book> books = parseJson2BooksList(jsonData);
        return books;
    }

    private static ArrayList<Book> parseJson2BooksList(String jsonData) {
        ArrayList<Book> books = new ArrayList<>();
        if(jsonData == null)
            return books;

        try {
            JSONObject root = new JSONObject(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return books;
    }

    private static String getBooksJsonData(URL url) {
        String jsonData = null;
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.connect();

            InputStream inputStream = httpURLConnection.getInputStream();
            jsonData = readFromStream(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(httpURLConnection != null)
                httpURLConnection.disconnect();
        }
        Log.v("jsonData", jsonData);
        return jsonData;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();

        if(inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = bufferedReader.readLine();
            while (line != null){
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        return output.toString();
    }
}
