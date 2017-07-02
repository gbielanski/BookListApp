package com.booklist.booklistapp;

class Book {
    public Book(String authorName, String title) {
        this.mAuthorName = authorName;
        this.mTitle = title;
    }

    public String getAuthorName() {
        return mAuthorName;
    }

    public String getTitle() {
        return mTitle;
    }

    private String mAuthorName;
    private String mTitle;
}
