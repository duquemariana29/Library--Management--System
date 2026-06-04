package co.edu.uniremington.model;

//Aquí se realiza encapsulamiento ya que impide que otras clases accedan directamente a ellos.
public class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private final String category;
    private StateBook state;

    //Constructor
    public Book(String isbn, String title, String author, String category) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.category = category;
        this.state = StateBook.Available;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public StateBook getState() {
        return state;
    }

    public Boolean isAvailable() {
        return this.state == StateBook.Available;
    }

    public void changeState(StateBook newState) {
        this.state = newState;
    }

    public String toString() {
        return  "ISBN: " + isbn +
                " | Title: " + title +
                " | Author: " + author +
                " | Category: " + category +
                " | State: " + state;
    }
}
