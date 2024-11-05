package classes;

import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books;

    public Library(){
        this.books = new ArrayList<Book>();
    }

    public void addBook(Book book){
        this.books.add(book);
    }

    public void listBooks(){
        for(Book book : books){
            System.out.println(book);
        }
    }
}
