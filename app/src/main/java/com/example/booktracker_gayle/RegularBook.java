package com.example.booktracker_gayle;

public class RegularBook extends Book{
//    public RegularBook(String bookCode, String bookTitle, String bookAuthor, int numDays, boolean isBorrowed){
//        super(bookCode, bookTitle, bookAuthor, numDays, isBorrowed);
//    }
    public RegularBook (int numDays){
        super(numDays);
    }
    @Override
    public double calculateTotalPrice(){
        double totalPrice = 20.00 * getDaysBorrow();
        return totalPrice;
    }
}



