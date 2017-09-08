package com.booklist.booklistapp;


import com.booklist.booklistapp.repositories.BooksRepository;

import java.math.BigInteger;
import java.util.List;

class MainActivityPresenter {
    private MainActivityView view;
    private BooksRepository booksRepository;

    public MainActivityPresenter(MainActivityView view, BooksRepository booksRepository) {
        this.view = view;
        this.booksRepository = booksRepository;
    }

    public void loadBooks() {
        List<Book> bookList = booksRepository.getBooks();

        if(bookList.isEmpty())
            view.displayNoBooks();
        else
            view.displayBooks(bookList);
    }
}
