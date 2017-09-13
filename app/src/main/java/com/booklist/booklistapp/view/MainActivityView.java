package com.booklist.booklistapp.view;





import android.content.Context;
import android.support.v4.app.LoaderManager;

import com.booklist.booklistapp.model.Book;

import java.util.List;

public interface MainActivityView {
    void displayBooks(List<Book> books);
    void displayNoBooks();
    void displayNoConnection();
    LoaderManager getViewLoaderManager();
    Context getViewContext();
}
