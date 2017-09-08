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

    private List<Book> mData;

    public BooksLoader(Context context, String searchWord) {
        super(context);
        mContext = context;
        mSearchWord = searchWord;
    }

    @Override
    protected void onStartLoading() {
        if (mData != null) {
            // Use cached data
            deliverResult(mData);
        } else {
            forceLoad();
        }
    }

    @Override
    public List<Book> loadInBackground() {
        URL url = QueryUtils.getApiURL(mSearchWord, mContext.getString(R.string.book_api_key));
        if (url == null)
            mData = null;
        else
            mData = QueryUtils.fetchDataFromServer(url);
        return mData;
    }

    @Override
    public void deliverResult(List<Book> data) {
        // We’ll save the data for later retrieval
        mData = data;
        // We can do any pre-processing we want here
        // Just remember this is on the UI thread so nothing lengthy!
        super.deliverResult(data);
    }
}
