package com.example.booktracker_gayle;

public class PremiumBook extends Book{
//    public PremiumBook (String bookCode, String bookTitle, String bookAuthor, int numDays, boolean isBorrowed){
//        super(bookCode, bookTitle, bookAuthor, numDays, isBorrowed);
//    }

    public PremiumBook (int numDays){
        super(numDays);
    }
    @Override
    public double calculateTotalPrice(){
//        double totalPrice = 50.00 * getDaysBorrow();
//        return totalPrice;

        int premprice = 50;
        int borrowdays = getDaysBorrow();
        int totalprice = 0;
        if (borrowdays > 7) {
            totalprice = premprice * 7;
            borrowdays = borrowdays - 7;
            premprice = premprice + 25;
        }
        totalprice = totalprice + premprice * borrowdays;
        return totalprice;
    }
}