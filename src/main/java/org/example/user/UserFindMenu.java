package org.example.user;

import org.example.util.InputValidator;

import java.util.Scanner;

public class UserFindMenu {
    private Scanner scanner = new Scanner(System.in);
    private boolean isRunning = true;

    UserFindMenu() {
        System.out.println();
        System.out.println("Find user menu:");
        while(isRunning) {
            System.out.println("1. Find by id.");
            System.out.println("2. Find by name.");
            System.out.println("3. Find by email.");
            System.out.println("4. Find by phone.");
            System.out.println("5. Exit.");

            int choose = InputValidator.getValidatedIntInput(scanner, "Choose an option: ");

            switch (choose) {
                case 1:
                    System.out.println();
                    System.out.print("Enter id: ");
                    int id = scanner.nextInt();
                    UserManager.userFindById(id);
                    System.out.println();
                    break;
                case 2:
                    System.out.println();
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    UserManager.userFindByName(name);
                    System.out.println();
                    break;
                case 3:
                    System.out.println();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    UserManager.userFindByEmail(email);
                    System.out.println();
                    break;
                case 4:
                    System.out.println();
                    System.out.print("Enter phone: ");
                    String phone = scanner.nextLine();
                    UserManager.userFindByPhone(phone);
                    System.out.println();
                    break;
                case 5:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Select options from the list!");
            }
        }
    }

}
