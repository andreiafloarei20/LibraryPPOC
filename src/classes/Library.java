package classes;

import java.io.*;
import java.util.ArrayList;

public class Library implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Book> books;

    public Library(){
        this.books = new ArrayList<Book>();
    }

    public void addBook(Book book){
        this.books.add(book);
    }

    public void borrowBook(String title){
        for(Book book: books){
            if(book.getTitle().equals(title)){
                book.borrow();
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void returnBook(String title){
        for(Book book : books){
            if(book.getTitle().equals(title)){
                book.returnBook();
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void listBooks(){
        for(Book book : books){
            System.out.println(book);
        }
    }

    public void saveLibrary(String filename){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){
            oos.writeObject(this);
            System.out.println("Library data saved successfully");
        }catch(IOException e){
            System.out.println("Error saving library: " + e.getMessage());
        }
    }

    public static Library loadLibrary(String filename){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){
            return (Library) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Creating a new library...");
            return new Library();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading library: " + e.getMessage());
            return new Library();
        }
    }


}
