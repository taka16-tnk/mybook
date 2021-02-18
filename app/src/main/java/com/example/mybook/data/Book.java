package com.example.mybook.data;

public class Book {

    private int _id;
    private String book_title;
    private String book_author;
    private int book_pages;

    public Book(int _id, String book_title, String book_author, int book_pages) {
        this._id = _id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages = book_pages;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getBookTitle() {
        return book_title;
    }

    public void setBookTitle(String book_title) {
        this.book_title = book_title;
    }

    public String getBookAuthor() {
        return book_author;
    }

    public void setBookAuthor(String book_author) {
        this.book_author = book_author;
    }

    public int getBookPages() {
        return book_pages;
    }

    public void setBookPages(int book_pages) {
        this.book_pages = book_pages;
    }
}
