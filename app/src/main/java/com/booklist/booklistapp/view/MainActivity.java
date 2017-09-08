package com.booklist.booklistapp.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.booklist.booklistapp.R;
import com.booklist.booklistapp.model.Book;
import com.booklist.booklistapp.presenter.MainActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainActivityView
{

    public static final String SEARCH_WORD = "SEARCH_WORD";
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
        presenter = new MainActivityPresenter(this, null, this, getSupportLoaderManager());
        String savedSearchString = "";
        if (savedInstanceState != null)
            savedSearchString = savedInstanceState.getString(SAVED_SEARCH_STRING);

        mAdapter = new BooksAdapter(this, new ArrayList<Book>());
        booksListView.setEmptyView(emptyTextView);
        booksListView.setAdapter(mAdapter);
        if (savedInstanceState != null)
            booksListView.onRestoreInstanceState(savedInstanceState.getParcelable(LISTVIEW_STATE));
        searchEditText.setText(savedSearchString);

        presenter.onCreate(savedSearchString);
    }

    @OnClick(R.id.search_button)
    public void search(){
        String searchString = searchEditText.getText().toString();
        presenter.onSearchButton(searchString);
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
        mAdapter.clear();
        mAdapter.addAll(books);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayNoBooks() {
        mAdapter.clear();
        mAdapter.notifyDataSetChanged();
        emptyTextView.setText(getString(R.string.not_found));
    }

    @Override
    public void displayNoConnection() {
        mAdapter.clear();
        mAdapter.notifyDataSetChanged();
        emptyTextView.setText(getString(R.string.no_connection));
    }
}
