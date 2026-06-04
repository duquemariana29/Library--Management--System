package co.edu.uniremington.ui;

import co.edu.uniremington.model.Book;
import co.edu.uniremington.model.Loan;
import co.edu.uniremington.service.Library;
import java.util.ArrayList;
import java.util.Scanner;

//Aquí se realiza encapsulamiento ya que impide que otras clases accedan directamente a ellos.
public class ConsoleUI {
    private final Library library;
    private final Scanner scanner;

    //Constructor
    public ConsoleUI(Library library) {
        this.library = library;
        this.scanner = new Scanner(System.in);
    }

    /** Open/close
     *Es posible agregar nuevas opciones al menú
     *pero habría que modificar los métodos switch, por lo que no está completamente cerrada a modificaciones.
     */

    public void start() {

        int option = 0;

        while (option != 4) {

            System.out.println("\n===== SISTEMA DE GESTIÓN DE LIBROS =====");
            System.out.println("1. Libros");
            System.out.println("2. Préstamos");
            System.out.println("3. Resumen");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");

            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> booksMenu();
                case 2 -> loansMenu();
                case 3 -> showSummary();
                case 4 -> System.out.println("AIOSHHHHH!");
                default -> System.out.println("Opción inválida.");
            }
        }
    }
    private void booksMenu() {

        int option = 0;

        while (option != 6) {

            System.out.println("\n--- LIBROS ---");
            System.out.println("1. Registrar libro");
            System.out.println("2. Buscar por ISBN");
            System.out.println("3. Buscar por título");
            System.out.println("4. Ver todos");
            System.out.println("5. Ver Disponibles");
            System.out.println("6. Atrás");
            System.out.print("Elija una opción: ");

            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> registerBook();
                case 2 -> searchByIsbn();
                case 3 -> searchByTitle();
                case 4 -> displayBooks(
                        library.getCatalog(),
                        "No hay libros registrados."
                );
                case 5 -> displayBooks(
                        library.getAvailableBooks(),
                        "No hay libros disponibles."
                );
                case 6 -> System.out.println("Devolviendo...");
                default -> System.out.println("Opción no válida.");
            }
        }
    }
    private void registerBook() {

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Título: ");
        String title = scanner.nextLine();

        System.out.print("Autor: ");
        String author = scanner.nextLine();

        System.out.print("Categoría: ");
        String category = scanner.nextLine();

        Book book = new Book(isbn, title, author, category);

        boolean added = library.addBook(book);

        System.out.println(
                added
                        ? "Libro registrado correctamente."
                        : "Ya existe un libro con ese ISBN"
        );
    }
    private void searchByIsbn() {

        System.out.print("Ingrese el ISBN: ");
        String isbn = scanner.nextLine();

        displayBook(
                library.searchByIsbn(isbn)
        );
    }
    private void searchByTitle() {

        System.out.print("Ingrese el título: ");
        String title = scanner.nextLine();

        displayBooks(
                library.searchByTitle(title),
                "No se encontraron libros."
        );
    }
    private void loansMenu() {

        int option = 0;

        while (option != 5) {

            System.out.println("\n--- PRÉSTAMOS ---");
            System.out.println("1. Registrar préstamo");
            System.out.println("2. Registrar devolución");
            System.out.println("3. Ver préstamos activos");
            System.out.println("4. Ver todos los préstamos");
            System.out.println("5. Atrás");
            System.out.print("Elija una opción: ");

            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> registerLoan();
                case 2 -> registerReturn();
                case 3 -> displayLoans(
                        library.getActiveLoans(),
                        "No hay préstamos activos."
                );
                case 4 -> displayLoans(
                        library.getAllLoans(),
                        "No hay préstamos registrados."
                );
                case 5 -> System.out.println("Devolviendo...");
                default -> System.out.println("Option inválida.");
            }
        }
    }
    private void registerLoan() {

        System.out.print("Ingresar el ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Nombre de quien hace el préstamo: ");
        String borrower = scanner.nextLine();

        Loan loan = library.loanBooks(isbn, borrower);

        if (loan != null) {
            System.out.println("Préstamo registrado correctamente.");
            System.out.println(loan);
        } else {
            System.out.println("Libro no encontrado o no disponible.");
        }
    }
    private void registerReturn() {

        System.out.print("Ingresar el ID del préstamo: ");
        String loanId = scanner.nextLine();

        boolean returned = library.returnBook(loanId);

        System.out.println(
                returned
                        ? "Libro devuelto correctamente."
                        : "Préstamo no encontrado o ya devuelto."
        );
    }
    private void showSummary() {

        System.out.println("\n===== RESUMEN =====");
        System.out.println("Total de libros: " + library.getCatalog().size());
        System.out.println("Libros disponibles: " + library.getAvailableBooks().size());
        System.out.println("Préstamos activos: " + library.getActiveLoans().size());
        System.out.println("Total de préstamos: " + library.getAllLoans().size());
    }
    private void displayBook(Book book) {

        if (book == null) {
            System.out.println("Libro no encontrado.");
            return;
        }

        System.out.println(book);
    }
    private void displayBooks(ArrayList<Book> books, String emptyMessage) {

        if (books.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }

        books.forEach(System.out::println);
    }
    private void displayLoans(ArrayList<Loan> loans, String emptyMessage) {

        if (loans.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }

        loans.forEach(System.out::println);
    }
}