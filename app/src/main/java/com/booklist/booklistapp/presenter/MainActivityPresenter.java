package com.booklist.booklistapp.presenter;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.booklist.booklistapp.model.BooksLoader;
import com.booklist.booklistapp.view.MainActivityView;
import com.booklist.booklistapp.model.Book;

import java.util.List;

import static com.booklist.booklistapp.view.MainActivity.SEARCH_WORD;

public class MainActivityPresenter implements LoaderManager.LoaderCallbacks<List<Book>>, Presenter {
    private static final int LOADER_ID = 12345;

    private MainActivityView view;
    private Context context;
    private final LoaderManager loaderManager;

    public MainActivityPresenter(MainActivityView view, Context context, LoaderManager supportLoaderManager) {
        this.view = view;
        this.context = context;
        loaderManager = supportLoaderManager;
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        String searchWord = args.getString(SEARCH_WORD);
        return new BooksLoader(context, searchWord);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        if (books.isEmpty())
            view.displayNoBooks();
        else
            view.displayBooks(books);
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        view.displayNoBooks();
    }


    @Override
    public void onCreate(String savedSearchString) {

        if (savedSearchString.isEmpty() == false) {
            Bundle bundle = new Bundle();
            bundle.putString(SEARCH_WORD, savedSearchString);
            searchOnCreate(savedSearchString);
        }
    }

    private void searchOnCreate(String savedSearchString) {
        if (isConnected()) {
            if (!savedSearchString.isEmpty()) {
                Bundle bundle = new Bundle();
                bundle.putString(SEARCH_WORD, savedSearchString);
                loaderManager.initLoader(LOADER_ID, bundle, this);
            }
        } else
            view.displayNoConnection();
    }

    private boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    private void search(String savedSearchString) {
        if (isConnected()) {
            if (!savedSearchString.isEmpty()) {
                Bundle bundle = new Bundle();
                bundle.putString(SEARCH_WORD, savedSearchString);
                loaderManager.restartLoader(LOADER_ID, bundle, this);
            }
        } else
            view.displayNoConnection();
    }

    @Override
    public void onSearchButton(String searchString) {
        search(searchString);
    }
}
