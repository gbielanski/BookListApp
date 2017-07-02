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

    class BookAsyncTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... url) {

            if(url.length == 0 || TextUtils.isEmpty(url[0]))
                return null;

            return null;

        }
    }
}
