package project.classes;

import java.io.Serial;
import java.io.Serializable;

/**
 * This class contains the main character of this application, Books
 */
public class Book implements Serializable {
    @Serial
    /**
     * serial number for saving in binary files
     */
    private static final long serialVersionUID = 1L;
    /**
     * The title of a Book object
     */
    private String title;
    /**
     * The author of the book represented by the object
     */
    private String author;
    /**
     * The category/genre of the book
     */
    private String category;
    /**
     * Boolean variable used to track if the book is available or borrowed by somebody else
     */
    boolean isBorrowed;

    /**
     * Constructor with all parameters
     * @param title
     * @param author
     * @param category
     */
    public Book(String title, String author, String category){
        this.title = title;
        this.author = author;
        this.category = category;
        this.isBorrowed = false;
    }

    /**
     * Getter for title
     * @return the title of the book
     */
    public String getTitle(){
        return this.title;
    }

    /**
     * Getter for author
     * @return the author of the book
     */
    public String getAuthor(){
        return this.author;
    }

    /**
     * Getter for the category
     * @return the category of a book
     */
    public String getCategory(){
        return this.category;
    }

    /**
     *Getter for isBorrowed, checks if the book is borrowed or not
     * @return true if borrowed, false if available
     */
    public boolean status(){
        return this.isBorrowed;
    }

    /**
     * Setter for isBorrowed, works like a switch. If true makes false and vice versa
     */
    public void setStatus(){
        this.isBorrowed = !this.isBorrowed;
    }

    /**
     * Checks if two book objects are equal, based on their title and author
     * @param obj the book to be compared with
     * @return true if the books are equal, false otherwise
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return title.equals(book.title) && author.equals(book.author);
    }

    @Override
    public int hashCode() {
        return title.hashCode() + author.hashCode();
    }

    /**
     * Used to print books in the console
     * @return String with book's details
     */
    public String toString(){
        return "Title: " + this.title + ", Author: " + this.author + ", Available: " + (isBorrowed ? "already borrowed" : "yes");
    }
}
