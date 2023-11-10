package org.yates.utils;

import java.util.Scanner;

public class SafeInput {

    public static int getRangedInt(Scanner console, String prompt, int low, int high) {
        int input;
        while (true) {
            System.out.print(prompt);
            try {
                input = Integer.parseInt(console.nextLine());
                if (input >= low && input <= high) {
                    return input;
                } else {
                    System.out.println("Please enter a number between " + low + " and " + high + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public static boolean getYNConfirm(Scanner console, String prompt) {
        while (true) {
            System.out.print(prompt + " (y/n): ");
            String input = console.nextLine().toLowerCase();
            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        }
    }
}