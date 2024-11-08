package project.classes;

import java.time.LocalDate;

public class BorrowRecord {
    private String bookTitle;
    private LocalDate borrowDate;

    public BorrowRecord(String title, LocalDate date){
        this.bookTitle = title;
        this.borrowDate = date;
    }

    public String toString(){
        return "Book: " + bookTitle + ", Borrowed at: " + this.borrowDate;
    }
}
