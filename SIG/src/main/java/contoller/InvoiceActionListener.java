/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contoller;

import java.util.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.InvoiceHeader;
import model.InvoiceLine;
import model.InvoiceHeaderTableModel;
import model.InvoiceLineTableModel;
import view.InvoiceFrame;
import view.headerDialog;
import view.lineDialog;

/**
 *
 * @author khalifa
 */
public class InvoiceActionListener implements ActionListener{
    
    private InvoiceFrame frame;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    
    private headerDialog hDialog;
    private lineDialog lDialog;

    public InvoiceActionListener(InvoiceFrame frame) {
        this.frame = frame;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand())
        {
            case "Load Files":
                loadFiles();
                break;
                
            case "Save Files":
                saveFiles();
                break;
                
            case "New Invoice":
                createNewInvoice();
                break;
                
            case "Delete Invoice":
                deleteInvoice();
                    break;
                    
            case "Save":
                createNewLine();
                break;
                
            case "Cancel":
                cancel();
                break;
            case "newInvoiceOK":
                newInvoiceOk();
                break;
            case"newInvoiceCancel":
                cancelInvoice();
                break;
            case "newLineOK":
                newLineOk();
                break;
            case"newLineCancel":
                newLineCancel();
                    break;
                    
                    
        }
    }

    private void loadFiles() {
        JFileChooser chooser = new JFileChooser();
        try {
        int result = chooser.showOpenDialog(frame);
        if(result == JFileChooser.APPROVE_OPTION){
            File headerFile = chooser.getSelectedFile();
            Path headerPath = Paths.get(headerFile.getAbsolutePath());
            List<String> headerLines = Files.readAllLines(headerPath);
            ArrayList<InvoiceHeader> invoiceHeader = new ArrayList<>();
            for(String headerLine: headerLines)
            {
                String [] substring = headerLine.split(",");
                String substring1 = substring[0];
                String substring2 = substring[1];
                String substring3 = substring[2];
                int num = Integer.parseInt(substring1);
                Date date = InvoiceFrame.dateFormat.parse(substring2);
                InvoiceHeader header = new InvoiceHeader(num, date, substring3);
                invoiceHeader.add(header);
            }
            frame.setInvoiceArray(invoiceHeader);
            result = chooser.showOpenDialog(frame);
            if(result == JFileChooser.APPROVE_OPTION)
            {
                File lineFile = chooser.getSelectedFile();
                Path linePath = Paths.get(lineFile.getAbsolutePath());
                List<String> lineLines = Files.readAllLines(linePath);
                ArrayList<InvoiceLine> invoiceline = new ArrayList<>();
                for(String lineLine : lineLines)
                {
                    String [] arr = lineLine.split(",");
                    String subArr1 = arr[0];
                    String subArr2 = arr[1];
                    String subArr3 = arr[2];
                    String subArr4 = arr[3];
                    
                    int num = Integer.parseInt(subArr1);
                    double price = Double.parseDouble(subArr3);
                    int count = Integer.parseInt(subArr4);
                    
                    InvoiceHeader inv = frame.getInvObject(num);
                    InvoiceLine line = new InvoiceLine(subArr2, price, count, inv);
                    inv.getLines().add(line);
                }
            }
            InvoiceHeaderTableModel ITM = new InvoiceHeaderTableModel(invoiceHeader);
            frame.setITM(ITM);
            frame.getjTable1().setModel(ITM);

        }
        }catch(IOException | ParseException e)
    {
        JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

 }
    private void saveFiles() {
        ArrayList<InvoiceHeader> invoicesArray = frame.getInvoiceArray();
        JFileChooser fc = new JFileChooser();
        try{
            int result = fc.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File hFile = fc.getSelectedFile();
                FileWriter hfw = new FileWriter(hFile);
                String headers = "";
                String lines = "";
                for (InvoiceHeader invoice : invoicesArray) {
                    headers += invoice.toString();
                    headers += "\n";
                    for (InvoiceLine line : invoice.getLines()) {
                        lines += line.toString();
                        lines += "\n";
                        }
                    }
                headers = headers.substring(0, headers.length()-1);
                lines = lines.substring(0, lines.length()-1);
                result = fc.showSaveDialog(frame);
                File lineFile = fc.getSelectedFile();
                FileWriter lfw = new FileWriter(lineFile);
                hfw.write(headers);
                lfw.write(lines);
                hfw.close();
                lfw.close();
            }
        }catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
}   
   


    private void createNewInvoice() {
        hDialog = new headerDialog(frame);
        hDialog.setVisible(true);
    }

    private void deleteInvoice() {
        int selectedInvoice = frame.getjTable1().getSelectedRow();
        if(selectedInvoice != -1)
        {
            frame.getInvoiceArray().remove(selectedInvoice);
            frame.getITM().fireTableDataChanged();
            frame.getjTable2().setModel(new InvoiceLineTableModel (null));
            frame.setLinesArray(null);
            
            frame.getNumLabel().setText("");
            frame.getDateLabel().setText("");
            frame.getCutomerLabel().setText("");
            frame.getTotalLabel().setText("");
        }
    }

    private void createNewLine() {
        lDialog = new lineDialog(frame);
        lDialog.setVisible(true);
    }

    private void cancel() {
        int selectedLine = frame.getjTable2().getSelectedRow();
        int selectedInvoice= frame.getjTable1().getSelectedRow();
        if(selectedLine != -1)
        {
            frame.getLinesArray().remove(selectedLine);
            InvoiceLineTableModel lineTableModel = (InvoiceLineTableModel) frame.getjTable2().getModel();
            lineTableModel.fireTableDataChanged();
            frame.getTotalLabel().setText(""+ frame.getInvoiceArray().get(selectedLine).getInvoiceTotal());
            frame.getITM().fireTableDataChanged();
            frame.getjTable1().setRowSelectionInterval(selectedInvoice, selectedInvoice);
            
        }
    }

    private void newInvoiceOk() {
         hDialog.setVisible(true);
         String customerName = hDialog.getCustNameField().getText();
         String date = hDialog.getInvDateField().getText();
         Date dt = new Date();
         try{
         dt = InvoiceFrame.dateFormat.parse(date);
         }catch(ParseException e)
         {
             JOptionPane.showMessageDialog(frame, "The date must be valid","Invalid Date Format", JOptionPane.ERROR_MESSAGE);
         }
         int billNumber = 0;
         for(InvoiceHeader inv : frame.getInvoiceArray())
         {
             if(inv.getNumber()> billNumber)
             {
                 billNumber = inv.getNumber();
             }
             billNumber ++;
         }
         
        InvoiceHeader invHeader = new InvoiceHeader(billNumber, dt, customerName);
        frame.getInvoiceArray().add(invHeader);
        frame.getITM().fireTableDataChanged();
        hDialog.dispose();
        hDialog = null;
    }

    private void cancelInvoice() {
        hDialog.setVisible(false);
        hDialog.dispose();
        hDialog = null;
    }

    private void newLineCancel() {
        lDialog.setVisible(false);
        lDialog.dispose();
        lDialog = null;
    }

    private void newLineOk() {
        lDialog.setVisible(false);
        
        String name = lDialog.getItemNameField().getText();
        String str1 = lDialog.getItemCountField().getText();
        String str2 = lDialog.getItemPriceField().getText();
        int count = 1;
        double price = 1;
        try {
            count = Integer.parseInt(str1);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Cannot convert number", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }

        try {
            price = Double.parseDouble(str2);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Cannot convert price", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }
        int selectedHeader = frame.getjTable1().getSelectedRow();
        if(selectedHeader != -1)
        {
            InvoiceHeader invHeader = frame.getInvoiceArray().get(selectedHeader);
            InvoiceLine invLine = new InvoiceLine(name, price, count, invHeader);
            frame.getLinesArray().add(invLine);
            InvoiceLineTableModel lineTableModel = (InvoiceLineTableModel) frame.getjTable2().getModel();
            lineTableModel.fireTableDataChanged();
            frame.getITM().fireTableDataChanged();
        }
        frame.getjTable1().setRowSelectionInterval(selectedHeader, selectedHeader);
        lDialog.dispose();
        lDialog = null;
    }
}
