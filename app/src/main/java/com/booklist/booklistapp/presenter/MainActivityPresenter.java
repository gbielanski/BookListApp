package com.booklist.booklistapp.presenter;


import com.booklist.booklistapp.view.MainActivityView;
import com.booklist.booklistapp.model.Book;
import com.booklist.booklistapp.repositories.BooksRepository;

import java.util.List;

public class MainActivityPresenter {
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
