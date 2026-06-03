package co.edu.uniremington;

import co.edu.uniremington.service.Library;
import co.edu.uniremington.ui.ConsoleUI;

public class Main {


    public static void main(String[] args) {

        Library library = new Library();

        ConsoleUI consoleUI = new ConsoleUI(library);

        consoleUI.start();
    }


}
