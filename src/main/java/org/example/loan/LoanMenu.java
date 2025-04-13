package org.example.loan;

import org.example.book.BookFindMenu;
import org.example.book.BooksManager;
import org.example.util.InputValidator;

import java.util.Scanner;

public class LoanMenu{
    private Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        public LoanMenu() {
            while(isRunning) {
                System.out.println();
                System.out.println("LoanMenu.");
                System.out.println("1. Show all loans.");
                System.out.println("2. Show all loans for a given user.");
                System.out.println("3. Rent book.");
                System.out.println("4. Return book.");
                System.out.println("5. Exit.");

                int choose = InputValidator.getValidatedIntInput(scanner, "Choose an option: ");

                switch(choose) {
                    case 1:
                        System.out.println();
                        System.out.println("All loans:");
                        selectAllLoan();
                        break;
                    case 2:
                        System.out.println();
                        selectAllLoanForUser();
                        break;
                    case 3:
                        System.out.println();
                        rentBook();
                        break;
                    case 4:
                        System.out.println();
                        returnBook();
                        break;
                    case 5:
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Select options from the list!");
                }
            }
        }

        private void selectAllLoan() {
            LoanManager.selectAllLoan();
        }

        private void selectAllLoanForUser() {
            System.out.println("All loans for user:");
            System.out.print("Enter user name:");
            String userName = scanner.nextLine();
            LoanManager.selectAllLoanForUser(userName);
        }

        private void rentBook() {
            System.out.println("Rent book:");
            System.out.print("Enter book ID: ");
            int idBook = scanner.nextInt();

            System.out.print("Enter user ID: ");
            int idUser = scanner.nextInt();

            if(LoanManager.isBookAvailable(idBook)) {
                LoanManager.rentBook(idBook, idUser);
            } else {
                System.out.println("This book is not available for loan or does not exist!");
            }
        }

        private void returnBook() {
            System.out.print("Enter rental ID: ");
            int loanId = scanner.nextInt();
            LoanManager.returnBook(loanId);

        }

}
