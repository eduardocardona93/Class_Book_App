package com.example.class_book_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    ListView cartLV;
    Button deleteCartBookBtn, placeOrderBtn, cartReturnMainBtn;
    TextView cartBorrowedBooksLbl;
    ConstraintLayout cartLayout;
    Book deleteBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartLV = findViewById(R.id.cartLV);
        deleteCartBookBtn = findViewById(R.id.deleteCartBookBtn);
        placeOrderBtn = findViewById(R.id.placeOrderBtn);
        cartReturnMainBtn = findViewById(R.id.cartReturnMainBtn);
        cartBorrowedBooksLbl = findViewById(R.id.cartBorrowedBooksLbl);
        cartLayout = findViewById(R.id.cartLayout);

        cartBorrowedBooksLbl.setText(String.valueOf(MainActivity.borrowBooks.size()));
        cartLV.setAdapter(new BookAdapter(getBaseContext(),MainActivity.borrowBooks));
        cartLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deleteBook = MainActivity.borrowBooks.get(position);
            }
        });

        placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.selectedBook = null;
                MainActivity.borrowBooks = new ArrayList<>();
                deleteBook = null;
                cartLV.setAdapter(new BookAdapter(getBaseContext(),MainActivity.borrowBooks));
                cartBorrowedBooksLbl.setText(String.valueOf(MainActivity.borrowBooks.size()));
            }
        });

        cartReturnMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getBaseContext(), MainActivity.class));
            }
        });
        deleteCartBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deleteBook !=null){
                    MainActivity.borrowBooks.remove(deleteBook);
                    cartLV.setAdapter(new BookAdapter(getBaseContext(),MainActivity.borrowBooks));
                    cartBorrowedBooksLbl.setText(String.valueOf(MainActivity.borrowBooks.size()));
                    deleteBook = null;
                }else{
                    Snackbar.make(cartLayout,"Not selected Book",Snackbar.LENGTH_SHORT);
                }


            }
        });

    }
}