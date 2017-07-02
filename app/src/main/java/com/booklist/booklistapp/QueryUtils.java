package com.booklist.booklistapp;

import java.net.MalformedURLException;
import java.net.URL;

public class QueryUtils {
    public static final String BOOK_API_URL = "";

    public static URL getApiURL() {
        URL url = null;

        try {
            url = new URL(BOOK_API_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String fetchDataFromServer(URL url) {
        return null;
    }
}