package com.homelearning;

import java.util.Scanner;

public class TerminalHelper {

    private static final int FIND_KEY = 1;
    private static final int SORT_KEY = 2;

    private ArrayHolder arrayHolder;
    private Scanner scanner;

    public TerminalHelper() {
        scanner = new Scanner(System.in);
    }

    public void execute(){
        askArraySize();
        askArrayElements();
        printMenu();
        scanner.close();
    }

    private void askArraySize(){
        System.out.println("Please enter array size:");
        if (scanner.hasNextInt()){
            int size = scanner.nextInt();
            if (size > 0) {
                arrayHolder = new ArrayHolder(size);
            } else {
                System.out.println("You can't create numbers sequence with size smaller then 1!");
                askArraySize();
            }
        } else {
            printInvalidInputMsg();
            scanner.next();
            askArraySize();
        }
    }

    private void askArrayElements(){
        int size = arrayHolder.getSize();
        for (int i = 0; i < size; i++) {
            System.out.printf("Please enter integer #%d:\n", i);
            if (scanner.hasNextInt()){
                arrayHolder.setElement(i, scanner.nextInt());
            } else {
                printInvalidInputMsg();
                scanner.next();
                i--;
            }
        }
    }

    private void printInvalidInputMsg() {
        System.out.println("Invalid input, please enter an integer:");
    }

    private void printMenu(){
        System.out.println("You have entered an array: " + arrayHolder);
        System.out.printf("To find an integer press %d\n", FIND_KEY);
        System.out.printf("To sort an array press %d\n", SORT_KEY);
        askForAction();
    }

    private void askForAction() {
        int key = readIntegerFromInput("Please select an action:");
        switch (key){
            case FIND_KEY:
                findAndPrint();
                break;
            case SORT_KEY:
                sortAndPrint();
                break;
            default:
                System.out.println("The are iKs no action for entered number!");
                askForAction();
        }
    }

    private void findAndPrint() {
        int number = readIntegerFromInput("Please enter an integer to find:");
        String numberIndex = arrayHolder.findNumberIndex(number);
        if (numberIndex.length() > 1){
            String indexMsg = "Index";
            if (numberIndex.length() > 2) indexMsg = "Indexes";
            System.out.printf("%s of a number %d is: %s\n", indexMsg, number,numberIndex);
        } else {
            System.out.println("Number not found");
        }
    }

    private void sortAndPrint() {
        System.out.println("Sorting an array: " + arrayHolder);
        arrayHolder.sort();
        System.out.println(("Array is sorted: \n" + arrayHolder));
    }

    private int readIntegerFromInput(String msg){
        System.out.println(msg);
        if (scanner.hasNextInt()){
            return scanner.nextInt();
        } else {
            printInvalidInputMsg();
            scanner.next();
            return readIntegerFromInput(msg);
        }
    }
}
