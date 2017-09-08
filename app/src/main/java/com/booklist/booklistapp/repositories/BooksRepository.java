package com.booklist.booklistapp.repositories;

import com.booklist.booklistapp.model.Book;

import java.util.List;

public interface BooksRepository {

    List<Book> getBooks();
}
