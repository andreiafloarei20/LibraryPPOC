package classes;

public class Book {
    private String title;
    private String author;
    private String category;
    private boolean isBorrowed;

    public Book(String title, String author, String category){
        this.title = title;
        this.author = author;
        this.category = category;
        this.isBorrowed = false;
    }

    public String getTitle(){
        return this.title;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getCategory(){
        return this.category;
    }

    public void borrow(){
        if(!this.isBorrowed){
            this.isBorrowed = true;
            System.out.println("You successfully borrowed the book!");
        } else {
            System.out.println("Sorry, the book is not available.");
        }
    }

    public String toString(){
        return "Title: " + this.getTitle() + ", Author: " + this.getAuthor();
    }
}
