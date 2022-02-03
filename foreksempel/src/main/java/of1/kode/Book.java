package of1.kode;

import java.lang.reflect.Constructor;

public class Book {

    String title;
    int numPages;

    public Book(int numPages, String title) {
        this.numPages = numPages;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    @Override
    public String toString() {
        return "The book \"" + this.getTitle() + "\" has " + this.getNumPages() + " pages.";
    }

    public static void main(String[] args) {
        
        Book book = new Book(100, "Big Java");
        
        System.out.println(book);
         
        book.setNumPages(718);
        book.setTitle("Introduction to Algorithms");
         
        System.out.println(book);
        
    }

}
