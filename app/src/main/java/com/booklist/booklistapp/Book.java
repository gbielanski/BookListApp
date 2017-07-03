package com.booklist.booklistapp;

import static android.R.attr.author;

class Book {
    public Book(String [] authorsNames, String title) {
        this.mAuthorsNames = authorsNames;
        this.mTitle = title;
    }

    public String [] getAuthorsNames() {
        return mAuthorsNames;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getFormattedAuthorsNames(){
        StringBuilder formattedAuthorsName = new StringBuilder("no data about author");
        for (int i = 0; i < mAuthorsNames.length; i++) {
            if(i == 0) {
                formattedAuthorsName.delete(0, formattedAuthorsName.length());
                formattedAuthorsName.append(mAuthorsNames[i]);
            }
            else
                formattedAuthorsName.append(", " + mAuthorsNames[i]);
        }

        return formattedAuthorsName.toString();
    }

    private String [] mAuthorsNames;
    private String mTitle;
}
