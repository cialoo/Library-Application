package org.example.book;

import org.example.LibraryMenu;
import org.example.book.BookFindMenu;
import org.example.book.BooksManager;
import org.example.util.InputValidator;

import java.util.Scanner;

public class BooksMenu {
    private Scanner scanner = new Scanner(System.in);
    boolean isRunning = true;

    public BooksMenu() {
        while(isRunning) {
            System.out.println();
            System.out.println("BooksMenu.");
            System.out.println("1. Show all books.");
            System.out.println("2. Find book.");
            System.out.println("3. Add book.");
            System.out.println("4. Delete book.");
            System.out.println("5. Exit.");

            int choose = InputValidator.getValidatedIntInput(scanner, "Choose an option: ");

            switch(choose) {
                case 1:
                    System.out.println();
                    System.out.println("All books:");
                    selectAllBook();
                    break;
                case 2:
                    new BookFindMenu();
                    break;
                case 3:
                    System.out.println();
                    addBook();
                    break;
                case 4:
                    System.out.println();
                    deleteBook();
                    break;
                case 5:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Select options from the list!");
            }
        }
    }

    private void selectAllBook() {
        BooksManager.selectAllBook();
    }

    private void addBook() {
        boolean available = false;
        System.out.println("Add new book:");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter publication year: ");
        int publicationYear = InputValidator.getValidatedIntInput(scanner, "");
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter available of book (YES/NO): ");
        String availableStr = InputValidator.getValidatedYesNoInput(scanner);
        available = availableStr.equalsIgnoreCase("YES");

        BooksManager.addBook(title, author, genre, publicationYear, isbn, available);
    }

    private void deleteBook() {
        System.out.println("Delete book:");
        System.out.print("Enter book ID: ");
        int id = scanner.nextInt();

        BooksManager.deleteBook(id);
    }

}
