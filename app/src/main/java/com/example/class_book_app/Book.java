package com.example.class_book_app;

public class Book {
    private String topic;
    private String isbn;
    private String name;
    private String author;
    private String publisher;
    private String description;
    private String imageName;

    public Book(String topic, String isbn, String name, String author, String publisher, String description, String imageName) {
        this.topic = topic;
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.description = description;
        this.imageName = imageName;
    }

    public String getTopic() {
        return topic;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDescription() {
        return description;
    }

    public String getImageName() {
        return imageName;
    }
}
