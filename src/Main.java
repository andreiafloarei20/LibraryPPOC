import classes.Book;
import classes.Library;
import classes.UserInterface;

import java.io.IOException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String filename = "library.dat";
        Scanner scan = new Scanner(System.in);
        Library library = Library.loadLibrary(filename);

        UserInterface UI = new UserInterface(scan, library);
        Book b1 = new Book("1984", "George Orwell", "Fiction");
        Book b2 = new Book("Brave new world", "Aldous Huxley", "Fiction");
        Book b3 = new Book("Dune", "Frank Herbert", "SF");

        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);

        UI.start();
    }
}