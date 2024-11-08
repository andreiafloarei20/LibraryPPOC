package project.classes;

public class Book {
    private String title;
    private String author;
    private String category;
    int noCopies;

    public Book(String title, String author, String category){
        this.title = title;
        this.author = author;
        this.category = category;
        this.noCopies = 0;
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

    public int getNoCopies(){
        return this.noCopies;
    }

    public void increase(){
        this.noCopies++;
    }

    public void decrease(){
        this.noCopies--;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return title.equals(book.title) && author.equals(book.author);
    }

    @Override
    public int hashCode() {
        return title.hashCode() + author.hashCode();
    }

    public String toString(){
        return "Title: " + this.title + ", Author: " + this.author + ", Available: " + this.noCopies;
    }
}
