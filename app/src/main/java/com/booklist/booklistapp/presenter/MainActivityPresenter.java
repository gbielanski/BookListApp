package com.booklist.booklistapp.presenter;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.SupportActivity;
import android.support.v4.content.Loader;

import com.booklist.booklistapp.R;
import com.booklist.booklistapp.model.BooksLoader;
import com.booklist.booklistapp.view.MainActivity;
import com.booklist.booklistapp.view.MainActivityView;
import com.booklist.booklistapp.model.Book;
import com.booklist.booklistapp.repositories.BooksRepository;

import java.util.List;

import static com.booklist.booklistapp.view.MainActivity.SEARCH_WORD;

public class MainActivityPresenter implements LoaderManager.LoaderCallbacks<List<Book>>, Presenter{
    private static final int LOADER_ID = 12345;

    private MainActivityView view;
    private BooksRepository booksRepository;
    private Context context;
    private final LoaderManager loaderManager;

    public MainActivityPresenter(MainActivityView view, BooksRepository booksRepository, Context context, LoaderManager supportLoaderManager) {
        this.view = view;
        this.booksRepository = booksRepository;
        this.context = context;
        loaderManager = supportLoaderManager;
    }

    public void loadBooks() {
        List<Book> bookList = booksRepository.getBooks();

        if(bookList.isEmpty())
            view.displayNoBooks();
        else
            view.displayBooks(bookList);
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

        if(savedSearchString.isEmpty() == false){
            Bundle bundle = new Bundle();
            bundle.putString(SEARCH_WORD, savedSearchString);
            searchOnCreate(savedSearchString);
        }
    }

    private void searchOnCreate(String savedSearchString) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            if (!savedSearchString.isEmpty()) {
                Bundle bundle = new Bundle();
                bundle.putString(SEARCH_WORD, savedSearchString);
                loaderManager.initLoader(LOADER_ID, bundle, this);
            }
        } else
            view.displayNoConnection();
    }

    private void search(String savedSearchString) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
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
