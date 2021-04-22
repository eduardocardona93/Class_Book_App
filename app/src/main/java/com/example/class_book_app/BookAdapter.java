package com.example.class_book_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BookAdapter extends BaseAdapter {
    private ArrayList<Book> bookList;
    private LayoutInflater inflater;

    public BookAdapter(Context context, ArrayList<Book> bookList) {
        this.bookList = bookList;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BookViewHolder holder;
        if(convertView==null) {
            convertView = inflater.inflate(R.layout.book_row, null);
            holder = new BookViewHolder();
            holder.isbnBookListLbl = convertView.findViewById(R.id.isbnBookListLbl);
            holder.nameBookListLbl = convertView.findViewById(R.id.nameBookListLbl);
            holder.authorBookListLbl = convertView.findViewById(R.id.authorBookListLbl);
            convertView.setTag(holder);
        }else{
            holder=(BookViewHolder) convertView.getTag();
        }
        holder.isbnBookListLbl.setText(bookList.get(position).getIsbn());
        holder.nameBookListLbl.setText(bookList.get(position).getName());
        holder.authorBookListLbl.setText(bookList.get(position).getAuthor());

        return convertView;
    }

    private class BookViewHolder{
        private TextView isbnBookListLbl,nameBookListLbl,authorBookListLbl;
    }
}
