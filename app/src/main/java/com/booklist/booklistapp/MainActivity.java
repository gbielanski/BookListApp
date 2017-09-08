package com.booklist.booklistapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.booklist.booklistapp.R.id.empty_text_view;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>>,
    MainActivityView
{

    private static final String SEARCH_WORD = "SEARCH_WORD";
    private static final int LOADER_ID = 12345;
    private static final String SAVED_SEARCH_STRING = "SAVED_SEARCH_STRING";

    @BindView(R.id.search_editText) EditText searchEditText;
    @BindView(R.id.empty_text_view) TextView emptyTextView;
    @BindView(R.id.books_list_view) ListView booksListView;
    private BooksAdapter mAdapter;
    private final String LISTVIEW_STATE = "LISTVIEW_STATE";
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainActivityPresenter(this, null);
        String savedSearchString = "";
        if (savedInstanceState != null)
            savedSearchString = savedInstanceState.getString(SAVED_SEARCH_STRING);

        mAdapter = new BooksAdapter(this, new ArrayList<Book>());
        booksListView.setAdapter(mAdapter);
        if (savedInstanceState != null)
            booksListView.onRestoreInstanceState(savedInstanceState.getParcelable(LISTVIEW_STATE));

        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        emptyTextView = (TextView) findViewById(empty_text_view);
        booksListView.setEmptyView(emptyTextView);
        if (isConnected) {
            emptyTextView.setText(getString(R.string.not_found));
            if (!savedSearchString.isEmpty()) {
                Bundle bundle = new Bundle();
                bundle.putString(SEARCH_WORD, savedSearchString);
                getSupportLoaderManager().restartLoader(LOADER_ID, bundle, this).forceLoad();
            }
        } else
            emptyTextView.setText(getString(R.string.no_connection));

        searchEditText.setText(savedSearchString);
    }

    @OnClick(R.id.search_button)
    public void search(){
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        mAdapter.clear();
        mAdapter.notifyDataSetChanged();

        if (isConnected) {
            emptyTextView.setText(getString(R.string.not_found));
            String searchString = searchEditText.getText().toString();
            searchString = searchString.replace(" ", "+");
            Bundle bundle = new Bundle();
            bundle.putString(SEARCH_WORD, searchString);
            getSupportLoaderManager().restartLoader(LOADER_ID, bundle, MainActivity.this).forceLoad();

        } else
            emptyTextView.setText(getString(R.string.no_connection));

    }
    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        String searchWord = args.getString(SEARCH_WORD);
        return new BooksLoader(MainActivity.this, searchWord);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        mAdapter.addAll(books);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        mAdapter.clear();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Parcelable listState = booksListView.onSaveInstanceState();
        outState.putParcelable(LISTVIEW_STATE, listState);
        outState.putString(SAVED_SEARCH_STRING, searchEditText.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void displayBooks(List<Book> books) {

    }

    @Override
    public void displayNoBooks() {

    }
}
