package com.booklist.booklistapp;

import com.booklist.booklistapp.model.Book;
import com.booklist.booklistapp.presenter.MainActivityPresenter;
import com.booklist.booklistapp.view.MainActivityView;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MainActivityPresenterTest {

    @Test
    public void shouldPass(){
        Assert.assertEquals(1, 1);
    }

    @Test
    public void shouldPassBookToPresenter(){
//        //given
//        MainActivityView view = new MockView();
//        BooksRepository booksRepository = new MockBookRepository();
//        // when
//        MainActivityPresenter presenter = new MainActivityPresenter(view, booksRepository);
//        presenter.loadBooks();
//
//        //then
//        assertEquals(true, ((MockView)view).displayBooksWithBooksCalled);
        Assert.assertEquals(1, 0);
    }

    @Test
    public void shouldHandleNoBooks()
    {
//        // given
//        MainActivityView view = new MockView();
//        BooksRepository repository = new MockBookRepositoryNoBooks();
//
//        // when
//        MainActivityPresenter presenter = new MainActivityPresenter(view, repository);
//        presenter.loadBooks();
//
//        // then
//        Assert.assertEquals(true, ((MockView)view).displayBooksWithNoBooksCalled);
        Assert.assertEquals(1, 0);
    }

//    private class MockView implements MainActivityView{
//
//        boolean displayBooksWithBooksCalled = false;
//        boolean displayBooksWithNoBooksCalled = false;
//        @Override
//        public void displayBooks(List<Book> books) {
//            if (books.size() == 3) displayBooksWithBooksCalled = true;
//        }
//
//        @Override
//        public void displayNoBooks() {
//            displayBooksWithNoBooksCalled = true;
//        }
//    }
//
//    private class MockBookRepository implements BooksRepository{
//
//        @Override
//        public List<Book> getBooks() {
//            return Arrays.asList(new Book(), new Book(), new Book());
//        }
//    }
//
//    private class MockBookRepositoryNoBooks implements BooksRepository{
//
//        @Override
//        public List<Book> getBooks() {
//            return new ArrayList<>();
//        }
//    }

}