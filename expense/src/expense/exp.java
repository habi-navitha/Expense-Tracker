package expense;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class exp {
    private String name;
    private double amount;

    public exp(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }
}

class ExpenseTracker {
    private ArrayList<exp> expenses;
    private double dailyLimit;
    private double totalExpenses;

    public ExpenseTracker(double dailyLimit) {
        expenses = new ArrayList<>();
        this.dailyLimit = dailyLimit;
        totalExpenses = 0;
    }

    public void addExpense(String name, double amount) {
        exp expense = new exp(name, amount);
        expenses.add(expense);
        totalExpenses += amount;
        System.out.println("Expense added: " + name + " - $" + amount);
    }

    public void deleteExpense(int index) {
        if (index >= 0 && index < expenses.size()) {
            totalExpenses -= expenses.get(index).getAmount();
            System.out.println("Expense deleted: " + expenses.remove(index).getName());
        } else {
            System.out.println("Invalid index. No expense deleted.");
        }
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public void viewAllExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses added yet.");
            return;
        }
        System.out.println("All Expenses:");
        for (int i = 0; i < expenses.size(); i++) {
            System.out.println(i + ". " + expenses.get(i).getName() + " - $" + expenses.get(i).getAmount());
        }
        System.out.println(totalExpenses > dailyLimit ? "Exceed Your Daily Limit! Learn to Save Money."
                : "Expenses within your Daily Limit.");
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your daily spending limit: ");
        double dailyLimit = scanner.nextDouble();
        ExpenseTracker expenseTracker = new ExpenseTracker(dailyLimit);
        boolean exit = false;
        while (!exit) {
            System.out.println("\nExpense Tracker Menu:\n1. Add Expense\n2. View Total Expenses\n3. View All Expenses\n4. Delete Expense\n5. Exit");
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter expense name: ");
                        scanner.nextLine(); // Consume newline
                        String expenseName = scanner.nextLine();
                        System.out.print("Enter expense amount: ");
                        double expenseAmount = scanner.nextDouble();
                        if (expenseAmount <= 0) {
                            System.out.println("Invalid amount. Amount must be greater than zero.");
                            break;
                        }
                        expenseTracker.addExpense(expenseName, expenseAmount);
                        break;
                    case 2:
                        System.out.println("Total Expenses: $" + expenseTracker.getTotalExpenses());
                        System.out.println(expenseTracker.getTotalExpenses() > dailyLimit ? "Exceed Your Daily Limit! Learn to Save Money."
                                : "Expenses within your Daily Limit.");
                        break;
                    case 3:
                        expenseTracker.viewAllExpenses();
                        break;
                    case 4:
                        System.out.print("Enter the index of the expense to delete: ");
                        int index = scanner.nextInt();
                        expenseTracker.deleteExpense(index);
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            }
        }
        System.out.println("Exiting Expense Tracker. Goodbye!");
        scanner.close();
    }
}