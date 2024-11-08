package project.classes;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 * Main entity of the app
 */
public class Library implements Serializable {
    @Serial
    /**
     * Serial number for binary files
     */
    private static final long serialVersionUID = 1L;
    /**
     * List of all books mixed together
     */
    private ArrayList<Book> books;
    /**
     * Collection of books by category
     */
    private HashMap<String, ArrayList<Book>> booksByCategory;
    /**
     * Collection of books by author
     */
    private HashMap<String, ArrayList<Book>> booksByAuthor;
    /**
     * Collection of borrowing records
     */
    private Vector<BorrowRecord>  records;

    /**
     * Double massive for keeping track of the average rating for each book
     */
    private double[] avgRatings;
    /**
     * Keeps track of the number of ratings for each book
     */
    private int[] noRatings;

    /**
     * Ctor default, initializes all the parameters
     */
    public Library(){
        this.books = new ArrayList<>();
        this.booksByCategory = new HashMap<>();
        this.booksByAuthor = new HashMap<>();
        this.records = new Vector<>();
        this.avgRatings = new double[0];
        this.noRatings = new int[0];
    }

    public void addRecords(BorrowRecord rec){
        this.records.add(rec);
    }

    /**
     * Populates the HashMaps and the List with the book, no duplicates allowed
     * @param book new book to be inserted
     */
    public void addBook(Book book){
        if(books.contains(book)){
            System.out.println("Book already on stock.");
        } else{
            books.add(book);
            booksByCategory.putIfAbsent(book.getCategory(), new ArrayList<>());
            booksByCategory.get(book.getCategory()).add(book);
            booksByAuthor.putIfAbsent(book.getAuthor(), new ArrayList<>());
            booksByAuthor.get(book.getAuthor()).add(book);
            double[] newRatings = new double[avgRatings.length + 1];
            int[] newCount = new int[noRatings.length + 1];

            for(int i = 0; i < avgRatings.length;i++){
                newRatings[i] = avgRatings[i];
                newCount[i] = noRatings[i];
            }

            newRatings[avgRatings.length] = 0.0;
            newCount[noRatings.length] = 0;
            avgRatings = newRatings;
            noRatings = newCount;
        }
    }

    public void displayRatings(){
        for(int i = 0 ; i < books.size() ; i++){
            System.out.println(books.get(i) + "has a rating of " + avgRatings[i]);
        }
    }

    /**
     * Print the HashMap of categories
     */
    public void printCategories(){
        for(String category : booksByCategory.keySet()){
            System.out.println("Category: " + category);

            ArrayList<Book> list = booksByCategory.get(category);
            for(Book book : list){
                System.out.println("\t-"+book);
            }
        }
    }

    /**
     * Print the HashMap of authors
     */
    public void printAuthors(){
        for(String author : booksByAuthor.keySet()){
            System.out.println("Author: " + author);

            ArrayList<Book> list = booksByAuthor.get(author);
            for(Book book : list){
                System.out.println("\t-"+book);
            }
        }
    }

    /**
     * More flexible way of searching a book by keywords, either from the title or author or category
     * @param keyword
     */
    public void search(String keyword){
        ArrayList<Book> results = new ArrayList<>();
        for(Book book : books){
            if(book.getTitle().contains(keyword) || book.getAuthor().contains(keyword) || book.getCategory().contains(keyword)){
                results.add(book);
            }
        }

        if(results.size() == 0){
            System.out.println("No results.");
        } else {
            System.out.println("Found " + results.size() + " matching items: ");
            for(Book book : results){
                System.out.println(book);
            }
        }
    }

    /**
     * Borrow book, updates its availability status and issues a borrowing record
     * @param title
     */
    public void borrow(String title){
        for(Book book : books){
            if(book.getTitle().equals(title)){
                if(book.status()){
                    System.out.println("The desired book is not available at the moment.");
                } else {
                    book.setStatus();
                    BorrowRecord record = new BorrowRecord(title, LocalDate.now());
                    records.add(record);
                }
            }
        }
    }

    /**
     * Resets the availability and asks the user for a rating
     * @param title
     * @return
     */
    public boolean returnBook(String title){
        for(Book book : books){
            if(book.getTitle().equals(title)){
                if (book.status()) {
                    book.setStatus();
                    System.out.println("Book successfully returned.");
                    return true;
                } else{
                    System.out.println("You cannot return something that hasn't been borrowed yet. ;)");
                }
            }
        }
        return false;
    }

    /**
     * Adds the rating into the two massives
     * @param title book title
     * @param rating rating for the book
     */
    public void rateBook(String title, double rating){
        for(Book book : books){
            if(book.getTitle().equals(title)){
                int index = books.indexOf(book);
                avgRatings[index] = (avgRatings[index] * noRatings[index] + rating) / (noRatings[index] + 1);
            }
        }
    }

    /**
     * Getter for category collection
     * @return
     */
    public HashMap<String, ArrayList<Book>> getCategories(){
        return this.booksByCategory;
    }

    /**
     * Getter for author collection
     * @return
     */
    public HashMap<String, ArrayList<Book>> getAuthors(){
        return this.booksByAuthor;
    }

    /**
     * Getter for records collection
     * @return
     */
    public Vector<BorrowRecord> getRecords(){
        return this.records;
    }

}
