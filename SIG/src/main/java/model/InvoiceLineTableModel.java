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
public class InvoiceLineTableModel extends AbstractTableModel{

    private ArrayList<InvoiceLine> linesArray;
    
    private String[] columns = {"Item Name", "Price", "Count", "Line Total"};

    public InvoiceLineTableModel(ArrayList<InvoiceLine> linesArray) {
        this.linesArray = linesArray;
    }

    @Override
    public int getRowCount() {
        return linesArray == null ? 0 : linesArray.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceLine inv = linesArray.get(rowIndex);
        switch (columnIndex) {
            case 0 -> {
                return inv.getItem();
            }
            case 1 -> {
                return inv.getPrice();
            }
            case 2 -> {
                return inv.getCount();
            }
            case 3 -> {
                return inv.getLineTotal();
            }
        }
        return "";
        
    }
    
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    
    
}
