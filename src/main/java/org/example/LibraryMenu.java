package org.example;

import org.example.book.BooksMenu;
import org.example.user.UsersMenu;
import org.example.util.InputValidator;


import java.util.Scanner;

public class LibraryMenu {
    private boolean isRunning = true;
    Scanner scanner = new Scanner(System.in);

    LibraryMenu(){
        System.out.println();
        System.out.println("Welcome to the library application!");
        while(isRunning) {
            System.out.println();
            System.out.println("Menu:");
            System.out.println("1. Users.");
            System.out.println("2. Books");
            System.out.println("3. Exit.");

            int choose = InputValidator.getValidatedIntInput(scanner, "Choose an option: ");


            switch (choose) {
                case 1:
                    new UsersMenu();
                    break;
                case 2:
                    new BooksMenu();
                    break;
                case 3:
                    System.out.println();
                    System.out.println("See you later!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Select options from the list!");
            }

        }
    }
}
