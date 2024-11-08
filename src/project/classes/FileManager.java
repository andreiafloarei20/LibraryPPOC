package project.classes;

import project.exceptions.InvalidAuthor;
import project.exceptions.InvalidCategory;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Utility class, used for the operations that work with files, especially text files
 */
public class FileManager {
    /**
     * Reference to the main entity of the app, the library
     */
    private Library library;

    /**
     * Ctor with params
     * @param library sets the provided library object
     */
    public FileManager(Library library){
        this.library = library;
    }

    /**
     * It loads bulk data to populate the application, some pre-written books
     * Could simulate the receiving of boxes with books from Humanitas, let's say, or as donations
     * from people and loading them into the system
     * @param filename the file with the so-called oreder
     */
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

    /**
     * Same as above, but loads bulk borrowing records
     * @param filename
     */
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

    /**
     * Iterates through the Category based HashMap and writes in a txt file the report
     * of the specified category
     * @param category the desired category to list the books from
     */
    public void issueCategoryReport(String category){
        try{
        if(!library.getCategories().containsKey(category)){
            throw new InvalidCategory("Invalid Category.");
        }
        String path = "./categories/"+category+".txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write("\t\tAvailable " + category + " books:\n" );
            for(Book book : library.getCategories().get(category)){
                writer.write("\n"+book.toString());
            }

            System.out.println("Category report successfully issued!");
        }catch(InvalidCategory e){
            System.out.println(e.getMessage());
        }catch(Exception e){
            System.out.println("Error. " + e.getMessage());
        }
    }

    /**
     * Iterates through the author based HashMap and writes to file the coresponding books
     * @param author
     */
    public void issueAuthorReport(String author){
        try{
            if(!library.getAuthors().containsKey(author)){
                throw new InvalidAuthor("Invalid Author");
            }
        String path = "./authors/"+author+".txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write("/t/tAvailable books from " + author + ":\n");
            for(Book book : library.getAuthors().get(author)){
                writer.write("\n" + book.toString());
            }

            System.out.println("Author report successfully issued.");
        }catch(InvalidAuthor ex){
            System.out.println(ex.getMessage());
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Writes to file all book titles that have been borrowed at a specified date
     * @param date
     */
    public void issueBorrowingReport(String date){
        boolean found = false;
        String path = "./borrowing reports/report" + date+".txt";
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
            writer.write("\t\tBooks borrowed on " + date);
            for(BorrowRecord record : library.getRecords()){
                if(record.getDate().equals(LocalDate.parse(date))){
                    found = true;
                    writer.write("\n" + record.getBookTitle());
                }
            }
            if(!found){
                writer.write("\nNo books borrowed on the specifed date.");
            }
            System.out.println("Date report successfully issued.");
        }catch(IOException e){
            System.out.println("File not found. " + e.getMessage());
        }
    }

    /**
     * Ensures data persistence by refreshing the input binary file
     * @param filename the name of the file
     */
    public void saveData(String filename){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){
            oos.writeObject(library);
            System.out.println("Data saved successfully");
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
