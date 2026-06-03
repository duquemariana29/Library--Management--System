package co.edu.uniremington.service;


import co.edu.uniremington.model.Book;
import co.edu.uniremington.model.Loan;
import co.edu.uniremington.model.StateBook;

import java.util.ArrayList;

public class Library {
    private final ArrayList<Book> catalog;
    private final ArrayList<Loan> loans;

    public Library() {
        this.catalog = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    public boolean addBook(Book book) {
        for (Book bok : catalog) {
            if (bok.getIsbn().equals(book.getIsbn())) {
                return false;
            }
        }
        catalog.add(book);
        return true;
    }

    public Book searchByIsbn(String isbn) {
        for (Book book : catalog) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public ArrayList<Book> searchByTitle(String title) {
        ArrayList<Book> results = new ArrayList<>();
        for (Book book : catalog) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }

    public ArrayList<Book> getCatalog() {
        return catalog;
    }

    public ArrayList<Book> getAvailableBooks() {
        ArrayList<Book> available = new ArrayList<>();
        for (Book book : catalog) {
            if (book.isAvailable()) {
                available.add(book);
            }
        }
        return available;
    }

    public Loan loanBooks(String isbn, String borrowerName) {
        Book book = searchByIsbn(isbn);
        if (book == null || !book.isAvailable()) {
            return null;
        }
        String id = "POO" + (loans.size() + 1);
        book.changeState(StateBook.Borrowed);
        Loan loan = new Loan(id, book, borrowerName);
        loans.add(loan);
        return loan;
    }

    public boolean returnBook(String loanId) {
        for (Loan loan : loans) {
            if (loan.getId().equals(loanId) && !loan.isReturned()) {
                loan.regisiterReturn();
                loan.getBook().changeState(StateBook.Available);
                return true;
            }
        }
        return false;
    }
    public ArrayList<Loan> getActiveLoans() {
        ArrayList<Loan> active = new ArrayList<>();
        for (Loan loan : loans) {
            if (!loan.isReturned()) {
                active.add(loan);
            }
        }
        return active;
    }
    public ArrayList<Loan> getAllLoans() {
        return loans;
    }


}
