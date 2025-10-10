package com.pluralsight;

import java.util.Scanner;

public class TransactionApp {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {

        homeScreen();
        scan.close();



    }

    public static void homeScreen(){
        boolean running=true;
        while(running){
            System.out.println("""
                ***************  Home
                D) Add Deposit
                P) Make Payment(Debit)
                L) Ledger
                X) Exit""");
            System.out.print("your choose: ");
            String input=scan.nextLine().toUpperCase();
            switch (input){
                case "D" -> {}
                case "P" -> {}
                case "L" -> {}
                case "X" -> running=false;
                default -> System.out.println("wrong entry please try again.");
            }
        }
    }








}
