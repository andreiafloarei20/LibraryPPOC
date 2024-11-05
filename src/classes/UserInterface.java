package classes;

import java.sql.SQLOutput;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private Library library;

    public UserInterface(Scanner scanner, Library library){
        this.scanner = scanner;
        this.library = library;
    }

    public void start(){
        System.out.println("==============================");
        System.out.println();
        System.out.println("    Library Management App    ");
        System.out.println();
        System.out.println("==============================");
        System.out.println();

        while(true){
            System.out.println("Enter command: sex");
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
                break;
            }
        }

    }
}
