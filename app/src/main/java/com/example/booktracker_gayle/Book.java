package com.example.booktracker_gayle;

public class Book {
    private String bookCode;
    private String bookAuthor;
    private String bookTitle;
    private int numDays;
    private boolean isBorrowed;
    private int totalPrice;

    public Book(){
        this.bookCode = "";
        this.bookTitle = "";
        this.bookAuthor = "";
        this.numDays = 0;
        this.isBorrowed = false;
    }

    //Constructor
    public Book(String bookCode, String bookTitle, String bookAuthor, int numDays, boolean isBorrowed){
        this.bookCode = bookCode;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.numDays = 0;
        this.isBorrowed = false;
    }

    public Book(int numDays){
        this.numDays = numDays;
    }

    public double calculateTotalPrice(){
        return totalPrice;
    }

    public int getDaysBorrow(){
        return numDays;
    }

    public int getTotalPrice(){
        return totalPrice;
    }
}
