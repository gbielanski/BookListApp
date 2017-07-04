package com.booklist.booklistapp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.net.URL;
import java.util.List;


class BooksLoader extends AsyncTaskLoader<List<Book>> {

    private Context mContext;
    private String mSearchWord;
    BooksLoader(Context context, String searchWord) {
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
