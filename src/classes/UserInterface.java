package classes;

import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private Library library;

    public UserInterface(Scanner scanner, Library library){
        this.scanner = scanner;
        this.library = library;
    }

    public void start() throws IOException, ClassNotFoundException {
        System.out.println("==============================");
        System.out.println();
        System.out.println("    Library Management App    ");
        System.out.println();
        System.out.println("==============================");
        System.out.println();

        while(true){
            System.out.println("Enter command: ");
            System.out.println("list - Display the available books");
            System.out.println("add - Add a book manually");
            System.out.println("exit - Exit App");

            String command = this.scanner.nextLine();

            if(command.equals("list")){
                System.out.println();
                System.out.println("Available books:");
                this.library.listBooks();
                System.out.println();
            }

            if(command.equals("add")){

                System.out.println();
                System.out.println("Enter Title: ");
                String title = scanner.nextLine();
                System.out.println("Enter Author: ");
                String author = scanner.nextLine();
                System.out.println("Enter Category: ");
                String category = scanner.nextLine();

                this.library.addBook(new Book(title, author, category));
                System.out.println();
            }

            if(command.equals("exit")){
                System.out.println();
                System.out.println("See you around!");
                library.saveLibrary("library.dat");
                break;
            }
        }

    }



//    public void loadData(String filename, Library library){
//        try(Scanner reader = new Scanner(Paths.get(filename))){
//            while(reader.hasNextLine()){
//                String book = reader.nextLine();
//                String[] data = book.split(",");
//
//                String title = data[0];
//                String author = data[1];
//                String category = data[2];
//
//                library.addBook(new Book(title, author, category));
//            }
//        }catch(Exception e){
//            System.out.println("Could not open the file." + e.getMessage());
//        }
//    }



}
