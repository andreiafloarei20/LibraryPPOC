package classes;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Library implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Book> books;

    public Library(){
        this.books = new ArrayList<Book>();
    }

    public void addBook(Book book){
        this.books.add(book);
    }

    public void removeBook(String title){
        for(Book book : books){
            if(book.getTitle().equals(title)){
                this.books.remove(book);
                System.out.println("Book successfully removed!");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void receiveStock(String filename){
        try(Scanner reader = new Scanner(Paths.get(filename))){
            while(reader.hasNextLine()){
                String input = reader.nextLine();
                String[] data = input.split(",");

                String name = data[0];
                String author = data[1];
                String category = data[2];
                this.addBook(new Book(name, author, category));
            }
        }catch(Exception e ){
            System.out.println("Error: " + e.getMessage());
        }
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

    public ArrayList<Book> getBooks(){
        return this.books;
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
