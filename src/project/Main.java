package project;

import project.classes.FileManager;
import project.classes.Library;
import project.classes.UserInterface;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

/**
 * Entry point of the application
 */
public class Main {
    /**
     * Loads the library data from previous sessions, ensures data persistence
     * @param filename so-called local database from a file
     * @return the library initialized with the data or an empty one if it's the first use of the system
     */
    public static Library loadData(String filename){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){
            Library library = (Library)ois.readObject();
            if(library == null){
                library = new Library();
            }
            return library;

        }catch(Exception e ){
            System.out.println("Error: " + e.getMessage());
        }
        return new Library();
    }

    /**
     * Initializes the needed object for d
     *the UI to work, and then starts the application from start() method
     * @param args
     */
    public static void main(String[] args){
        String filename = "library.dat";
        Scanner scanner = new Scanner(System.in);
        Library library = loadData(filename);
        FileManager fileManager= new FileManager(library);
        UserInterface ui = new UserInterface(scanner, library, fileManager);

        ui.start();
    }
}
