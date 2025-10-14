package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {

    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public double getAmount() {
        return amount;
    }

    public String getVendor() {
        return vendor;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "\n-----------------------------------------" +
                "\ndate= " + date +
                "\ntime= " + time +
                "\ndescription= '" + description + '\'' +
                "\nvendor= '" + vendor + '\'' +
                "\namount= " + amount +
                "\n-----------------------------------------";
    }
}
