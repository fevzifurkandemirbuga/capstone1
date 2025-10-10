package com.pluralsight;

import java.util.Scanner;

public class HomeScreen {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {

        mainMenu();
        scan.close();



    }

    public static void mainMenu(){
//        while(true){
            System.out.println("""
                ***************  Home
                D) Add Deposit
                P) Make Payment(Debit)
                L) Ledger
                X) Exit""");
            System.out.print("your choose: ");

//        }




    }




}
