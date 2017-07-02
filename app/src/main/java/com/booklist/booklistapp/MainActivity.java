package com.booklist.booklistapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BookAsyncTask task = new BookAsyncTask();
        task.execute();
    }

    class BookAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            URL url = QueryUtils.getApiURL();
            if (url == null)
                return null;

            String jsonResponse = QueryUtils.fetchDataFromServer(url);

            return jsonResponse;
        }
    }
}
