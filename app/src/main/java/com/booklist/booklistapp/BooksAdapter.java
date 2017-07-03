package com.booklist.booklistapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

class BooksAdapter extends ArrayAdapter<Book> {
    public BooksAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Book> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listViewItem = convertView;

        if(listViewItem == null)
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.book_list_item, parent, false);
        Book book = getItem(position);

        TextView tv_book_title = (TextView) listViewItem.findViewById(R.id.book_title);
        tv_book_title.setText(book.getTitle());

        TextView tv_book_authors = (TextView) listViewItem.findViewById(R.id.book_authors);
        tv_book_authors.setText(book.getFormattedAuthorsNames());

        return listViewItem;
    }
}
