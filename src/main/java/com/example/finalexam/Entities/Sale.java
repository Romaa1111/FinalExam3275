package com.example.finalexam.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

/**
 * Represents a Sale entity in the system.
 * Maps to a database table where sales records are stored.
 */
@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for each sale record

    private String name; // Name of the salesman
    private String itemType; // Type of item sold (e.g., Washing Machine, Refrigerator, Music System)
    private double salesAmount; // Amount of the sale
    private String transactionCode; // Unique code for the transaction
    private LocalDate transactionDate; // Date when the transaction occurred

    /**
     * Gets the unique identifier for the sale.
     * @return the ID of the sale
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the sale.
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the salesman.
     * @return the name of the salesman
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the salesman.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the type of item sold.
     * @return the item type
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * Sets the type of item sold.
     * @param itemType the item type to set
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * Gets the amount of the sale.
     * @return the sales amount
     */
    public double getSalesAmount() {
        return salesAmount;
    }

    /**
     * Sets the amount of the sale.
     * @param salesAmount the sales amount to set
     */
    public void setSalesAmount(double salesAmount) {
        this.salesAmount = salesAmount;
    }

    /**
     * Gets the unique transaction code.
     * @return the transaction code
     */
    public String getTransactionCode() {
        return transactionCode;
    }

    /**
     * Sets the unique transaction code.
     * @param transactionCode the transaction code to set
     */
    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    /**
     * Gets the date of the transaction.
     * @return the transaction date
     */
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the date of the transaction.
     * @param transactionDate the transaction date to set
     */
    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
}
