package com.example.class_book_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static String topics[] = {"Computer", "English", "Math", "Science"};
    public static ArrayList<Book> allBooks = new ArrayList<>();
    public static ArrayList<Book> topicBooks = new ArrayList<>();
    public static ArrayList<Book> borrowBooks = new ArrayList<>();

    public static Book selectedBook;
    ListView booksLV;
    Spinner topicSp;
    Button addBookBtn, viewCartBtn, detailBookBtn;
    TextView borrowedBooksLbl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fillBooks();


        booksLV = findViewById(R.id.booksLV);
        topicSp = findViewById(R.id.topicSp);
        addBookBtn = findViewById(R.id.addBookBtn);
        viewCartBtn = findViewById(R.id.viewCartBtn);
        detailBookBtn = findViewById(R.id.detailBookBtn);
        borrowedBooksLbl = findViewById(R.id.borrowedBooksLbl);

        topicSp.setAdapter(new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, topics));

        borrowedBooksLbl.setText(String.valueOf(borrowBooks.size()));

        topicSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getTopicBooks(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                getTopicBooks(0);
            }
        });

        booksLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedBook = topicBooks.get(position);
            }
        });

        addBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(borrowBooks.size() >= 5){
                    Toast.makeText(getBaseContext(),"Borrow Limit (5 books) reached",Toast.LENGTH_LONG);
                }else{
                    boolean alreadyAdded = false;
                    for (int i = 0; i < borrowBooks.size(); i++){
                        if(borrowBooks.get(i).getIsbn().equalsIgnoreCase(selectedBook.getIsbn())){
                            alreadyAdded = true;
                            break;
                        }
                    }
                    if (alreadyAdded){
                        Toast.makeText(getBaseContext(),"Book already in the cart", Toast.LENGTH_LONG);
                    }else{
                        borrowBooks.add(selectedBook);
                        borrowedBooksLbl.setText(String.valueOf(borrowBooks.size()));
                    }

                }
            }
        });
        detailBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getBaseContext(), DetailActivity.class));
            }
        });
        viewCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getBaseContext(), CartActivity.class));
            }
        });

    }
    private void getTopicBooks(int position){
        topicBooks.clear();
        for (Book bk : allBooks) {
            if (bk.getTopic().equals(topics[position])){
                topicBooks.add(bk);
            }
        }
        booksLV.setAdapter(new BookAdapter(getBaseContext(),topicBooks));
        selectedBook = topicBooks.get(0);
    }
    private void fillBooks(){
        if(allBooks.size() == 0){
            allBooks.add(new Book("Computer","9780262033848", "Introduction to Algorithms","Thomas H. Cormen", "The MIT Press", "Some books on algorithms are rigorous but incomplete; others cover masses of material but lack rigor. Introduction to Algorithms uniquely combines rigor and comprehensiveness. The book covers a broad range of algorithms in depth, yet makes their design and analysis accessible to all levels of readers. Each chapter is relatively self-contained and can be used as a unit of study. The algorithms are described in English and in a pseudocode designed to be readable by anyone who has done a little programming. The explanations have been kept elementary without sacrificing depth of coverage or mathematical rigor.","introductionalgorithms"));
            allBooks.add(new Book("Computer","9780201896831", "The Art of Computer Programming", "Donald Knuth", "Addison-Wesley Professional;", "This first volume in the series begins with basic programming concepts and techniques, then focuses more particularly on information structures–the representation of information inside a computer, the structural relationships between data elements and how to deal with them efficiently. Elementary applications are given to simulation, numerical methods, symbolic computing, software and system design. Dozens of simple and important algorithms and techniques have been added to those of the previous edition. The section on mathematical preliminaries has been extensively revised to match present trends in research.", "artprogramming"));
            allBooks.add(new Book("Math", "9780691118809", "The Princeton Companion to Mathematics", "Timothy Gowers", "Princeton University Press", "This is a one-of-a-kind reference for anyone with a serious interest in mathematics. Edited by Timothy Gowers, a recipient of the Fields Medal, it presents nearly two hundred entries, written especially for this book by some of the world's leading mathematicians, that introduce basic mathematical tools and vocabulary; trace the development of modern mathematics; explain essential terms and concepts; examine core ideas in major areas of mathematics; describe the achievements of scores of famous mathematicians; explore the impact of mathematics on other disciplines such as biology, finance, and music--and much, much more.", "math1"));
            allBooks.add(new Book("Math", "9780471433347", "Abstract Algebra","David S. Dummit","Wiley","This revision of Dummit and Foote's widely acclaimed introduction to abstract algebra helps students experience the power and beauty that develops from the rich interplay between different areas of mathematics.\n\nThe book carefully develops the theory of different algebraic structures, beginning from basic definitions to some in-depth results, using numerous examples and exercises to aid the student's understanding. With this approach, students gain an appreciation for how mathematical structures and their interplay lead to powerful results and insights in a number of different settings.","math2"));
            allBooks.add(new Book("English", "9781108781626", "IELTS 15 General Training", "Cambridge University Press", "Cambridge English", "Inside IELTS 15 General Training with Answers with Audio you'll find FOUR complete examination papers plus details of the different parts of the test and the scoring system, so you can familiarise yourself with the General Training test format and practise your exam technique. Download the audio for the Listening tests, example Speaking test video, answer keys with extra explanations and sample Writing answers (instructions on inner front cover), or access your audio and video directly via QR codes in the book.","english1"));
            allBooks.add(new Book("English","9780995346703","Test Expert: Writing Practice for CELPIP", "Christien Lee", "Christien Lee","• More than 40 high-scoring, easy-to-understand model responses\n• More than 40 writing topics (20 for Task 1, and another 20 for Task 2)\n• Practice exercises, challenges, and study tips to help you improve quickly\n• Detailed introductions and step-by-step guides to both writing tasks\n• Over 120 useful words and phrases for natural, accurate writing","english2"));
            allBooks.add(new Book("Science","9781465419651","The Science Book: Big Ideas Simply Explained","DK","DK","With over 225,000 copies in print, DK's Big Ideas series has struck a chord with readers fascinated-but also intimidated-by complex subjects like philosophy, psychology, politics, and religion.\nThe newest title in this successful and acclaimed series is The Science Book, an inventive visual take on astronomy, biology, chemistry, geology, and physics. With eye-catching artwork, step-by-step diagrams, and illustrations that break down complicated ideas into manageable concepts, The Science Book will have readers conversant in genetic engineering, black holes, and global warming in no time. Along the way are found mini-biographies of the most well-known scientists, and a glossary of helpful scientific terms.","science1"));
            allBooks.add(new Book("Science","9781465473226","Science: A Visual Encyclopedia","DK Children","DK Children","The exciting exploration of biology, chemistry, and physics is vital reading for curious minds. Science becomes simple and straightforward, so you never get your wires crossed again. Hundreds of pages feature stunning images, simple graphics, and crystal-clear text. What makes a firework go bang? How do plants make food from sunlight? What makes a robot clever? Find the answers to all these questions and much, much more.","science2"));
        }
    }

}