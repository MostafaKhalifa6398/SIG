/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contoller;

import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.InvoiceHeader;
import model.InvoiceLine;
import model.InvoiceLineTableModel;
import view.InvoiceFrame;

/**
 *
 * @author khalifa
 */
public class InvoiceSelectionListener implements ListSelectionListener{

    private InvoiceFrame frame;

    public InvoiceSelectionListener(InvoiceFrame frame) {
        this.frame = frame;
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        
        int SelectInvIndex = frame.getjTable1().getSelectedRow();
        if (SelectInvIndex != -1){
        InvoiceHeader selectedInv = frame.getInvoiceArray().get(SelectInvIndex);
        ArrayList<InvoiceLine> selectedLine = selectedInv.getLines();
        InvoiceLineTableModel lineTableModel = new InvoiceLineTableModel(selectedLine);
        frame.setLinesArray(selectedLine);
        frame.getjTable2().setModel(lineTableModel);
        
        frame.getNumLabel().setText(""+selectedInv.getNumber());
        frame.getDateLabel().setText(InvoiceFrame.dateFormat.format(selectedInv.getDate()));
        frame.getCutomerLabel().setText(selectedInv.getCustomerName());
        frame.getTotalLabel().setText(""+selectedInv.getInvoiceTotal());
        
        }
        
    }
    
}