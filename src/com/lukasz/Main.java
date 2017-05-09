package com.lukasz;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static int charsNum = 15;
    private static int specialCharsNum = 3;
    private static int numbersNum = 2;
    private static int upperLower = 3; // 1 - only lower case, 2 - only upper case, 3 - both
    private static String password = "(choose 5 to generate password)";

    public static void main(String[] args) {
        loop:
        while(true) {


            printMenu();
            switch (scanner.nextLine().toUpperCase()) {
                case "Q":
                    break loop;
                case "1":
                    changeCharsNum();
                    checkNums("chars");
                    break;
                case "2":
                    specialCharsNum = changeNums("special chars");
                    checkNums("special chars");
                    break;
                case "3":
                    numbersNum = changeNums("numbers");
                    checkNums("numbers");
                    break;
                case "4":
                    changeLetterCase();
                    break;
                case "5":
                    password = new Password(charsNum, specialCharsNum, numbersNum, upperLower).getPassword();
                    break;
                case "C":
                    copyToClipboard();
                    break;
                default:
                    break;
            }


        }

    }

    private static void printMenu() {
        System.out.println("--------------------------------");
        System.out.println("-------Password generator-------");
        System.out.println("--------------------------------");
        System.out.println();
        System.out.println("You're password is: " + password);
        System.out.println("Choose 'C' to copy to clipboard");
        System.out.println();
        System.out.println("Or choose option:");
        System.out.println("1. How many chars:\t\t\t" + charsNum);
        System.out.println("2. How many special chars:\t" + specialCharsNum);
        System.out.println("3. How many numbers:\t\t" + numbersNum);
        System.out.print("4. Letter case: ");
        if(upperLower == 3) System.out.println("both");
        if(upperLower == 2) System.out.println("only upper");
        if(upperLower == 1) System.out.println("only lower");
        System.out.println("5. Generate password");
        System.out.println("Q to exit");
    }

    private static void changeCharsNum() {
        System.out.println("How many chars do you want in you're password? (6 to 20)");
        while(true) {
            int number = scanner.nextInt();
            scanner.nextLine();
            if (number > 5 && number < 21){
                charsNum = number;
                break;
            }else
                System.out.println("Choose number between 6 and 20");
        }
    }

    private static int changeNums(String s) {
        System.out.println("How many " + s + " do you want in you're password? (0 to " + charsNum + ")");
        while(true) {
            int number = scanner.nextInt();
            scanner.nextLine();
            if(number < 0 && number > charsNum)
                System.out.println("Choose number between 0 and " + charsNum );
            else
                return number;
        }
    }

    private static void changeLetterCase() {
        System.out.println("What letter case do you want in you're password?");
        while(true) {
            System.out.println("1. Only lower case");
            System.out.println("2. Only upper case");
            System.out.println("3. Both");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if(choice > 0 && choice < 4) {
                upperLower = choice;
                break;
            }else
                System.out.println("Choose number between 1 and 3.");
        }
    }

    private static void checkNums(String s) {
        boolean counter = true;
        while(charsNum - specialCharsNum - numbersNum < 0) {
            switch (s) {
                case "chars":
                    if(specialCharsNum > 0 && counter) specialCharsNum--;
                    if(numbersNum > 0 && counter) numbersNum--;
                    counter = !counter;
                    break;
                case "special chars":
                    numbersNum--;
                    break;
                case "numbers":
                    specialCharsNum--;
                    break;
            }
        }
    }

    private static void copyToClipboard() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        StringSelection str = new StringSelection(password);
        clipboard.setContents(str, null);
    }
}
