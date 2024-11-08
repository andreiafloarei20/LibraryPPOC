package project;

import project.classes.FileManager;
import project.classes.Library;
import project.classes.UserInterface;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        FileManager fileManager= new FileManager(library);
        UserInterface ui = new UserInterface(scanner, library, fileManager);

        ui.start();
    }
}
