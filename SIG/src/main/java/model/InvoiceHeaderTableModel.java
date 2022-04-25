/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import view.InvoiceFrame;

/**
 *
 * @author khalifa
 */
public class InvoiceHeaderTableModel extends AbstractTableModel{
    
    private ArrayList<InvoiceHeader> invoiceArray;
    
    private String[] columns = {"Invoice Number", "Invoice Date", "Customer Name", "Invoice Total"};

    public InvoiceHeaderTableModel(ArrayList<InvoiceHeader> invoiceArray) {
        this.invoiceArray = invoiceArray;
    }
    
    

    @Override
    public int getRowCount() {
        return invoiceArray.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader inv = invoiceArray.get(rowIndex);
        switch (columnIndex) {
            case 0 -> {
                return inv.getNumber();
            }
            case 1 -> {
                return InvoiceFrame.dateFormat.format(inv.getDate()) ;
            }
            case 2 -> {
                return inv.getCustomerName();
            }
            case 3 -> {
                return inv.getInvoiceTotal();
            }
        }
        return "";
            
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    
}
