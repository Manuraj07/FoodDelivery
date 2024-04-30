package com.task.BankApplication;

import java.util.ArrayList;
import java.util.Scanner;

public class BankApplication {
	
	private static ArrayList<String> usernames = new ArrayList<>();
    private static ArrayList<String> passwords = new ArrayList<>();
    private static ArrayList<Double> balances = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMainMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    customerLogin();
                    break;
                case "2":
                    createNewCustomer();
                    break;
                case "3":
                    System.out.println("Exited Application successfully...");
                    return;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\nCustomer Menu:");
        System.out.println("1. Customer Login");
        System.out.println("2. New Customer Sign in");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void customerLogin() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        int index = findCustomer(username);
        if (index != -1 && passwords.get(index).equals(password)) {
            System.out.println("Welcome " + username + "!");
            handleCustomerOperations(index);
        } else {
            System.out.println("Not a valid customer...");
        }
    }

    private static int findCustomer(String username) {
        return usernames.indexOf(username);
    }

    private static void createNewCustomer() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        if (usernames.contains(username)) {
            System.out.println("Username already exists. Please choose another one.");
            return;
        }
        System.out.print("Create a password: ");
        String password = scanner.nextLine();

        usernames.add(username);
        passwords.add(password);
        balances.add(0.0);

        System.out.println("Account created successfully.");
    }

    private static void handleCustomerOperations(int index) {
        while (true) {
            displayCustomerMenu();
            String option = scanner.nextLine();

            switch (option) {
                case "a":
                    System.out.println("Balance: $" + balances.get(index));
                    break;
                case "b":
                    handleDeposit(index);
                    break;
                case "c":
                    handleWithdrawal(index);
                    break;
                case "d":
                    System.out.println("Balance: $" + balances.get(index));
                    break;
                case "e":
                    return;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }

    private static void displayCustomerMenu() {
        System.out.println("\nCustomer Menu:");
        System.out.println("a. Account Details");
        System.out.println("b. Amount Deposit");
        System.out.println("c. Amount Withdrawal");
        System.out.println("d. Check Balance");
        System.out.println("e. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void handleDeposit(int index) {
        System.out.print("Enter amount to deposit: ");
        double amount = Double.parseDouble(scanner.nextLine());
        if (amount > 0) {
            balances.set(index, balances.get(index) + amount);
            System.out.println("Deposit successful. Current balance: $" + balances.get(index));
        } else {
            System.out.println("Invalid amount. Please enter a positive number.");
        }
    }

    private static void handleWithdrawal(int index) {
        System.out.print("Enter amount to withdraw: ");
        double amount = Double.parseDouble(scanner.nextLine());
        if (amount > 0 && amount <= balances.get(index)) {
            balances.set(index, balances.get(index) - amount);
            System.out.println("Withdrawal successful. Current balance: $" + balances.get(index));
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }


}
