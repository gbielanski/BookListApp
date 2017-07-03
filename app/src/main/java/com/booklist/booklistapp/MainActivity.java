package com.booklist.booklistapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mBookListView;
    private BooksAdapter mAdapter;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBookListView = (ListView)findViewById(R.id.books_list_view);
        mAdapter = new BooksAdapter(this, 0, new ArrayList<Book>());
        mBookListView.setAdapter(mAdapter);

        mEditText = (EditText)findViewById(R.id.search_editText);
        Button mSearchButton = (Button)findViewById(R.id.search_button);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchString = mEditText.getText().toString();
                searchString.replace(" ", "+");
                BookAsyncTask task = new BookAsyncTask();
                task.execute(searchString);
            }
        });
    }

    class BookAsyncTask extends AsyncTask<String, Void, List<Book>> {

        @Override
        protected List<Book> doInBackground(String ... params) {

            if(params.length == 0 || params[0] == null || params[0].isEmpty())
                return null;

            URL url = QueryUtils.getApiURL(params[0], getString(R.string.book_api_key));
            if (url == null)
                return null;

            List<Book> booksList = QueryUtils.fetchDataFromServer(url);

            return booksList;
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            if(books == null)
                return;

            mAdapter.clear();
            mAdapter.addAll(books);
            mAdapter.notifyDataSetChanged();
        }
    }
}
