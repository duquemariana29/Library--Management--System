package co.edu.uniremington.ui;

import co.edu.uniremington.model.Book;
import co.edu.uniremington.model.Loan;
import co.edu.uniremington.service.Library;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleUI {

    private final Library library;
    private final Scanner scanner;

    public ConsoleUI(Library library) {
        this.library = library;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int option = 0;

        while (option != 4) {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Books");
            System.out.println("2. Loans");
            System.out.println("3. Summary");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> booksMenu();
                case 2 -> loansMenu();
                case 3 -> showSummary();
                case 4 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void booksMenu() {
        int option = 0;

        while (option != 6) {
            System.out.println("\n--- BOOKS ---");
            System.out.println("1. Register book");
            System.out.println("2. Search by ISBN");
            System.out.println("3. Search by title");
            System.out.println("4. View all");
            System.out.println("5. View available");
            System.out.println("6. Back");
            System.out.print("Choose an option: ");

            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> registerBook();
                case 2 -> searchByIsbn();
                case 3 -> searchByTitle();
                case 4 -> viewAllBooks();
                case 5 -> viewAvailableBooks();
                case 6 -> System.out.println("Returning...");
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void registerBook() {

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("Category: ");
        String category = scanner.nextLine();

        Book book = new Book(isbn, title, author, category);

        boolean added = library.addBook(book);

        if (added) {
            System.out.println("Book registered successfully.");
        } else {
            System.out.println("A book with that ISBN already exists.");
        }
    }

    private void searchByIsbn() {
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        Book book = library.searchByIsbn(isbn);

        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("Book not found.");
        }
    }

    private void searchByTitle() {

        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        ArrayList<Book> results = library.searchByTitle(title);

        if (results.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book book : results) {
                System.out.println(book);
            }
        }
    }

    private void viewAllBooks() {

        ArrayList<Book> books = library.getCatalog();

        if (books.isEmpty()) {
            System.out.println("No books registered.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private void viewAvailableBooks() {

        ArrayList<Book> books = library.getAvailableBooks();

        if (books.isEmpty()) {
            System.out.println("No available books.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private void loansMenu() {

        int option = 0;

        while (option != 5) {

            System.out.println("\n--- LOANS ---");
            System.out.println("1. Register loan");
            System.out.println("2. Register return");
            System.out.println("3. View active loans");
            System.out.println("4. View all loans");
            System.out.println("5. Back");
            System.out.print("Choose an option: ");

            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> registerLoan();
                case 2 -> registerReturn();
                case 3 -> viewActiveLoans();
                case 4 -> viewAllLoans();
                case 5 -> System.out.println("Returning...");
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void registerLoan() {

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Borrower name: ");
        String borrower = scanner.nextLine();

        Loan loan = library.loanBooks(isbn, borrower);

        if (loan != null) {
            System.out.println("Loan registered successfully.");
            System.out.println(loan);
        } else {
            System.out.println("Book not found or unavailable.");
        }
    }

    private void registerReturn() {

        System.out.print("Enter loan ID: ");
        String id = scanner.nextLine();

        boolean returned = library.returnBook(id);

        if (returned) {
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Loan not found or already returned.");
        }
    }

    private void viewActiveLoans() {

        ArrayList<Loan> loans = library.getActiveLoans();

        if (loans.isEmpty()) {
            System.out.println("No active loans.");
        } else {
            for (Loan loan : loans) {
                System.out.println(loan);
            }
        }
    }

    private void viewAllLoans() {

        ArrayList<Loan> loans = library.getAllLoans();

        if (loans.isEmpty()) {
            System.out.println("No loans registered.");
        } else {
            for (Loan loan : loans) {
                System.out.println(loan);
            }
        }
    }

    private void showSummary() {

        System.out.println("\n===== SUMMARY =====");
        System.out.println("Total books: " + library.getCatalog().size());
        System.out.println("Available books: " + library.getAvailableBooks().size());
        System.out.println("Active loans: " + library.getActiveLoans().size());
        System.out.println("Total loans: " + library.getAllLoans().size());
    }


}
