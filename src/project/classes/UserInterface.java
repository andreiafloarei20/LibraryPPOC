package project.classes;

import java.util.Scanner;

/**
 * Actual user interface for the console
 */
public class UserInterface {
    /**
     * Used to get data from the user
     */
    private Scanner scanner;
    /**
     * Used to manage the data of the system
     */
    private Library library;
    /**
     * Used for file operations
     */
    private FileManager fileManager;

    /**
     * Initialize the attributes with the provided ones
     * @param scanner
     * @param library
     * @param fileManager
     */
    public UserInterface(Scanner scanner, Library library, FileManager fileManager){
        this.scanner = scanner;
        this.library = library;
        this.fileManager = fileManager;
    }

    /**
     * The Logic flow of the application, evolving depending on the user's commands
     */
    public void start(){
        /**
         * Main menu
         */
        System.out.println("==============================");
        System.out.println();
        System.out.println("    Library Management App    ");
        System.out.println();
        System.out.println("==============================");
        System.out.println("Commands:");
        System.out.println("add - add a book");
        System.out.println("list - display all books");
        System.out.println("borrow - borrow a book");
        System.out.println("return - return a book");
        System.out.println("browse - browse available books");
        System.out.println("get - unpack books from the deposit");
        System.out.println("reports - issue reports");
        System.out.println("ratings");
        System.out.println("exit - quit app");
        System.out.println();


        while(true){
            System.out.println("Enter command: ");
            String command = scanner.nextLine();


            if(command.equals("exit")){
                System.out.println("See you around!");
                fileManager.saveData("library.dat");
                break;
            }

            switch(command){
                case "add":
                    System.out.println("Enter title:");
                    String title = scanner.nextLine();
                    System.out.println("Enter author:");
                    String author = scanner.nextLine();
                    System.out.println("Enter category:");
                    String category = scanner.nextLine();

                    library.addBook(new Book(title,author,category));
                    break;
                case "remove":
                    System.out.println("Remove panel");
                    break;
                case "list":
                    System.out.println("Display all books.");
                    library.printCategories();
                    break;
                case "borrow":
                    System.out.println("Enter title: ");
                    String borrowTitle = scanner.nextLine();
                    library.borrow(borrowTitle);
                    break;
                case "return":
                    System.out.println("Enter title:");
                    String returnTitle = scanner.nextLine();
                    boolean returned = library.returnBook(returnTitle);
                    if(returned){
                        System.out.println("Rate the book (0 - 5 stars):");
                        double rating = Double.parseDouble(scanner.nextLine());
                        library.rateBook(returnTitle, rating);
                    }

                    break;
                case "browse":
                    System.out.println("\n==============================");
                    System.out.println("\n\t\tBrowse panel");
                    System.out.println("\n==============================");

                    while(true){
                        System.out.println("See available books by category - press 1");
                        System.out.println("See available books by their authors - press 2");
                        System.out.println("Search for something specific - press 3");
                        System.out.println("back - go back");
                        String choice = scanner.nextLine();

                        if(choice.equals("back")){
                            break;
                        }

                        switch(choice){
                            case "1":
                                library.printCategories();
                                break;
                            case "2":
                                library.printAuthors();
                                break;
                            case "3":
                                System.out.println("Enter keyword:");
                                String keyword = scanner.nextLine();
                                library.search(keyword);
                                break;
                            default:
                                System.out.println("Unknown command");
                        }
                    }

                    break;
                case "get":
                    fileManager.getOrder("bulk.txt");
                    fileManager.getRecords("archive.txt");
                    for(BorrowRecord rec : library.getRecords()){
                        System.out.println(rec);
                    }
                    break;
                case "ratings":
                    library.displayRatings();
                    break;
                case "reports":
                    System.out.println("\n==============================");
                    System.out.println("\n\t\tReports section");
                    System.out.println("\n==============================");
                    System.out.println("\nGet all books from a specified category - press 1");
                    System.out.println("Get all books from a specified author - press 2");
                    System.out.println("Get all books borrowed at a specified date - press 3");

                    String choice = scanner.nextLine();

                    switch(choice){
                        case "1":
                            System.out.println("Enter category:");
                            String cat = scanner.nextLine();
                            fileManager.issueCategoryReport(cat);
                            break;
                        case "2":
                            System.out.println("Enter author:");
                            String name = scanner.nextLine();
                            fileManager.issueAuthorReport(name);
                            break;
                        case "3":
                            System.out.println("Enter date: yyyy-mm-dd");
                            String date = scanner.nextLine();
                            fileManager.issueBorrowingReport(date);
                            break;
                    }

                    break;
                default:
                    System.out.println("Unknown command");
            }

        }

    }
}
