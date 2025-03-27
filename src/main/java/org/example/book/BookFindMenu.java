package org.example.book;

import org.example.util.InputValidator;

import java.util.Scanner;

public class BookFindMenu {
    private Scanner scanner = new Scanner(System.in);
    private boolean isRunning = true;

    BookFindMenu() {
        while(isRunning) {
            System.out.println();
            System.out.println("Find book menu:");
            System.out.println("1. Find by id.");
            System.out.println("2. Find by title.");
            System.out.println("3. Find by author.");
            System.out.println("4. Find by genre.");
            System.out.println("5. Find by publication year.");
            System.out.println("6. Find by ISBN.");
            System.out.println("7. Find by available.");
            System.out.println("8. Exit.");

            int choose = InputValidator.getValidatedIntInput(scanner, "Choose an option: ");

            switch (choose) {
                case 1:
                    System.out.print("Enter id: ");
                    int id = scanner.nextInt();
                    BooksManager.bookFindById(id);
                    break;
                case 2:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    BooksManager.bookFindByTitle(title);
                    break;
                case 3:
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    BooksManager.bookFindByAuthor(author);
                    break;
                case 4:
                    System.out.print("Enter genre: ");
                    String genre = scanner.nextLine();
                    BooksManager.bookFindByGenre(genre);
                    break;
                case 5:
                    System.out.print("Enter publication year: ");
                    int publicationYear = scanner.nextInt();
                    BooksManager.bookFindByPublicationYear(publicationYear);
                    break;
                case 6:
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    BooksManager.bookFindByIsbn(isbn);
                    break;
                case 7:
                    System.out.print("Enter available (YES/NO): ");
                    String availableStr = InputValidator.getValidatedYesNoInput(scanner);
                    boolean available = availableStr.equalsIgnoreCase("YES");
                    BooksManager.bookFindByAvailable(available);
                    break;
                case 8:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Select options from the list!");
            }

        }

    }

}
