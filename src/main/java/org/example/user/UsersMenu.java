package org.example.user;

import org.example.user.UserFindMenu;
import org.example.user.UserManager;
import org.example.util.InputValidator;

import java.util.Scanner;

public class UsersMenu {
    private Scanner scanner = new Scanner(System.in);
    private boolean isRunning = true;

    public UsersMenu() {
        while(isRunning) {
            System.out.println();
            System.out.println("UsersMenu:");
            System.out.println("1. Show all users.");
            System.out.println("2. Find user.");
            System.out.println("3. Add user.");
            System.out.println("4. Delete user.");
            System.out.println("5. Exit.");

            int choose = InputValidator.getValidatedIntInput(scanner, "Choose an option: ");

            switch(choose) {
                case 1:
                    System.out.println();
                    System.out.println("All users:");
                    selectAllUser();
                    break;
                case 2:
                    new UserFindMenu();
                    break;
                case 3:
                    System.out.println();
                    addUser();
                    break;
                case 4:
                    System.out.println();
                    deleteUser();
                    break;
                case 5:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Select options from the list!");
            }
        }
    }

    private void selectAllUser() {
        UserManager.selectAllUser();
    }

    private void addUser() {
        System.out.println("Add new user:");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        UserManager.addUser(name, email, phone);
    }

    private void deleteUser() {
        System.out.println("Delete user:");
        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();

        UserManager.deleteUser(id);
    }

}
