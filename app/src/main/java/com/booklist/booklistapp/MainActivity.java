package com.booklist.booklistapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mBookListView;
    private BooksAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBookListView = (ListView)findViewById(R.id.books_list_view);
        mAdapter = new BooksAdapter(this, 0, new ArrayList<Book>());
        mBookListView.setAdapter(mAdapter);
        BookAsyncTask task = new BookAsyncTask();
        task.execute();
    }

    class BookAsyncTask extends AsyncTask<Void, Void, List<Book>> {

        @Override
        protected List<Book> doInBackground(Void... params) {
            URL url = QueryUtils.getApiURL(getString(R.string.book_api_key));
            if (url == null)
                return null;

            List<Book> booksList = QueryUtils.fetchDataFromServer(url);

            return booksList;
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            mAdapter.addAll(books);
            mAdapter.notifyDataSetChanged();
        }
    }
}
