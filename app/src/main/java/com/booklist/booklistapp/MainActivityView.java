package com.booklist.booklistapp;


import java.util.List;

public interface MainActivityView {
    void displayBooks(List<Book> books);
    void displayNoBooks();
}
