package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class TransactionApp {
    public static Scanner scan = new Scanner(System.in);
    public static ArrayList<Transaction> transactions=new ArrayList<>();
    public static void main(String[] args) {

        cvsToData();
        homeScreen();
        scan.close();

    }

    public static void homeScreen(){
        boolean running=true;
        while(running){
            System.out.println("""
                ********  Home ********
                D) Add Deposit
                P) Make Payment(Debit)
                L) Ledger
                X) Exit""");
            System.out.print("your choose: ");
            String input=scan.nextLine().toUpperCase();
            switch (input){
                case "D" -> addTransaction(true);
                case "P" -> addTransaction(false);
                case "L" -> ledger();
                case "X" -> running=false;
                default -> System.out.println("wrong entry please try again.");
            }
        }
    }

    public static void addTransaction(boolean positive){

        System.out.print("enter Vendor name: ");
        String vendor=scan.nextLine();
        System.out.print("enter description: ");
        String description=scan.nextLine();
        System.out.print("enter amount: ");

        double amount= scan.nextDouble();
        scan.nextLine();
        amount=(!positive && amount>0 ? amount*-1 : amount);

        LocalDate date=LocalDate.now();
        LocalTime time=LocalTime.now().withNano(0);

        Transaction t=new Transaction(date,time,description,vendor,amount);
        dataToCvs(t);


        transactions.add(t);
        transactions.sort(Comparator
                .comparing(Transaction::getDate)
                .thenComparing(Transaction::getTime));


    }

    public static void ledger(){

        boolean running=true;
        while(running){
            System.out.println("""
                *******  Ledger *******
                A) All
                D) Deposits
                P) Payments
                R) Reports
                H) Home""");
            System.out.print("your choose: ");
            String input=scan.nextLine().toUpperCase();
            switch (input){
                case "A" -> {
                    for(Transaction t:transactions){
                        System.out.println(t);
                    }
                }
                case "D" -> {
                    for(Transaction t:transactions){
                        if(t.getAmount()>0){
                            System.out.println(t);
                        }
                    }
                }
                case "P" -> {
                    for(Transaction t:transactions){
                        if(t.getAmount()<0){
                            System.out.println(t);
                        }
                    }
                }
                case "R" -> reports();
                case "H" -> running=false;
                default -> System.out.println("wrong entry please try again.");
            }
        }

    }

    public static void reports(){
        boolean running =true;
        while(running){
            System.out.println("""
                ************** Reports
                1) Month To Date
                2) Previous Month
                3) Year To Date
                4) Previous Year
                5) Search by vendor
                0) Back""");
            System.out.print("your choose: ");
            String input=scan.nextLine().toUpperCase();
            switch (input){
                case "1" -> {
                    LocalDate currentMonth=LocalDate.now().withDayOfMonth(1);
                    for(Transaction t:transactions){
                        if(t.getDate().isAfter(currentMonth)){
                            System.out.println(t);
                        }

                    }
                }
                case "2" -> {
                    LocalDate lastMonthEnd=LocalDate.now().withDayOfMonth(1).minusDays(1);
                    LocalDate lastMonthStart=LocalDate.now().minusMonths(1).withDayOfMonth(1);
                    for(Transaction t:transactions){
                        if(t.getDate().isAfter(lastMonthStart) && t.getDate().isBefore(lastMonthEnd)){
                            System.out.println(t);
                        }

                    }}
                case "3" -> {
                    LocalDate currentYear=LocalDate.now().withMonth(1).withDayOfMonth(1);
                    for(Transaction t:transactions){
                        if(t.getDate().isAfter(currentYear)){
                            System.out.println(t);
                        }
                    }
                }
                case "4" -> {
                    LocalDate lastYearStart=LocalDate.now().minusYears(1).withMonth(1).withDayOfMonth(1);
                    LocalDate lastYearEnd=LocalDate.now().withMonth(1).withDayOfMonth(1).minusDays(1);
                    for(Transaction t:transactions){
                        if(t.getDate().isAfter(lastYearStart) && t.getDate().isBefore(lastYearEnd)){
                            System.out.println(t);
                        }

                    }}
                case "5" -> {
                    boolean found=false;
                    System.out.print("Vendor name: ");
                    String vendorName= scan.nextLine();
                    for(Transaction t:transactions){
                        if (t.getVendor().equalsIgnoreCase(vendorName)){
                            found=true;
                            System.out.println(t);
                        }

                    }
                    if(!found){
                        System.out.println("transaction could not find");
                    }
                }
                case "0" -> running=false;
                default -> System.out.println("wrong entry please try again.");
            }
        }

    }

    public static void dataToCvs(Transaction t){

        try{
            FileWriter fw=new FileWriter("transactions.csv",true);
            BufferedWriter bw=new BufferedWriter(fw);

            String text=String.format("%s|%s|%s|%s|%.2f",
                    t.getDate(),t.getTime(),t.getDescription(), t.getVendor(),t.getAmount());
            bw.write(text+"\n");
            bw.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void cvsToData(){

        try{
            FileReader fr=new FileReader("transactions.csv");
            BufferedReader bf=new BufferedReader(fr);
            String input;
            bf.readLine();
            while((input=bf.readLine())!=null){
                String[] data =input.split("\\|");

//                convert String date to LocalDate
                String[] stringDate=data[0].split("-");
                LocalDate date= LocalDate.of(Integer.parseInt(stringDate[0]),
                        Integer.parseInt(stringDate[1]),Integer.parseInt(stringDate[2]));

//              convert String time to LocalTime
                String[] stringTime=data[1].split(":");
                LocalTime time=LocalTime.of(Integer.parseInt(stringTime[0]),
                        Integer.parseInt(stringTime[1]),Integer.parseInt(stringTime[2]));

//              convert string amount to double
                double amount=Double.parseDouble(data[4]);

                transactions.add(new Transaction(date,time,data[2],data[3],amount));

            }
            bf.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        transactions.sort(Comparator
                .comparing(Transaction::getDate)
                .thenComparing(Transaction::getTime));


    }


}
