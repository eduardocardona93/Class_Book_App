package com.example.class_book_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    TextView bookDetailNameLbl, bookDetailIsbnLbl, bookDetailAuthorLbl, bookDetailPublisherLbl, bookDetailDescriptionLbl;
    Button bookDetailAddBtn, detailReturnMainBtn;
    ImageView bookDetailImg;

    Book detailBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        bookDetailNameLbl = findViewById(R.id.bookDetailNameLbl);
        bookDetailIsbnLbl = findViewById(R.id.bookDetailIsbnLbl);
        bookDetailAuthorLbl = findViewById(R.id.bookDetailAuthorLbl);
        bookDetailPublisherLbl = findViewById(R.id.bookDetailPublisherLbl);
        bookDetailDescriptionLbl = findViewById(R.id.bookDetailDescriptionLbl);
        bookDetailAddBtn = findViewById(R.id.bookDetailAddBtn);
        detailReturnMainBtn = findViewById(R.id.detailReturnMainBtn);
        bookDetailImg = findViewById(R.id.bookDetailImg);


        detailBook = MainActivity.selectedBook;
        detailReturnMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getBaseContext(), MainActivity.class));

            }
        });

        if(detailBook != null){
            bookDetailNameLbl.setText(detailBook.getName());
            bookDetailIsbnLbl.setText(detailBook.getIsbn());
            bookDetailAuthorLbl.setText(detailBook.getAuthor());
            bookDetailPublisherLbl.setText(detailBook.getPublisher());
            bookDetailDescriptionLbl.setText(detailBook.getDescription());
            bookDetailImg.setImageResource(getResources().getIdentifier(detailBook.getImageName(),"mipmap",getPackageName()));
            bookDetailAddBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(MainActivity.borrowBooks.size() >= 5){
                        Toast.makeText(getBaseContext(),"Borrow Limit (5 books) reached",Toast.LENGTH_LONG);
                    }else{
                        boolean alreadyAdded = false;
                        for (int i = 0; i < MainActivity.borrowBooks.size(); i++){
                            if(MainActivity.borrowBooks.get(i).getIsbn().equals(detailBook.getIsbn())){
                                alreadyAdded = true;
                                break;
                            }
                        }
                        if (alreadyAdded){
                            Toast.makeText(getBaseContext(),"Book already in the cart", Toast.LENGTH_LONG);
                        }else{
                            MainActivity.borrowBooks.add(detailBook);
                            startActivity( new Intent(getBaseContext(), MainActivity.class));
                        }

                    }
                }
            });
        }else{
            Toast.makeText(getBaseContext(),"Book not selected", Toast.LENGTH_LONG);

//            startActivity( new Intent(getBaseContext(), MainActivity.class));

        }
    }
}