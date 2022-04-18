/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sig.model;

/**
 *
 * @author karim taha
 */
public class InvoiceLine {

    private int itemNumber;
    private String itemName;
    private double priceItems;
    private int countItems;
    private InvoiceHeader invoiceHeader;

    public InvoiceLine() {}

    public InvoiceLine(int itemNumber, String itemName, double priceItems, int countItems, InvoiceHeader invoiceHeader) {
        this.itemNumber = itemNumber;
        this.itemName = itemName;
        this.priceItems = priceItems;
        this.countItems = countItems;
        this.invoiceHeader = invoiceHeader;
    }

    public int getItemNumber() {return itemNumber;}

    public void setItemNumber(int itemNumber) {this.itemNumber = itemNumber;}

    public InvoiceHeader getInvoiceHeader() {return invoiceHeader;}

    public void setInvoiceHeader(InvoiceHeader invoiceHeader) {this.invoiceHeader = invoiceHeader;}

    public String getItemName() {return itemName;}

    public void setItemName(String itemName) {this.itemName = itemName;}

    public double getPriceItems() {return priceItems;}

    public void setPriceItems(double priceItems) {this.priceItems = priceItems;}

    public int getCountItems() {return countItems;}

    public void setCountItems(int countItems) {this.countItems = countItems;}

    public double getLineTotal() {return priceItems * countItems;}

    public String getDataAsCSV() {return "" + getInvoiceHeader().getInvoiceNumber() + "," + getItemName() + "," + getPriceItems() + "," + getCountItems();}
}
