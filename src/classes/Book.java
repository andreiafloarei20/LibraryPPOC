package classes;

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
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

    public void returnBook(){
        if(this.isBorrowed){
            this.isBorrowed = false;
            System.out.println("You successfully returned the book!");
        } else {
            System.out.println("The book was not borrowed yet.");
        }
    }

    public String toString(){
        return "Title: " + this.getTitle() + ", Author: " + this.getAuthor() + ", Category: " + this.getCategory();
    }
}
