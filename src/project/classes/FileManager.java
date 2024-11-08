package project.classes;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;

public class FileManager {
    private Library library;

    public FileManager(Library library){
        this.library = library;
    }
    public void getOrder(String filename){
        int count = 0;
        try(Scanner scan = new Scanner(Paths.get(filename))){
            while(scan.hasNextLine()){
                String input = scan.nextLine();
                String[] data = input.split(",");

                String title = data[0];
                String author = data[1];
                String category = data[2];

                library.addBook(new Book(title, author, category));
                count++;
            }

            System.out.println("Successfully added " + count + " books!");
        }catch(Exception e){
            System.out.println("No order found." );
        }
    }

    public void getRecords(String filename){
        int count = 0;
        try(Scanner reader = new Scanner(Paths.get(filename))){
            while(reader.hasNextLine()){
                String input = reader.nextLine();
                String[] data = input.split(",");

                String title = data[0];
                LocalDate date = LocalDate.parse(data[1]);

                library.addRecords(new BorrowRecord(title, date));
                count++;
            }

            System.out.println("Successfully added " + count + " records");
        }catch(Exception e){
            System.out.println("File not found.");
        }
    }

    public void issueCategoryReport(String category){
        String path = "./categories/"+category+".txt";
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
            writer.write("\t\tAvailable " + category + " books:\n" );
            for(Book book : library.getCategories().get(category)){
                writer.write("\n"+book.toString());
            }

            System.out.println("Category report successfully issued!");
        }catch(IOException e){
            System.out.println("File not found. " + e.getMessage());
        }
    }

    public void issueAuthorReport(String author){
        String path = "./authors/"+author+".txt";
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
            writer.write("/t/tAvailable books from " + author + ":\n");
            for(Book book : library.getAuthors().get(author)){
                writer.write("\n" + book.toString());
            }

            System.out.println("Author report successfully issued.");
        }catch(IOException e){
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
