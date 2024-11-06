package classes;

import java.io.*;
import java.nio.file.Paths;
import java.sql.SQLOutput;
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
            System.out.println("remove - Remove a book from library");
            System.out.println("update - Add the new books in the system");
            System.out.println("reports - Generate Reports");
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

            if(command.equals("remove")){
                System.out.println();
                System.out.println("Enter title:");
                String title = scanner.nextLine();

                library.removeBook(title);
            }

            if(command.equals("update")){
                System.out.println();
                System.out.println("Enter order: ");
                String order = scanner.nextLine();
                library.receiveStock(order);
            }

            if(command.equals("reports")){
                System.out.println();
                System.out.println("=============================");
                System.out.println("1. See all from an author");
                System.out.println("2. See all from a category");

                String choice = scanner.nextLine();
                if(choice.equals("1")){
                    System.out.println("Enter author: ");
                    String author = scanner.nextLine();

                    getBooksByAuthor(author, "./authors/"+author+".txt");
                }

                if(choice.equals("2")){
                    System.out.println("Enter category: ");
                    String category = scanner.nextLine();

                    getBooksByCategory(category, "./categories/"+category+".txt");
                }
            }

            if(command.equals("exit")){
                System.out.println();
                System.out.println("See you around!");
                library.saveLibrary("library.dat");
                break;
            }
        }

    }

    public void getBooksByAuthor(String author, String file){
        int count = 0;
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            for(Book book : this.library.getBooks()){
                if(book.getAuthor().equals(author)){
                    count++;
                    writer.write(book + "\n");
                }
            }
            if(count == 0){
                System.out.println("No");
            } else {
                System.out.println("Success");
            }
        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void getBooksByCategory(String category, String file){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            for(Book book : this.library.getBooks()){
                if(book.getCategory().equals(category)){
                    writer.write(book + "\n");
                }
            }
        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

}
