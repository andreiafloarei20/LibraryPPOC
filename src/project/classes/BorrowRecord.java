package project.classes;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Secondary class, works like a registry for borrowings
 */
public class BorrowRecord implements Serializable {
    @Serial
    /**
     * Serial number for binary files
     */
    private static final long serialVersionUID = 1L;
    /**
     * The title of the borrowed book
     */
    private String bookTitle;
    /**
     * The date on which the borrowing takes place
     */
    private LocalDate borrowDate;

    /**
     * Constructor with all params
     * @param title new book's title
     * @param date current/provided date
     */
    public BorrowRecord(String title, LocalDate date){
        this.bookTitle = title;
        this.borrowDate = date;
    }

    /**
     * Getter for the borrowed book
     * @return String title
     */
    public String getBookTitle(){
        return this.bookTitle;
    }

    /**
     * Getter for borrowing date
     * @return LocalDate
     */
    public LocalDate getDate(){
        return this.borrowDate;
    }

    /**
     * Used to print records in the console
     * @return  String with record details
     */
    public String toString(){
        return "Book: " + bookTitle + ", Borrowed at: " + this.borrowDate;
    }
}
