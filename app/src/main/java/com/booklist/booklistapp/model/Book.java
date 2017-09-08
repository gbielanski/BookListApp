package com.booklist.booklistapp.model;

import android.support.annotation.NonNull;

public class Book {
    public Book() {
    }

    public Book(String [] authorsNames, String title) {
        this.mAuthorsNames = authorsNames;
        this.mTitle = title;
    }

    @NonNull
    public String getTitle() {
        return mTitle;
    }

    public String getFormattedAuthorsNames(){
        StringBuilder formattedAuthorsName = new StringBuilder("---");
        for (int i = 0; i < mAuthorsNames.length; i++) {
            if(i == 0) {
                formattedAuthorsName.delete(0, formattedAuthorsName.length());
                formattedAuthorsName.append(mAuthorsNames[i]);
            }
            else {
                formattedAuthorsName.append(", ");
                formattedAuthorsName.append(mAuthorsNames[i]);
            }
        }

        return formattedAuthorsName.toString();
    }

    private String [] mAuthorsNames = {""};
    private String mTitle = "";
}
