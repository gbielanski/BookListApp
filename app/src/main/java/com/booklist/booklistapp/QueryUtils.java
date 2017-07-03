package com.booklist.booklistapp;

import android.net.Uri;
import android.util.Log;

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

import static android.R.attr.apiKey;

public class QueryUtils {
    public static final String BOOK_API_URL = "https://www.googleapis.com/books/v1/volumes";

    public static URL getApiURL(String searchWord, String apiKey) {
        URL url = null;

        try {
            Uri uri = Uri.parse(BOOK_API_URL).buildUpon()
                    .appendQueryParameter("q", "intitle:" + searchWord)
                    .appendQueryParameter("key", apiKey)
                    .appendQueryParameter("maxResults", "20")
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
        if (jsonData == null)
            return books;

        try {
            JSONObject root = new JSONObject(jsonData);
            JSONArray items = root.getJSONArray("items");
            for (int item_idx = 0; item_idx < items.length(); item_idx++) {
                JSONObject item = items.getJSONObject(item_idx);
                JSONObject volumeInfor = item.getJSONObject("volumeInfo");
                String title = volumeInfor.getString("title");
                JSONArray authors = volumeInfor.optJSONArray("authors");

                ArrayList<String> authorsArray = new ArrayList<>();
                if (authors != null) {
                    for (int author_idx = 0; author_idx < authors.length(); author_idx++) {
                        String author = authors.getString(author_idx);
                        authorsArray.add(author);
                    }
                }

                String[] authorsStringArray = authorsArray.toArray(new String[authorsArray.size()]);

                books.add(new Book(authorsStringArray, title));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (Book b : books) {
            Log.v("jsonData Authors", b.getFormattedAuthorsNames());
            Log.v("jsonData Title", b.getTitle());
        }

        return books;
    }

    private static String getBooksJsonData(URL url) {
        String jsonData = null;
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.connect();

            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {

                InputStream inputStream = httpURLConnection.getInputStream();
                jsonData = readFromStream(inputStream);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null)
                httpURLConnection.disconnect();
        }
        return jsonData;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();

        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = bufferedReader.readLine();
            while (line != null) {
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        return output.toString();
    }
}
