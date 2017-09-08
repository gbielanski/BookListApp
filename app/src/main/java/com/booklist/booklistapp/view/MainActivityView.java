package com.booklist.booklistapp.view;


import com.booklist.booklistapp.model.Book;

import java.util.List;

public interface MainActivityView {
    void displayBooks(List<Book> books);
    void displayNoBooks();
}
