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

        UI.start();
    }
}