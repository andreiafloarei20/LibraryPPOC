package project.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class Library {
    private ArrayList<Book> books;
    private HashMap<String, ArrayList<Book>> booksByCategory;
    private HashMap<String, ArrayList<Book>> booksByAuthor;
    private Vector<BorrowRecord>  records;

    public Library(){
        this.books = new ArrayList<>();
        this.booksByCategory = new HashMap<>();
        this.booksByAuthor = new HashMap<>();
        this.records = new Vector<>();
    }

    public void addRecords(BorrowRecord rec){
        this.records.add(rec);
    }

    public void addBook(Book book){
        if(books.contains(book)){
            books.get(books.indexOf(book)).increase();
//            booksByCategory.get(book.getCategory()).get(booksByCategory.in);
        } else{
            books.add(book);
            book.increase();
            booksByCategory.putIfAbsent(book.getCategory(), new ArrayList<>());
            booksByCategory.get(book.getCategory()).add(book);
            booksByAuthor.putIfAbsent(book.getAuthor(), new ArrayList<>());
            booksByAuthor.get(book.getAuthor()).add(book);
//            book.increase();
        }
    }

    public void printCategories(){
        for(String category : booksByCategory.keySet()){
            System.out.println("Category: " + category);

            ArrayList<Book> list = booksByCategory.get(category);
            for(Book book : list){
                System.out.println("\t-"+book);
            }
        }
    }

    public void printAuthors(){
        for(String author : booksByAuthor.keySet()){
            System.out.println("Author: " + author);

            ArrayList<Book> list = booksByAuthor.get(author);
            for(Book book : list){
                System.out.println("\t-"+book);
            }
        }
    }

    public HashMap<String, ArrayList<Book>> getCategories(){
        return this.booksByCategory;
    }

    public HashMap<String, ArrayList<Book>> getAuthors(){
        return this.booksByAuthor;
    }

    public Vector<BorrowRecord> getRecords(){
        return this.records;
    }

}
