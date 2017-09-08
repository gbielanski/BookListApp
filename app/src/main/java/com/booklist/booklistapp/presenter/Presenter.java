package com.booklist.booklistapp.presenter;

/**
 * Created by gbielanski on 9/8/2017.
 */

public interface Presenter {

    void onCreate(String savedSearchString);

    void onSearchButton(String searchString);
}
