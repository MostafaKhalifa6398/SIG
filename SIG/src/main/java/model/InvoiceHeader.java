/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Date;
import view.InvoiceFrame;
import contoller.InvoiceActionListener;
import java.text.SimpleDateFormat;

/**
 *
 * @author khalifa
 */
public class InvoiceHeader {
    private int number;
    private Date date;
    private String customerName;
    private  SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private ArrayList<InvoiceLine> lines;

    public InvoiceHeader() {
    }

    public InvoiceHeader(int number, Date date, String customerName) {
        this.number = number;
        this.date = date;
        this.customerName = customerName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<InvoiceLine> getLines() {
        if(lines == null)
        {
            lines = new ArrayList<>();
        }
        return lines;
    }

    public void setLines(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }
    
    public double getInvoiceTotal() {
        double total = 0.0;

        for (int i = 0; i < getLines().size(); i++) {
            total += getLines().get(i).getLineTotal();
        }

        return total;
    }

    @Override
    public String toString() {
        return number + "," + dateFormat.format(date) + "," + customerName;
    }

    
    
    
    
}
