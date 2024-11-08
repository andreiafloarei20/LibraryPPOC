package project.classes;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private Library library;
    private FileManager fileManager;

    public UserInterface(Scanner scanner, Library library, FileManager fileManager){
        this.scanner = scanner;
        this.library = library;
        this.fileManager = fileManager;
    }

    public void start(){
        System.out.println("==============================");
        System.out.println();
        System.out.println("    Library Management App    ");
        System.out.println();
        System.out.println("==============================");
        System.out.println("Commands:");
        System.out.println("add - add a book");
        System.out.println("list - display all books");
        System.out.println("browse - browse available books");
        System.out.println("get - unpack books from the deposit");
        System.out.println("reports - issue reports");
        System.out.println("exit - quit app");
        System.out.println();


        while(true){
            System.out.println("Enter command: ");
            String command = scanner.nextLine();

            if(command.equals("exit")){
                System.out.println("See you around!");
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
                                System.out.println("search engine");
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
                case "reports":
                    System.out.println("\n==============================");
                    System.out.println("\n\t\tReports section");
                    System.out.println("\n==============================");
                    System.out.println("\nGet all books from a specified category - press 1");
                    System.out.println("Get all books from a specified author - press 2");

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
                    }

                    break;
                default:
                    System.out.println("Unknown command");
            }

        }

    }
}
