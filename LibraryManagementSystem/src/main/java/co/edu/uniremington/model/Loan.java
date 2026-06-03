package co.edu.uniremington.model;
 import java.time.LocalDate;
public class Loan {
    private final String id;
    private final Book book;
    private final String borrowerName;
    private final LocalDate loanDate;
    private final LocalDate dueDate;
    private boolean returned;

    public Loan(String id, Book book, String borrowerName, LocalDate loanDate, LocalDate dueDate) {
        this.id = id;
        this.book = book;
        this.borrowerName = borrowerName;
        this.loanDate = LocalDate.now();
        this.dueDate = dueDate.plusDays(14);
        this.returned = false;
    }

    public String getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isReturned(){
        return returned;
    }
    public void regisiterReturn(){
        returned = true;
    }
    public boolean isOverdue(){
        return LocalDate.now().isAfter(dueDate) && !returned;
    }

    public String toString() {
        return "ID" + id +
                " | Book: " + book.getTitle() +
                " | BorrowerName: " + borrowerName +
                " | Loan Date: " + loanDate +
                " | Due Date: " + dueDate +
                " | Returned: " + returned;


    }
}

