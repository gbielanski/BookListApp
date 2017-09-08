package com.booklist.booklistapp.model;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import com.booklist.booklistapp.R;

import java.net.URL;
import java.util.List;


public class BooksLoader extends AsyncTaskLoader<List<Book>> {

    final private Context mContext;
    final private String mSearchWord;
    public BooksLoader(Context context, String searchWord) {
        super(context);
        mContext = context;
        mSearchWord = searchWord;
    }

    @Override
    public List<Book> loadInBackground() {
        URL url = QueryUtils.getApiURL(mSearchWord, mContext.getString(R.string.book_api_key));
        if (url == null)
            return null;

        return QueryUtils.fetchDataFromServer(url);
    }
}
