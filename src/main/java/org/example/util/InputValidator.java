package org.example.util;

import java.util.Scanner;

public class InputValidator {

    public static int getValidatedIntInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (true) {
            String input = scanner.nextLine();
            if (!input.isEmpty()) {
                try {
                    return Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Enter the number!");
                }
            }

        }
    }

    public static String getValidatedYesNoInput(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("YES") || input.equalsIgnoreCase("NO")) {
                return input;
            } else {
                System.out.println("Enter YES or NO!");
            }
        }
    }
}

